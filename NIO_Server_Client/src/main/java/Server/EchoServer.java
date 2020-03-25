package Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        EchoServer echoServer = new EchoServer();
        echoServer.serve();
    }

    public static final int PORT_NUMBER = 8080;
    private static final InetSocketAddress PORT = new InetSocketAddress(PORT_NUMBER);
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1096);
    Selector selector;
    private static final System.Logger LOG = System.getLogger(EchoServer.class.getName());
    private Dictionary<java.net.SocketAddress, String> allClientMessages = new Hashtable<>();

    void serve() throws IOException {
        initializeSelection();
        startListening();

        while (true){
            if(selector.select(1000) == 0){
//                no clients ready
//                LOG.log(System.Logger.Level.INFO, "waiting...");
            }else {
                handleReadyChannels();
            }
        }

    }

    private void handleReadyChannels() throws IOException {
        //we get selected keys associated with channels.
        Set<SelectionKey> keys = selector.selectedKeys();

        for (SelectionKey key : keys) {
            if (key.isAcceptable()) {
                acceptConnection(key);
            }

            else if(key.isReadable()){
                final SocketChannel socketChannel = (SocketChannel) key.channel();
                readClientsMessage(socketChannel);
            }

            else if(key.isWritable()){
                final SocketChannel socketChannel = (SocketChannel) key.channel();
                echoToClient(socketChannel);
            }
            keys.remove(key);
        }
    }

    private void echoToClient(SocketChannel socketChannel) throws IOException {
        LOG.log(System.Logger.Level.INFO, "Echoing to: " + socketChannel);
        SocketAddress clientsAddress = socketChannel.getRemoteAddress();
        String clientMessage = allClientMessages.get(clientsAddress);
        allClientMessages.remove(clientsAddress);

        byteBuffer.put(clientMessage.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

    }

    private void readClientsMessage(SocketChannel socketChannel) throws IOException {
        byteBuffer.clear();
        LOG.log(System.Logger.Level.INFO, "Reading from a client: " + socketChannel);
        socketChannel.read(byteBuffer);
        byteBuffer.rewind();
        allClientMessages.put(socketChannel.getRemoteAddress(), byteBuffer.toString());
        byteBuffer.clear();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }


    private void acceptConnection(SelectionKey key) throws IOException {
        LOG.log(System.Logger.Level.INFO, "Accepting connection ...");
        final ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        final SocketChannel clientSocket = serverSocket.accept();
        clientSocket.configureBlocking(false);
        clientSocket.register(selector, SelectionKey.OP_READ);
    }

    private void startListening() throws IOException {
        final ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(PORT);
        //if blocking true can't register to selector
        channel.configureBlocking(false);
        //when client wants to connect, we will be notified and accept the connection
        channel.register(selector, SelectionKey.OP_ACCEPT);

    }

    private void initializeSelection() throws IOException {
        selector = Selector.open();
    }
}

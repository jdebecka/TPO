package Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.serve();
    }

    public static final int PORT_NUMBER = 8080;
    private static final InetSocketAddress PORT = new InetSocketAddress(PORT_NUMBER);
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    Selector selector;
    private static final System.Logger LOG = System.getLogger(Server.class.getName());
    private Dictionary<java.net.SocketAddress, Integer> allClientMessages = new Hashtable<>();

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
                try{
                    readClientsMessage(socketChannel);
                }catch (IOException e) {
                    break;
                }
            }

            else if(key.isWritable()){
                final SocketChannel socketChannel = (SocketChannel) key.channel();
                try{
                    respondToClient(socketChannel);
                }catch (IOException e){
                    LOG.log(System.Logger.Level.WARNING, "Connection with: " + socketChannel.getRemoteAddress() + " closed unexpectedly");
                    socketChannel.close();
                    break;
                }
            }
            keys.remove(key);
        }
    }

    private void respondToClient(SocketChannel socketChannel) throws IOException {

        SocketAddress clientsAddress = socketChannel.getRemoteAddress();
        int response = allClientMessages.get(clientsAddress);
        allClientMessages.remove(clientsAddress);
        byteBuffer.clear();
        String string = Integer.toString(response);

        LOG.log(System.Logger.Level.INFO, "Responding to: " + socketChannel + " with: " + string);

        byte[] inputBytes = string.getBytes();
        byteBuffer = ByteBuffer.wrap(inputBytes);
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

    }

    private void readClientsMessage(SocketChannel socketChannel) throws IOException {
        byteBuffer = ByteBuffer.allocate(1024);
        LOG.log(System.Logger.Level.INFO, "Reading from a client: " + socketChannel);

        socketChannel.read(byteBuffer);
        byteBuffer.rewind();
        byte[] byteArray = byteBuffer.array();
        String message = new String(byteArray).trim();

        messageDecoder(message, socketChannel);
        byteBuffer.clear();
    }

    private void messageDecoder(String clientMessage, SocketChannel socketChannel) throws IOException {
        if(clientMessage.contains("add")){
            allClientMessages.put(socketChannel.getRemoteAddress(), addIntegers(clientMessage));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        }else if (clientMessage.contains("close")) {
            LOG.log(System.Logger.Level.WARNING, "Client: " + socketChannel.getRemoteAddress() + " closed the connection");
            socketChannel.close();
            byteBuffer.clear();
        }else {
            LOG.log(System.Logger.Level.INFO, "from: " + socketChannel.getRemoteAddress() + " Message: " + clientMessage);
        }
    }

    private int addIntegers(String message){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(message);
        List<Integer> integerList = new ArrayList<>();

        while (m.find()) {
            integerList.add(Integer.parseInt(m.group()));
        }
        return integerList.stream().mapToInt(Integer::intValue).sum();
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

        LOG.log(System.Logger.Level.INFO, "Started listening on port: " + PORT_NUMBER);

    }

    private void initializeSelection() throws IOException {
        selector = Selector.open();
    }
}

package Client;
import Server.Server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private static final System.Logger LOG = System.getLogger(Client.class.getName());
    private static Scanner scanner =  new Scanner(System.in);

    public void establishConnection(){
        final InetSocketAddress serverAddress = new InetSocketAddress("localhost", Server.PORT_NUMBER);
        try(SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(serverAddress);
            writeToServer(socketChannel);
            socketChannel.finishConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String getClientMessage(){
        System.out.println("What's your message (if you want to close the connection type: 'close')");
        return scanner.nextLine();
    }

    private void writeToServer(SocketChannel socketChannel) throws IOException {
        String clientsMessage = " ";
        ByteBuffer byteBuffer;
        while(!clientsMessage.equalsIgnoreCase("close")){
            clientsMessage = getClientMessage();

            byte[] inputBytes = clientsMessage.getBytes();
            byteBuffer = ByteBuffer.wrap(inputBytes);
            socketChannel.write(byteBuffer);

            LOG.log(System.Logger.Level.INFO, "Writing message: " + clientsMessage);

            if(clientsMessage.contains("add")){
                waitForResponse(socketChannel);
            }

            byteBuffer.clear();
        }
    }

    private void waitForResponse(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        byteBuffer.rewind();
        byte[] byteArray = byteBuffer.array();

        String response = new String(byteArray).trim();
        byteBuffer.clear();

        LOG.log(System.Logger.Level.INFO, "Server responded: " + response);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.establishConnection();
    }
}

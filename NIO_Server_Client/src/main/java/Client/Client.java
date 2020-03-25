package Client;
import Server.EchoServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private static final System.Logger LOG = System.getLogger(Client.class.getName());
    private static Scanner scanner =  new Scanner(System.in);

    public void establishConnection(){
        final InetSocketAddress serverAddress = new InetSocketAddress("localhost", EchoServer.PORT_NUMBER);
        try(SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(serverAddress);
            writeToServer(socketChannel);
            socketChannel.finishConnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    private String getClientMessage(){
        System.out.println("What's your message (if you want to close the connection type: 'close')");
        return scanner.nextLine();
    }

    private void writeToServer(SocketChannel socketChannel) throws IOException, InterruptedException {
        String clientsMessage = getClientMessage();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        do{
            byteBuffer.put(clientsMessage.getBytes());
            byteBuffer.rewind();
            socketChannel.write(byteBuffer);
            LOG.log(System.Logger.Level.INFO, "Writing message: " + clientsMessage);
            waitForResponse(socketChannel);
            clientsMessage = getClientMessage();
            byteBuffer.clear();

        }while(!clientsMessage.equalsIgnoreCase("close"));
    }

    private void waitForResponse(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder message = new StringBuilder();

        socketChannel.read(byteBuffer);
        byteBuffer.rewind();
        while (byteBuffer.hasRemaining()){
                message.append(byteBuffer.get());
        }
        byteBuffer.clear();

        String echo = message.toString();
        LOG.log(System.Logger.Level.INFO, "Server responded: " + echo);
        byteBuffer.clear();
    }

    public static void main(String[] args) {

        Client client = new Client();
        client.establishConnection();
    }
}

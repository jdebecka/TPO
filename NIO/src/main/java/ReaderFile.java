import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReaderFile {
    private static final int BSIZE = 12;
    private ByteBuffer intBuf;
    private RandomAccessFile file;
    private FileChannel readChannel;
    private String filePath;
    private static final int READ_MODE = 1;
    private List<Integer> integerList = new ArrayList<>();

    public ReaderFile(String filePath) {
        this.filePath = filePath;
    }

    Runnable readFile = () -> {
        while (true) {
            try{
                reloadFile();

                int bytesRead = readChannel.read(intBuf);
                intBuf.rewind();
                int mark =  intBuf.getInt();
                bytesRead -= 4;
                if(mark == READ_MODE){
                    while (bytesRead != -1) {
                        while (intBuf.hasRemaining()){
                            integerList.add(intBuf.getInt());
                        }
                        intBuf.clear();
                        bytesRead = readChannel.read(intBuf);
                    }
                    closeResources();
                    printSum();
                    writeMode();
                }else {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("waiting...");
                }
                intBuf.clear();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private void closeResources(){
        try {
            readChannel.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSum(){
        Integer sum = integerList.stream().mapToInt(Integer::intValue).sum();
        System.out.print("Sum of: ");
        for(int i : integerList){
            System.out.print(i + ", ");
        }
        integerList.clear();
        System.out.println(" = " + sum);
    }

    private void writeMode() throws IOException{
        reloadFile();
        intBuf = ByteBuffer.allocate(4);
        intBuf.putInt(2);
        intBuf.rewind();
        readChannel.write(intBuf);
        readChannel.force(true);
        closeResources();
    }

    private void reloadFile(){
        try {
            file = new RandomAccessFile(filePath, "rw");
            readChannel = file.getChannel();
            intBuf = ByteBuffer.allocate(BSIZE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/juliadebecka/Desktop/4th semester/TPO/NIO/src/main/resources/sample.txt";
        ReaderFile readerFile = new ReaderFile(filePath);
        readerFile.readFile.run();

    }
}

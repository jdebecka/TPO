import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WriterFile {
    private String filePath;
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;
    private ByteBuffer mark_buffer;
    private static final int INTS_SIZE = 12;
    private static final int INT_SIZE = 4;
    private static final int WRITE_MODE = 2;
    private static final int READY_TO_READ = 1;
    private boolean STOP = false;
    public WriterFile(String filepath){
        this.filePath = filepath;
    }

    Runnable writeFile = () -> {
        while (true){
            try {
                randomAccessFile = new RandomAccessFile(filePath, "rw");
                fileChannel = randomAccessFile.getChannel();
                mark_buffer = ByteBuffer.allocate(INT_SIZE);
                fileChannel.read(mark_buffer);
                mark_buffer.rewind();

                STOP = mark_buffer.getInt() != WRITE_MODE;

                if(!STOP){
                    ByteBuffer byteBuff = ByteBuffer.allocate(INTS_SIZE);
                    fileChannel.truncate(0);
                    byteBuff.clear();
                    byteBuff.putInt(READY_TO_READ);
                    byteBuff.putInt(generateRandomInt());
                    byteBuff.putInt(generateRandomInt());
                    byteBuff.flip();

                    while (byteBuff.hasRemaining()){
                        fileChannel.write(byteBuff);
                    }
                    fileChannel.force(true);
                    STOP = true;
                    closeResourses();
                }else {
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private int generateRandomInt(){
        Random random = new Random();
        return random.nextInt();
    }

    private void closeResourses(){
        try {
            randomAccessFile.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/juliadebecka/Desktop/4th semester/TPO/NIO/src/main/resources/sample.txt";
        WriterFile writerFile = new WriterFile(filePath);
        writerFile.writeFile.run();
    }
}

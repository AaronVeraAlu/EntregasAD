import java.util.*;	
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;


class ej2{


    public static void main(String[] args) throws IOException{
        
        RandomAccessFile fileCopy = new RandomAccessFile("atropodos.dat", "rw");
        RandomAccessFile filePaste = new RandomAccessFile("atropo2.dat", "rw");
        
        Set<StandardOpenOption> options = new HashSet<>();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.APPEND);
        Path path = Paths.get("artopo2.dat");

        FileChannel fileChannelCopy = fileCopy.getChannel();
        FileChannel fileChannelPaste = FileChannel.open(path, options);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4000);

        while(fileChannelCopy.read(byteBuffer) > 0 ){
            byteBuffer.flip();
            while(byteBuffer.hasRemaining())
                byteBuffer.get();
        }
        byteBuffer.flip();
      
        fileChannelPaste.write(byteBuffer);
        fileChannelCopy.close();
        fileChannelPaste.close();
        fileCopy.close();
        filePaste.close();
    }
}

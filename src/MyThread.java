import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class MyThread extends Thread {
    BlockingQueue<String> in;
    BlockingQueue<String> out;
    String path;
    public MyThread(BlockingQueue<String> in, BlockingQueue<String> out, String path){
        this.in = in;
        this.out = out;
        this.path = path;
    }



    @Override
    public void run(){
        try(FileReader fr = new FileReader(path);){

            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null){
                in.take();
                System.out.println(line);
                Thread.sleep(1000);
                out.put("_______");

            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

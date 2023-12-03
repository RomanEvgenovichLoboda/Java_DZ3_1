import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;

public class MyThread extends Thread {
    BlockingQueue<String> in;
    BlockingQueue<String> out;
    static String country;
    static String capital;
    static ArrayList<CountryInfo> countryInfos;
    String path;

    public MyThread(BlockingQueue<String> in, BlockingQueue<String> out, String path) {
        this.in = in;
        this.out = out;
        this.path = path;
        if (countryInfos == null) countryInfos = new ArrayList<>();
    }


    @Override
    public void run() {
        try (FileReader fr = new FileReader(path)) {

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                in.take();
                if (path.equals("countries.txt")) {
                    System.out.print(line + " --> ");
                    country = line;
                } else {
                    System.out.print(line + "\n");
                    capital = line;
                    countryInfos.add(new CountryInfo(country, capital));
                }
                Thread.sleep(500);
                out.put("you turn");
            }
            if (path.equals("capitals.txt")) {
                System.out.println("\tSorting by country:");
                countryInfos.sort(Comparator.comparing(CountryInfo::getCountry));
                countryInfos.forEach(System.out::println);
                System.out.println("\tSorting by capital:");
                countryInfos.sort(Comparator.comparing(CountryInfo::getCapital));
                countryInfos.forEach(System.out::println);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class Test {

    public Test() throws IOException {
        String doc =  Jsoup.connect("https://aboullaite.me").get().html();
        System.out.println(doc);
    }

    public static void main(String[] args) throws IOException {
//        new Test();
        File file = new File("C:\\Users\\behesht\\Downloads\\Music\\Siavash Ghomayshi - Tardid.mp3");
        System.out.println(file.getName());
    }
}

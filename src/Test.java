import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Test {

    public Test() throws IOException {
        String doc =  Jsoup.connect("https://aboullaite.me").get().html();
        System.out.println(doc);
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}

import org.jsoup.Jsoup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException {
//        SearchLyrics searchLyrics = new SearchLyrics();
//        LyricsServiceBean bean = new LyricsServiceBean();
//        bean.setSongName("Atash");
//        bean.setSongAlbum("Reza Bahram");
//
//        List<Lyrics> lyrics;
//        try {
//            lyrics = searchLyrics.searchLyrics(bean);
//            for (Lyrics lyric : lyrics) {
//                System.out.println("Text :" + lyric.getText());
//            }
//        } catch (SearchLyricsException e) {
//            e.printStackTrace();
//        }
        SearchLyrics searchLyrics = new SearchLyrics();
        SearchLyricsBean bean = new SearchLyricsBean();
        bean.setSongName("lucky one");
        bean.setTopMaxResult(1);
        bean.setSongArtist("Taylor swift");
        bean.setSites(Sites.SONGMEANINGS);

        List<Lyrics> lyrics;
        try {
            lyrics = searchLyrics.searchLyrics(bean);
            for (Lyrics lyric : lyrics) {
                System.out.println("Link :" + lyric.getLink());
                System.out.println("Site :" + lyric.getSites().getName());
                System.out.println("Text :" + lyric.getText());
            }
        } catch (SearchLyricsException e) {
            e.printStackTrace();
        }
    }
}

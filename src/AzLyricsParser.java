import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * This parser is used to parse all lyrics of www.azlyrics.com {@link Sites}
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public class AzLyricsParser implements LyricsParser {

    @Override
    public List<Lyrics> getLyrics(List<URL> urls) throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();

        if (urls != null) {

            for (URL url : urls) {
                // System.out.println("In Az Parser :" + url.toString());
                Lyrics lyric = parse(url.toString());
                if (lyric != null) {
                    lyrics.add(lyric);
                }
            }

        }

        return lyrics;
    }

    /**
     * Parse all lyrics for url.
     *
     * @param url
     * @return
     */
    private Lyrics parse(String url) throws SearchLyricsException {
        Lyrics lyrics = null;

        try {
            Document document = Jsoup.connect(url).get();
            String data = document.getElementsByAttributeValueContaining(
                    "style", "margin-left:10px;").toString();
            if (data.length() > 0) {
                data = HtmlUtil.replaceBrWithNewLine(data);
                data = HtmlUtil.removeHTMLTags(data);
                if (data.length() > 0) {
                    lyrics = new Lyrics();
                    lyrics.setLink(url);
                    lyrics.setText(data);
                    lyrics.setSites(Sites.AZLYRICS);
                }
            }

        } catch (IOException e) {
            throw new SearchLyricsException(e);
        }
        return lyrics;
    }
}
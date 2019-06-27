import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This parser is used to parse all lyrics of www.metrolyrics.com {@link Sites}
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public class MetroLyricsParser implements LyricsParser {

    @Override
    public List<Lyrics> getLyrics(List<URL> urls) throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();

        if (urls != null) {

            for (URL url : urls) {
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
            String data = "";

            Elements elements = document.getElementsByAttributeValueContaining(
                    "class", "lyrics-body");

            if (elements != null && elements.size() > 0) {

                for (Element element : elements) {

                    Elements pTags = element.getElementsByTag("p");

                    if (pTags != null && pTags.size() > 0) {

                        for (Element pTag : pTags) {

                            String dataLine = HtmlUtil
                                    .replaceBrWithNewLine(pTag.html());
                            dataLine = HtmlUtil.removeHTMLTags(dataLine) + "\n";
                            data += dataLine;

                        }
                    }

                }

            }

            if (data.length() > 0) {
                lyrics = new Lyrics();
                lyrics.setLink(url);
                lyrics.setText(data);
                lyrics.setSites(Sites.METROLYRICS);
            }

        } catch (IOException e) {
            throw new SearchLyricsException(e);
        }
        return lyrics;
    }

}
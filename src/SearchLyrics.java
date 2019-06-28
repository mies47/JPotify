import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This is main class. It is use to search your lyrics using sites or services.
 * Or You can make your custom services or parser to search lyrics.
 *
 * <br>
 * <b>Example</b><br>
 * <br>
 *
 * Search lyrics using sites. <br>
 * ------------------------------------------------ <br>
 * <br>
 * SearchLyrics searchLyrics = new SearchLyrics();<br>
 * SearchLyricsBean bean = new SearchLyricsBean();<br>
 * bean.setSongName("lucky one"); <br>
 * bean.setTopMaxResult(1);<br>
 * bean.setSongArtist("Taylor swift");<br>
 * bean.setSites(Sites.SONGMEANINGS);<br>
 * <br>
 * List<Lyrics> lyrics = searchLyrics.searchLyrics(bean);<br>
 * <br>
 * for (Lyrics lyric : lyrics) {<br>
 * System.out.println("Link :" + lyric.getLink());<br>
 * System.out.println("Site :" + lyric.getSites().getName());<br>
 * System.out.println("Text :" + lyric.getText());<br>
 * }<br>
 * <br>
 * ------------------------------------------------<br>
 * Search lyrics using Service. <br>
 * ------------------------------------------------ <br>
 * <br>
 * SearchLyrics searchLyrics = new SearchLyrics();<br>
 * <br>
 * LyricsServiceBean bean = new LyricsServiceBean();<br>
 * bean.setSongName("let her go");<br>
 * bean.setSongAlbum("passenger");<br>
 *
 * List<Lyrics> lyrics = searchLyrics.searchLyrics(bean);<br>
 *
 * for (Lyrics lyric : lyrics) { <br>
 * System.out.println("Text :" + lyric.getText());<br>
 * }<br>
 * <br>
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public class SearchLyrics {
    /**
     * This method will use sites See {@link Sites} to search your lyrics.Use
     * SearchLyricsBean bean to search your lyrics. <br>
     *
     *
     * @param bean
     * @return
     */
    public List<Lyrics> searchLyrics(SearchLyricsBean bean)
            throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();
        lyrics = searchLyrics(bean, getSearchEngine(bean.getEngines()), getLyricsParser(bean.getSites()));
        return lyrics;
    }

    /**
     * This method will use sites See {@link Sites} to search your lyrics. Use
     * SearchLyricsBean , SearchEngine and LyricsParser to search your lyrics.
     *
     * @param bean
     * @param searchEngine
     * @param lyricsParser
     * @return
     */
    public List<Lyrics> searchLyrics(SearchLyricsBean bean, SearchEngine searchEngine, LyricsParser lyricsParser)
            throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();

        try {

            List<URL> urls = FilterURL.filter(bean.getSites(),
                    searchEngine.search(searchEngine.getSearchString(bean)),
                    bean.getTopMaxResult());
            System.out.println(urls);
            lyrics = lyricsParser.getLyrics(urls);
            System.out.println(lyrics);
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }

        return lyrics;
    }

    /**
     * This method will search your lyrics using service. use LyricsServiceBean
     * to search your lyrics.
     *
     * @param bean
     * @return
     */
    public List<Lyrics> searchLyrics(LyricsServiceBean bean)
            throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();

        lyrics = searchLyrics(bean, getLyricsService(bean.getServices()));

        return lyrics;
    }

    /**
     * This method will search your lyrics using service. use LyricsServiceBean
     * and LyricsService to search your lyrics.
     *
     * @param bean
     * @param service
     * @return
     */
    public List<Lyrics> searchLyrics(LyricsServiceBean bean,
                                     LyricsService service) throws SearchLyricsException {
        List<Lyrics> lyrics = new ArrayList<Lyrics>();

        lyrics = service.getLyrics(bean);

        return lyrics;
    }

    /**
     * This method will return implementation of LyricsParser base on
     * {@link Sites}
     *
     * @param sites
     * @return
     */
    public LyricsParser getLyricsParser(Sites sites) {
        LyricsParser lyricsParser = null;

        if (sites == Sites.LYRICS) {
            lyricsParser = new LyricsSiteParser();
        } else if (sites == Sites.AZLYRICS) {
            lyricsParser = new AzLyricsParser();
        } else if (sites == Sites.SONGMEANINGS) {
            lyricsParser = new SongMeaningsParser();
        } else if (sites == Sites.METROLYRICS) {
            lyricsParser = new MetroLyricsParser();
        }

        return lyricsParser;
    }

    /**
     * This method will return implementation of SearchEngine base on
     * {@link Engines}
     *
     * @param engines
     * @return
     */
    public SearchEngine getSearchEngine(Engines engines) {
        SearchEngine searchEngine = null;
        if (Engines.GOOGLE == engines) {
            searchEngine = new GoogleSearch();
        }
        // else if (Engines.YAHOO == engines) {
        // // lyrics = searchByYahoo(bean);
        // } else if (Engines.BING == engines) {
        // // lyrics = searchByBing(bean);
        // }
        return searchEngine;
    }

    /**
     * This method will return implementation of LyricsService base on
     * {@link Services}
     *
     * @param services
     * @return
     */
    public LyricsService getLyricsService(Services services) {
        LyricsService lyricsService = null;
        if (services == Services.LYRICSWIKIA) {
            lyricsService = new LyricsWikiService();
        }
        return lyricsService;
    }

}
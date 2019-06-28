import java.net.URL;
import java.util.List;

/**
 * Main interface to create your own LyricsParser.<br>
 * To create your own LyricsParser.<br>
 * Implement this interface and it's method <b>getLyrics</b> and Use
 * SearchLyrics class and pass instance of your parser.<br/>
 *
 * <b>Example</b> <br/>
 * SearchLyrics searchLyrics = new SearchLyrics();<br/>
 * <br/>
 * searchLyrics.searchLyrics(searchLyricsBeanObj, new GoogleSearch(), new
 * MyOwnLyricsParser());
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public interface LyricsParser {

    /**
     * This method provide List of all Lyrics.
     *
     * @param list
     * @return
     */
    public List<Lyrics> getLyrics(List<URL> list) throws SearchLyricsException;

}
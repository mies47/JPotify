import java.util.List;

/**
 * This interface is require to implement your own service
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 11 Sep 2013
 * @version 1.0
 *
 */
public interface LyricsService {

    /**
     * Return list of all found lyrics.
     *
     * @param bean
     * @return
     */
    public List<Lyrics> getLyrics(LyricsServiceBean bean)
            throws SearchLyricsException;

}
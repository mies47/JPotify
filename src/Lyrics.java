

/**
 * This is main Lyrics bean. This bean return by all parsers and services as a
 * search result.
 *
 *
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public class Lyrics {

    private String text = "";
    private Sites sites = null;
    private String link = "";

    /**
     * It contains lyrics of song.
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Set song lyrics.
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get Lyrics site link that contains current lyrics.
     *
     * @return
     */
    public String getLink() {
        return link;
    }

    /**
     * Set site link that contains current lyrics.
     *
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get site that contains current lyrics
     *
     * @return
     */
    public Sites getSites() {
        return sites;
    }

    /**
     * Set site that contains current lyrics
     *
     * @param sites
     */
    public void setSites(Sites sites) {
        this.sites = sites;
    }

}
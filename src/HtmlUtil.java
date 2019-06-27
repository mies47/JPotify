public class HtmlUtil {

    /**
     * Replace all BR tags with new line.
     *
     * @param html
     * @return
     */
    public static String replaceBrWithNewLine(String html) {
        return html.replaceAll("(?i)<br[^>]*>", "\n");
    }

    /**
     * Remove all html tags from your String.
     *
     * @param html
     * @return
     */
    public static String removeHTMLTags(String html) {
        return html.replaceAll("\\<.*?>", "");
    }

    /**
     * Remove "& quot" tag from your String
     *
     * @param html
     * @return
     */
    public static String removeQUOTTags(String html) {
        return html.replaceAll("&quot;", "");
    }

    /**
     * Replace all blank space with %20
     *
     * @param url
     * @return
     */
    public static String parseURL(String url) {
        return url.replaceAll("\\s", "%20");
    }

}
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This is used your {@link Sites} information to filter search links.
 *
 * @author omt
 * @author Dhiral Pandya (dhiralpandya@gmail.com)
 * @since 10 Sep 2013
 * @version 1.0
 *
 */
public class FilterURL {

    /**
     *
     */
    public static List<URL> filter(Sites sites, List<String> urls, int topMax)
            throws MalformedURLException {
        List<URL> filterURLs = new ArrayList<URL>();

        if (sites != null) {

            for (String urlString : urls) {
                if (urlString.toLowerCase().indexOf(sites.getName()) > 0) {
                    URL url = new URL(urlString);
                    filterURLs.add(url);
                    if (filterURLs.size() >= topMax) {
                        break;
                    }
                }
            }

        } else {
            for (String urlString : urls) {

                URL url = new URL(urlString);
                filterURLs.add(url);
                if (filterURLs.size() >= topMax) {
                    break;
                }

            }
        }

        return filterURLs;
    }

}
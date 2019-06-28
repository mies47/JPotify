import java.util.List;
public interface SearchEngine {

    /**
     * Search your query
     *
     * @param searchString
     * @return
     */
    List<String> search(String searchString)
            throws SearchLyricsException;

    /**
     * Get Search string (Means your query ) from bean.
     *
     * @param bean
     * @return
     */
    String getSearchString(SearchLyricsBean bean);
}
import java.util.List;
public class Main {
    public static void main(String[] args){
        SearchLyrics searchLyrics = new SearchLyrics();
//		SearchLyricsBean bean = new SearchLyricsBean();
//		bean.setSongName("lucky one");
//		// bean.setSongAlbum("passenger");
//		bean.setTopMaxResult(1);
//		bean.setSongArtist("Taylor swift");
//		bean.setSites(Sites.SONGMEANINGS);
//
//		List<Lyrics> lyrics;
//		try {
//			lyrics = searchLyrics.searchLyrics(bean);
//			for (Lyrics lyric : lyrics) {
//				System.out.println("Link :" + lyric.getLink());
//				System.out.println("Site :" + lyric.getSites().getName());
//				System.out.println("Text :" + lyric.getText());
//			}
//		} catch (SearchLyricsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        // Search By Service

       // SearchLyrics searchLyrics = new SearchLyrics();
        SearchLyricsBean bean = new SearchLyricsBean();
        bean.setSongName("All the Young Dudes");
        bean.setTopMaxResult(1);
        bean.setSongArtist("Queen");
        bean.setSites(Sites.SONGMEANINGS);
        System.out.println("aaa");
        List<Lyrics> lyrics;
        try {

            lyrics = searchLyrics.searchLyrics(bean);
            System.out.println(lyrics);
            int i=0;
            for (Lyrics lyric : lyrics) {
                System.out.println(i);
                i++;
                System.out.println("Text :" + lyric.getText());
                System.out.println("Link :" + lyric.getLink());
                System.out.println("Site :" + lyric.getSites().getName());
                System.out.println("Text :" + lyric.getText());
            }
        } catch (SearchLyricsException e) {
            e.printStackTrace();
        }
    }
}

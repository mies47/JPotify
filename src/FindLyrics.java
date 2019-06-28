import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import java.io.IOException;
public class FindLyrics {
    public FindLyrics() throws IOException, MusixMatchException {
        String apiKey = "5c816ea1678c68472364e0d23f9e4302";
        MusixMatch musixMatch = new MusixMatch(apiKey);
        String trackName = "rap god";
        String artistName = "eminem";

// Track Search [ Fuzzy ]
        Track track = musixMatch.getMatchingTrack(trackName, artistName);
        TrackData data = track.getTrack();

        System.out.println("AlbumID : "    + data.getAlbumId());
        System.out.println("Album Name : " + data.getAlbumName());
        System.out.println("Artist ID : "  + data.getArtistId());
        System.out.println("Album Name : " + data.getArtistName());
        System.out.println("Track ID : "   + data.getTrackId());
        int trackID = data.getTrackId();

        Lyrics lyrics = musixMatch.getLyrics(trackID);

        System.out.println("Lyrics ID       : "     + lyrics.getLyricsId());
        System.out.println("Lyrics Language : "     + lyrics.getLyricsLang());
        System.out.println("Lyrics Body     : "     + lyrics.getLyricsBody());
        System.out.println("Script-Tracking-URL : " + lyrics.getScriptTrackingURL());
        System.out.println("Pixel-Tracking-URL : "  + lyrics.getPixelTrackingURL());
        System.out.println("Lyrics Copyright : "    + lyrics.getLyricsCopyright());
    }

    public static void main(String[] args) throws IOException, MusixMatchException {
        new FindLyrics();
    }
}

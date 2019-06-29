import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class FindLyrics extends JFrame {
    public FindLyrics(String title,String artist) throws IOException, MusixMatchException {
        String apiKey = "5c816ea1678c68472364e0d23f9e4302";
        MusixMatch musixMatch = new MusixMatch(apiKey);
        String trackName = title;
        String artistName = artist;

// Track Search [ Fuzzy ]
        Track track = musixMatch.getMatchingTrack(trackName, artistName);
        TrackData data = track.getTrack();

        //System.out.println("AlbumID : "    + data.getAlbumId());
        //System.out.println("Album Name : " + data.getAlbumName());
        //System.out.println("Artist ID : "  + data.getArtistId());
        //System.out.println("Album Name : " + data.getArtistName());
        //System.out.println("Track ID : "   + data.getTrackId());
        int trackID = data.getTrackId();

        Lyrics lyrics = musixMatch.getLyrics(trackID);
        JLabel label=new JLabel();
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setText("<html>"+lyrics.getLyricsBody().replaceAll("\n","<br/>")+"</html>");
        //System.out.println("Lyrics ID       : "     + lyrics.getLyricsId());
        //System.out.println("Lyrics Language : "     + lyrics.getLyricsLang());
        //System.out.println("Lyrics Body     : "     + lyrics.getLyricsBody());
        //System.out.println("Script-Tracking-URL : " + lyrics.getScriptTrackingURL());
        //System.out.println("Pixel-Tracking-URL : "  + lyrics.getPixelTrackingURL());
        //System.out.println("Lyrics Copyright : "    + lyrics.getLyricsCopyright());
        this.setLayout(new BorderLayout());

        this.add(label,BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, MusixMatchException {
        //new FindLyrics();
    }
}

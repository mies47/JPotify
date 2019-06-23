import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PlayAddedSong {
    void playAddedSong(boolean newSong) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException;
}

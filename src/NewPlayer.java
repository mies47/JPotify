import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for getting a mp3 file and seeking to the given second
 * this is done by making another file and splitting the main file
 * in the parent directory
 * for better functionality you must delete the temp file after you
 * are done using it.
 * This class uses mp3agic to get mp3file size in seconds
 *
 * @author milad
 */
public class NewPlayer {
    private File newFile;

    /**
     * @param file   the main file given by user
     * @param second the second you want to seek to
     * @throws InvalidDataException
     * @throws IOException
     * @throws UnsupportedTagException
     */
    public NewPlayer(File file, int second) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File(file);
        int firstByte = (int) (file.length() * second / (mp3File.getLengthInMilliseconds() / 1000));
        byte[] buffer = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        newFile = new File(file.getParent(), "split.mp3");
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            fileOutputStream.write(buffer, firstByte, buffer.length - firstByte - 1);
        }
    }

    /**
     * @return the temp splitted file
     */
    public File getNewFile() {
        return newFile;
    }
}

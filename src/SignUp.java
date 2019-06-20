import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SignUp {
    public SignUp() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("member.txt", "UTF-8");
        writer.println("amir");
        writer.println("askari");
        writer.close();
    }
}

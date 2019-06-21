import java.io.*;
import java.util.Scanner;

public class SignUp {
    public SignUp(String name,char[] pass) throws IOException {
        BufferedWriter out = new BufferedWriter(
                new FileWriter("member.txt", true));
        out.write(name+"\n");
        out.close();
        PrintWriter writer1=new PrintWriter(name,"UTF-8");
        String a=String.valueOf(pass);
        writer1.println(a.hashCode());
        writer1.close();
    }
}

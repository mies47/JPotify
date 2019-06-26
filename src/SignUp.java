import java.io.*;

/**
 * @author naha
 * Save user and Hash of pass in file
 */
public class SignUp {
    public SignUp(String name,char[] pass) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("member.txt", true));//open append mode
        BufferedWriter song = new BufferedWriter(new FileWriter(name+"songs", true));//open append mode
        BufferedWriter fav = new BufferedWriter(new FileWriter(name+"favorite", true));//open append mode
        BufferedWriter Rec = new BufferedWriter(new FileWriter(name+"Recentsongs", true));//open append mode
        if(System.getProperty("os.name").contains("Windows")) {
            out.write(name + "\r\n");
        }else {
            out.write(name + "\n");
        }
        out.close();
        PrintWriter writer1=new PrintWriter(name,"UTF-8");
        String a=String.valueOf(pass);
        writer1.print(a.hashCode());
        writer1.close();
    }
}

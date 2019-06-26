import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.BaseStream;

public class ClientHandler implements Runnable {
    Socket socket;
    ClientObj clientObj;
    public ClientHandler (Socket socket1){
        socket = socket1;
    }
    @Override
    public void run() {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            clientObj = (ClientObj) objectInputStream.readObject();
            File member = new File("member.txt");
            try(PrintStream memberWriter = new PrintStream(new FileOutputStream(member,true) , true)) {
                byte[] memberInByte = clientObj.getMember();
                for (byte b : memberInByte) {
                    memberWriter.print((char) b);
                }
            }
            for (User user :clientObj.getUserSongs().keySet()) {
                try(PrintStream outputStream = new PrintStream(new FileOutputStream(new File(user.name) , true) , true)){
                    outputStream.println(user.password);
                    File f = new File(user.name + "Pic");
                    FileUtils.writeByteArrayToFile(f , user.image);
                    outputStream.println(f.getName());
                    FileUtils.writeByteArrayToFile(new File(user.name + "Recentsongs") , clientObj.getUserRecents().get(user));
                    FileUtils.writeByteArrayToFile(new File(user.name + "favorites") , clientObj.getUserFavorites().get(user));
                }
                File userSong = new File(user.name + "songs");
                File userRecent = new File("new " +user.name + "RecentSongs");
                File userFavorite = new File("new" + user.name + "Favorites");
                File mainUserRecent = new File(user.name + "Recentsongs");
                File mainUserFavorite = new File(user.name + "favorites");
                PrintStream recentWriter = new PrintStream(new FileOutputStream(userRecent) , true);
                PrintStream favoriteWriter = new PrintStream(new FileOutputStream(userFavorite) , true);
                try (PrintStream songWriter = new PrintStream(new FileOutputStream(userSong) , true)) {
                    int counter = 0;
                    for (byte[] bytes : clientObj.getUserSongs().get(user)) {
                        File temp = new File(clientObj.getAllSongNames().get(user).get(counter));
                        FileUtils.writeByteArrayToFile(temp, bytes);
                        songWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                        if(FileUtils.readFileToString(mainUserFavorite).contains(clientObj.getAllSongNames().get(user).get(counter))){
                            favoriteWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                        }
                        if(FileUtils.readFileToString(mainUserRecent).contains(clientObj.getAllSongNames().get(user).get(counter))){
                            recentWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                        }
                        counter++;
                    }
                }
                FileUtils.forceDelete(mainUserFavorite);
                FileUtils.forceDelete(mainUserRecent);
                recentWriter.close();
                favoriteWriter.close();
                Files.move(userRecent.toPath(), new File(user.name + "Recentsongs").toPath() , StandardCopyOption.REPLACE_EXISTING );
                Files.move(userFavorite.toPath(), new File(user.name + "favorite").toPath() , StandardCopyOption.REPLACE_EXISTING );


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

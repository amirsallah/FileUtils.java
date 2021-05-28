import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {

    public void addNote(String fileName) {
        Scanner in = new Scanner(System.in);
        System.out.println("inter title:");
        String str = in.nextLine();
        System.out.println("inter note:");
        String str2 = in.nextLine();
        System.out.println("inter date");
        String str3 = in.nextLine();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName,true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Note note = new Note(str,str2,str3);
            objectOutputStream.writeObject(note);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteNode(String fileName, int n) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 1; objectInputStream.available()>0; ++i) {
            if (i == n) {
                objectInputStream.readObject();
                continue;
            }
            notes.add((Note) objectInputStream.readObject());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (int i = 0; i <= notes.size()- 1; ++i) {
            objectOutputStream.writeObject(notes.get(i));
        }
        objectOutputStream.close();
        fileOutputStream.close();
        objectInputStream.close();
        fileInputStream.close();
    }

    public void showNodes(String fileName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            int n = 1;
            while (true) {
                Note note = (Note) objectInputStream.readObject();
                System.out.println(n + ") " + note.getTitle());
                ++n;
            }
        } catch (ClassNotFoundException |EOFException | StreamCorruptedException ignored) {
        }
    }

    public void showNode(String fileName, int n) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            int i = 1;
            while (i != n) {
                    objectInputStream.readObject();
                ++i;
            }
            Note note = (Note) objectInputStream.readObject();
            System.out.println(note.getTitle() + "\n" + note.getContent() + "\n" + note.getDate());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

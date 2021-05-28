import java.io.IOException;
import java.util.Scanner;

public class NoteBook {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileUtils fileUtils = new FileUtils();
        while (true) {
            System.out.println("1)new note\n" +
                    "2)notes\n" +
                    "3)delete note\n" +
                    "4)exit");
            Scanner input = new Scanner(System.in);

            int n = input.nextInt();
            if (n == 1) {
                fileUtils.addNote("nodes.txt");
            }
            if (n == 2) {
                Scanner in = new Scanner(System.in);
                fileUtils.showNodes("nodes.txt");
                int i = in.nextInt();
                fileUtils.showNode("nodes.txt", i);
            }
            if (n == 3) {
                Scanner in = new Scanner(System.in);
                fileUtils.showNodes("nodes.txt");
                int i = in.nextInt();
                fileUtils.deleteNode("nodes.txt", i);
            }
            if (n == 4)
                break;
        }
    }
}

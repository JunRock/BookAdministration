import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchBook {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> file_line = new ArrayList<String>();
    private ArrayList<BookInfo> bi = new ArrayList<BookInfo>();

    public void search() {
        File f = new File("BookInformation.txt");
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(f);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String readLine = null;
            int LineNumber = 0;
            while ((readLine = br.readLine()) != null) {
                file_line.add(readLine);
                LineNumber++;
            }

            for (int i = 0; i < LineNumber; i++) {
                BookInfo b2 = new BookInfo();
                String text = file_line.get(i);
                StringTokenizer st2 = new StringTokenizer(text, "/");

                String bCat = st2.nextToken();
                String bId = st2.nextToken();
                String bTitle = st2.nextToken();
                String bAut = st2.nextToken();
                String bPub = st2.nextToken();

                b2.setCategory(bCat);
                b2.setBookId(bId);
                b2.setTitle(bTitle);
                b2.setAuthor(bAut);
                b2.setPublisher(bPub);

                bi.add(b2);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.*;
import java.util.*;

public class AddBookInfo {
    Scanner s = new Scanner(System.in);
    public BookInfo book = new BookInfo();

    public void Add_File() {
        String s5 = book.getBookId();
        String s6 = "\r\n";
        try {
            FileWriter fout = new FileWriter("BookInformation.txt", true);
            String s1 = book.getCategory();
            String s2 = book.getTitle();
            String s3 = book.getAuthor();
            String s4 = book.getPublisher();
            fout.write(s1, 0, s1.length());
            fout.write("/");
            fout.write(s5);
            fout.write("/");
            fout.write(s2);
            fout.write("/");
            fout.write(s3);
            fout.write("/");
            fout.write(s4);
            fout.write(s6);
            fout.close();
        } catch (IOException e) {
            System.out.println("파일에 저장할 수 없었습니다. 경로명을 확인해주세요!");
            System.exit(0);
        }
        return;
    }
}

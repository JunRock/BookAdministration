import java.io.*;
import java.util.*;

public class DeleteBookInfo {
    Scanner sc = new Scanner(System.in);

    public boolean road_BookInfo(String id) {
        int nCount = 0;
        try {
            File f = new File("BookInformation.txt");

            FileReader fr = new FileReader(f);

            BufferedReader buf = new BufferedReader(fr);
            String line = "";

            while ((line = buf.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "/");
                st.nextToken();
                if (id.equals(st.nextToken())) {
                    String dummy = "";

                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                        //1. 삭제하고자 하는 position 이전까지는 이동하며 dummy에 저장

                        String line2;

                        for (int j = 0; j < nCount; j++) {

                            line2 = br.readLine(); //읽으며 이동

                            dummy += (line2 + "\r\n");

                        }

                        //2. 삭제하고자 하는 데이터는 건너뛰기

                        String delData = br.readLine();


                        //3. 삭제하고자 하는 position 이후부터 dummy에 저장

                        while ((line2 = br.readLine()) != null) {

                            dummy += (line2 + "\r\n");

                        }
                        //4. FileWriter를 이용해서 덮어쓰기

                        FileWriter fw = new FileWriter(f);

                        fw.write(dummy);

                        fw.close();

                        br.close();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    return true;
                }
                ++nCount;
            }
        } catch (IOException e) {
            System.exit(0);
        }
        return false;
    }
}
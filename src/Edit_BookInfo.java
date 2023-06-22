import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;

public class Edit_BookInfo {
    public Edit_BookInfo(int index, String str[]) {
        try {
            File f = new File("BookInformation.txt");

            FileReader fr = new FileReader(f);

            BufferedReader buf = new BufferedReader(fr);
            String line = "";

            while ((line = buf.readLine()) != null) {
                String dummy = "";

                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                    //1. 수정하고자 하는 position 이전까지는 이동하며 dummy에 저장

                    String line2;

                    for (int j = 0; j < index; j++) {
                        line2 = br.readLine(); //읽으며 이동
                        dummy += (line2 + "\r\n");

                    }

                    //2. 수정하고자 하는 데이터는 추가 및 줄 건너뛰기
                    String deldat = br.readLine();
                    for (int i = 0; i < 5; i++) {
                        dummy += (str[i] + "/");
                    }
                    dummy += ("\r\n");

                    //3. 수정하고자 하는 position 이후부터 dummy에 저장

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
            }
        } catch (IOException e) {
            System.exit(0);
        }
    }


}


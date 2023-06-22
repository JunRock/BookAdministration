import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.StringTokenizer;

class Booklistpanel extends JPanel {
    FileReader fr = null;
    BufferedReader br = null;
    File f = new File("BookInformation.txt");
    Container c = getParent();

    public Booklistpanel(JFrame j) {
        setBackground(new Color(153, 204, 204));
        AddBookInfo addBookInfo = new AddBookInfo();
        setLayout(null);
        JButton logout = new JButton("로그아웃");
        logout.setBounds(831, 580, 137, 38);
        logout.setFont(new Font("굴림", Font.PLAIN, 23));
        logout.setBackground(Color.WHITE);
        add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
                new Login();
            }
        });

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setBackground(Color.WHITE);
            }
        });
        int n = 0;
        int i = 0;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                n++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] header = new String[]{"분야", "도서번호", "책 제목", "저자", "출판사"};
        String[][] contents = new String[n][30];
        String[][] temp = new String[1][5];
        try {
            fr = new FileReader(f);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(readLine, "/");
                int a = 0;
                while (st.hasMoreTokens()) {
                    contents[i][a] = st.nextToken();
                    a++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int c = 0; c < n; c++) {
            for (int d = c + 1; d < n; d++) {
                if (Integer.parseInt(contents[c][1]) > Integer.parseInt(contents[d][1])) {
                    temp[0] = contents[c];
                    contents[c] = contents[d];
                    contents[d] = temp[0];
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(contents, header) {
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
        ;
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 27, 950, 550);
        add(scrollPane);
        scrollPane.setViewportView(table);
    }
}
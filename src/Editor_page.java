import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.security.sasl.AuthorizeCallback;
import javax.swing.*;

public class Editor_page extends JFrame {
    File f = new File("BookInformation.txt");
    BufferedReader br = null;
    FileReader fr = null;
    int n = 0;

    public Editor_page(int index, JFrame j, JTabbedPane jt) {
        AddBookInfo addBookInfo = new AddBookInfo();
        JPanel editor_page = new JPanel();
        editor_page.setLayout(null);
        JLabel 분야 = new JLabel("분야");
        JLabel 도서번호 = new JLabel("도서번호");
        JLabel 책제목 = new JLabel("책 제목");
        JLabel 작가 = new JLabel("작가");
        JLabel 출판사 = new JLabel("출판사");
        editor_page.add(분야);
        editor_page.add(도서번호);
        editor_page.add(책제목);
        editor_page.add(작가);
        editor_page.add(출판사);
        add(editor_page);

        try {
            fr = new FileReader(f);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] contents = new String[n][5];

        int i = 0;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(readLine, "/");
                for (int a = 0; a < 5; a++)
                    contents[i][a] = st.nextToken();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] temp = new String[1][5];
        for (int c = 0; c < n; c++) {
            for (int d = c + 1; d < n; d++) {
                if (Integer.parseInt(contents[c][1]) > Integer.parseInt(contents[d][1])) {
                    temp[0] = contents[c];
                    contents[c] = contents[d];
                    contents[d] = temp[0];
                }
            }
        }

        분야.setFont(new Font("굴림", Font.PLAIN, 20));
        분야.setHorizontalAlignment(SwingConstants.CENTER);
        분야.setBounds(87, 93, 66, 30);

        도서번호.setFont(new Font("굴림", Font.PLAIN, 20));
        도서번호.setHorizontalAlignment(SwingConstants.CENTER);
        도서번호.setBounds(52, 146, 95, 30);

        책제목.setFont(new Font("굴림", Font.PLAIN, 20));
        책제목.setHorizontalAlignment(SwingConstants.CENTER);
        책제목.setBounds(64, 201, 95, 30);

        작가.setFont(new Font("굴림", Font.PLAIN, 20));
        작가.setHorizontalAlignment(SwingConstants.CENTER);
        작가.setBounds(75, 257, 95, 30);

        출판사.setFont(new Font("굴림", Font.PLAIN, 20));
        출판사.setHorizontalAlignment(SwingConstants.CENTER);
        출판사.setBounds(64, 311, 95, 30);

        JTextPane cat = new JTextPane();
        cat.setBounds(153, 85, 248, 38);
        editor_page.add(cat);
        cat.setText(contents[index][0]);

        JTextPane Num = new JTextPane();
        Num.setBounds(153, 140, 248, 38);
        editor_page.add(Num);
        Num.setText(contents[index][1]);

        JTextPane Title = new JTextPane();
        Title.setBounds(153, 193, 248, 38);
        editor_page.add(Title);
        Title.setText(contents[index][2]);

        JTextPane Author = new JTextPane();
        Author.setBounds(153, 249, 248, 38);
        editor_page.add(Author);
        Author.setText(contents[index][3]);

        JTextPane Publisher = new JTextPane();
        Publisher.setBounds(153, 303, 248, 38);
        editor_page.add(Publisher);
        Publisher.setText(contents[index][4]);

        JButton enter = new JButton("입력완료");
        enter.setBounds(207, 351, 137, 38);
        enter.setFont(new Font("굴림", Font.PLAIN, 23));
        enter.setBackground(Color.WHITE);
        editor_page.add(enter);

        setSize(500, 450);

        setTitle("도서 수정 ");
        editor_page.setBackground(new Color(153, 204, 204));
        setLocationRelativeTo(null);
        setVisible(true);

        cat.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        Num.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        Title.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        Author.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        Publisher.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        enter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enter.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enter.setBackground(Color.WHITE);
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                String[] str1 = new String[5];
                str1[0] = cat.getText();
                str1[1] = Num.getText();
                str1[2] = Title.getText();
                str1[3] = Author.getText();
                str1[4] = Publisher.getText();
                new Edit_BookInfo(index, str1);
                JOptionPane.showMessageDialog(null, "도서가 수정되었습니다.");
                dispose();
                j.dispose();
                new HomeMenu(jt.getSelectedIndex());
            }
        });

    }
}

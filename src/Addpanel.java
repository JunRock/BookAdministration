import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Addpanel extends JPanel {
    FileReader fr = null;
    BufferedReader br = null;
    File f = new File("BookInformation.txt");

    public Addpanel(JFrame j, JTabbedPane jt) {
        setBackground(new Color(153, 204, 204));
        AddBookInfo addBookInfo = new AddBookInfo();
        setLayout(null);
        JLabel 분야 = new JLabel("분야");
        JLabel 도서번호 = new JLabel("도서번호");
        JLabel 책제목 = new JLabel("책 제목");
        JLabel 작가 = new JLabel("작가");
        JLabel 출판사 = new JLabel("출판사");
        add(분야);
        add(도서번호);
        add(책제목);
        add(작가);
        add(출판사);

        분야.setFont(new Font("굴림", Font.PLAIN, 20));
        분야.setHorizontalAlignment(SwingConstants.CENTER);
        분야.setBounds(42, 50, 76, 38);

        도서번호.setFont(new Font("굴림", Font.PLAIN, 20));
        도서번호.setHorizontalAlignment(SwingConstants.CENTER);
        도서번호.setBounds(27, 120, 80, 38);

        책제목.setFont(new Font("굴림", Font.PLAIN, 20));
        책제목.setHorizontalAlignment(SwingConstants.CENTER);
        책제목.setBounds(35, 209, 76, 38);

        작가.setFont(new Font("굴림", Font.PLAIN, 20));
        작가.setHorizontalAlignment(SwingConstants.CENTER);
        작가.setBounds(42, 301, 76, 38);

        출판사.setFont(new Font("굴림", Font.PLAIN, 20));
        출판사.setHorizontalAlignment(SwingConstants.CENTER);
        출판사.setBounds(40, 379, 76, 38);

        JTextPane cat = new JTextPane();
        cat.setBounds(106, 59, 150, 25);
        add(cat);

        JTextPane Num = new JTextPane();
        Num.setBounds(106, 129, 150, 25);
        add(Num);

        JTextPane Title = new JTextPane();
        Title.setBounds(106, 218, 150, 25);
        add(Title);

        JTextPane Author = new JTextPane();
        Author.setBounds(106, 310, 150, 25);
        add(Author);

        JTextPane Publisher = new JTextPane();
        Publisher.setBounds(106, 388, 150, 25);
        add(Publisher);

        JButton enter = new JButton("입력완료");
        enter.setBounds(82, 462, 150, 63);
        enter.setFont(new Font("굴림", Font.PLAIN, 23));
        enter.setBackground(Color.WHITE);

        JButton logout = new JButton("로그아웃");
        logout.setBounds(832, 580, 137, 38);
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
        add(enter);

        Publisher.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
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
        String[][] contents = new String[n][50];
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

        DefaultTableModel model = new DefaultTableModel(contents, header) {
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
        ;
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(270, 27, 700, 550);
        add(scrollPane);
        scrollPane.setViewportView(table);

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
                addBookInfo.book.setCategory(cat.getText());
                addBookInfo.book.setBookId(Num.getText());
                addBookInfo.book.setTitle(Title.getText());
                addBookInfo.book.setAuthor(Author.getText());
                addBookInfo.book.setPublisher(Publisher.getText());
                addBookInfo.Add_File();
                cat.setText("");
                Num.setText("");
                Title.setText("");
                Author.setText("");
                Publisher.setText("");
                JOptionPane.showMessageDialog(null, "도서가 추가되었습니다.");
                j.dispose();
                new HomeMenu(jt.getSelectedIndex());
            }
        });

    }
}

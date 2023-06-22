import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Deletepanel extends JPanel {
    DeleteBookInfo deleteBookInfo = new DeleteBookInfo();
    FileReader fr = null;
    BufferedReader br = null;
    File f = new File("BookInformation.txt");

    public Deletepanel(JFrame j, JTabbedPane jt) {
        setBackground(new Color(153, 204, 204));
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
        scrollPane.setBounds(10, 60, 950, 530);
        add(scrollPane);
        scrollPane.setViewportView(table);


        setLayout(null);

        JButton logout = new JButton("로그아웃");
        logout.setBounds(822, 590, 137, 38);
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

        JButton enter = new JButton("입력완료");
        enter.setBounds(600, 15, 137, 38);
        enter.setFont(new Font("굴림", Font.PLAIN, 23));
        enter.setBackground(Color.WHITE);
        add(enter);

        JLabel 도서번호 = new JLabel("도서번호");
        도서번호.setFont(new Font("굴림", Font.PLAIN, 20));
        도서번호.setHorizontalAlignment(SwingConstants.CENTER);
        도서번호.setBounds(50, 15, 137, 38);
        add(도서번호);
        JTextPane Num = new JTextPane();
        Num.setBounds(200, 15, 150, 38);
        add(Num);

        setVisible(true);

        Num.addKeyListener(new KeyAdapter() {
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
                if (deleteBookInfo.road_BookInfo(Num.getText()) == true) {
                    JOptionPane.showMessageDialog(null, "도서가 삭제되었습니다");
                    j.dispose();
                    new HomeMenu(jt.getSelectedIndex());
                } else
                    JOptionPane.showMessageDialog(null, "해당 도서가 존재하지 않습니다.");
                Num.setText("");
            }
        });

    }
}

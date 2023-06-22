import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Searchpanel extends JPanel {
    SearchBook searchBook = new SearchBook();
    File f = new File("BookInformation.txt");
    BufferedReader br = null;
    FileReader fr = null;
    JTextField search_bar = new JTextField();
    int n = 0;

    public Searchpanel(JFrame j) {
        setBackground(new Color(153, 204, 204));
        setLayout(null);

        String[] str = {"분야", "도서번호", "책 제목", "작가", "출판사"};
        JComboBox<String> select = new JComboBox<String>(str);
        select.setBounds(50, 20, 137, 38);
        add(select);

        JButton enter = new JButton("입력완료");
        enter.setBounds(600, 20, 137, 38);
        enter.setFont(new Font("굴림", Font.PLAIN, 23));
        enter.setBackground(Color.WHITE);
        add(enter);

        search_bar.setBounds(200, 20, 250, 38);
        add(search_bar);

        search_bar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

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

        String[] header = new String[]{"분야", "도서번호", "책 제목", "저자", "출판사"};
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


        DefaultTableModel model = new DefaultTableModel(contents, header) {
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
        ;

        JButton logout = new JButton("로그아웃");
        logout.setBounds(822, 580, 137, 38);
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

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 80, 950, 500);
        add(scrollPane);

        scrollPane.setViewportView(table);

        table.getTableHeader().setReorderingAllowed(false);

        setVisible(true);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.getModel();
                int row = table.getRowCount();
                for (int i = row - 1; i >= 0; i--)//기존에 있는 목록 삭제
                    model.removeRow(i);
                for (int i = 0; i < n; i++)
                    model.addRow(contents[i]);
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
                int index = select.getSelectedIndex();
                String tmp = search_bar.getText();

                table.getModel();
                int row = table.getRowCount();
                for (int i = row - 1; i >= 0; i--)//기존에 있는 목록 삭제
                    model.removeRow(i);
                for (int i = 0; i < n; i++) {
                    if (contents[i][index].equals(tmp))
                        model.addRow(contents[i]);

                }
                search_bar.setText(tmp);
            }
        });
    }
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

class Editorpanel extends JPanel {
    File f = new File("BookInformation.txt");
    BufferedReader br = null;
    FileReader fr = null;
    private JTable table;
    int n = 0;

    public Editorpanel(JFrame j, JTabbedPane jt) {
        setBackground(new Color(153, 204, 204));
        setLayout(null);
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

        String[] header = new String[]{"분야", "도서번호", "책 제목", "저자", "출판사", "수정"};
        String[][] contents = new String[n][6];

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

        DefaultTableModel model = new DefaultTableModel(contents, header);

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

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 950, 570);
        add(scrollPane);

        scrollPane.setViewportView(table);
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCell(j, jt));
        table.getColumnModel().getColumn(5).setCellEditor(new TableCell(j, jt));
    }

    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton edit;

        public TableCell(JFrame j, JTabbedPane jt) {
            edit = new JButton("수정");

            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = table.getSelectedRow();
                    new Editor_page(index, j, jt);
                }
            });

        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            return edit;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            return edit;
        }
    }
}


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class HomeMenu extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();
    public Booklistpanel booklistpanel = new Booklistpanel(this);
    public Addpanel addpanel = new Addpanel(this, tabbedPane);
    public Searchpanel searchpanel = new Searchpanel(this);
    public Deletepanel deletepanel = new Deletepanel(this, tabbedPane);
    public Editorpanel editorpanel = new Editorpanel(this, tabbedPane);
    public JButton RefreshButton = new JButton("새로 고침");

    public HomeMenu(int jtabindex) {
        tabbedPane.addTab("도서 목록", booklistpanel);
        tabbedPane.addTab("도서 추가", addpanel);
        tabbedPane.addTab("도서 검색", searchpanel);
        tabbedPane.addTab("도서 삭제", deletepanel);
        tabbedPane.addTab("도서 수정", editorpanel);
        tabbedPane.setSelectedIndex(jtabindex);
        add(tabbedPane);

        setSize(1000, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("도서 관리 프로그램");
        setLocationRelativeTo(null);
    }
}
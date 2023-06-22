import javax.management.ValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Login extends JFrame {
    private ArrayList<Admin> am = new ArrayList<Admin>();
    private String[] admin = new String[20];

    public Login() {

        JPanel LoginPage = new JPanel();
        LoginPage.setLayout(null);

        JLabel ja = new JLabel("자바 도서 관리 프로그램");
        ja.setBounds(50, 30, 200, 30);
        ja.setFont(new Font("굴림", Font.BOLD, 15));
        ja.setBackground(Color.BLACK);

        LoginPage.add(ja);

        JButton login = new JButton("LOGIN");
        login.setBounds(150, 200, 120, 30);
        login.setFont(new Font("굴림", Font.BOLD, 15));
        login.setBackground(Color.WHITE);

        JButton signup = new JButton("회원가입");
        signup.setBounds(10, 200, 120, 30);
        signup.setFont(new Font("굴림", Font.BOLD, 15));
        signup.setBackground(Color.WHITE);

        LoginPage.add(login);
        LoginPage.add(signup);
        add(LoginPage);

        JLabel id = new JLabel("ID");
        JLabel pw = new JLabel("PW");
        id.setFont(new Font("굴림", Font.PLAIN, 15));
        id.setHorizontalAlignment(SwingConstants.CENTER);
        id.setBounds(70, 100, 20, 20);
        pw.setFont(new Font("굴림", Font.PLAIN, 15));
        pw.setHorizontalAlignment(SwingConstants.CENTER);
        pw.setBounds(66, 140, 25, 20);

        LoginPage.add(id);
        LoginPage.add(pw);

        JTextField Id = new JTextField();
        Id.setLocation(90, 100);
        Id.setSize(100, 20);
        LoginPage.add(Id);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(90, 140, 100, 20);
        LoginPage.add(passwordField);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("로그인 화면 ");
        LoginPage.setBackground(new Color(153, 204, 204));
        setLocationRelativeTo(null);
        setVisible(true);
        LoginPage.setFocusable(true);
        LoginPage.requestFocus();

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    login.doClick();
                }
            }
        });
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(Color.WHITE);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Id.getText();
                String pw = passwordField.getText();
                FileReader fin = null;
                BufferedReader br = null;
                File f = new File("Admin.txt");
                try {
                    fin = new FileReader(f);
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    int n = 0;
                    String readLine = null;
                    while ((readLine = br.readLine()) != null) {
                        admin[n] = readLine;
                        n++;
                    }

                    for (int i = 0; i < n; i++) {
                        StringTokenizer st = new StringTokenizer(admin[i], "/");
                        String id1 = st.nextToken();
                        String pw1 = st.nextToken();
                        Admin ad = new Admin(id1, pw1);
                        am.add(ad);
                    }
                    int count = 0;
                    for (int i = 0; i < am.size(); i++) {
                        if (id.equals(am.get(i).getId())) {
                            if (pw.equals(am.get(i).getPw())) {
                                JOptionPane.showMessageDialog(null, "로그인 성공!");
                                LoginPage.setVisible(false);
                                dispose();
                                new HomeMenu(0);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "로그인 실패!");
                                break;
                            }
                        } else {
                            count++;
                            continue;
                        }
                    }
                    if (count == am.size())
                        JOptionPane.showMessageDialog(null, "존재하지 않는 아이디!");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        signup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signup.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signup.setBackground(Color.WHITE);
            }
        });

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignupPage();
            }
        });

    }

    public static void main(String[] args) {
        new Login();
    }
}


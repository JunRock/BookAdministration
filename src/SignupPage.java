import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class SignupPage extends JFrame {
    public SignupPage() {
        JPanel signupPage = new JPanel();
        signupPage.setLayout(null);

        JLabel id = new JLabel("ID");
        JLabel pw = new JLabel("PW");

        JButton signup = new JButton("가입 완료");
        signup.setBounds(150, 200, 120, 30);
        signup.setFont(new Font("굴림", Font.PLAIN, 20));
        signup.setBackground(Color.WHITE);

        JButton back = new JButton("뒤로가기");
        back.setBounds(10, 200, 120, 30);
        back.setFont(new Font("굴림", Font.PLAIN, 20));
        back.setBackground(Color.WHITE);


        id.setFont(new Font("굴림", Font.PLAIN, 20));
        id.setHorizontalAlignment(SwingConstants.CENTER);
        id.setBounds(40, 100, 80, 34);
        pw.setFont(new Font("굴림", Font.PLAIN, 20));
        pw.setHorizontalAlignment(SwingConstants.CENTER);
        pw.setBounds(34, 150, 80, 34);

        signupPage.add(id);
        signupPage.add(pw);
        signupPage.add(signup);
        signupPage.add(back);

        JTextField Id = new JTextField();
        Id.setBounds(100, 100, 100, 25);
        signupPage.add(Id);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 150, 100, 25);
        signupPage.add(passwordField);

        add(signupPage);

        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("회원가입 화면");
        signupPage.setBackground(new Color(153, 204, 204));
        setLocationRelativeTo(null);
        setVisible(true);

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    signup.doClick();
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

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setBackground(Color.WHITE);
            }
        });

        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File f = new File("Admin.txt");
                FileWriter bf = null;
                BufferedReader br = null;
                String checkid = null;
                try {
                    bf = new FileWriter(f, true);
                    br = new BufferedReader(new FileReader(f));
                    String num;

                    while ((num = br.readLine()) != null) {
                        String[] value = num.split("/");
                        checkid = value[0];
                        if (checkid.equals(Id.getText())) {
                            JOptionPane.showMessageDialog(null, "존재하는 아이디 입니다!");
                            return;
                        }
                    }

                    while (true) {
                        String 아이디 = Id.getText();
                        String 비번 = passwordField.getText();
                        if (아이디.length() == 0) {
                            JOptionPane.showMessageDialog(null, "아이디를 입력하세요!");
                            passwordField.setText("");
                            return;
                        } else if (비번.length() == 0) {
                            JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요!");
                            Id.setText("");
                            return;
                        } else if (아이디.length() == 0 && 비번.length() == 0) {
                            JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요!");
                            passwordField.setText("");
                            Id.setText("");
                            return;
                        } else {
                            bf.write(아이디, 0, 아이디.length());
                            bf.write("/");
                            bf.write(비번, 0, 비번.length());

                            bf.write("\r\n", 0, 2);
                            JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
                            bf.close();
                            dispose();
                            new Login();
                            break;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
    }

}
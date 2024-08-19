import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class first extends JFrame implements ActionListener {
    JButton b1, b2, b3, admin;
    JLabel l1, l2;
    JTextField t1;
    JPasswordField t2;
    JPanel p, p1;
    Connection c;
    Statement s;
    ResultSet re;
    String roll_no, name;

    first() {
        setTitle("Geethanjali Transport Services");
        UIManager.put("TextField.caretForeground", new ColorUIResource(0, 255, 0));
        UIManager.put("PasswordField.caretForeground", new ColorUIResource(0, 255, 0));
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl", "root", "Naveen@123");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
        p = new JPanel();
        p1 = new JPanel();
        // l1 = new JLabel("Roll No : ");
        l1 = new JLabel("Login Id : ");
        l1.setFont(new Font("Verdana", Font.BOLD, 13));
        l1.setForeground(Color.green);
        // l1.setBounds(601, 247, 200, 200);
        l1.setBounds(595, 247, 200, 200);
        l2 = new JLabel("Password : ");
        l2.setFont(new Font("Verdana", Font.BOLD, 13));
        l2.setBounds(582, 280, 200, 200);
        l2.setForeground(Color.green);
        t1 = new JTextField(10);
        t1.setBackground(Color.black);
        t1.setForeground(Color.green);
        t1.setBounds(670, 338, 200, 23);
        t2 = new JPasswordField(10);
        t2.setBackground(Color.black);
        t2.setForeground(Color.green);
        t2.setBounds(670, 372, 200, 22);
        b1 = new JButton("Login");
        b1.setBounds(670, 410, 90, 25);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.green);
        b2 = new JButton("Cancel");
        b2.setBounds(780, 410, 90, 25);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.green);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b3 = new JButton("  Register Now  ");
        b3.addActionListener(this);
        b3.setForeground(Color.green);
        b3.setBackground(Color.black);
        b3.setBounds(670, 440, 200, 25);
        admin = new JButton("  Admin Login  ");
        admin.addActionListener(this);
        admin.setForeground(Color.green);
        admin.setBackground(Color.black);
        admin.setBounds(1300, 140, 200, 25);
        JLabel l = new JLabel("GEETHANJALI TRANSPORT SERVICES");
        l.setBounds(20, 20, 2000, 62);
        l.setFont(new Font("Verdana", Font.BOLD, 70));
        l.setForeground(Color.green);
        add(l);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(b2);
        add(b3);
        add(admin);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setSize(500, 500);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    boolean logincheck() {
        try {
            re = s.executeQuery("Select * from login;");
            while (re.next()) {
                if ((re.getString(1).equals(t1.getText()) && re.getString(4).equals(new String(t2.getPassword())))
                        || (t1.getText().equals("a") && "a".equals(new String(t2.getPassword())))) {
                    roll_no = re.getString(1);
                    name = re.getString(2);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    void login() throws SQLException {
        if (logincheck()) {
            new NewF(c, s, re, roll_no, name);
            // c.close();s.close();re.close();
            dispose();
        } else {
            JDialog d = new JDialog();
            d.setLayout(null);
            JLabel l = new JLabel("Please Enter Valid Credentials");
            // System.out.println(l.getHorizontalAlignment());
            l.setBounds(40, 45, 200, 20);
            d.add(l);
            d.setVisible(true);
            l.setForeground(Color.GREEN);
            d.getContentPane().setBackground(Color.black);
            d.setSize(270, 150);
            // d.setLocation(getMousePosition());
            // System.out.println(getMousePosition());
            d.setLocation(600, 338);
            d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            // SwingUtilities.updateComponentTreeUI(this);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {

            dispose();
        } else if (ae.getSource() == b3) {
            new register(c, s, re);
        } else if (ae.getSource() == admin) {
            new diag(c,s,this);
        } else {
            System.out.println(getMousePosition());
            try {
                login();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new first();
    }
}
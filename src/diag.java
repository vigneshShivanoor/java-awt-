import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class diag extends JDialog implements ActionListener{
    JLabel l,l1, l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;
    JPanel p;
    Connection c;
    Statement s;
    ResultSet re;
    boolean test=false;
    JFrame f;
    diag(Connection c,Statement s,JFrame f)
    {
        setTitle("Admin Login");
        this.c=c;
        this.s=s;
        this.f=f;
        p=new JPanel();
        p.setLayout(null);
        p.setBackground(Color.black);
        l=new JLabel("    ----------Admin Login----------");
        l.setForeground(Color.green);
        l.setFont(new Font("Verdana",Font.BOLD,20));
        l1 = new JLabel("Login Id : ");
        l1.setFont(new Font("Verdana", Font.BOLD, 13));
        l1.setForeground(Color.green);
        l1.setBounds(100, 20, 100, 20);
        // l1.setBounds(595, 247, 200, 200);
        l2 = new JLabel("Password : ");
        l2.setFont(new Font("Verdana", Font.BOLD, 13));
        l2.setBounds(87, 50, 100, 20);
        l2.setForeground(Color.green);
        t1 = new JTextField(10);
        t1.setBackground(Color.black);
        t1.setForeground(Color.green);
        t1.setBounds(180, 20, 150, 22);
        t2 = new JPasswordField(10);
        t2.setBackground(Color.black);
        t2.setForeground(Color.green);
        t2.setBounds(180, 50, 150, 22);
        b1 = new JButton("Login");
        b1.setBounds(100, 100, 90, 25);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.green);
        b2 = new JButton("Cancel");
        b2.setBounds(200, 100, 90, 25);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.green);
        b2.addActionListener(this);
        b1.addActionListener(this);
        add(l,"North");
        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
        p.add(b1);
        p.add(b2);
        add(p);
        setLocation(575,300);
        getContentPane().setBackground(Color.black);
        setSize(400,200);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    boolean admincheck() throws SQLException
    {
        re=s.executeQuery("Select * from adminlogin where id='"+t1.getText()+"' and pass='"+t2.getText()+"';");
        if(re.next())
        {
            return true;
        }
        return false;
    }
    boolean cl()
    {
        return test;
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b2)
        dispose();
        else
        {
            try{
                if(admincheck())
                {
                    new admin(c,s);
                    f.dispose();
                    dispose();
                }
                else {
                    
                    JOptionPane.showMessageDialog(this,"Enter Valid Credentials");
                }
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}

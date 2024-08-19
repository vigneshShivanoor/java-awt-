import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class register extends JDialog implements ActionListener {
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5;
    JTextField f1,f2;
    JPasswordField p,p1;

    JComboBox f3;
    Connection c;
    Statement s;
    ResultSet re;
    register(Connection c,Statement s,ResultSet re)
    {
        setTitle("Registration ");
        this.c=c;
        this.s=s;
        this.re=re;
        String dep[]={"CSE","ECE","EEE","CE","ME","CSE-AIML","CSE-DS","CSE-CS","CSE-IOT"};
        // UIManager.put("TextField.caretForeground", new ColorUIResource(0,255,0));
        // UIManager.put("PasswordField.caretForeground", new ColorUIResource(0,255,0));
        setLayout(null);
        b1=new JButton("Submit");
        b1.setBounds(105+63,161+124,120,25);
        b2=new JButton("Cancel");
        b2.setBounds(235+63*2,161+124,120,25);
        b1.addActionListener(this);
        b2.addActionListener(this);
        l1=new JLabel("Name :");
        l1.setFont(new Font("Verdana", Font.BOLD, 13));
        l1.setBounds(105+63*2,97,100,23);
        l2=new JLabel("Roll No : ");
        l2.setFont(new Font("Verdana", Font.BOLD, 13));
        l2.setBounds(97+63*2,128,100,23);
        l3=new JLabel("Department : ");
        l3.setFont(new Font("Verdana", Font.BOLD, 13));
        l3.setBounds(61+63*2,159,100,23);
        l4=new JLabel("New Password : ");
        l4.setFont(new Font("Verdana", Font.BOLD, 13));
        l4.setBounds(41+63*2,159+31,200,23);
        l5=new JLabel("Confirm Password : ");
        l5.setFont(new Font("Verdana", Font.BOLD, 13));
        l5.setBounds(18+63*2,159+62,200,23);
        f1=new JTextField(20);
        f1.setBounds(160+63*2,99,200,23);
        f2=new JTextField(20);
        f2.setBounds(160+63*2,130,200,23);
        f3=new JComboBox(dep);
        // f3.addActionListener(this);
        f3.setBounds(160+63*2,161,200,23);
        p=new JPasswordField(20);
        p.setBounds(160+63*2,161+31,200,23);
        p1=new JPasswordField(20);
        p1.setBounds(160+63*2,161+62,200,23);
        b1.setForeground(Color.green);
        b2.setForeground(Color.green);
        l1.setForeground(Color.green);
        l2.setForeground(Color.green);
        l3.setForeground(Color.green);
        l4.setForeground(Color.green);
        l5.setForeground(Color.green);
        f1.setForeground(Color.green);
        f2.setForeground(Color.green);
        f3.setForeground(Color.green);
        p.setForeground(Color.green);
        p1.setForeground(Color.green);
        f1.setBackground(Color.black);
        f2.setBackground(Color.black);
        f3.setBackground(Color.black);
        p.setBackground(Color.black);
        p1.setBackground(Color.black);
        b1.setBackground(Color.BLACK);
        b2.setBackground(Color.BLACK);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(f1);
        add(f2);
        add(f3);
        add(p);
        add(p1);
        add(b1);
        add(b2);
        setLayout(null);
        setVisible(true);
        setLocation(430,249);
        setSize(700,400);
        getContentPane().setBackground(Color.black);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b2)
        dispose();
        else
        {
            if((new String(p1.getPassword()).equals((new String(p.getPassword()))))){
                    try{
                // s.execute("insert into login values('"+f2.getText()+"','"+f1.getText()+"','"+(""+f3.getSelectedItem())+"','"+(new String(p1.getPassword()))+"');");
                // dispose();
            PreparedStatement ps=c.prepareStatement("insert into login values(?,?,?,?);");
            ps.setString(1,f2.getText());
            ps.setString(2,f1.getText());
            ps.setString(3,""+f3.getSelectedItem());
            ps.setString(4,new String(p1.getPassword()));
           ps.executeUpdate();
        //    new d();
        JOptionPane.showMessageDialog(this,"You Have Succesfully Registered to Geethanjali Transport Services");
           dispose();
        }
           catch(Exception e){
            System.out.println(e);
           }
        }
           else {
            JOptionPane.showMessageDialog(this,"Please check your entered password");
           }
        }
    }
    // class d extends JDialog implements ActionListener{
    //     JButton b;
    //     d(){
    //         JLabel l=new JLabel("Your Have Succesfully Registered");
    //         b=new JButton("OK");
    //         b.addActionListener(this);
    //         add(l);
    //         setLocation(430,249);
    //         add(b,"South");
    //         setSize(300,50);
    //         setVisible(true);
    //     }
    //     public void actionPerformed(ActionEvent ae)
    //     {
    //         dispose();
    //     }
    // }
}
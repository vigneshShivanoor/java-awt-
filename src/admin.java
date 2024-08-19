import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class admin extends JFrame implements ActionListener, ItemListener {
    JLabel l1, l2;
    JTextField t1, t2;
    JScrollPane sp;
    JComboBox<String> aBox;
    String buses[];
    JButton b1, b2, b3, refresh,logout;
    Connection c;
    Statement s;
    JPanel p;
    int size;
    String count;
    ResultSet re,re1;
    String arr[][] = new String[100][100];
    String arr1[] = { "Complain_No", "Roll_no", "Bus_No", "Complaints", "Status" };

    admin(Connection c, Statement s) throws SQLException {
        String buses[] = { "ALL", "3", "8", "10", "11", "16", "20", "29", "31" };
        this.buses = buses;
        this.c = c;
        this.s = s;
        setLayout(null);
        t2 = new JTextField();
        t2.setEditable(false);
        t2.setFont(new Font("Verdana", Font.BOLD, 15));
        t2.setBackground(Color.BLACK);
        t2.setForeground(Color.green);
        t2.setBounds(250, 125, 200, 25);
        ResultSet re1 = s.executeQuery("Select count(*) from complaintable");
        if (re1.next())
            t2.setText(count=re1.getString(1));
        ResultSet count = s.executeQuery("Select count(*) from complaintable");
        while (count.next()) {
            size = Integer.parseInt(count.getString(1));
        }
        re = s.executeQuery(
                "select sd_no,c.roll_no,bus_no,complaints,Status from complaintable c,buses b where c.roll_no=b.roll_no;");
        int i = 0;
        while (re.next()) {
            for (int j = 0; j < 5; j++)
                arr[i][j] = re.getString(j + 1);
            i++;
        }
        JLabel Route = new JLabel("Route No : ");
        Route.setBounds(603, 110, 100, 20);
        Route.setFont(new Font("Verdana", Font.BOLD, 15));
        Route.setForeground(Color.green);
        aBox = new JComboBox<>(buses);
        aBox.setBackground(Color.black);
        aBox.setForeground(Color.green);
        aBox.setBounds(603, 130, 300, 23);
        aBox.addItemListener(this);
        refresh = new JButton("REFRESH");
        refresh.setBackground(Color.BLACK);
        refresh.setForeground(Color.green);
        refresh.addActionListener(this);
        // refresh.setBounds(1400, 104, 100, 30);
        refresh.setBounds(1260, 104, 100, 30);
        logout = new JButton("LogOut");
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.green);
        logout.setBounds(1381, 104, 100, 30);
        logout.addActionListener(this);
        add(refresh);
        p = new JPanel();
        p.setLayout(new BorderLayout());
        JTable table = new JTable(arr, arr1);
        table.setForeground(Color.green);
        table.setRowHeight(30);
        table.setBackground(Color.BLACK);
        // table.set(Color.black);
        table.setFont(new Font("Verdana", Font.PLAIN, 13));
        table.setEnabled(false);
        sp = new JScrollPane(table);
        sp.setBounds(40, 110, 427, 80);
        p.add(sp);
        p.setBounds(30, 200, 999, 600);
        JLabel l = new JLabel("GEETHANJALI TRANSPORT SERVICES");
        l.setBounds(20, 20, 2000, 62);
        l.setFont(new Font("Verdana", Font.BOLD, 70));
        l.setForeground(Color.green);
        l2 = new JLabel("Total Complaints : ");
        l2.setFont(new Font("Verdana", Font.BOLD, 15));
        l2.setForeground(Color.green);
        l2.setBounds(70, 120, 1000, 30);
        p.setBackground(Color.black);
        l1 = new JLabel("Enter Complain_No  : ");
        l1.setFont(new Font("Verdana", Font.BOLD, 15));
        l1.setForeground(Color.green);
        l1.setBounds(1050, 300, 1000, 30);
        t1 = new JTextField();
        t1.setBackground(Color.BLACK);
        t1.setForeground(Color.green);
        t1.setBounds(1250, 305, 200, 25);
        b1 = new JButton("Resolved ✅");
        b1.setBackground(Color.black);
        b1.setForeground(Color.green);
        b1.setBounds(1180, 405, 200, 30);
        b2 = new JButton("Denied  ❌");
        b2.setBackground(Color.black);
        b2.setForeground(Color.green);
        b2.setBounds(1180, 445, 200, 30);
        b3 = new JButton("Close");
        b3.setBackground(Color.black);
        b3.setForeground(Color.green);
        b3.setBounds(1180, 485, 200, 30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        add(Route);
        add(p);
        add(l);
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(b1);
        add(b2);
        add(b3);
        add(aBox);
        add(logout);
        setTitle("Admin Login");
        setVisible(true);
        getContentPane().setBackground(Color.black);
        setSize(500, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                s.execute("update complaintable set Status='Resolved' where sd_no=" + t1.getText());
                new admin(c, s);
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            try {
                s.execute("update complaintable set Status='Denied' where sd_no=" + t1.getText());
                new admin(c, s);
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == refresh) {
            try {
                new admin(c, s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dispose();
        }
        else if(ae.getSource()==logout)
        {
            new first();
            dispose();
        } else
            dispose();
    }

    public void itemStateChanged(ItemEvent e){
        if (e.getSource() == aBox) {
            if (e.getSource() == aBox) {
                if (aBox.getSelectedItem().equals("ALL")) {
                    t2.setText(count);
                    sp.setVisible(false);
                    JTable table1 = new JTable(arr, arr1);
                    table1.setForeground(Color.green);
                    table1.setBackground(Color.BLACK);
                    table1.setRowHeight(30);
                    table1.setFont(new Font("Verdana", Font.PLAIN, 13));
                    table1.setEnabled(false);// TO MAKE TABLE DATA UNEDITABLE
                    sp = new JScrollPane(table1);
                    sp.setBackground(Color.BLACK);
                    sp.setSize(500, 500);
                    p.add(sp);
                    SwingUtilities.updateComponentTreeUI(this);
                } else {
                    try {
                        ResultSet re2=s.executeQuery("select count(*) from complaintable c,buses b where c.roll_no=b.roll_no and bus_no="+aBox.getSelectedItem()+";");
                        if(re2.next())
                        t2.setText(re2.getString(1));
                        re2.close();
                        re1 = s.executeQuery(
                                "select sd_no,c.roll_no,bus_no,complaints,Status from complaintable c,buses b where c.roll_no=b.roll_no and bus_no="+aBox.getSelectedItem()+";");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    int i = 0;
                    String arr2[][] =new String[100][100];
                    try {
                        while (re1.next()) {
                            for (int j = 0; j < 5; j++)
                                arr2[i][j] = re1.getString(j + 1);
                            i++;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    sp.setVisible(false);
                        JTable table1 = new JTable(arr2, arr1);
                        table1.setForeground(Color.green);
                        table1.setBackground(Color.BLACK);
                        table1.setRowHeight(30);
                        table1.setFont(new Font("Verdana", Font.PLAIN, 13));
                        table1.setEnabled(false);// TO MAKE TABLE DATA UNEDITABLE
                        sp = new JScrollPane(table1);
                        sp.setBackground(Color.BLACK);
                        sp.setSize(500, 500);
                        p.add(sp);
                        SwingUtilities.updateComponentTreeUI(this);
                    }
                }
            }
        }
    }

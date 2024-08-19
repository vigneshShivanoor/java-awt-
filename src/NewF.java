import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class NewF extends JFrame implements ItemListener, ActionListener {
    JButton logout;
    JComboBox<String> aBox;
    JTable table;
    JTextField detail1, detail2;
    JButton complain,refresh,viewcomplaints;
    String buses[];
    Connection c;
    Statement s;
    ResultSet re;
    JScrollPane sp, fra;
    JPanel p, details;
    int size = 0;
     String roll_no,Name;
    String busdata[][];
    String column[] = { "ROLL_NO", "NAME", "DEPARTMENT", "BUS_NO", "No.Of.Complaints" };

    NewF(Connection c, Statement s, ResultSet re, String roll_no, String Name) throws SQLException {
        viewcomplaints=new JButton("ShowMyComplaints");
        viewcomplaints.addActionListener(this);
        viewcomplaints.setBounds(1070,780,200,25);
        viewcomplaints.setForeground(Color.green);
        viewcomplaints.setBackground(Color.black);
        add(viewcomplaints);
        refresh=new JButton("REFRESH");
        refresh.addActionListener(this);
        refresh.setBounds(1260, 104, 100, 30);
        add(refresh);
        this.roll_no=roll_no;
        this.Name=Name;
        // JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        //         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JLabel total = new JLabel("Total Registered Complaints : ");
        total.setBounds(1066, 210, 400, 25);
        total.setFont(new Font("Verdana", Font.BOLD, 20));
        total.setForeground(Color.green);
        add(total);
        ResultSet count = s.executeQuery("Select count(*) from complaintable");
        count.next();
        JLabel cou = new JLabel("" + count.getString(1));
        count.close();
        cou.setBounds(1090, 240, 500, 500);
        cou.setForeground(Color.green);
        cou.setFont(new Font("Verdana", Font.BOLD, 200));
        add(cou);
        details = new JPanel();
        // JLabel det=new JLabel("USER DETAILS : ");
        // det.setFont(new Font("Verdana", Font.BOLD, 15));
        // det.setForeground(Color.green);
        details.setBackground(Color.black);
        details.setBounds(40, 110, 427, 80);
        JLabel roll = new JLabel("RollNo : ");
        roll.setFont(new Font("Verdana", Font.BOLD, 15));
        roll.setForeground(Color.green);
        JLabel name = new JLabel("Name :  ");
        name.setFont(new Font("Verdana", Font.BOLD, 15));
        name.setForeground(Color.green);
        detail1 = new JTextField(20);
        detail1.setFont(new Font("Verdana", Font.PLAIN, 15));
        detail1.setForeground(Color.green);
        detail1.setBackground(Color.black);
        detail1.setText(Name);
        detail2 = new JTextField(20);
        detail2.setFont(new Font("Verdana", Font.PLAIN, 15));
        detail2.setForeground(Color.green);
        detail2.setBackground(Color.black);
        detail2.setText(roll_no);
        detail1.setEditable(false);
        detail2.setEditable(false);
        // details.add(det);
        details.add(name);
        details.add(detail1);
        details.add(roll);
        details.add(detail2);
        this.c = c;
        this.s = s;
        this.re = re;
        String buses[] = { "ALL", "3", "8", "10", "11", "16", "20", "29", "31" };
        this.buses = buses;
        data();
        logout = new JButton("LogOut");
        logout.setBounds(1381, 104, 100, 30);
        logout.addActionListener(this);
        complain = new JButton("Register Complaint");
        complain.addActionListener(this);
        complain.setBounds(1286, 780, 200, 25);
        complain.setForeground(Color.green);
        complain.setBackground(Color.black);

        JLabel Route = new JLabel("Route No : ");
        Route.setBounds(603, 110, 100, 20);
        Route.setFont(new Font("Verdana", Font.BOLD, 15));
        Route.setForeground(Color.green);
        aBox = new JComboBox<>(buses);
        aBox.setBounds(603, 130, 300, 23);
        aBox.addItemListener(this);
        setLayout(null);
        JLabel l = new JLabel("GEETHANJALI TRANSPORT SERVICES");
        l.setBounds(20, 20, 2000, 62);
        l.setFont(new Font("Verdana", Font.BOLD, 70));
        l.setForeground(Color.green);
        // String column[] = { "ROLL_NO", "NAME", "DEPARTMENT",
        // "BUS_NO","No.Of.Complaints" };
        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBounds(30, 200, 999, 600);
        p.setBackground(Color.black);
        add(details);
        add(l);
        add(Route);
        add(aBox);
        p.add(sp);
        add(p);
        // add(list);
        add(complain);
        add(logout);
        setSize(500, 500);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == complain) {
            new complaint(c, s,roll_no);
            SwingUtilities.updateComponentTreeUI(this);
        }
        else if(ae.getSource()==refresh)
        {
            try {
                new NewF(c, s, re, roll_no,Name);
                dispose();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==viewcomplaints)
        {
            int no_of_complaints=0;
            try{
                ResultSet temp=s.executeQuery("Select  no_of_complaints from buses where roll_no='"+detail2.getText()+"';");
                while(temp.next())
                no_of_complaints=Integer.parseInt(temp.getString(1));
            }
            catch(Exception e){}
            System.out.println(no_of_complaints);
        new showcomplaint(c, s,no_of_complaints,roll_no);
    } else {
            new first();
            dispose();
        }
    }

    void data() {
        try {
            ResultSet count = s.executeQuery("Select count(*) from buses");
            while (count.next()) {
                size = Integer.parseInt(count.getString(1));
            }
            count.close();
            System.out.println("Size = " + size);
            String busdata[][] = new String[100][5];
            this.busdata = busdata;
            re = s.executeQuery("Select * from buses;");
            int i = 0;
            while (re.next()) {
                for (int j = 0; j < 5; j++) {
                    busdata[i][j] = re.getString(j + 1);
                }
                i++;
            }
        } catch (Exception e) {

        }
        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBounds(30, 200, 999, 600);
        p.setBackground(Color.black);
        table = new JTable(busdata, column);
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // table.setColumnSelectionInterval(ALLBITS, ABORT);
        // table.getColumnModel().getColumn(1).setWidth(100);
        table.setForeground(Color.green);
        table.setRowHeight(30);
        table.setBackground(Color.BLACK);
        // table.set(Color.black);
        table.setFont(new Font("Verdana", Font.PLAIN, 13));
        table.setEnabled(false); // TO MAKE TABLE DATA UNEDITABLE
        sp = new JScrollPane(table);
        sp.setBackground(Color.BLACK);
        sp.setSize(500, 500);
    }

    public void itemStateChanged(ItemEvent e) {
        System.out.println("Get selected item = " + aBox.getSelectedItem());
        if (e.getSource() == aBox) {
            if (aBox.getSelectedItem().equals("ALL")) {
                sp.setVisible(false);
                JTable table1 = new JTable(busdata, column);
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
                System.out.println("Fired");
                System.out.println(busdata.length);
                int count = 0;
                for (int i = 0; i < size; i++) {
                    if (("" + aBox.getSelectedItem()).equals(busdata[i][3])) {
                        try {
                            re = s.executeQuery("Select * from buses where bus_no='" + busdata[i][3] + "'");
                            while (re.next()) {
                                count++;
                            }
                            System.out.println(count);

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
                String busdata1[][] = new String[50][5];
                for (int i = 0; i < size; i++) {
                    if (("" + aBox.getSelectedItem()).equals(busdata[i][3])) {

                        try {
                            int k = 0;
                            re = s.executeQuery("Select * from buses where bus_no='" + busdata[i][3] + "'");
                            while (re.next()) {
                                for (int j = 0; j < 5; j++)
                                    busdata1[k][j] = re.getString(j + 1);
                                k++;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        for (i = 0; i < count; i++) {
                            for (int j = 0; j < 5; j++)
                                System.out.print(busdata1[i][j] + "  ");
                            System.out.println();
                        }
                        sp.setVisible(false);
                        JTable table1 = new JTable(busdata1, column);
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
                        break;
                    }
                }
            }
        }
    }
}

class complaint extends JDialog implements ActionListener, ItemListener {
    JLabel label;
    JComboBox<String> CB;
    JTextArea textarea;
    JButton cancel, submit;
    String[] complaints;
    Connection c;
    Statement s;
    String roll_no;String Name;
    complaint(Connection c, Statement s,String roll_no) {
        this.roll_no=roll_no;
        this.c = c;
        this.s = s;
        JPanel top = new JPanel();
        String comp[] = { "<Select>","Insufficient Seating", "Frequent Cancelation","Others"};
        CB = new JComboBox<>(comp);
        CB.addItemListener(this);
        JPanel pane = new JPanel();
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        submit.addActionListener(this);
        cancel.addActionListener(this);
        pane.add(submit);
        pane.add(cancel);
        // setLayout(new FlowLayout());
        label = new JLabel("Select Complaint :  ");
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        top.add(label);
        top.add(CB);
        textarea = new JTextArea(300, 200);
        textarea.setFont(new Font("Verdana", Font.BOLD, 25));
        textarea.setEnabled(false);
        JScrollPane scroll = new JScrollPane (textarea, 
   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(top, "North");
        add(scroll);
        add(pane, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.black);
        setLocation(570, 270);
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel)
            dispose();
        else {
            if((""+CB.getSelectedItem()).equals("<Select>"))
            JOptionPane.showMessageDialog(this,"Select a Valid Complaint");
            else{
            try{
            PreparedStatement ps=c.prepareStatement("insert into complaintable(roll_no,complaints) values(?,?);");
            ps.setString(1,roll_no);
            ps.setString(2,textarea.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this,"Your Complaint Has been registered and will be Solved");
            dispose();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"You are not eligible for this action");
            dispose();
        }
    }
}
}

    public void itemStateChanged(ItemEvent ae) {
        if((""+CB.getSelectedItem()).equals("Others")){
            textarea.setText("");
            textarea.setEnabled(true);
        }
        else{
            textarea.setText(""+CB.getSelectedItem());
         textarea.setEnabled(false);
        }    
    }
}
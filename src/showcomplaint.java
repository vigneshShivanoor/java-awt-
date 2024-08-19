import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
// import javax.swing.plaf.ColorUIResource;
public class showcomplaint extends JDialog implements ActionListener{
    Connection c;
    Statement s;
    JLabel label;
    JTextField t;
    JButton closeB;
    JTable table;
    JScrollPane sp;
    showcomplaint(Connection c,Statement s,int no_of_complaints,String roll_no)
    {
        setTitle("MyComplaints");
        String arr[][]=new String[50][3];
        try{
            int i=0,j;
            ResultSet re=s.executeQuery("Select sd_no,complaints,Status from complaintable where roll_no='"+roll_no+"';");
            while(re.next())
            {
                for(j=0;j<3;j++)
                arr[i][j]=re.getString(j+1);
                i++;
            }
        }catch(Exception e)
        {}
        System.out.println("inserted");
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            System.out.print(arr[i][j]);
            System.out.println();
        }
        JPanel top=new JPanel();
        top.setBackground(Color.black);
        this.c=c;
        this.s=s;
        label=new JLabel("My Complains : ");
        label.setForeground(Color.green);
        t=new JTextField(10);
        t.setForeground(Color.green);
        t.setBackground(Color.black);
        t.setText(""+no_of_complaints);
        t.setEditable(false);
        top.add(label);
        top.add(t);
        closeB=new JButton("Close");
        closeB.setBackground(Color.black);
        closeB.setForeground(Color.green);
        closeB.addActionListener(this);
        String column[]={"Complaint Number","Complaint Details","Status"};
        table=new JTable(arr,column);
        table.setBackground(Color.black);
        table.setOpaque(false);
        table.setRowHeight(30);
        table.setForeground(Color.green);
        table.setEnabled(false);
        // table. getColumnModel(). getColumn(2).setPreferredWidth(300);
        sp=new JScrollPane(table);
        sp.setBackground(Color.black);
        add(sp);
        add(closeB,"South");
        add(top,"North");
        setVisible(true);
        setSize(500,500);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        dispose();
    }
    
}
import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class StudentRecord

{  
    private Connection con;
    private Statement st;
    private ResultSet rs;  
    private String sql;
    public StudentRecord()
    {
      try
      {  
       Class.forName("com.mysql.jdbc.Driver"); 
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
       st=con.createStatement(); 
       sql = "CREATE TABLE IF NOT EXISTS student " +
                   "(rollno INTEGER not NULL, " +
                   " name VARCHAR(50), " + 
                   " age INTEGER, " + 
                  " mark INTEGER, " + 
                 " PRIMARY KEY ( rollno ))";
       st.executeUpdate(sql);
      }
      catch(Exception e)
      {
        //System.out.println("Error :"+e);
      }
    }
    public void insert()
    {
        try
        { 
         Scanner s=new Scanner(System.in); 
     
     System.out.println("Enter rollno:");  
      int r=s.nextInt();
      System.out.println("Enter name of student:");
        Scanner o=new Scanner(System.in);
      String n=o.nextLine();
      System.out.println("Enter age:");
      int a=s.nextInt();
      System.out.println("Enter mark:");
      int m=s.nextInt();
         sql = "INSERT INTO student " +
                   "VALUES ("+r+", '"+n+"', "+a+", "+m+")";
      st.executeUpdate(sql);
      System.out.println("Inserted records into the table...");
        }
        catch(Exception e)
        {
            //System.out.println("Error :"+e);
        }
    }
    public void delete()
    {
        try
        {
            sql = "TRUNCATE student";
            st.executeUpdate(sql); 
            sql = "DELETE FROM student";
            st.executeUpdate(sql);
            System.out.println("Deleted all details from the table");
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }
    }
    public void getdata()
    {
      try
      {
        int d=0;
        String query="select * from student";
        rs=st.executeQuery(query);
        System.out.println("Records of student table");
        while(rs.next())
        {
            d++;
            String rollno=rs.getString("rollno:");
            String name=rs.getString("name:");
            String age=rs.getString("age:");
            String marks=rs.getString("mark:");
            System.out.println("Rollno : "+rollno+" Name : "+name+" Age : "+age+" Marks : "+marks);
        }
        if(d==0)
        {
            System.out.println("TABLE IS EMPTY");
        }
      }
      catch(Exception e)
      {
        System.out.println("Error :"+e);
      }
     }
     public void searchdata()
     {
        try
        {
            int f=0;
            System.out.println("Enter the rollno to search:");
            Scanner in=new Scanner(System.in);
            int r=in.nextInt();
            String query="select * from student where rollno="+r;
            rs=st.executeQuery(query);
            while(rs.next())
            {
             f++;
            String rollno=rs.getString("Rollno:");
            String name=rs.getString("Name:");
            String age=rs.getString("Age:");
            String marks=rs.getString("Mark:");
            System.out.println("Rollno : "+rollno+" Name : "+name+" Age : "+age+" Marks : "+marks);
            }
            if(f==0)
            {
                System.out.println("ROLLNO NOT FOUND IN TABLE student");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }
     }
   }
class BtnPgm extends JFrame implements ActionListener
{
    private JButton button1,button2,button3,button4;
    public BtnPgm()
    {   
        super("STUDENT RECORD");
        setLayout(new FlowLayout());
        button1=new JButton("INSERT STUDENT DETAILS");
        button2=new JButton("SEARCH BY ROLL NO");
        button3=new JButton("VIEW DETAILS");
        button4=new JButton("DELETE DETAILS");
        button1.setBackground(Color.YELLOW);
        button1.setContentAreaFilled(false);
        button1.setOpaque(true);
        button2.setBackground(Color.GREEN);
        button2.setContentAreaFilled(false);
        button2.setOpaque(true);
        button3.setBackground(Color.GREEN);
        button3.setContentAreaFilled(false);
        button3.setOpaque(true);
        button4.setBackground(Color.GREEN);
        button4.setContentAreaFilled(false);
        button4.setOpaque(true);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        setSize(300,300);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button1)
        {
          StudentRecord connect=new StudentRecord();
          connect.insert();
        }
          if(e.getSource()==button2)
        {
          StudentRecord connect=new StudentRecord();
          connect.searchdata();
        }
        if(e.getSource()==button3)
        {
          StudentRecord connect=new StudentRecord();
          connect.getdata();
        }
         if(e.getSource()==button4)
        {
          StudentRecord connect=new StudentRecord();
          connect.delete();
        }
    }
}
class Srecord
{ 
    public static void main (String args[]) 
    { 
      BtnPgm b=new BtnPgm();
    }
}  

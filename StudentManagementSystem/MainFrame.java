import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;
JLabel label1;

MainFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());

Icon Add = new ImageIcon("add.png");
btnAdd=new JButton("Add");
btnAdd.setIcon(Add);

Icon view = new ImageIcon("view.png");
btnView=new JButton("View");
btnView.setIcon(view);

Icon up = new ImageIcon("update.png");
btnUpdate=new JButton("Update");
btnUpdate.setIcon(up);

Icon delete = new ImageIcon("delete.png");
btnDelete=new JButton("Delete");
btnDelete.setIcon(delete);

Icon image = new ImageIcon(getClass().getResource("Sms.png"));
label1=new JLabel(image);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
c.add(label1);

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}
});


btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
UpdateFrame a=new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a=new DeleteFrame();
dispose();
}
});

setTitle("Student Management System");
setSize(400,300);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


public static void main(String args[])
{
MainFrame m=new MainFrame();
}
}

class DbHandler{
public String check(String rno1)
{
String f="f";
if(rno1.equals(""))
{
JOptionPane.showMessageDialog(null, "Roll Number should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
f = "fa";
}else
if(rno1.matches("[0-9]+")){
	if(Integer.parseInt(rno1)==0)
	{JOptionPane.showMessageDialog(null, "Roll Number should start from 1", "Error", JOptionPane.ERROR_MESSAGE);
	f="fa";
	return f;
	}
	else
	return f;	
}else 
f="gh";
return f;
}

public int check1(String name){
int f1=1;
	if(name.equals("")){
	JOptionPane.showMessageDialog(null, "Name should not be empty", "Error", JOptionPane.ERROR_MESSAGE);
	f1=3;
return f1;
	}
	 else
	if(!(name.matches("[a-zA-Z]+"))){
	return f1;}
else
f1=2;
return f1;

}


public void addStudent(int rno,String name)
{
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="insert into student values(?,?)";

PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,rno);
pst.setString(2,name);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"record inserted");
con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(null,"Roll Number already exists", "Error", JOptionPane.ERROR_MESSAGE);
}
}


public String viewStudent()
{
StringBuffer sb=new StringBuffer();
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="select * from student";
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(sql);

while(rs.next()){
int rno=rs.getInt(1);
String name=rs.getString(2);
sb.append("Rno : "+rno+"  Name : "+name+"\n");
}
rs.close();
con.close();
}
catch(SQLException e)
{
}
return sb.toString();
}


public void UpdateStudent(String name,int rno)
{
try{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="update student set name=? where rno=?";
PreparedStatement pstn=con.prepareStatement(sql);
pstn.setString(1,name);
pstn.setInt(2,rno);
int r=pstn.executeUpdate();
			if(r==0){
			JOptionPane.showMessageDialog(null,"Roll number not in List");
			}
			else {
			JOptionPane.showMessageDialog(null,r+"record Updated");
			}
con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(),"ii"+e);
}
}



public void DeleteStudent(int rno)
{
		try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
		String sql="delete from student where rno=?";
		PreparedStatement pstn=con.prepareStatement(sql);
		pstn.setInt(1,rno);
		int r=pstn.executeUpdate();
			if(r==0){
			JOptionPane.showMessageDialog(null,"Roll number not in List");
			}
			else {
			JOptionPane.showMessageDialog(null,r+"record Deleted");
			}
		pstn.close();
		con.close();
		}
		catch(SQLException e){
		JOptionPane.showMessageDialog(new JDialog(),"iiiiiiiiiiiii"+e);
		}
	
}
}
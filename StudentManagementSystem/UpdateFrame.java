import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpdateFrame extends JFrame{
Container c;
JLabel lblRno,lblName;
JTextField txtRno,txtName;
JButton btnSave,btnBack;
JPanel p1,p2,p3;
JLabel label1;

UpdateFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

p1=new JPanel();
lblRno=new JLabel("Rno");
txtRno=new JTextField(4);
lblName=new JLabel("Name");
txtName=new JTextField(10);

p1.add(lblRno);
p1.add(txtRno);
p1.add(lblName);
p1.add(txtName);
c.add(p1);

p2=new JPanel();

Icon save = new ImageIcon("update1.png");
btnSave=new JButton("Update");
btnSave.setIcon(save);

Icon back = new ImageIcon("back.png");
btnBack=new JButton("Back");
btnBack.setIcon(back);

p2.add(btnSave);
p2.add(btnBack);
c.add(p2);


p3=new JPanel();
Icon image = new ImageIcon(getClass().getResource("update2.png"));
label1=new JLabel(image);

p3.add(label1);
c.add(p3);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
String rno1=txtRno.getText();
String name=txtName.getText();
DbHandler db=new DbHandler();
String r=db.check(rno1);
switch(r){
case "f" : {int rno=Integer.parseInt(rno1);
	int rf=db.check1(name);
	switch(rf){
	case 2:db.UpdateStudent(name,rno);
		txtRno.setText("");
		txtRno.requestFocus();
		txtName.setText("");
		break;
	case 1 : JOptionPane.showMessageDialog(null, "Name should only contain alphabets", "Error", JOptionPane.ERROR_MESSAGE);
		txtName.setText("");
		txtName.requestFocus();
		break;
	default : txtName.setText("");
		txtName.requestFocus();
		}}
	break;
case "gh" :{ 
	JOptionPane.showMessageDialog(null, "Roll Number should be positive Integer", "Error", JOptionPane.ERROR_MESSAGE);
	txtRno.setText("");
	txtRno.requestFocus();
	}
default : txtRno.setText("");
	txtRno.requestFocus();
	}
}});
btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("Update Student");
setSize(350,200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
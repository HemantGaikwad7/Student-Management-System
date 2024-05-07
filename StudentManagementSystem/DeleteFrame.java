import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame{
Container c;
JLabel lblRno;
JTextField txtRno;
JButton btnSave,btnBack;
JPanel p1,p2,p3;
JLabel label1;

DeleteFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

p1=new JPanel();
lblRno=new JLabel("Rno");
txtRno=new JTextField(4);


p1.add(lblRno);
p1.add(txtRno);
c.add(p1);

p2=new JPanel();

Icon save = new ImageIcon("delete1.png");
btnSave=new JButton("Delete");
btnSave.setIcon(save);

Icon back = new ImageIcon("back.png");
btnBack=new JButton("Back");
btnBack.setIcon(back);

p2.add(btnSave);
p2.add(btnBack);
c.add(p2);

Icon image = new ImageIcon(getClass().getResource("delete2.png"));
label1=new JLabel(image);


p3=new JPanel();
p3.add(label1);
c.add(p3);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
String rno=txtRno.getText();
DbHandler db=new DbHandler();
String z=db.check(rno);
switch(z){
case "f":{db.DeleteStudent(Integer.parseInt(rno));
txtRno.setText("");
txtRno.requestFocus();
}break;
case "gh" :{
	JOptionPane.showMessageDialog(null, "Roll Number should be positive Integer", "Error", JOptionPane.ERROR_MESSAGE);
	txtRno.setText("");
	txtRno.requestFocus();
	}
default : txtRno.setText("");
	txtRno.requestFocus();
	}}
});
btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("Delete Student");
setSize(350,200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
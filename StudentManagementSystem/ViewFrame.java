import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame{
Container c;
TextArea ta;
JButton btnBack;
JPanel p1,p2;

ViewFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

p1=new JPanel();
ta=new TextArea(5,40);

p1.add(ta);
c.add(p1);

p2=new JPanel();
Icon back = new ImageIcon("back.png");
btnBack=new JButton("Back");
btnBack.setIcon(back);

p2.add(btnBack);
c.add(p2);
DbHandler db=new DbHandler();
String data=db.viewStudent();
ta.setText(data);


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("View Student");
setSize(350,200);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}
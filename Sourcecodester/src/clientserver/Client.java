package clientserver;
//Client.java
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Client extends JFrame
{
 JTextField txtenter;
 JTextArea txtadisplay;
 ObjectOutputStream output;
 ObjectInputStream input;
 String message="";

 public Client()
 {
  super("CLIENT");
  Container c=getContentPane();

  txtenter=new JTextField();
  txtenter.setEnabled(false);
  txtenter.addActionListener(
  new ActionListener(){
  public void actionPerformed(ActionEvent e)
  {
   sendData(e.getActionCommand());
  }
  });
  c.add(txtenter,BorderLayout.SOUTH);

  txtadisplay=new JTextArea();
  txtadisplay.setEditable(false);
  c.add(new JScrollPane(txtadisplay),BorderLayout.CENTER);

  setSize(300,150);
  show();
 }
 public void runClient()
 {
  Socket s;
  try
  {
    txtadisplay.setText("Attempting Connection...");

     //establishing connection
    s=new Socket("localhost",5000);
    txtadisplay.append("Connectedto:"+s.getInetAddress().getHostName());

    output=new ObjectOutputStream(s.getOutputStream());
    output.flush();
    input=new ObjectInputStream(s.getInputStream());

    txtenter.setEnabled(true);

     //processing connection
    do
    {
     message=(String)input.readObject();
     txtadisplay.append(""+message);txtadisplay.setCaretPosition(txtadisplay.getText().length());
    }while(!message.equals("SERVER>>>TERMINATE"));
    txtadisplay.append("Closing Connection...");
    input.close();
    output.close();
    s.close();
 }
 catch(Exception e)
 {}
 }
 public void sendData(String s)
 {
  try
   {
    message=s;
    output.writeObject("CLIENT>>>"+s);
    output.flush();

    txtadisplay.append("CLIENT>>>"+s);
   }
  catch(Exception e)
  {

  }
 }
 public static void main(String args[])
 {
  Client cli=new Client();

  cli.addWindowListener(
  new WindowAdapter(){
  public void windowClosing(WindowEvent e)
  {
   System.exit(0);
  }
  }
  );
  cli.runClient();

 }
}
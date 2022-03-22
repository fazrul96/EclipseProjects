package clientserver;
//Server.java
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Server extends JFrame
{
	private JTextField txtenter;
	JTextArea txtadisplay;
	ObjectOutputStream output;
	ObjectInputStream input;
	public Server()
 {
  super("SERVER");
  Container c=getContentPane();

  txtenter=new JTextField();
  txtenter.setEnabled(false);
  txtenter.addActionListener(
  new ActionListener(){
   public void actionPerformed(ActionEvent e)
   {
     sendData(e.getActionCommand());
   }
  }
  );
  c.add(txtenter,BorderLayout.SOUTH);

  txtadisplay=new JTextArea();
  txtadisplay.setEditable(false);
  c.add(new JScrollPane(txtadisplay),BorderLayout.CENTER);

  setSize(300,150);
  show();
 }
 public void runServer()
 {
  ServerSocket ss;
  Socket s;
  int counter = 1;

  try
  {
    //create a seversocket
   ss=new ServerSocket(5000,100);

   while(true)
   {

     //wait for the connection
    txtadisplay.setText("Waiting for the Connection...");

     //establishing connection
    s=ss.accept();
    txtadisplay.append("Conection "+counter+"receivedfrom:"+s.getInetAddress().getHostName());

     //getting input/output
    output=new ObjectOutputStream(s.getOutputStream());
    output.flush();

    input=new ObjectInputStream(s.getInputStream());

     //processing connection
    String message="Server>>>Conection Sucessfull...";
    output.writeObject(message);
    output.flush();
    txtenter.setEnabled(true);

    do
    {
     message=(String) input.readObject();
     txtadisplay.append(""+message);txtadisplay.setCaretPosition(txtadisplay.getText().length());
    }while(!message.equals("CLIENT>>>TERMINATE"));

    txtadisplay.append("User Terminated Connection...");
    output.close();
    input.close();
    s.close();
    ++counter;
   }
  }
  catch(Exception e)
  {

  }
 }
 public void sendData(String s)
 {
  try
  {
   output.writeObject("SERVER>>>"+s);
   txtadisplay.append("SERVER>>>"+s);
  }
  catch(Exception e)
  {

  }
 }
 public static void main(String args[])
 {
  Server ser=new Server();

  ser.addWindowListener(
  new WindowAdapter(){
  public void WindowClosing(WindowEvent e)
  {
   System.exit(0);
  }
  }
  );
  ser.runServer();
 }

}
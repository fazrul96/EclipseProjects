 import javax.swing.*;
 class  TextAnimation extends JFrame{
	private static final long serialVersionUID = 1L;
	JLabel lblText= new JLabel("<html><p style='color: #ff00ff; font-size:20pt'>Happy New Year</p></html>");;
    TextAnimation(){
       //setTitle("Text Animation");
       //setLayout(null);
       
       lblText.setSize(200,50);
     setSize(800,500);
     //setResizable(false);
     //setDefaultCloseOperation(EXIT_ON_CLOSE);
     add(lblText);

     setVisible(true);
     doAnimation();
 }
   public void doAnimation(){
          int x=1;
          int y=5;
          try{
              while(x<800){
                     lblText.setLocation(x,y);
                    Thread.sleep(200);
                    x+=20;
                    y=20;
                    if(x>800) x=1;
                    if(y>500) y=5;
                    } 
           }catch(InterruptedException ie){System.out.println("Interrupted...");}
    }
}
 public class Animation{
 public static void main(String args[]){
       new TextAnimation();
 }
}
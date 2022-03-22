import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;

public class See {
    public static void main( String [] args ){
        JFrame frame = new JFrame();
        frame.setBackground( Color.orange );


        frame.add( new JPanel(){{
                        add( new JLabel("Center"));
                        setBackground(new Color(0,0,0,64));
                    }} , BorderLayout.CENTER );
        frame.add( new JLabel("North"), BorderLayout.NORTH);
        frame.add( new JLabel("South"), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible( true );
    }
}
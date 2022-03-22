package TowerDefense;
import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String title = "TowerBacon Alpha";
	public static Dimension size = new Dimension(700,550);

	public Frame(){
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	public void init(){
		setLayout(new GridLayout(1,1,0,0));
		Screen screen = new Screen(this);
		add(screen);
		setVisible(true);
	}
	public static void main(String args[]){
		Frame frame = new Frame();
	}
}

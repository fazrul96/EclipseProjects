import java.io.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class MusicLoop {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
			File in = new File("looperman-l-0159051-0101677-minor2go-piano-quality-female.wav"); // file type wav je boleh 
			AudioInputStream audio = AudioSystem.getAudioInputStream(in);
			Clip play = AudioSystem.getClip();
			
			play.open(audio);
			play.loop(Clip.LOOP_CONTINUOUSLY);
			
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					
					/*---------------- GUI --------------------*/
					
					JFrame frame = new JFrame("TESTTTTTT");
					frame.setSize(800,500);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			});
		
	}

}

import javax.swing.*;
import sun.audio.*;
import java.io.*;

public class MusicLong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*---------------- GUI --------------------*/
		JFrame frame = new JFrame();
		frame.setSize(200,200);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		
			//Masukkan music
			music();
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}


		public static void music() {       
	       AudioPlayer MGP = AudioPlayer.player;
	       AudioStream BGM;
	       ContinuousAudioDataStream loop = null;
	       
		       try
		       {
		           InputStream test = new FileInputStream("i-dont-think-so-1.wav");
		           BGM = new AudioStream(test);         
		           AudioPlayer.player.start(BGM);
		       }
		       catch(FileNotFoundException e){
		           System.out.print(e.toString());
		       }
		       catch(IOException error)
		       {
		           System.out.print(error.toString());
		       }
		       MGP.start(loop);
		   }
	}

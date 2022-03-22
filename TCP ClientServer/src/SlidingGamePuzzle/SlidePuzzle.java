package SlidingGamePuzzle;

import javax.swing.JFrame;
public class SlidePuzzle {
@SuppressWarnings("deprecation")
public static void main(String args[]){
	JFrame window = new JFrame("My Awesome Puzzle :)");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setContentPane(new SlidePuzzleGUI());
	window.pack();
	window.show();
	window.setResizable(false);
}
}

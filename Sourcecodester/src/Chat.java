
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Font;

public class Chat extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField input = new JTextField();
	JTextField dialog = new JTextField();
	JLabel picture = new JLabel();

	String[][] GUIBot={
			//standard greetings
			{"hi","hello","hola","ola","howdy","hi there"},
			{"Hi","Hello","Hey","Annyeong","Vanikam","Yo"},
			//question greetings
			{"how are you","how r you","are u ok"},
			{"good","doing well","i'm fine"},
			{"what is your name","your name","name"},
			{"my name is chatbot","u can call me chatbot","why u want to know my name?"},
			//yes
			{"yes"},
			{"no","NO","NO!!!!!!!"},
			//default
			{"shut up","you're bad","noob","stop talking",
			"(michael is unavailable, due to LOL)"}
		};
		
		String[][] verbs={
			{"is","'re"},	{"was","'re"}, 	{"think","think"}, {"s","'re"},
			{"'re",",'re"}
		};
		
		
		public static void main(String[] args){

			new Chat();
	
		}
		public Chat(){
		super();
		setResizable(false);
		getContentPane().setEnabled(false);
		getContentPane().setBackground(UIManager.getColor("textHighlight"));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/greda/TCP Client Server/bin/img/logo.png"));
		picture = new JLabel(createImageIcon("img/1.jpg"));
		getContentPane().add(picture, BorderLayout.CENTER);
		setTitle("Client Server Bot");

		setSize(1000,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		input.setBounds(46, 74, 376, 57);
		getContentPane().add(input);
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setColumns(20);
		dialog.setBounds(547, 74, 427, 57);
		getContentPane().add(dialog);
		dialog.setHorizontalAlignment(SwingConstants.CENTER);
		dialog.setColumns(20);
		
		
		JLabel lblNewLabel = new JLabel("Client :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLabelFor(input);
		lblNewLabel.setBounds(46, 40, 110, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblServer.setForeground(UIManager.getColor("text"));
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setLabelFor(dialog);
		lblServer.setBounds(547, 49, 143, 14);
		getContentPane().add(lblServer);
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setBounds(10, 0, 984, 594);
		
		getContentPane().add(picture);
		input.addKeyListener(this);
		setVisible(true);
		}
		protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = Chat.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				input.setEditable(false);
				//===== grab quote =======
				String quote=input.getText();
				input.setText("");
				dialog.setText("");
				quote.trim();
				while(
						quote.charAt(quote.length()-1)=='!' ||
						quote.charAt(quote.length()-1)=='.' ||
						quote.charAt(quote.length()-1)=='?'
				){
					quote=quote.substring(0,quote.length()-1);
				}
				quote.trim();
				byte response=0;
				/*
				 0:We're searching through GUIBot[][] for matches
				 1:We didn't find anything in GUIBot[][]
				 2:We did find something in GUIBot[][]
				 */
				//===== check for matches ===
				int j=0;//Which Group we're checking
				while(response==0){
					if(inArray(quote.toLowerCase(),GUIBot[j*2])){
						response=2;
						int r=(int)Math.floor(Math.random()*GUIBot[(j*2)+1].length);
						addText(GUIBot[(j*2)+1][r]);
					}
					j++;
					if((j*2)==GUIBot.length-1 && response==0){
						response=1;
					}
				}
				//counter
				if(response==1){
					String quoteWords[]=quote.split("[ ']");
					int c=counter(quoteWords);
					if(c!=-1){
						String ext=quote.split(verbs[c][0])[1];
						addText(verbs[c][1]+ext);
						response=2;
					}
				}
				//default
				if(response==1){
						int r=(int)Math.floor(Math.random()*GUIBot[GUIBot.length-1].length);
						addText(GUIBot[GUIBot.length-1][r]);
					}
				}
		}
		private int counter(String[] quoteWords) {
			int verbID=-1;
			for(int i = 0;i<quoteWords.length;i++){
				for(int j = 0;j<verbs.length;j++){
					if(quoteWords[i].equals(verbs[j][0])){
						verbID=j;
					}
				}
			}
			return verbID;
		}
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				input.setEditable(true);
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {}
		public void addText(String str){
			dialog.setText(dialog.getText()+str);
		}
		public boolean inArray(String in,String[] str){
			boolean match=false;
			for(int i =0; i<str.length; i++){
				if(str[i].equals(in)){
					match=true;
				}
			}
			return match;
		}
		}


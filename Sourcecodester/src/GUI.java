import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;

public class GUI extends JFrame implements KeyListener{
	JPanel p = new JPanel();
	JTextArea dialog =  new JTextArea(17,50);
	JTextArea input =  new JTextArea(1,50);
	JScrollPane scroll = new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
		String[][] GUIBot={
				{"hi","hello","hola","annyeong","hey"},
				{"oi","yo","ya"},
				//greeting
				{"How are you","how r u","do u know me,"},
				{"good","doing well"},
				//yes
				{"yes"},
				{"no","no","NO!!!!!"},
				//default
				{"Shut up","Talk to my hand","noob","pabo","(aiman is unavailable, due to LOL)"}	
		};
	
		public static void main(String[] args){
			new GUI();
			
		}
		public GUI(){
			super();
			setSize(600,400);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
	
			dialog.setEditable(false);
			input.addKeyListener(this);//Those 3 listener
	
			p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		getContentPane().add(p);
	
		setVisible(true);
		}

		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				input.setEditable(false);
				//===== grab quote =======
				String quote=input.getText();
				input.setText("");
				addText("-->You:\t"+quote);
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
						addText("\n--> Aiman\t"+GUIBot[(j*2)+1][r]);
					}
					j++;
					if((j*2)==GUIBot.length-1 && response==0){
						response=1;
					}
				}//default
					if(response==1){
						int r=(int)Math.floor(Math.random()*GUIBot[GUIBot.length-1].length);
						addText("\n--> Aiman\t"+GUIBot[GUIBot.length-1][r]);
					}
					addText("\n");
				}
				//===== default ====
			
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


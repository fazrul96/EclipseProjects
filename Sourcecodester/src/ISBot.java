
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Font;

public class ISBot extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField client = new JTextField();
	JTextField server = new JTextField();
	JLabel picture = new JLabel();
		
		String init = "Learn Learn English. Are you ready to play now? <Y/N>";
	    
	    String[] riddle = {"What letter of the alphabet is an insect? ",
	                       "What letter is a part of the head?",
	                       "What letter is a drink?",
	                       "What letter is a body of water?",
	                       "What letter is a pronoun like 'you'?",
	                       "What letter is a vegetable?",
	                       "What letter is an exclamation?",
	                       "What letter is a European bird?",
	                       "What letter is looking for causes ?",
	                       "What comes once in a minute, twice in a moment but not once in a thousand years? ",
	                       "Which is the loudest vowel? ",
	                       "Which character is always happy ?",
	                       "What is the end of everything?"};
	                        
	    String correct ="Great Job ! You are Correct ! ";
	    
	    String[] actualAnswer = {"B (Bee) ",
	                       "I (Eye) ",
	                       "T (Tea) ",
	                       "C (Sea) ",
	                       "The letter 'I' ",
	                       "P (Pea) ",
	                       "O (oh !) ",
	                       "J (Jay) ",
	                       "Y (Why) ",
	                       "The letter 'm' ",
	                       "The letter 'I'. It is always in the midst of noIse. ",
	                       "Because 'U' is in the middle of 'fun' ",
	                       "The letter 'g' "};
	    
	    String[] answer = {"B", "I", "T", "C", "I", "P", "O", "J", "Y", "M", "I", "U", "G"};
	                            
	    String next = "Ready For Next Question? <Y/N>"; 
	    
	    String isOk = "Is ok. Give It A Try Next Time. Have a Nice Day =)"; 
	    String bye = "Thank For Your Guessing. Have a Nice Day =)";
	    String again = "Play Again? <Y/N>"; 
	    
	    int INITIAL = 0;
	    int STEP1 = 1;  //asking question part
	    int STEP2 = 2;  //confirm answer part
	    int question = 0;
	    int noOfQuestionAnswered = 0;
	    int state = INITIAL;
	
		public static void main(String[] args){
			new ISBot();
		}
		public ISBot(){
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
		client.setBounds(46, 74, 376, 57);
		getContentPane().add(client);
		client.setHorizontalAlignment(SwingConstants.CENTER);
		client.setColumns(20);
		server.setBounds(547, 74, 427, 57);
		getContentPane().add(server);
		server.setHorizontalAlignment(SwingConstants.CENTER);
		server.setColumns(20);
		
		
		JLabel lblNewLabel = new JLabel("Client :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLabelFor(client);
		lblNewLabel.setBounds(46, 40, 110, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblServer = new JLabel("Server:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblServer.setForeground(UIManager.getColor("text"));
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setLabelFor(server);
		lblServer.setBounds(547, 49, 143, 14);
		getContentPane().add(lblServer);
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setBounds(10, 0, 984, 594);
		
		getContentPane().add(picture);
		client.addKeyListener(this);
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
		@Override
		public void keyPressed(KeyEvent e){
		
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				//String input=server.getText();
				client.setEditable(false);
				client.setText("");
				server.setText("");
				
				}
		}
		@Override
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				client.setEditable(true);
				
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {}
		public void addText(String str){
			server.setText(server.getText()+str);
		}

		public String processAnswer(String input)
	    {
	        String output = null;       
	        
	        if (state == STEP1) //asking question part
	        {
	            if (input.equalsIgnoreCase("y"))
	            {
	                output = riddle [question];
	                state = STEP2;              
	            }
	            else
	            {   
	                if (noOfQuestionAnswered == 0)
	                    output = isOk;
	                else
	                    output = bye;
	            }       
	        }
	        
	        else if (state == STEP2) // confirm answer part
	        {   
	            if (input.equalsIgnoreCase(answer[question]))
	            {
	                output = correct +next;
	                state = STEP1;
	            }
	            else
	            {
	                output = "Oh no, you answer wrongly ! "+ "Correct Answer : " + actualAnswer[question]+ " . " + again;    
	                state = STEP1;
	            }
	            question = (question + 1) % 13;
	            noOfQuestionAnswered++;
	        }
	        
	        else if (state == INITIAL)
	        {       
	            output = init;
	            state = STEP1;
	            Random rand = new Random();

	            question = rand.nextInt(12);
	        }
	            
	        return output;      
	    }
}


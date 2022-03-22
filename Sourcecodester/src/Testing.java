import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Testing {
	
	private JFrame frame;
	private JTextField clienttext = new JTextField();
	private JTextField servertext = new JTextField();;
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing window = new Testing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Testing() {
		super();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 446, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel client = new JPanel();
		client.setBackground(Color.PINK);
		FlowLayout flowLayout = (FlowLayout) client.getLayout();
		flowLayout.setVgap(120);
		frame.getContentPane().add(client, BorderLayout.WEST);
		
		clienttext = new JTextField();
//		clienttext.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//		  		  Object obj = event.getSource();
//		  			if (obj == clienttext) {
//		  				servertext.setText("I'm just a server");
//		  			}
//
//		  	  }
//		});
		
		clienttext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					clienttext.setFocusable(true);
					String quote=clienttext.getToolTipText();
					clienttext.setToolTipText("");
					addText(quote);
					quote.trim();
				}
			}
			private void addText(String string) {
				servertext.setText(servertext.getText());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		clienttext.setHorizontalAlignment(SwingConstants.CENTER);
		client.add(clienttext);
		clienttext.setColumns(10);
		
		JPanel server = new JPanel();
		server.setBackground(Color.GREEN);
		FlowLayout flowLayout1 = (FlowLayout) server.getLayout();
		flowLayout1.setVgap(120);
		frame.getContentPane().add(server, BorderLayout.EAST);
		
		servertext = new JTextField();
		server.add(servertext);
		servertext.setColumns(10);
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

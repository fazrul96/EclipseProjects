import java.awt.BorderLayout;import java.awt.Color;import java.awt.Dimension;import java.awt.EventQueue;import java.awt.Font;import java.awt.Toolkit;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.KeyEvent;import java.awt.event.MouseAdapter;import java.awt.event.MouseEvent;import java.io.BufferedReader;import java.io.File;import java.io.IOException;import java.io.InputStreamReader;import java.io.PrintWriter;import java.net.Socket;import java.net.UnknownHostException;import java.util.Random;import javax.sound.sampled.AudioInputStream;import javax.sound.sampled.AudioSystem;import javax.sound.sampled.Clip;import javax.swing.AbstractAction;import javax.swing.BorderFactory;import javax.swing.BoxLayout;import javax.swing.ImageIcon;import javax.swing.JButton;import javax.swing.JCheckBox;import javax.swing.JDialog;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JMenu;import javax.swing.JMenuBar;import javax.swing.JMenuItem;import javax.swing.JPanel;import javax.swing.JPopupMenu;import javax.swing.JSeparator;import javax.swing.JTextArea;import javax.swing.JTextPane;import javax.swing.KeyStroke;import javax.swing.SwingConstants;import javax.swing.SwingUtilities;import javax.swing.Timer;import javax.swing.border.Border;import javax.swing.border.CompoundBorder;

public class TCPClient extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final boolean VK_ENTER = false;
	//GUI declaration component
	private static JTextArea client = new JTextArea();
	private static JTextArea server = new JTextArea();
	private static JTextArea serverhint = new JTextArea();
	private static JLabel hint = new JLabel(createImageIcon("img/hint.gif"));
	private static JLabel user = new JLabel(createImageIcon("img/user.png"));
	private static JLabel user2 = new JLabel(createImageIcon("img/hinter.png"));
	//GUI component
	JLabel clienttext = new JLabel(createImageIcon("img/client.gif"));
	JLabel servertext = new JLabel(createImageIcon("img/server.gif"));
	JLabel snow = new JLabel(createImageIcon("img/giphy.gif"));
	JLabel snow1 = new JLabel(createImageIcon("img/giphy.gif"));
	JLabel snow2= new JLabel(createImageIcon("img/giphy.gif"));
	JLabel snow3 = new JLabel(createImageIcon("img/giphy.gif"));
	JLabel icon = new JLabel(createImageIcon("img/2.png"));
	JLabel user1 = new JLabel(createImageIcon("img/user1.png"));
	JLabel picture = new JLabel(createImageIcon("img/background.png"));
	//GUI feature
	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private static String fromServer;
	private static String fromUser;
	private static String change = null;
	//private static String sound = null;
	private static File music = null;
	private static int fromHint=0;
	private static BufferedReader in = null;
	private static AudioInputStream audio = null;
	private static Clip play = null;
	
	public TCPClient() {
		EventQueue.invokeLater(() -> {createMenuBar();});
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setTitle("Client Server");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 583);
		setVisible(true);
		setLocationRelativeTo(null);
		
		snow.setBounds(0, 0, 200, 200);
		panel.add(snow);
		snow1.setBounds(200, 0, 200, 143);
		panel.add(snow1);
		snow2.setBounds(400, 0, 190, 167);
		panel.add(snow2);
		snow3.setBounds(600, 0, 207, 156);
		panel.add(snow3);
		
		client.setBackground(Color.WHITE);
		client.setForeground(Color.BLACK);
		client.setFont(new Font("Tahoma", Font.BOLD, 15));
		client.setBounds(210, 64, 182, 81);
		panel.add(client);
		client.setVisible(false);
		client.setColumns(10);
		client.setLineWrap(true);
		client.setWrapStyleWord(true);
		
		server.setEditable(false);
		server.setBackground(Color.WHITE);
		server.setForeground(Color.BLACK);
		server.setFont(new Font("Tahoma", Font.BOLD, 15));
		server.setBounds(576, 39, 272, 128);
		panel.add(server);
		server.setColumns(20);
		server.setLineWrap(true);
		
		server.setWrapStyleWord(true);
		serverhint.setTabSize(6);
		serverhint.setVisible(false);
		serverhint.setEnabled(false);
		serverhint.setEditable(false);
		serverhint.setBackground(Color.WHITE);
		serverhint.setForeground(Color.BLACK);
		serverhint.setFont(new Font("Arial", Font.BOLD, 15));
		serverhint.setBounds(102, 267, 165, 68);
		panel.add(serverhint);
		serverhint.setColumns(10);
		serverhint.setLineWrap(true);
		serverhint.setWrapStyleWord(true);
		
		clienttext.setFont(new Font("Tahoma", Font.BOLD, 20));
		clienttext.setBounds(210, 479, 160, 24);
		panel.add(clienttext);
		servertext.setForeground(Color.WHITE);
		servertext.setFont(new Font("Tahoma", Font.BOLD, 22));
		servertext.setBounds(576, 479, 160, 24);
		panel.add(servertext);
		
		hint.setVisible(false);
		hint.setBackground(Color.BLACK);
		hint.setForeground(Color.WHITE);
		hint.setFont(new Font("Tahoma", Font.BOLD, 20));
		hint.setBounds(102, 226, 74, 28);
		hint.setLabelFor(serverhint);
		
		panel.add(hint);
		user.setBounds(-150, -150, 895, 525);
		panel.add(user);
		user.setVisible(false);
		user1.setBounds(270, -135, 895, 525);
		panel.add(user1);
		user2.setBounds(-260, 50, 895, 525);
		panel.add(user2);
		user2.setVisible(false);
		//background
		
		picture.setBounds(-11, 0, 895, 525);
		panel.add(picture);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/greda/TCP Client Server/bin/img/Logo-UKM.png"));
	}
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TCPClient.class.getResource(path);
		if (imgURL != null) {return new ImageIcon(imgURL);}else{System.err.println("Couldn't find file: " + path);return null;}}

	public static void music()throws IOException,Exception {
		Random random = new Random();
		int song = random.nextInt(5);//random song
		if(song==0)
		 {music = new File("Acoustic Guitar - fmajor.wav");}
		else if(song==1)
		 {music = new File("Guitar Unlimited - My Passion.wav");}
		else if(song==2)
		 {music = new File("Guitar Quality Gold - Only You.wav");}
		else if(song==3)
		 {music = new File("Guitar Quality Gold - I love music.wav");}
		else
		 {music = new File("Piano Quality - Female.wav");} 
		 audio = AudioSystem.getAudioInputStream(music);
		 play = AudioSystem.getClip();
		 play.open(audio);
		 play.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public static void main(String[] args) throws IOException,Exception {
		//Introduction component
		System.out.println("Author : Fazrul \n\t Illi \n\t Azam");
		System.out.println("\n------------->\tREMINDER enter 'exit' to close the program\t<-------------");
		System.out.println("------------->\tREMINDER enter 'change' to switch category  <-------------");
		System.out.println("------------->\tREMINDER enter 'sound' to change song  <-------------");
		System.out.println("------------->\tREMINDER enter 'sound stop' to stop song  <-------------");
		System.out.println("------------->\tREMINDER enter 'mark' to see the result  <-------------\n");
		new TCPClient();{}
//		doAnimation();{}
//		display(1);
		music();{}
		// TCP component
		int port = 8081;
		String host = "localhost";
		Socket nisSocket = null;
		PrintWriter out = null;	
		String[] hint = { " Object ", " Hand ", " When there is light, _._._._ ", " Letter ", " 2 side ", " Ouch! ",
				"Yummy ", " Shhh!! Dont tell anyone", "Cross Finger ", "Red color", " Ingredient ", " 2 word", "W _ _ _ _ w","Sickness","12 Numbers","Games with board",
				"Blackout","Tick, tick, tick","Refreshing","W _ _ _ R","The earlist one","Dishes","Same species","1-10","S _ _ _ S","House","Print","Double Key"};
		String[] riddle = { "What has a face and two hands but no arms or legs?",
				"What has a thumb and four fingers but is not alive?",
				"Everyone has it and no one can lose it, what is it?",
				"What can travel around the world while staying in a corner?", "What has a head and a tail, but no body?",
				"What has an eye but can not see?",
				"There was a green house. Inside the green house there was a white house. Inside the white house there was a red house. Inside the red house there were lots of babies. What is it??",
				"If you have me, you want to share me. If you share me, you haven't got me. What am I?",
				"What gets broken without being held?", "Feed me and I live, yet give me a drink and I die.",
				"Take off my skin - I won't cry, but you will! What am I? ",
				"Imagine you are in a dark room. How do you get out? ",
				"What invention lets you look right through a wall? ",
				"What can you catch but not throw?","If you look at the number on my face you won't find thirteen anyplace.",
				"The eight of us go forth not back to protect our king from a foes attack.",
				"I’m tall when I’m young and I’m short when I’m old. What am I?","What has hands but can not clap?","What starts with the letter “t”, is filled with “t” and ends in “t”?",
				"What can run but can’t walk?","Beth’s mother has three daughters. One is called Lara, the other one is Sara. What is the name of the third daughter?"
				,"What’s full of holes but still holds water?","My name is Ruger, I live on a farm. There are four other dogs on the farm with me. Their names are Snowy, Flash, Speedy and Brownie. What do you think the fifth dog’s name is?",
				"I am an odd number. Take away one letter and I become even. What number am I?","What word looks the same backwards and upside down?",
				"What never asks questions but is often answered?","The more you take, the more you leave behind. What are they?","What two keys can’t open any door?"};
		try {nisSocket = new Socket(host, port);out = new PrintWriter(nisSocket.getOutputStream(), true);in = new BufferedReader(new InputStreamReader(nisSocket.getInputStream()));}catch (UnknownHostException e){System.out.println(e.toString() + " :Don't know about " + host);System.exit(1);}catch (IOException e){System.out.println(e.toString() + " :Couldn't get I/O for the connection to " + host);System.exit(1);}
		try {
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
//				timer(2);
				server.setText(fromServer);
//				timer(2);
				//hint part
				if (fromServer.equalsIgnoreCase("Is ok. Give It A Try Next Time. Have a Nice Day =)"))
					break;
				if (fromServer.equalsIgnoreCase("Thank For Your Guessing. Have a Nice Day =)"))
					break;
				if (fromServer.equalsIgnoreCase("Have a nice day friend, let's play again ^_^"))
					break;
				if (fromServer.equalsIgnoreCase(riddle[fromHint])){
					serverhint.setText(hint[fromHint]);
					fromHint++;
					display(4);
					timer(1);
				}
				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("\nClient: " + fromUser);
					out.println(fromUser);
					client.setText(fromUser);
					display(3);
					timer(2);
				}
				if (fromUser != change) {
					serverhint.setText("");
					//display(2);
					//timer(1);
				}
				if (fromUser.equalsIgnoreCase("sound")){
					play.close();
					audio.close();
					music();
					System.out.println("Sound: Song has been changed to " + music);
					serverhint.setText("Song has been changed to " + music);
					display(4);
					//timer(1);
				}
				if (fromUser.equalsIgnoreCase("sound stop")) {
					play.close();
					audio.close();
					System.out.println("Sound: Song has stop");
					serverhint.setText("Song has stop");
					//display(2);
					//timer(1);
				}
			}
		} catch (IOException e) {
			System.out.println(e.toString() + " Error!!, Try another words " + host);
		}
		out.flush();out.close();in.close();stdIn.close();
		// if (!nisSocket.isClosed())
		nisSocket.close();System.out.println("TCP session closed.");
	}
private static void display(int i){
	if (i==1){
	user.setVisible(false);
	client.setVisible(false);
	user2.setVisible(false);
	serverhint.setVisible(false);
	hint.setVisible(false);
	}
	if (i==2){
		user2.setVisible(true);
		serverhint.setVisible(true);
		hint.setVisible(true);
		}
	if (i==3){
		client.setVisible(true);
		user.setVisible(true);
		}
	if (i==4){
		user2.setVisible(true);
		serverhint.setVisible(true);
		hint.setVisible(true);
		}
}
private static void timer(int i){
	int delay = 10000; //milliseconds
	if (i==1){//hint invisible
		   ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user2.setVisible(false);
				serverhint.setVisible(false);
				hint.setVisible(false);
			}
		   };
		   new Timer(delay, taskPerformer).start();
		   }
	else if (i==2){//client invisible
		   ActionListener taskPerformer = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					user.setVisible(false);
					client.setVisible(false);
				}
			   };
			   new Timer(delay, taskPerformer).start();
			   }
	else if (i==3){//client invisible
		   ActionListener taskPerformer = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					user.setVisible(false);
					client.setVisible(false);
					 user2.setVisible(false);
						serverhint.setVisible(false);
						hint.setVisible(false);
				}
			   };
			   new Timer(delay, taskPerformer).start();
			   }
	else{
		   ActionListener taskPerformer = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					user.setVisible(true);
					client.setVisible(true);
					 user2.setVisible(true);
						serverhint.setVisible(true);
						hint.setVisible(true);
				}
			   };
			   new Timer(delay, taskPerformer).start();
			   }
	}
private void createMenuBar() {
	        JMenuBar menubar = new JMenuBar();
	        JPopupMenu pmenu = new JPopupMenu();
	        ImageIcon iconOpen = new ImageIcon("C:/Users/greda/TCP Client Server/bin/img/open.png");
	        ImageIcon iconSave = new ImageIcon("C:/Users/greda/TCP Client Server/bin/img/save.png");
	        ImageIcon iconExit = new ImageIcon("C:/Users/greda/TCP Client Server/bin/img/cancel.png");
	        ImageIcon iconSetting = new ImageIcon("C:/Users/greda/TCP Client Server/bin/img/settings.png");
	        
	        JMenu fileMenu = new JMenu("File");
	        fileMenu.setMnemonic(KeyEvent.VK_F);
	        JMenu helpMenu = new JMenu("Help");
	        helpMenu.setMnemonic(KeyEvent.VK_H);
	        JMenuItem helpcontent = new JMenuItem("Help Contents");
	        helpcontent.addActionListener((ActionEvent e) -> {
	        	SwingUtilities.invokeLater(new Runnable() {
	                public void run() {
	                    Tips ex = new Tips();
	                    ex.setVisible(true);
	                }
	            });
	        });
	        JMenuItem open = new JMenuItem(new MenuItemAction("Open", iconOpen,KeyEvent.VK_O));
	        open.addActionListener((ActionEvent e)-> {
	        	//OpenL();
	        });
	        JMenuItem save = new JMenuItem(new MenuItemAction("Save", iconSave,KeyEvent.VK_S));
	        JMenuItem setting =  new JMenuItem(new MenuItemAction("Settings", iconSetting,KeyEvent.VK_E));
	        JMenuItem max = new JMenuItem("Maximize");
	        max.addActionListener((ActionEvent e) -> {
	            if (getExtendedState() != JFrame.MAXIMIZED_BOTH) {
	                setExtendedState(JFrame.MAXIMIZED_BOTH);
	            }
	        });
	        JMenuItem quit = new JMenuItem("Quit");
	        quit.addActionListener((ActionEvent e) -> {
	            System.exit(0);
	        });
	        addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseReleased(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON3) {
	                    pmenu.show(e.getComponent(), e.getX(), e.getY());
	                }
	            }
	        });
	        JMenuItem exit = new JMenuItem("Exit", iconExit);
	        exit.setMnemonic(KeyEvent.VK_E);
	        exit.setToolTipText("Exit application");
	        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	                ActionEvent.CTRL_MASK));
	        exit.addActionListener((ActionEvent event) -> {
	            System.exit(0);
	        });
	        fileMenu.add(open);
	        fileMenu.add(save);
	        fileMenu.add(setting);
	        fileMenu.add(exit);
	        helpMenu.add(helpcontent);
	        menubar.add(fileMenu);
	        menubar.add(helpMenu);
	        pmenu.add(max);
	        pmenu.add(quit);
	        setJMenuBar(menubar);}
//private class OpenL implements ActionListener {
//@Override
//public void actionPerformed(ActionEvent e) {
	//JFileChooser c = new JFileChooser();
    // Demonstrate "Open" dialog:
   // int rVal = c.showOpenDialog(TCPClient.this);
    //if (rVal == JFileChooser.APPROVE_OPTION) {
      //filename.setText(c.getSelectedFile().getName());
     // dir.setText(c.getCurrentDirectory().toString());
    //}
  //  if (rVal == JFileChooser.CANCEL_OPTION) {
      //filename.setText("You pressed cancel");
      //dir.setText("");
  //  }}}
	private class Tips extends JDialog {
		private static final long serialVersionUID = 1L; 
		public Tips(){content();}
		public final void content(){
	        JPanel basic = new JPanel();
		        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		        getContentPane().add(basic);
	        JPanel topPanel = new JPanel();
	        	topPanel.setMaximumSize(new Dimension(450, 0));
	        	topPanel.setLayout(null);
	        	basic.add(topPanel);
	        JPanel textPanel = new JPanel();
	        	textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
	        JTextPane pane = new JTextPane();
	        	pane.setBounds(180, 170, 510, 239);
	        	pane.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        	pane.setBackground(Color.CYAN);
	        	pane.setContentType("text/html");
	        String text = "<p align='center'>This project divided into <b>2 Category</b> that is <b>Riddle & Chat Bot</b></p>" +
	            "<div align='center'>*The programmed are still under construction and might cause minor error.</div>" +
	            "<p align='center'>There are several guide that user can use :</p>"+
	            "<p> Type <b>'change'</b> to switch either <b>Riddle | Chat Bot</b></p>" +
	            "<p> Type <b>'sound'</b> to change <b>Background Music</b></p>" +
	            "<p> Type <b>'sound stop'</b> to stop <b>Background Music</b></p>" +
	            "<p> Type <b>'mark'</b> to see your <b>result</b></p>";
	        String text1 ="<p> Type <b>'exit'</b> to <b>close</b> the program</p>";
	        textPanel.setLayout(null);
	        	pane.setBorder(RainbowBorder());
	        	pane.setText(text);
	        	pane.setEditable(false);
	        textPanel.add(pane);
	        	basic.add(textPanel);                          
	        JCheckBox box = new JCheckBox("Show Tips at startup");
		        box.setBounds(170, 430, 125, 23);
		        textPanel.add(box);
		        box.setFont(new Font("Tahoma", Font.PLAIN, 11));
		        box.setMnemonic(KeyEvent.VK_S);
	        JLabel title = new JLabel(createImageIcon("img/welcome3.gif"));
		        title.setHorizontalAlignment(SwingConstants.CENTER);
		        title.setBounds(250, 43, 400, 100);
		        textPanel.add(title);
	        JButton ntip = new JButton("Next");
	        	ntip.setBounds(636, 430, 74, 23);
	        	textPanel.add(ntip);
	        	ntip.setMnemonic(KeyEvent.VK_N);
	        	ntip.addActionListener((ActionEvent event) -> {
	        	pane.setText(text1);});
	        JButton btip = new JButton("Back");
	        	btip.setBounds(540, 430, 94, 23);
	        	textPanel.add(btip);
	        	btip.setMnemonic(KeyEvent.VK_B);
	        	btip.addActionListener((ActionEvent event) -> {
	        	pane.setText(text);});
	        JLabel hint = new JLabel("Help Contents");
	        	hint.setForeground(Color.BLACK);
	        	hint.setBounds(10, 11, 168, 16);
	        	textPanel.add(hint);
	        	hint.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
	        	hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));                                                                      
	        JSeparator separator = new JSeparator();
	        	separator.setBounds(20, 28, 449, 2);
	        	textPanel.add(separator);
	        	separator.setForeground(Color.BLACK);
		        setTitle("Help Contents");
		        setSize(new Dimension(895, 525));
		        setResizable(false);
		        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        setLocationRelativeTo(null);
		        
	        setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/greda/TCP Client Server/bin/img/Logo.png"));
				icon.setBounds(0, 0, 895, 525);
				textPanel.add(icon);
	    }
	}
	private static Border RainbowBorder(){
			Border redBorder = BorderFactory.createLineBorder(Color.red);
	        Border orangeBorder = BorderFactory.createLineBorder(Color.orange);
	        Border yellowBorder = BorderFactory.createLineBorder(Color.yellow);
	        Border greenBorder = BorderFactory.createLineBorder(Color.green);
	        Border blueBorder = BorderFactory.createLineBorder(Color.blue);
	        Border magentaBorder = BorderFactory.createLineBorder(Color.magenta);
	        Border twoColorBorder = new CompoundBorder(magentaBorder, blueBorder);
	        Border threeColorBorder = new CompoundBorder(twoColorBorder,greenBorder);
	        Border fourColorBorder = new CompoundBorder(threeColorBorder,yellowBorder);
	        Border fiveColorBorder = new CompoundBorder(fourColorBorder,orangeBorder);
	        Border sixColorBorder = new CompoundBorder(fiveColorBorder, redBorder);
	        return sixColorBorder;
		}
	private class MenuItemAction extends AbstractAction {
			private static final long serialVersionUID = 1L;
			public MenuItemAction(String text, ImageIcon icon,
	                Integer mnemonic) {
	            super(text);
	            putValue(SMALL_ICON, icon);
	            putValue(MNEMONIC_KEY, mnemonic);
	        }
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println(e.getActionCommand());
	        }}
}
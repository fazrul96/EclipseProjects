import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
public class TipOfDayExample extends JDialog {
	private static final long serialVersionUID = 1L;
	public TipOfDayExample() {initUI();Animated();}
    public final void initUI() {
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
        pane.setBounds(20, 131, 449, 239);
        pane.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pane.setBackground(Color.CYAN);
       
        pane.setContentType("text/html");
        String text =" <p>This project divided into <b>2 Category</b> that is <b>Riddle & Chat Bot</b></p>" +
            " *The programmed are still under construction and might cause minor error.</p>" +
            "<p>There are several guide that user can use :</p>"+
            "<p>  Type <b>'change'</b> to switch either <b>Riddle | Chat Bot</b></p>" +
            "<p>  Type <b>'sound'</b> to switch <b>Background Music</b></p>" +
            "<p>  Type <b>'mark'</b> to see your <b>result</b></p>" +
            "<p>  Type <b>'exit'</b> to <b>close</b> the program</p>";
        textPanel.setLayout(null);
        pane.setBorder(RainbowBorder());
        JLabel picture1 = new JLabel(createImageIcon("img/giphy1.gif"));
        picture1.setBounds(20, 131, 449, 239);
        textPanel.add(picture1);
        
        pane.setText(text);
        pane.setEditable(false);
        textPanel.add(pane);
        basic.add(textPanel);
                                        
        JCheckBox box = new JCheckBox("Show Tips at startup");
        box.setBounds(20, 377, 125, 23);
        textPanel.add(box);
        box.setFont(new Font("Tahoma", Font.PLAIN, 11));
        box.setMnemonic(KeyEvent.VK_S);
        JButton ntip = new JButton("Next Tip");
        ntip.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {}});
        JLabel title = new JLabel(createImageIcon("img/welcome3.gif"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 43, 449, 77);
        textPanel.add(title);
        
        ntip.setBounds(299, 381, 94, 23);
        textPanel.add(ntip);
        ntip.setMnemonic(KeyEvent.VK_N);
        JButton close = new JButton("Close");
        close.setBounds(395, 381, 74, 23);
        textPanel.add(close);
        close.setMnemonic(KeyEvent.VK_C);
        JLabel hint = new JLabel("Help Contents");
        hint.setBounds(20, 11, 168, 16);
        textPanel.add(hint);
        hint.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
                                                                                
        JSeparator separator = new JSeparator();
        separator.setBounds(20, 28, 449, 2);
        textPanel.add(separator);
        separator.setForeground(Color.CYAN);
        JLabel picture =new JLabel(createImageIcon("img/2.jpg"));
        picture.setBounds(-161, -62, 854, 533);
        textPanel.add(picture);
        setTitle("Tip of the Day");
        setSize(new Dimension(489, 444));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TipOfDayExample.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	private static void Animated(){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 int width = 449;
                 int height = 239;
                 BufferedImage image = new BufferedImage(
                        width,height,BufferedImage.TYPE_INT_ARGB);
                 JLabel label = new JLabel(new ImageIcon(image));
                int pad = 5;
                 Shape rectangle = new Rectangle2D.Double(
                        (double)pad,(double)pad,
                        (double)(width-2*pad),
                        (double)(height-2*pad));
               ActionListener listener = new ActionListener() {
                    float dashPhase = 0f;
                    float dash[] = {5.0f,5.0f};
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        dashPhase += 9.0f;
                        BasicStroke dashedStroke = new BasicStroke(
                                1.5f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER,
                                1.5f, //miter limit
                                dash,
                                dashPhase
                                );
                        Graphics2D g = image.createGraphics();

                        g.setColor(Color.CYAN);
                        g.fillRect(0,0,width,height);
                        g.setColor(Color.BLACK);
                        g.setStroke(dashedStroke);
                        g.draw(rectangle);
                        g.dispose();
                        label.repaint();
                    }
                };
                Timer timer = new Timer(40, listener);
                timer.start();
                //JOptionPane.showMessageDialog(null, label);
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setSize(455, 245);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.add(label);
                
            }
        });
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
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TipOfDayExample ex = new TipOfDayExample();
                ex.setVisible(true);
            }
        });
    }
}
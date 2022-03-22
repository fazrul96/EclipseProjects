import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class AnimatedLabel {
    public static void main(String[] args) {new AnimatedLabel();}
    public AnimatedLabel() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.setSize(100, 100);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    public class TestPane extends JPanel {
		private static final long serialVersionUID = 1L;
		private String text = "Hello";
        private JLabel label;
        private int charIndex = 0;
        public TestPane() {
            setLayout(new GridBagLayout());
            label = new JLabel();
            add(label);
            Timer timer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String labelText = label.getText();
                    labelText += text.charAt(charIndex);
                    label.setText(labelText);
                    charIndex++;
                    if (charIndex >= text.length()) {
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            timer.stop();
        }
    }
}
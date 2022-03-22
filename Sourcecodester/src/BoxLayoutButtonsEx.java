import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BoxLayoutButtonsEx extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoxLayoutButtonsEx() {

        initUI();
    }

    private void initUI() {

        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        basic.add(Box.createVerticalGlue());

        JPanel bottom = new JPanel();
        bottom.setAlignmentX(1f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));

        JButton ok = new JButton("OK");
        JButton close = new JButton("Close");

        bottom.add(ok);
        bottom.add(Box.createRigidArea(new Dimension(5, 0)));
        bottom.add(close);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));

        basic.add(bottom);
        basic.add(Box.createRigidArea(new Dimension(0, 15)));

        setTitle("Two Buttons");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            BoxLayoutButtonsEx ex = new BoxLayoutButtonsEx();
            ex.setVisible(true);
        });
    }
}
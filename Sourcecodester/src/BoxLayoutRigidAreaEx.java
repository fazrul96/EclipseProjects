import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BoxLayoutRigidAreaEx extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoxLayoutRigidAreaEx() {

        initUI();
    }

    private void initUI() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        panel.add(new JButton("Button"));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(new JButton("Button"));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(new JButton("Button"));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(new JButton("Button"));

        add(panel);

        pack();

        setTitle("RigidArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            BoxLayoutRigidAreaEx ex = new BoxLayoutRigidAreaEx();
            ex.setVisible(true);
        });
    }
}
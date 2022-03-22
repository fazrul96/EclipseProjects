import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

public class FlowLayoutEx extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlowLayoutEx() {

        initUI();
    }

    private void initUI() {

        JPanel panel = new JPanel();

        JButton button = new JButton("button");
        panel.add(button);

        JTree tree = new JTree();
        panel.add(tree);

        JTextArea area = new JTextArea("text area");
        area.setPreferredSize(new Dimension(100, 100));

        panel.add(area);

        add(panel);

        pack();

        setTitle("FlowLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            FlowLayoutEx ex = new FlowLayoutEx();
            ex.setVisible(true);
        });
    }
}
import java.awt.BorderLayout;
/*from  w w w .  ja v a  2 s  . c o m*/
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Main {
  public static void main(String args[]) throws Exception{
    JFrame frame = new JFrame("TextPane Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    StyleContext context = new StyleContext();
    StyledDocument document = new DefaultStyledDocument(context);

    Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
    StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);

    document.insertString(document.getLength(), "java2s.com", style);

    JTextPane textPane = new JTextPane(document);
    textPane.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textPane);
    frame.add(scrollPane, BorderLayout.CENTER);

    frame.setSize(400, 450);
    frame.setVisible(true);
  }
}
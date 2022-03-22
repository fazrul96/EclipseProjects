import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class EnhancedFileTester extends JPanel {
  class AnOvalIcon implements Icon {
    Color color;

    public AnOvalIcon(Color c) {
      color = c;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
      g.setColor(color);
      g.fillOval(x, y, getIconWidth(), getIconHeight());
    }

    public int getIconWidth() {
      return 10;
    }

    public int getIconHeight() {
      return 15;
    }
  }

  public class IconView extends FileView {
    private HashMap hash = new HashMap();

    public IconView() {
      hash.put("htm", new AnOvalIcon(Color.RED));
      hash.put("html", new AnOvalIcon(Color.GREEN));
      hash.put("java", new AnOvalIcon(Color.BLUE));
    }

    public String getName(File f) {
      String s = f.getName();
      if (s.length() == 0) {
        s = f.getAbsolutePath();
      }
      return s;
    }

    public String getDescription(File f) {
      return f.getName();
    }

    public String getTypeDescription(File f) {
      return f.getAbsolutePath();
    }

    public Icon getIcon(File f) {
      String path = f.getAbsolutePath();
      int pos = path.lastIndexOf('.');
      if ((pos >= 0) && (pos < (path.length() - 1))) {
        String ext = path.substring(pos + 1).toLowerCase();
        return (Icon) hash.get(ext);
      }
      return null;
    }

    public Boolean isTraversable(File file) {
      return (new Boolean(file.isDirectory()));
    }
  }

  public class ExtensionFilter extends FileFilter {
    private String extensions[];

    private String description;

    public ExtensionFilter(String description, String extension) {
      this(description, new String[] { extension });
    }

    public ExtensionFilter(String description, String extensions[]) {
      this.description = description;
      this.extensions = (String[]) extensions.clone();
    }

    public boolean accept(File file) {
      if (file.isDirectory()) {
        return true;
      }
      int count = extensions.length;
      String path = file.getAbsolutePath();
      for (int i = 0; i < count; i++) {
        String ext = extensions[i];
        if (path.endsWith(ext)
            && (path.charAt(path.length() - ext.length()) == '.')) {
          return true;
        }
      }
      return false;
    }

    public String getDescription() {
      return (description == null ? extensions[0] : description);
    }
  }

  public EnhancedFileTester() {
    JButton jb = new JButton("Open File Viewer");
    add(jb);
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(".");
        FileFilter type1 = new ExtensionFilter("Java source", ".java");
        FileFilter type2 = new ExtensionFilter("Image files",
            new String[] { ".jpg", ".gif", "jpeg", "xbm" });
        FileFilter type3 = new ExtensionFilter("HTML files",
            new String[] { ".htm", ".html" });
        chooser.addChoosableFileFilter(type1);
        chooser.addChoosableFileFilter(type2);
        chooser.addChoosableFileFilter(type3);
        chooser.setFileFilter(type2); // Initial filter setting
        FileView view = new IconView();
        chooser.setFileView(view);
        int status = chooser.showOpenDialog(EnhancedFileTester.this);
        if (status == JFileChooser.APPROVE_OPTION) {
          File f = chooser.getSelectedFile();
          System.out.println(f);
        }
      }
    };
    jb.addActionListener(listener);
  }

  public static void main(String args[]) {
    JFrame f = new JFrame("Enhanced File Example");
    JPanel j = new EnhancedFileTester();
    f.getContentPane().add(j, BorderLayout.CENTER);
    f.setSize(300, 200);
    f.show();
  }
}
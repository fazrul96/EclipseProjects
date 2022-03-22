/*
Java Swing, 2nd Edition
By Marc Loy, Robert Eckstein, Dave Wood, James Elliott, Brian Cole
ISBN: 0-596-00408-7
Publisher: O'Reilly 
*/
// MyViewChooser.java
//An example that uses custom file views to show thumbnails of graphic files
//rather than the regular file icon. (see ThumbnailFileView.java)
//

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.metal.MetalIconFactory;

public class MyViewChooser extends JFrame {
  JFrame parent;

  public MyViewChooser() {
    super("File View Test Frame");
    setSize(350, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    parent = this;

    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    JButton openButton = new JButton("Open");
    final JLabel statusbar = new JLabel(
        "Output of your selection will go here");

    openButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser chooser = new JFileChooser();

        // Ok, set up our own file view for the chooser
        chooser.setFileView(new ThumbNailFileView(MyViewChooser.this));

        int option = chooser.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
          statusbar.setText("You chose "
              + chooser.getSelectedFile().getName());
        } else {
          statusbar.setText("You cancelled.");
        }
      }
    });

    c.add(openButton);
    c.add(statusbar);
  }

  public static void main(String args[]) {
    System.setProperty("swing.defaultlaf",
        "javax.swing.plaf.metal.MetalLookAndFeel");
    MyViewChooser vc = new MyViewChooser();
    vc.setVisible(true);
  }
}

//ThumbNailFileView.java
//A simple implementation of the FileView class that provides a 16x16 image of
//each GIF or JPG file for its icon. This could be SLOW for large images, as we
//simply load the real image and then scale it.
//

class ThumbNailFileView extends FileView {

  private Icon fileIcon = MetalIconFactory.getTreeLeafIcon();

  private Icon folderIcon = MetalIconFactory.getTreeFolderIcon();

  private Component observer;

  public ThumbNailFileView(Component c) {
    // We need a component around to create our icon's image
    observer = c;
  }

  public String getDescription(File f) {
    // We won't store individual descriptions, so just return the
    // type description.
    return getTypeDescription(f);
  }

  public Icon getIcon(File f) {
    // Is it a folder?
    if (f.isDirectory()) {
      return folderIcon;
    }

    // Ok, it's a file, so return a custom icon if it's an image file
    String name = f.getName().toLowerCase();
    if (name.endsWith(".jpg") || name.endsWith(".gif")) {
      return new Icon16(f.getAbsolutePath());
    }

    // Return the generic file icon if it's not
    return fileIcon;
  }

  public String getName(File f) {
    String name = f.getName();
    return name.equals("") ? f.getPath() : name;
  }

  public String getTypeDescription(File f) {
    String name = f.getName().toLowerCase();
    if (f.isDirectory()) {
      return "Folder";
    }
    if (name.endsWith(".jpg")) {
      return "JPEG Image";
    }
    if (name.endsWith(".gif")) {
      return "GIF Image";
    }
    return "Generic File";
  }

  public Boolean isTraversable(File f) {
    // We'll mark all directories as traversable
    return f.isDirectory() ? Boolean.TRUE : Boolean.FALSE;
  }

  public class Icon16 extends ImageIcon {
    public Icon16(String f) {
      super(f);
      Image i = observer.createImage(16, 16);
      i.getGraphics().drawImage(getImage(), 0, 0, 16, 16, observer);
      setImage(i);
    }

    public int getIconHeight() {
      return 16;
    }

    public int getIconWidth() {
      return 16;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
      g.drawImage(getImage(), x, y, c);
    }
  }
}
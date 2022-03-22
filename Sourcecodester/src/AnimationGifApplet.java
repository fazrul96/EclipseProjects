import java.applet.*;
import java.awt.*;
import java.net.*;

public class AnimationGifApplet extends Applet {
	public AnimationGifApplet() {
	}
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Image  []  img;
  int     index = 0;
  int     maxImg;
  MediaTracker tracker;

  public void init() {
    img = new Image[3];  // 2 images in animation
    maxImg = img.length - 1;
    tracker = new MediaTracker(this);
    try {
      // images loading
      img[0] = getImage(new URL(getDocumentBase(), "img/fazrul.gif"));
      img[1] = getImage(new URL(getDocumentBase(), "img/azam.gif"));
      img[2] = getImage(new URL(getDocumentBase(), "img/illi.gif"));
      tracker.addImage(img[0],0);
      tracker.addImage(img[1],1);
      tracker.addImage(img[2],2);
      tracker.waitForAll();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    AnimationThread at = new AnimationThread();
    at.delayedAnimation(this, 5000);
    at.start();
  }

  public void paint(Graphics g) {
    if (img[0] != null) {
       g.drawImage(img[index],0,0,this);
       index = (index < maxImg) ? index + 1 : 0;
    }
  }

  public void animate() {
    repaint();
  }

  class AnimationThread extends Thread {
    AnimationGifApplet animationApplet;
    int delay;

    public void delayedAnimation(AnimationGifApplet a, int delay) {
      this.animationApplet = a;
      this.delay = delay;
    }

    public void run() {
      while (true) {
        try {
        	sleep(delay);
          animationApplet.animate();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
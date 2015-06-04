package cn.javaplus.graphics;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class ImageFrame extends Frame {

	public class Closer extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent arg0) {
			dispose();
		}

	}

	private static final long serialVersionUID = -4218027416369469836L;
	private BufferedImage image;

	public ImageFrame(BufferedImage image) {
		this.image = image;
		int w = image.getWidth();
		int h = image.getHeight();
		
		setSize(w, h + 100);
		setVisible(true);
		addWindowListener(new Closer());
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 50, null);
	}


}

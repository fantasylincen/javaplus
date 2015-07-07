package cn.javaplus.stock.t;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.util.Util;

public class BufferedFrame extends Frame {

	public interface Paintable {

		void paint(Graphics g);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7781415860049508447L;

	Image image;

	private List<Paintable> children = Lists.newArrayList();

	public BufferedFrame() {

		setBounds();

		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();
	}

	private void setBounds() {
		setSize(800, 1000);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		double hw = screensize.getWidth() / 2;
		double hh = screensize.getHeight() / 2;

		setLocation((int) (hw - getWidth() / 2), (int) (hh - getHeight() / 2));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintChildren(g);
	}

	private void paintChildren(Graphics g) {
		for (Paintable p : children) {
			p.paint(g);
		}
	}

	public void add(Paintable g) {
		children.add(g);
	}

	@Override
	public void update(Graphics g) {
		if (image == null)
			image = createImage(getWidth(), getHeight());
		Graphics gi = image.getGraphics();
		if (gi != null)
			paint(gi);
		else
			paint(g);

		gi.dispose();
		g.drawImage(image, 0, 0, null);
	}

	class PaintThread extends Thread {

		public void run() {
			while (true) {
				Util.Thread.sleep(30);
				repaint();
			}
		}
	}
}

package uk.co.ramyun.mousedata.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uk.co.ramyun.mousedata.observer.Observer;

public class Ui extends JFrame implements Observer, LocalImage {

	/**
	 * @author © Michael 24 Jan 2018
	 * @file Ui.java
	 */

	private static final long serialVersionUID = 1L;

	private final JLabel view;
	private BufferedImage surface;

	private Optional<Point> previous = Optional.empty();

	public Ui() {

		try {
			surface = ImageIO.read(getStream("/map/OSRS_Blank.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (surface == null) {
			// Stop
		}

		view = new JLabel(new ImageIcon(surface));

		setBounds(500, 500, 790, 550);
		setContentPane(view);
	}

	private void drawNode(int x, int y, boolean connect) {
		Graphics g = surface.getGraphics();

		g.setColor(Color.CYAN);
		g.drawLine(x - 1, y, x + 1, y);
		g.drawLine(x, y + 1, x, y + 1);

		if (connect) {
			g.setColor(Color.CYAN.darker());
			previous.ifPresent(p -> g.drawLine(x, y, p.x, p.y));
		}

		g.dispose();
		view.repaint();
	}

	@Override
	public void pointClicked(Point p) {
		drawNode(p.x, p.y, true);
		previous = Optional.of(p);
	}

}

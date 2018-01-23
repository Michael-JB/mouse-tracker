package uk.co.ramyun.mousedata.ui;

import java.awt.Color;
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
	private final boolean connectNodes, cross;
	private BufferedImage surface;

	private Optional<Point> previous = Optional.empty();

	public Ui(boolean osrsView, boolean connectNodes, boolean cross) {
		this.connectNodes = connectNodes;
		this.cross = cross;

		surface = new BufferedImage(765, 502, BufferedImage.TYPE_INT_RGB);

		if (osrsView) {
			try {
				surface = ImageIO.read(getStream("/map/OSRS_Blank.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		view = new JLabel(new ImageIcon(surface));

		setBounds(500, 500, 790, 550);
		setContentPane(view);
	}

	private void drawNode(int x, int y) {
		Graphics g = surface.getGraphics();

		g.setColor(Color.CYAN);
		if (cross) {
			g.drawLine(x - 1, y, x + 1, y);
			g.drawLine(x, y + 1, x, y + 1);
		} else g.drawLine(x, y, x, y);

		if (connectNodes) {
			g.setColor(Color.CYAN.darker());
			previous.ifPresent(p -> g.drawLine(x, y, p.x, p.y));
		}

		g.dispose();
		view.repaint();
	}

	@Override
	public void pointClicked(Point p) {
		drawNode(p.x, p.y);
		previous = Optional.of(p);
	}

}

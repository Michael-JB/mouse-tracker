package uk.co.ramyun.mousedata.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.osbot.rs07.script.Script;

import uk.co.ramyun.mousedata.observer.Observer;

public class Ui extends JFrame implements Observer, LocalImage {

	/**
	 * @author © Michael 24 Jan 2018
	 * @file Ui.java
	 */

	private static final long serialVersionUID = 1L;

	private final JLabel view;
	private boolean connectNodes = false, cross = true;
	private BufferedImage surface;
	private final Script script;

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu fileMenu = new JMenu("File");
	private final JMenu viewMenu = new JMenu("View");

	private final JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_T);
	private final JMenuItem saveAsItem = new JMenuItem("Save as...", KeyEvent.VK_T);

	private final JCheckBoxMenuItem connectPositions = new JCheckBoxMenuItem("Connect positions");

	private Optional<String> fileName = Optional.empty();
	private Optional<Point> previous = Optional.empty();

	public Ui(Script script) {
		this.script = script;

		createMenu();
		setJMenuBar(menuBar);

		surface = new BufferedImage(765, 502, BufferedImage.TYPE_INT_RGB);

		try {
			surface = ImageIO.read(getStream("/map/OSRS_Blank.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		view = new JLabel(new ImageIcon(surface));

		setBounds(500, 500, 790, 570);
		setContentPane(view);
	}

	private void createMenu() {

		menuBar.add(fileMenu);
		menuBar.add(viewMenu);

		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);

		connectPositions.setSelected(connectNodes);
		viewMenu.add(connectPositions);

		connectPositions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ui.this.connectNodes = connectPositions.isSelected();
			}
		});

		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileName.isPresent()) saveImage(fileName.get());
				else saveAsImage();
			}
		});

		saveAsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAsImage();
			}
		});

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

	public void saveAsImage() {
		String fn = JOptionPane.showInputDialog(this, "Enter file name:");
		if (fn != null) {
			fileName = Optional.of(fn + (fn.endsWith(".png") ? "" : ".png"));
			saveImage(fn + (fn.endsWith(".png") ? "" : ".png"));
		}
	}

	public void saveImage(String fileName) {
		File dir = new File(script.getDirectoryData() + script.getName().replaceAll(" ", "_") + File.separator);
		File file = new File(dir, fileName);
		if (!file.exists()) {
			if (file.getParentFile().mkdirs()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			ImageIO.write(surface, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void pointClicked(Point p) {
		drawNode(p.x, p.y);
		previous = Optional.of(p);
	}

}

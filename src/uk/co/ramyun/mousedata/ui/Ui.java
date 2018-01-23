package uk.co.ramyun.mousedata.ui;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.UIManager;

import uk.co.ramyun.mousedata.observer.Observer;

public class Ui extends JFrame implements Observer {

	/**
	 * @author © Michael 24 Jan 2018
	 * @file Ui.java
	 */

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Ui frame = new Ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ui() {

		setBounds(500, 500, 300, 200);

	}

	@Override
	public void pointClicked(Point p) {

	}

}

package uk.co.ramyun.mousedata.core;

import java.awt.Point;

import javax.swing.JOptionPane;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import uk.co.ramyun.mousedata.observer.Observer;
import uk.co.ramyun.mousedata.ui.Ui;

@ScriptManifest(author = "Michael", info = "Gathers game input data", name = "Mouse Data", version = 1.00, logo = "")
public class Main extends Script implements Observer {

	/**
	 * @author � Michael 23 Jan 2018
	 * @file Main.java
	 */

	private final MouseTracker mouseTracker = new MouseTracker();
	private Logger logger;
	private final Ui ui = new Ui(this);

	@Override
	public void onStart() {
		log("Script started: " + this.getName() + " by " + this.getAuthor() + ".");
		getBot().addMouseListener(mouseTracker);

		String fn = JOptionPane.showInputDialog("Enter data output file name:");
		if (fn != null) logger = new Logger(this, fn + (fn.endsWith(".csv") ? "" : ".csv"));

		mouseTracker.registerObserver(ui);
		if (logger != null) mouseTracker.registerObserver(logger);
		mouseTracker.registerObserver(this);

		ui.setVisible(true);
	}

	@Override
	public void onExit() {
		if (logger != null) logger.save();
	}

	@Override
	public int onLoop() throws InterruptedException {
		return 0;
	}

	@Override
	public void pointClicked(Point p) {
		log("Point clicked: " + p.toString());
	}

}

package uk.co.ramyun.mousedata.core;

import java.awt.Point;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import uk.co.ramyun.mousedata.observer.Observer;
import uk.co.ramyun.mousedata.ui.Ui;

@ScriptManifest(author = "Michael", info = "Gathers game input data", name = "Mouse Data", version = 1.00, logo = "")
public class Main extends Script implements Observer {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file Main.java
	 */

	private final MouseTracker mouseTracker = new MouseTracker();

	@Override
	public void onStart() {
		log("Script started: " + this.getName() + " by " + this.getAuthor() + ".");
		getBot().addMouseListener(mouseTracker);

		Ui ui = new Ui(true, false, false);
		Ui ui_connected = new Ui(true, true, false);
		mouseTracker.registerObserver(ui);
		mouseTracker.registerObserver(ui_connected);
		mouseTracker.registerObserver(this);
		ui.setVisible(true);
		ui_connected.setVisible(true);
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

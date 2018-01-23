package uk.co.ramyun.mousedata.core;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "Michael", info = "Gathers game input data", name = "Mouse Data", version = 1.00, logo = "")
public class Main extends Script {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file Main.java
	 */

	private final MouseTracker mouseTracker = new MouseTracker();

	@Override
	public void onStart() {
		log("Script started: " + this.getName() + " by " + this.getAuthor() + ".");
		getBot().addMouseListener(mouseTracker);
	}

	@Override
	public int onLoop() throws InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

}

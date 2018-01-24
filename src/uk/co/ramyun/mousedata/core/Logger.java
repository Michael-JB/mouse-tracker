package uk.co.ramyun.mousedata.core;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.osbot.rs07.script.Script;

import uk.co.ramyun.mousedata.observer.Observer;

public class Logger implements Observer {

	/**
	 * @author © Michael 24 Jan 2018
	 * @file Logger.java
	 */

	private final File file;
	private PrintWriter out;

	public Logger(Script script, String fileName) {
		File dir = new File(script.getDirectoryData() + script.getName().replaceAll(" ", "_") + File.separator);
		this.file = new File(dir, fileName);
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
			this.out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		if (out != null) out.close();
	}

	@Override
	public void pointClicked(Point p) {
		if (out != null) out.println(p.x + "," + p.y);
	}
}

package uk.co.ramyun.mousedata.core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.osbot.rs07.script.MethodProvider;

public class MouseTracker implements MouseListener, MouseMotionListener {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file MouseTracker.java
	 */
	private long startTime = 0L, elapsedTime = 0L;
	private final MethodProvider mp;

	public MouseTracker(MethodProvider mp) {
		this.mp = mp;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point position = mp.getMouse().getPosition();
		Point localPos = arg0.getPoint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		startTime = System.nanoTime();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		elapsedTime = System.nanoTime() - startTime;
		mp.log("Elapsed: " + elapsedTime);
	}
}

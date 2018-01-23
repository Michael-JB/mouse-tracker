package uk.co.ramyun.mousedata.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import uk.co.ramyun.mousedata.observer.Observable;

public class MouseTracker extends Observable implements MouseListener {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file MouseTracker.java
	 */

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		notifyClicked(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}

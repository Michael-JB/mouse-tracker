package uk.co.ramyun.mousedata.core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.script.MethodProvider;

import uk.co.ramyun.mousedata.observer.Observable;
import uk.co.ramyun.mousedata.observer.Observer;

public class MouseTracker implements MouseListener, Observable {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file MouseTracker.java
	 */

	private final MethodProvider mp;
	private final List<Observer> observers = new ArrayList<Observer>();

	public MouseTracker(MethodProvider mp) {
		this.mp = mp;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point position = mp.getMouse().getPosition();
		// Point localPos = arg0.getPoint();
		observers.forEach(o -> o.pointClicked(position));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		if (observers.contains(o)) observers.remove(o);
	}
}

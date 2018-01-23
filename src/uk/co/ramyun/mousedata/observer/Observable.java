package uk.co.ramyun.mousedata.observer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Observable {

	/**
	 * @author © Michael 23 Jan 2018
	 * @file Observable.java
	 */

	private final List<Observer> observers = new ArrayList<Observer>();

	public void notifyClicked(Point p) {
		observers.forEach(o -> o.pointClicked(p));
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		if (observers.contains(o)) observers.remove(o);
	}

}

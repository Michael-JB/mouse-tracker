package uk.co.ramyun.mousedata.observer;

public interface Observable {

	/**
	 * @author © Michael 31 Dec 2017
	 * @file Observable.java
	 */

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

}

package uk.co.ramyun.mousedata.ui;

import java.io.InputStream;

import uk.co.ramyun.mousedata.core.Main;

/**
 * 
 * Implementing this interface allows local image loading to work both on the
 * SDN and locally.
 *
 */
public interface LocalImage {

	/**
	 * @author © Michael 9 Sep 2017
	 * @file LocalImage.java
	 */

	default InputStream getStream(String uri) {
		InputStream resourceUri = Main.class.getResourceAsStream("/resources" + uri);
		return resourceUri == null ? Main.class.getResourceAsStream(uri) : resourceUri;
	}

}

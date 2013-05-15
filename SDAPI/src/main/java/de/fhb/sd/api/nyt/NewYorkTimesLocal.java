/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.nyt;

import javax.ejb.Local;

/**
 *
 * @author Christoph Ott
 */
@Local
public interface NewYorkTimesLocal {

	void start();

	void stop();
	
	String getMostPopular();

}

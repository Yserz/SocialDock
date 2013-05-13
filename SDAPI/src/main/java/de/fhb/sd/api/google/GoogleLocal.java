/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.google;

import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface GoogleLocal {

	void start();

	void stop();
}

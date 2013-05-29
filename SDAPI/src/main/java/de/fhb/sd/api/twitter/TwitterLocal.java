/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.twitter;

import de.fhb.sd.domain.entity.Message;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface TwitterLocal {

	void start();

	void stop();

	List<Message> getMessages();

	String hello();

	String handleTwitterException(Exception e);
}

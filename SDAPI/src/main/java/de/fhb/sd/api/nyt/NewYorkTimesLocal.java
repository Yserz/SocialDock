/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.nyt;

import de.fhb.sd.domain.entity.Message;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Christoph Ott
 */
@Local
public interface NewYorkTimesLocal {

	void start();

	void stop();

	List<Message> getMessages();

}

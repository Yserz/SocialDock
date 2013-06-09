/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.sd.api.golem;

import de.fhb.sd.domain.entity.Message;

import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author MacYser
 */
@Local
public interface GolemLocal {

	void start();

	void stop();

	List<Message> getMessages();
}

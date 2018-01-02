package cz.zcu.kiv.pia.kivbook.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing a message;
 *
 * @author Jaroslav Klaus
 */
@Entity
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;

	private Integer conversationId;

	private String text;

}

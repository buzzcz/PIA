package cz.zcu.kiv.pia.kivbook.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.Instant;

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

	private Integer conversationId;

	private Instant created;

	private String text;

	@OneToOne
	@JoinColumn(name = "userId")
	private User owner;

}

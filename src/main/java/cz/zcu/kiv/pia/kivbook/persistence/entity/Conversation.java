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
 * Class representing a conversation.
 *
 * @author Jaroslav Klaus
 */
@Entity
@Data
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Instant created;

	@OneToOne
	@JoinColumn(name = "userId1")
	private User user1;

	@OneToOne
	@JoinColumn(name = "userId2")
	private User user2;


}

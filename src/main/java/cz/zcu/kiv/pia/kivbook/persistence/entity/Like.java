package cz.zcu.kiv.pia.kivbook.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class representing a like.
 *
 * @author Jaroslav Klaus
 */
@Entity
@Table(name = "reaction")
@Data
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer postId;

	@OneToOne
	@JoinColumn(name = "userId")
	private User owner;

}

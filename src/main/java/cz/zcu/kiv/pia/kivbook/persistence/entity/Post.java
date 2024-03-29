package cz.zcu.kiv.pia.kivbook.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * Call representing a post.
 *
 * @author Jaroslav Klaus
 */
@Entity
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Instant created;

	private String text;

	private String picture;

	private Boolean privacy;

	@OneToOne
	@JoinColumn(name = "userId")
	private User owner;

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "postId")
	private Set<Comment> comments;

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "postId")
	private List<Like> likes;

}

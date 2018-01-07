package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import java.time.Instant;

/**
 * Class representing a comment.
 *
 * @author Jaroslav Klaus
 */
@Data
public class CommentDto {

	private Integer id;

	private Integer postId;

	private Instant created;

	private String text;

	private UserDto owner;

}

package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * Call representing a post.
 *
 * @author Jaroslav Klaus
 */
@Data
public class PostDto {

	private Integer id;

	private Instant created;

	private String text;

	private String picture;

	private Boolean privacy;

	private UserDto owner;

	private List<CommentDto> comments;

	private List<LikeDto> likes;

}

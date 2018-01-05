package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

/**
 * Call representing a post.
 *
 * @author Jaroslav Klaus
 */
@Data
public class PostDto {

	private Integer id;

	private Integer userId;

	private String text;

	private String picture;

	private Boolean privacy;

}

package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

/**
 * Class representing a like.
 *
 * @author Jaroslav Klaus
 */
@Data
public class LikeDto {

	private Integer id;

	private Integer userId;

	private Integer postId;

}

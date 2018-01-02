package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing a comment.
 *
 * @author Jaroslav Klaus
 */
@Data
public class CommentDto {

	private Integer id;

	private Integer userId;

	private Integer postId;

	private String text;

}

package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.file.Path;

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

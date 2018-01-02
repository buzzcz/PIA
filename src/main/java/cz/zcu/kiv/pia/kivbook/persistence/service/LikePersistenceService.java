package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.LikeDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Service for working with Like persistence.
 *
 * @author Jaroslav Klaus
 */
public interface LikePersistenceService {

	/**
	 * Retrieves all likes for specified post.
	 *
	 * @param postId Id of the post of which likes to get.
	 * @return All likes for specified post.
	 */
	List<LikeDto> getAll(Integer postId);

	/**
	 * Saves new like.
	 *
	 * @param like New like to save.
	 * @return Saved like.
	 */
	LikeDto save(LikeDto like);

}

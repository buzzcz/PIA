package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.LikeDto;

/**
 * Interface for operations with Likes.
 *
 * @author Jaroslav Klaus
 */
public interface LikeService {

	LikeDto save(LikeDto like);

	void remove(Integer likeId);

}

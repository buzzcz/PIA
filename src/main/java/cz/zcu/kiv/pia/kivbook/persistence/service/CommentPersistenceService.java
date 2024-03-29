package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;

import java.util.Set;

/**
 * Service for working with Comment persistence.
 *
 * @author Jaroslav Klaus
 */
public interface CommentPersistenceService {

	/**
	 * Retrieves all comments for specified post.
	 *
	 * @param postId Id of the post of which comments to get.
	 * @return All comments for specified post.
	 */
	Set<CommentDto> getAll(Integer postId);

	/**
	 * Saves new comment.
	 *
	 * @param comment New comment to save.
	 * @return Saved comment.
	 */
	CommentDto save(CommentDto comment);

}

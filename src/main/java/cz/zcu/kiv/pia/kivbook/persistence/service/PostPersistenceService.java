package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;

import java.util.List;

/**
 * Service for working with Post persistence.
 *
 * @author Jaroslav Klaus
 */
public interface PostPersistenceService {

	/**
	 * Retrieves all posts of specified user.
	 *
	 * @param userId Id of the user of whose posts to find.
	 * @return All posts of specified user.
	 */
	List<PostDto> getAll(Integer userId);

	/**
	 * Retrieves all public posts of specified user.
	 *
	 * @param userId Id of the user of whose public posts to find.
	 * @return All public posts of specified user.
	 */
	List<PostDto> getPublic(Integer userId);

	/**
	 * Saves new post.
	 *
	 * @param post New post to save.
	 * @return Saved post.
	 */
	PostDto save(PostDto post);

}

package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.Set;

/**
 * Service for working with Post persistence.
 *
 * @author Jaroslav Klaus
 */
public interface PostPersistenceService {

	PostDto get(Integer postId);

	/**
	 * Retrieves all posts of specified user.
	 *
	 * @param owner User of whose posts to find.
	 * @return All posts of specified user.
	 */
	Set<PostDto> getAll(UserDto owner);

	/**
	 * Retrieves all public posts.
	 *
	 * @return All public posts.
	 */
	Set<PostDto> getAllPublic();

	/**
	 * Saves new post.
	 *
	 * @param post New post to save.
	 * @return Saved post.
	 */
	PostDto save(PostDto post);

}

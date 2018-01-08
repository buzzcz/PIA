package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;
import java.util.Set;

/**
 * Interface for operations with posts.
 *
 * @author Jaroslav Klaus
 */
public interface PostService {

	PostDto get(Integer postId);

	Set<PostDto> getPostsForUser(UserDto user);

	Set<PostDto> getPostsForUserAndFriends(UserDto user, List<UserDto> friends);

	Set<PostDto> getPostsForUserAndFriends(UserDto user);

	PostDto save(PostDto post);

}

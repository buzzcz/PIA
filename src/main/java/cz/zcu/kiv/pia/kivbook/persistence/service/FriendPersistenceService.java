package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.FriendDto;

import java.util.List;

/**
 * Service for working with Friend persistence.
 *
 * @author Jaroslav Klaus
 */
public interface FriendPersistenceService {

	/**
	 * Retrieves all friendship information for specified user.
	 *
	 * @param userId Id of the user whose friendship information to get.
	 * @return All friendship information for specified user.
	 */
	List<FriendDto> getAll(Integer userId);

	/**
	 * Retrieves friendship information between specified users.
	 *
	 * @param userId1 Id of one of the users whose friendship to retrieve.
	 * @param userId2 Id of one of the users whose friendship to retrieve.
	 * @return Friendship information between specified users or null if none exists.
	 */
	FriendDto get(Integer userId1, Integer userId2);

	/**
	 * Saves new friendship information.
	 *
	 * @param friend New friendship information to save.
	 * @return Saved friendship information.
	 */
	FriendDto save(FriendDto friend);

}

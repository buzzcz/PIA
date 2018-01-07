package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.FriendDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Interface for operations with Friends.
 *
 * @author Jaroslav Klaus
 */
public interface FriendService {

	List<UserDto> getFriends(UserDto userDto);

	FriendDto areFriends(UserDto user1, UserDto user2);

}

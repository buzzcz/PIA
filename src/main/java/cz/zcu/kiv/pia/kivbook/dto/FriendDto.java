package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

/**
 * Class representing a friendship between two users.
 *
 * @author Jaroslav Klaus
 */
@Data
public class FriendDto {

	private Integer id;

	private Integer userId1;

	private Integer userId2;

}

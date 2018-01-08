package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import java.time.Instant;

/**
 * Class representing a conversation.
 *
 * @author Jaroslav Klaus
 */
@Data
public class ConversationDto {

	private Integer id;

	private Instant created;

	private UserDto user1;

	private UserDto user2;

}

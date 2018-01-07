package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import java.time.Instant;

/**
 * Class representing a message;
 *
 * @author Jaroslav Klaus
 */
@Data
public class MessageDto {

	private Integer id;

	private Integer conversationId;

	private Instant created;

	private String text;

	private UserDto owner;

}

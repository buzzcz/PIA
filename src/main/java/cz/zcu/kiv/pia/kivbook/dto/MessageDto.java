package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

/**
 * Class representing a message;
 *
 * @author Jaroslav Klaus
 */
@Data
public class MessageDto {

	private Integer id;

	private Integer userId;

	private Integer conversationId;

	private String text;

}

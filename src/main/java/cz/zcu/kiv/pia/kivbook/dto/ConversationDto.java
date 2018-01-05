package cz.zcu.kiv.pia.kivbook.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing a conversation.
 *
 * @author Jaroslav Klaus
 */
@Data
public class ConversationDto {

	private Integer id;

	private Integer userId1;

	private Integer userId2;

}
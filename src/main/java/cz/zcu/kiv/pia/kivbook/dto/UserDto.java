package cz.zcu.kiv.pia.kivbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.zcu.kiv.pia.kivbook.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

/**
 * Entity class for users.
 *
 * @author Jaroslav Klaus
 */
@Data
public class UserDto {

	private Integer id;

	private String username;

	private String firstName;

	private String lastName;

	@JsonFormat(pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	private String email;

	private String password;

	private String passwordRepeat;

	private Gender gender;

	private String picture;

}

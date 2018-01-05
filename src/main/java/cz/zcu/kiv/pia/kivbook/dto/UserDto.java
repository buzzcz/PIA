package cz.zcu.kiv.pia.kivbook.dto;

import cz.zcu.kiv.pia.kivbook.enums.Sex;
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

	private LocalDate birthday;

	private String email;

	private String password;

	private Sex sex;

	private String picture;

}

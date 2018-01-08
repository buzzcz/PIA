package cz.zcu.kiv.pia.kivbook.dto;

import cz.zcu.kiv.pia.kivbook.enums.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	private String email;

	private String password;

	private String passwordRepeat;

	private Gender gender;

	private String picture;

	private MultipartFile file;



}

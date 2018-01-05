package cz.zcu.kiv.pia.kivbook.persistence.entity;

import cz.zcu.kiv.pia.kivbook.enums.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Entity class for users.
 *
 * @author Jaroslav Klaus
 */
@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String username;

	private String firstName;

	private String lastName;

	private LocalDate birthday;

	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String picture;

}

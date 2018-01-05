package cz.zcu.kiv.pia.kivbook.enums;

import lombok.Getter;

/**
 * Enum representing gender of a person.
 *
 * @author Jaroslav Klaus
 */
public enum Gender {

	MALE("Male"),
	FEMALE("Female");

	@Getter
	private final String humanReadable;

	Gender(String humanReadable) {
		this.humanReadable = humanReadable;
	}
}

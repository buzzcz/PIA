package cz.zcu.kiv.pia.kivbook.enums;

import lombok.Getter;

/**
 * Enum representing sex of a person.
 *
 * @author Jaroslav Klaus
 */
public enum Sex {

	MALE("Male"),
	FEMALE("Female");

	@Getter
	private final String humanReadable;

	Sex(String humanReadable) {
		this.humanReadable = humanReadable;
	}
}

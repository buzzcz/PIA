package cz.zcu.kiv.pia.kivbook.service.auth;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jaroslav Klaus
 */
@Component
public class UserValidatorImpl implements Validator {

	private final String NOT_EMPTY = "NotEmpty";

	@Autowired
	private UserPersistenceService userPersistenceService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDto user = (UserDto) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", NOT_EMPTY);
		if (userPersistenceService.get(user.getUsername()) != null) {
			errors.reject("username", "AlreadyUsed");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordRepeat", NOT_EMPTY);
		if (!user.getPassword().equals(user.getPasswordRepeat())) {
			errors.reject("passwordRepeat", "PasswordConfirm");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", NOT_EMPTY);
		// TODO: More complex validation.
	}

}

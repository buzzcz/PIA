package cz.zcu.kiv.pia.kivbook.service.auth;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.service.UserService;
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

	private static final String NOT_EMPTY = "NotEmpty";

	private static final String USERNAME = "username";

	private static final String PASSWORD_REPEAT = "passwordRepeat";

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME, NOT_EMPTY);
		if (userService.getUser((String) errors.getFieldValue(USERNAME)) != null) {
			errors.rejectValue(USERNAME, "AlreadyUsed");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", NOT_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD_REPEAT, NOT_EMPTY);
		if (!errors.getFieldValue("password").equals(errors.getFieldValue(PASSWORD_REPEAT))) {
			errors.rejectValue(PASSWORD_REPEAT, "PasswordConfirm");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", NOT_EMPTY);
		// TODO: More complex validation.
	}

}

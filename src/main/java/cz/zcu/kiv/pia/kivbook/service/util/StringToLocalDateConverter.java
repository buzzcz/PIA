package cz.zcu.kiv.pia.kivbook.service.util;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Jaroslav Klaus
 */
public class StringToLocalDateConverter extends DozerConverter<String, LocalDate> {

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public StringToLocalDateConverter(Class<String> string, Class<LocalDate> localDate) {
		super(string, localDate);
	}

	@Override
	public LocalDate convertTo(String source, LocalDate destination) {
		return LocalDate.parse(source, dateTimeFormatter);
	}

	@Override
	public String convertFrom(LocalDate source, String destination) {
		return source.format(dateTimeFormatter);
	}

}

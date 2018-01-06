package cz.zcu.kiv.pia.kivbook.service.util;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Custom converter between String and LocalDate.
 *
 * @author Jaroslav Klaus
 */
public class StringToLocalDateConverter extends DozerConverter<String, LocalDate> {

	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	public StringToLocalDateConverter() {
		super(String.class, LocalDate.class);
	}

	@Override
	public LocalDate convertTo(String source, LocalDate destination) {
		return source == null ? null : LocalDate.parse(source, dateTimeFormatter);
	}

	@Override
	public String convertFrom(LocalDate source, String destination) {
		return source == null ? null : source.format(dateTimeFormatter);
	}

}

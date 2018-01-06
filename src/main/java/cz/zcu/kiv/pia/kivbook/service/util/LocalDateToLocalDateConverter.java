package cz.zcu.kiv.pia.kivbook.service.util;

import org.dozer.DozerConverter;

import java.time.LocalDate;

/**
 * Custom converter between two LocalDates.
 *
 * @author Jaroslav Klaus
 */
public class LocalDateToLocalDateConverter extends DozerConverter<LocalDate, LocalDate> {

	public LocalDateToLocalDateConverter() {
		super(LocalDate.class, LocalDate.class);
	}

	@Override
	public LocalDate convertTo(LocalDate source, LocalDate destination) {
		return source == null ? null : LocalDate.from(source);
	}

	@Override
	public LocalDate convertFrom(LocalDate source, LocalDate destination) {
		return source == null ? null : LocalDate.from(source);
	}

}

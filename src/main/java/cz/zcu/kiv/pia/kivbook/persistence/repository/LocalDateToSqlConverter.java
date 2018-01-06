package cz.zcu.kiv.pia.kivbook.persistence.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Converter to convert LocalDate to sql Date.
 *
 * @author Jaroslav Klaus
 */
@Converter(autoApply = true)
public class LocalDateToSqlConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return attribute == null ? null : Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : dbData.toLocalDate();
	}

}

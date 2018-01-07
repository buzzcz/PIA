package cz.zcu.kiv.pia.kivbook.persistence.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Converter to convert LocalDate to sql Date.
 *
 * @author Jaroslav Klaus
 */
@Converter(autoApply = true)
public class InstantToSqlConverter implements AttributeConverter<Instant, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(Instant attribute) {
		return attribute == null ? null : Timestamp.from(attribute);
	}

	@Override
	public Instant convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.toInstant();
	}

}

package cz.zcu.kiv.pia.kivbook.service.util;

import org.dozer.DozerConverter;

import java.time.Instant;

/**
 * Custom converter between String and LocalDate.
 *
 * @author Jaroslav Klaus
 */
public class InstantToInstantConverter extends DozerConverter<Instant, Instant> {

	public InstantToInstantConverter() {
		super(Instant.class, Instant.class);
	}

	@Override
	public Instant convertTo(Instant source, Instant destination) {
		return source == null ? null : Instant.from(source);
	}

	@Override
	public Instant convertFrom(Instant source, Instant destination) {
		return convertTo(source, destination);
	}

}

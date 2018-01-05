package cz.zcu.kiv.pia.kivbook;

import cz.zcu.kiv.pia.kivbook.service.util.StringToLocalDateConverter;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class of the application
 *
 * @author Jaroslav Klaus
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		DozerBeanMapper dozer = new DozerBeanMapper();
		List<CustomConverter> converters = new LinkedList<>(dozer.getCustomConverters());
		converters.add(new StringToLocalDateConverter(String.class, LocalDate.class));
		dozer.setCustomConverters(converters);
		return dozer;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

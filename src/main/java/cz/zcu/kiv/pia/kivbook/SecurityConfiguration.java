package cz.zcu.kiv.pia.kivbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration of web security.
 *
 * @author Jaroslav Klaus
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO: Add all other pages to which user needs to be authenticated.
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css**", "/fonts**", "/js**", "/login**", "/authenticate**").permitAll()
				/*.antMatchers("/**").authenticated()*/
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout().permitAll();
	}

}

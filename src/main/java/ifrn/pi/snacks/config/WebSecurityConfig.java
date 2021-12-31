package ifrn.pi.snacks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/snacks/cardapio").hasRole("CARDAPIO")
			.antMatchers("/snacks/addItem/**").hasRole("ADDITEM")
			.antMatchers("/snacks/cadastrar").permitAll()
			.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/snacks/logar")
		.permitAll();
	}
	
}

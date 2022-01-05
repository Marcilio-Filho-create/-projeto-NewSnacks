package ifrn.pi.snacks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/snacks/cardapio/**").hasRole("CARDAPIO")
			.antMatchers(HttpMethod.POST, "/snacks/cardapio/finalizarPedido").hasRole("CARDAPIO")
			.antMatchers("/snacks/addItem/**").hasRole("ADDITEM")
			.antMatchers(HttpMethod.POST, "/snacks/addItem/salvarItem").hasRole("ADICIONARITEM")
			.antMatchers("/snacks/addItem/salvarItem").hasRole("ADICIONARITEM")
			.antMatchers("/snacks/cadastrar/**").permitAll()
			.antMatchers(HttpMethod.POST, "/snacks/cadastrar/salvar").permitAll()
			.antMatchers("/snacks/cadastroEspecial").hasRole("CADASTROESPECIAL")
			.antMatchers(HttpMethod.POST, "/snacks/cadastroEspecial/salvar").hasRole("CADASTROESPECIAL")
			.antMatchers("/snacks/pedidos").hasRole("PEDIDOS")
			.anyRequest().authenticated()
		.and()
		.csrf().ignoringAntMatchers ("/snacks/cadastrar/salvar")
		.and ()
		.csrf().ignoringAntMatchers("/snacks/addItem/salvarItem")
		.and()
		.csrf().ignoringAntMatchers ("/snacks/cardapio/finalizarPedido")
		.and ()
		.csrf().ignoringAntMatchers ("/snacks/cadastroEspecial/salvar")
		.and ()
		.formLogin()
		.loginPage("/snacks/logar")
		.permitAll();
	}
	
}

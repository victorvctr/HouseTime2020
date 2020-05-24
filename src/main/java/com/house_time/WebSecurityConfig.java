package com.house_time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/index").hasAnyRole("ADMINISTRADOR")
			.antMatchers("/produto").hasAnyRole("ADMINISTRADOR", "VISUALIZAR_PRODUTO")
			.antMatchers("/produto/**").hasAnyRole("ADMINISTRADOR", "CADASTRAR_PRODUTO")
			.antMatchers("/usuario").hasAnyRole("ADMINISTRADOR", "VISUALIZAR_USUARIO")
			.antMatchers("/usuario/**").hasAnyRole("ADMINISTRADOR", "CADASTRAR_USUARIO")
			.anyRequest()				
				.authenticated()
				.and()
			.formLogin() // Para colocar o formulário de login quando usamos Spring Security
				.loginPage("/login") // URL para o formulário de login
				.permitAll() // permissão de acesso para todos ao formulário de login
				.and()
			.sessionManagement() // Controla a sessão
				.maximumSessions(1) // O número máximo de sessões simultaneas para o mesmo usuário
				.expiredUrl("/login"); // Chama a página escolhida no caso de exceder o nr. de acessos ao mesmo tempo
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bootstrap-4.1.3/**")
				.antMatchers("/imagem/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

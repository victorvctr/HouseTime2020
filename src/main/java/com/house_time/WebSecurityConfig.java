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
			.antMatchers("/").permitAll()
			.antMatchers("/sobre").permitAll()
			.antMatchers("/contato").permitAll()
			.antMatchers("/cliente/novo").permitAll()
			.antMatchers("/cliente/salvar").permitAll()
			.antMatchers("/index").permitAll()
			
			
			.antMatchers("/produto/imagem").permitAll() 
				 .antMatchers("/produto/xiaomi").permitAll() 
			.antMatchers("/produto/detalhes/1").permitAll()
			.antMatchers("/produto/detalhes/2").permitAll()
			.antMatchers("/produto/detalhes/3").permitAll()
			.antMatchers("/produto/detalhes/4").permitAll()
			.antMatchers("/produto/detalhes/5").permitAll()
			.antMatchers("/produto/detalhes/6").permitAll()
		    
				 .antMatchers("/produto/apple").permitAll() 
			.antMatchers("/produto/detalhes/7").permitAll()
			.antMatchers("/produto/detalhes/8").permitAll()
			.antMatchers("/produto/detalhes/9").permitAll()
			.antMatchers("/produto/detalhes/10").permitAll()
			.antMatchers("/produto/detalhes/11").permitAll()
			.antMatchers("/produto/detalhes/12").permitAll()
				  
				.antMatchers("/produto/samsung").permitAll() 
			.antMatchers("/produto/detalhes/13").permitAll()
			.antMatchers("/produto/detalhes/14").permitAll()
			.antMatchers("/produto/detalhes/15").permitAll()
			.antMatchers("/produto/detalhes/16").permitAll()
		    .antMatchers("/produto/detalhes/17").permitAll()
		    .antMatchers("/produto/detalhes/18").permitAll()
			
		    
		    .antMatchers("/produto/diesel").permitAll() 
			.antMatchers("/produto/detalhes/19").permitAll()
			.antMatchers("/produto/detalhes/20").permitAll()
			.antMatchers("/produto/detalhes/21").permitAll()
			.antMatchers("/produto/detalhes/22").permitAll()
		    .antMatchers("/produto/detalhes/23").permitAll()
		    .antMatchers("/produto/detalhes/24").permitAll()
		    
		    .antMatchers("/produto/fossil").permitAll() 
			.antMatchers("/produto/detalhes/25").permitAll()
			.antMatchers("/produto/detalhes/26").permitAll()
			.antMatchers("/produto/detalhes/27").permitAll()
			.antMatchers("/produto/detalhes/28").permitAll()
		    .antMatchers("/produto/detalhes/29").permitAll()
		    .antMatchers("/produto/detalhes/30").permitAll()
			
			.antMatchers("/produto").hasAnyRole("Administrador", "VISUALIZAR_PRODUTO")
			.antMatchers("/produto").hasAnyRole("Cliente", "VISUALIZAR_PRODUTO")
			.antMatchers("/produto").hasAnyRole("Cliente", "VISUALIZAR_DETALHES")
			.antMatchers("/produto/**").hasAnyRole("Administrador", "CADASTRAR_PRODUTO")
			.antMatchers("/usuario").hasAnyRole("Administrador", "VISUALIZAR_USUARIO")
			.antMatchers("/usuario/**").hasAnyRole("Administrador", "CADASTRAR_USUARIO")
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
				.antMatchers("/js/**")
				.antMatchers("/webjars/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

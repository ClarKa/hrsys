package org.hrsys.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select user_name as username, password, CASE WHEN inactive_ind = 'N' THEN true ELSE false END as enable from user where user_name = ?")
//				.authoritiesByUsernameQuery(
//						"select u.user_name as username, c.value as role "
//						+ "from user u join user_role_assoc ura on u.user_id = ura.user_id "
//						+ "inner join code c on c.id = ura.role_code_id where u.user_name = ?");

		auth.inMemoryAuthentication().withUser("a").password("1").roles("USER");
		auth.inMemoryAuthentication().withUser("b").password("1").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("c").password("1").roles("SUPERADMIN");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/protected/**").access("hasRole('ROLE_ADMIN')")
//				.antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')").and().formLogin();
		  http
	        .authorizeRequests()
	        .antMatchers("/").access("isAuthenticated()")
	        .and()
	        	.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/perform_login")
	        .and()
	        	.logout().logoutSuccessUrl("/login?logout").logoutUrl("/perform_logout")
	        .and()
	        	.exceptionHandling().accessDeniedPage("/denied");

	}

}

package org.hrsys.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
//        auth.jdbcAuthentication()
//                .dataSource(dataSource);
//                .usersByUsernameQuery(
//                        "select us_user_id, us_username as username, us_password as password, us_enabled as enabled from users where us_username = ?")
//                .authoritiesByUsernameQuery(
//                        "select u.us_username as username, r.rl_role_name as authority from users u join user_role_assoc ura on u.us_user_id = ura.ura_user_id join role r on r.rl_role_id = ura.ura_role_id where u.us_username=?");

//        auth.inMemoryAuthentication().withUser("aa").password("1").roles("USER");
//        auth.inMemoryAuthentication().withUser("bb").password("1").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("cc").password("1").roles("SUPERADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests().antMatchers("/protected/**").access("hasRole('ROLE_ADMIN')")
        // .antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')").and().formLogin();
        http.authorizeRequests().antMatchers("/").access("isAuthenticated()")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/perform_login")
                .and()
                .logout().logoutSuccessUrl("/login?logout").logoutUrl("/perform_logout")
                .and()
                .exceptionHandling().accessDeniedPage("/denied");

    }

}

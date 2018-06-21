package com.company.project.config;

import com.fasterxml.jackson.databind.Module;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.company.project")
@PropertySource("classpath:properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    //do trzymania danych przeczytanych z pliku properties
    @Autowired
    private Environment env;


    @Autowired
    private DataSource securityDataSource;

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    private Logger logger = Logger.getLogger(getClass().getName());


 /*   //dodałam to bo był błąd
    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
*/

    //tymczasowo wpisane z ręki
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);
    }


    // kto może co oglądać
    //co obsługujemy - autoryzacja użytkowników
    //login i logout
    //jak nie ma strony w tych definicjach to mogą ją widzieć wszyscy użytkownicy ( np /books/getall/)
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/register/registration").permitAll()
                .antMatchers("/register/processRegistrationForm").permitAll()
                .antMatchers("/books/getall").permitAll()
                .antMatchers("/books/findbyid/{id}").permitAll()
                .antMatchers("/employee").permitAll()
                .antMatchers("/books/borrow/{id}").hasRole("EMPLOYEE")
                .antMatchers("/books/remove/{id}").hasRole("EMPLOYEE")
                .antMatchers("/books/removeconfirm/{id}").hasRole("EMPLOYEE")
                .antMatchers("/users/removeconfirm/{id}").hasRole("EMPLOYEE")
                .antMatchers("/users/remove/{id}").hasRole("EMPLOYEE")
                .antMatchers("/users/payment").hasRole("EMPLOYEE")
                .antMatchers("/users/findbyid/{id}").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/users/findall").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/issues/removeconfirm/{id}").hasAnyRole("EMPLOYEE")
                .antMatchers("/issues/remove/{id}").hasAnyRole("EMPLOYEE")
                .antMatchers("/issues/remove/{id}").hasAnyRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }


    //bean do bezpieczeństwa danych źrółowych
    @Bean
    public DataSource securityDataSource(){

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource(); //pole do łączenia

        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        }
        catch (PropertyVetoException exc){
           throw new RuntimeException(exc);
        }

        logger.info(">>> jdbc.url" + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.url" + env.getProperty("jdbc.user"));


        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        //to bierze inta, dlatego potrzebowałam metody getIntProperty(to do tych rozmiarów w properties
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));


        return securityDataSource;
    }

    private int getIntProperty(String propName) {

        String propertyVal = env.getProperty(propName);

        int intPropertyVal = Integer.parseInt(propertyVal);

        return intPropertyVal;
    }


    @Bean
    public UserDetailsManager userDetailsManager() {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

        jdbcUserDetailsManager.setDataSource(securityDataSource);

        return jdbcUserDetailsManager;
    }


    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }


    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}



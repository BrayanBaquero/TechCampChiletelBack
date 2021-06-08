package com.chiletel.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.chiletel.security.jwt.JwtEntryPoint;
import com.chiletel.security.jwt.JwtProvider;
import com.chiletel.security.jwt.JwtTokenFilter;
import com.chiletel.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//Indicar a que metodos solo va acceder el administrador
public class MainSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	 private static final List<String> AUTH_LIST = Arrays.asList(
	            "/swagger-resources/**",
	            "/swagger-ui.html**",
	            "/webjars/**",
	            "favicon.ico");

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers( HttpMethod.GET,
					"/swagger-resources/**",
			        "/swagger-ui.html",
			        "/v2/api-docs",
			        "/webjars/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling()
            .defaultAuthenticationEntryPointFor(swaggerAuthenticationEntryPoint(), new CustomRequestMatcher(AUTH_LIST))
            .and()
            .httpBasic()
			.and()
			.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
    @Bean
    public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Swagger Realm");
        return entryPoint;
    }

    private class CustomRequestMatcher implements RequestMatcher {

        private List<AntPathRequestMatcher> matchers;

        private CustomRequestMatcher(List<String> matchers) {
            this.matchers = matchers.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            return matchers.stream().anyMatch(a -> a.matches(request));
        }

    }
	

	
	
}

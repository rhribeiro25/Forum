package br.com.rhribeiro25.forum.config

import br.com.rhribeiro25.forum.enums.AuthorityEnum
import br.com.rhribeiro25.forum.security.JWTAuthenticationFilter
import br.com.rhribeiro25.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil,
    private val TOPICS: String = "/topics",
    private val RESPONSES: String = "/responses",
    private val REPORTS: String = "/reports"
)
    : WebSecurityConfigurerAdapter() {
        
    override fun configure(http: HttpSecurity?) {
        http?.
        csrf()?.disable()?.
        authorizeRequests()?.
        antMatchers(HttpMethod.GET, TOPICS)?.hasAuthority(AuthorityEnum.READ.name)?.
        antMatchers(HttpMethod.PUT, TOPICS)?.hasAuthority(AuthorityEnum.WRITE.name)?.
        antMatchers(HttpMethod.POST, TOPICS)?.hasAuthority(AuthorityEnum.WRITE.name)?.
        antMatchers(HttpMethod.DELETE, TOPICS)?.hasAuthority(AuthorityEnum.DELETE.name)?.

        antMatchers(HttpMethod.GET, RESPONSES)?.hasAuthority(AuthorityEnum.READ.name)?.
        antMatchers(HttpMethod.PUT, RESPONSES)?.hasAuthority(AuthorityEnum.WRITE.name)?.
        antMatchers(HttpMethod.POST, RESPONSES)?.hasAuthority(AuthorityEnum.WRITE.name)?.
        antMatchers(HttpMethod.DELETE, RESPONSES)?.hasAuthority(AuthorityEnum.DELETE.name)?.

        antMatchers(HttpMethod.GET, REPORTS)?.hasAuthority(AuthorityEnum.ADMIN.name)?.

        antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        antMatchers(HttpMethod.GET, "/swagger-ui/*")?.permitAll()?.
        antMatchers(HttpMethod.GET,"/v3/api-docs/**")?.permitAll()?.
        anyRequest()?.
        authenticated()?.
        and()
        http?.addFilterBefore(
            JWTLoginFilter(
                authManager = authenticationManager(),
                jwtUtil = jwtUtil
            ), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }
}
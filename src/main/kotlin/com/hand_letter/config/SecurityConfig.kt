package com.ez_studio.config

import com.ez_studio.config.jwt.JwtTokenFilter
import com.ez_studio.config.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider) {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() }
            .authorizeRequests { auth ->
                auth.requestMatchers("/api/auth/**").permitAll()
                auth.requestMatchers("/api/project").permitAll()
                auth.requestMatchers("/api/project/**").permitAll()
//                auth.anyRequest().authenticated().and()
//                    .addFilterBefore(JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
                auth.anyRequest().authenticated()
            }.oauth2Login { oauth2Login ->
                oauth2Login.userInfoEndpoint { userInfoEndpoint ->
                    userInfoEndpoint.oidcUserService(oidcUserService())
                }
                oauth2Login.defaultSuccessUrl("/oauth2/redirect")
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .httpBasic { httpBasic -> httpBasic.disable() }
            .formLogin { formLogin -> formLogin.disable() }



        return http.build()
    }

    @Bean
    fun clientRegistrationRepository(): InMemoryClientRegistrationRepository {
        val clientRegistration = ClientRegistration.withRegistrationId("google")
            .clientId("33257021890-oi8anu7gobv0qg1g1r7ohud3b98p3ru1.apps.googleusercontent.com")
            .clientSecret("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")
            .scope("openid", "profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
            .tokenUri("https://oauth2.googleapis.com/token")
            .userInfoUri("https://openidconnect.googleapis.com/v1/userinfo")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .userNameAttributeName("sub")
            .clientName("Google")
            .build()
        return InMemoryClientRegistrationRepository(clientRegistration)
    }

    @Bean
    fun oidcUserService(): OidcUserService {
        return OidcUserService()
    }


}
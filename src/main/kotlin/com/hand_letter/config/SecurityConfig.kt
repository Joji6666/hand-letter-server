package com.ez_studio.config

import com.ez_studio.config.jwt.JwtTokenFilter
import com.ez_studio.config.jwt.JwtTokenProvider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider,
                     private val jwtTokenFilter: JwtTokenFilter
) {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Value("\${google.oauth.client-id}")
    private lateinit var googleClientId: String

    @Value("\${google.oauth.client-secret}")
    private lateinit var googleClientSecret: String



    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests { auth -> // ✅ 새로운 방식
                auth.requestMatchers("/api/auth/**").permitAll()
                auth.anyRequest().authenticated()
            }.exceptionHandling { exceptionHandling ->
                exceptionHandling.authenticationEntryPoint { request, response, authException ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized") // ✅ 401 반환
                }
            }
            .addFilterBefore(JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
//            .oauth2Login { oauth2Login ->
//                oauth2Login.userInfoEndpoint { userInfoEndpoint ->
//                    userInfoEndpoint.oidcUserService(oidcUserService())
//                }
//                oauth2Login.defaultSuccessUrl("/oauth2/redirect")
//            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }

        return http.build()
    }

    @Bean
    fun clientRegistrationRepository(): InMemoryClientRegistrationRepository {
        val clientRegistration = ClientRegistration.withRegistrationId("google")
            .clientId(googleClientId)
            .clientSecret(googleClientSecret)
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

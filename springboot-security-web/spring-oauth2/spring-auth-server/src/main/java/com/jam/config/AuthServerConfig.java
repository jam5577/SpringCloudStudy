package com.jam.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * @Author Jam
 * @Date 2023/3/7
 * @Description 服务器配置类
 */
@Configuration
//导入OAuth2AuthorizationServerConfiguration类进行最小默认配置
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthServerConfig {
    /**
     * 注册客户端，通过下列配置来设置不同客户端，使用JdbcRegisteredClientRepository来使用数据库持久化客户端配置
     * <p>Client ID – 所需要授权的客户端id，使用id进行识别
     * Client secret code – 客户端和服务端信任的密钥
     * Authentication method – 客户端认证的方式，提供了多种方式看源码可以知道是什么方式
     * Authorization grant type – 认证授权的方式，可以配置多种方式
     * Redirect URI – 授权后重定向的uri
     * Scope – 定义了我们授权给客户端的权限，使用Oidc来做open connect的认证方式</p>
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("articles-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/articles-client-oidc")
                .redirectUri("http://127.0.0.1:8080/authorized")
                .scope(OidcScopes.OPENID)
                .scope("articles.read")
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
        //使用默认配置
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        //自定义custom的配置类
//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//                new OAuth2AuthorizationServerConfigurer();
//        http.apply(authorizationServerConfigurer);
//
//        authorizationServerConfigurer
//                //(REQUIRED)配置注册好的客户端
//                .registeredClientRepository(registeredClientRepository)
//                //配置授权服务，可使用内存或jdbc的方式，其中的authorization包含了可授权的权限信息
//                .authorizationService(authorizationService)
//                //资源所有者可以仅授予用户请求权限的子集，此服务会将新的权限集合存储，用于客户端和资源服务器的关联
//                .authorizationConsentService(authorizationConsentService)
//                //(REQUIRED)自定义授权服务器的配置，指定协议端点的uri和颁发者标识
//                .authorizationServerSettings(authorizationServerSettings)
//                //生成token
//                // when the value for OAuth2TokenType is:
//                //code, then OAuth2AuthorizationCode is generated.
//                //access_token, then OAuth2AccessToken is generated.
//                //refresh_token, then OAuth2RefreshToken is generated.
//                //id_token, then OidcIdToken is generated.
//                .tokenGenerator(new DelegatingOAuth2TokenGenerator())
//                .clientAuthentication(clientAuthentication -> { })
//                .authorizationEndpoint(authorizationEndpoint -> { })
//                .tokenEndpoint(tokenEndpoint -> { })
//                .tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> { })
//                .tokenRevocationEndpoint(tokenRevocationEndpoint -> { })
//                .authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> { })
//                .oidc(oidc -> oidc
//                        .providerConfigurationEndpoint(providerConfigurationEndpoint -> { })
//                        .userInfoEndpoint(userInfoEndpoint -> { })
//                        .clientRegistrationEndpoint(clientRegistrationEndpoint -> { })
//                );
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

//    @Bean
//    public ProviderSettings providerSettings() {
//        return ProviderSettings.builder()
//                .issuer("http://127.0.0.1:9000")
//                .build();
//    }
}

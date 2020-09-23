package com.krupp.cloud.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 授权
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public UserDetailsService kiteUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore redisTokenStore;

   @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 使用默认的验证管理器和用户信息服务
        endpoints
                //认证管理器
                .authenticationManager(authenticationManager)
                //若无，refresh_token会有UserDetailsService is required错误
                //如果你设置了这个属性的话，那说明你有一个自己的 UserDetailsService 接口的实现，
                // 或者你可以把这个东西设置到全局域上面去（例如 GlobalAuthenticationManagerConfigurer 这个配置对象），
                // 当你设置了这个之后，那么 "refresh_token" 即刷新令牌授权类型模式的流程中就会包含一个检查，用来确保这个账号是否仍然有效，假如说你禁用了这个账户的话
                .userDetailsService(kiteUserDetailsService)
                .accessTokenConverter(accessTokenConverter());
                //.tokenStore(redisTokenStore);

    }



    /**
     * 定义客户端详细信息服务的配置器。客户详细信息可以初始化，或者可以引用现有的 store
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //定义哪些客户端可以注册到服务
        clients.inMemory()  //使用基于内存的 Client 存储器
                //客户端 账号、密码。
                .withClient("krupp").secret(passwordEncoder.encode("krupp"))
                // 为什么要创建 Client 的 client-id 和 client-secret 呢？通过 client-id 编号和 client-secret，授权服务器可以知道调用的来源以及正确性。
                // 这样，即使“坏人”拿到 Access Token ，但是没有 client-id 编号和 client-secret，也不能和授权服务器发生有效的交互。
                // 支持的授权模式 密码模式 返回刷新令牌
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                //重定向Uris
                //.redirectUris("http://localhost:8085/hello")
                //访问令牌的有效期 3600秒 = 2小时
                .accessTokenValiditySeconds(3600)
                //刷新令牌的有效期 864000秒 = 10天
                .refreshTokenValiditySeconds(864000)
                //可授权的scope
                .scopes("all");

    }

   /* @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("test-client")
                .secret(passwordEncoder.encode("test-secret"))
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("default-scope");
    }*/

    /**
     * token加强
     */
   @Bean
   public AccessTokenConverter accessTokenConverter() {
       JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
       //jwtAccessTokenConverter.setKeyPair();
       jwtAccessTokenConverter.setSigningKey("lgz");
       return jwtAccessTokenConverter;
   }

    /**
     * 设置 /oauth/check_token 端点 对应 CheckTokenEndpoint ，通过认证后可访问。
     * 令牌的端点安全约束。不需要Security认证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端访问 OAuth2 授权接口，否则请求 token 会返回 401
        security.allowFormAuthenticationForClients();
        //允许已授权用户访问 checkToken 接口
        security.checkTokenAccess("isAuthenticated()");
        //允许已授权用户访问获取 token 接口
        security.tokenKeyAccess("isAuthenticated()");
    }
}
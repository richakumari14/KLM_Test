package com.afkl.cases.df.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;

@Configuration
public class OauthClientConfig {

	@Autowired(required = false)
	ClientHttpRequestFactory clientHttpRequestFactory;

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		if (clientHttpRequestFactory == null) {
			clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		}
		return clientHttpRequestFactory;
	}

	@Bean
	@Qualifier("myClientOnlyRestTemplate")
	public OAuth2RestOperations restClientOnlyTemplate(@Value("${oauth.token}") String tokenUrl,@Value("${oauth.clientID}") String client_id,@Value("${oauth.clientSecret}") String clientSecret,@Value("${oauth.grantType}") String grantType) {

		OAuth2RestTemplate template = new OAuth2RestTemplate(fullAccessresourceDetailsClientOnly(tokenUrl,client_id,clientSecret,grantType), new DefaultOAuth2ClientContext(
				new DefaultAccessTokenRequest()));
		return prepareTemplate(template, true);
	}

	public OAuth2RestTemplate prepareTemplate(OAuth2RestTemplate template, boolean isClient) {
		template.setRequestFactory(getClientHttpRequestFactory());
		if (isClient) {
			template.setAccessTokenProvider(clientAccessTokenProvider());
		} else {
			template.setAccessTokenProvider(userAccessTokenProvider());
		}
		return template;
	}

	@Bean
	public AccessTokenProvider userAccessTokenProvider() {
		ResourceOwnerPasswordAccessTokenProvider accessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
		accessTokenProvider.setRequestFactory(getClientHttpRequestFactory());
		return accessTokenProvider;
	}

	@Bean
	public AccessTokenProvider clientAccessTokenProvider() {
		ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
		accessTokenProvider.setRequestFactory(getClientHttpRequestFactory());
		return accessTokenProvider;
	}

	@Bean
	@Qualifier("myClientOnlyFullAcessDetails")
	public OAuth2ProtectedResourceDetails fullAccessresourceDetailsClientOnly(@Value("${oauth.token}") String tokenUrl,@Value("${oauth.clientID}") String client_id,@Value("${oauth.clientSecret}") String clientSecret,@Value("${oauth.grantType}") String grantType) {
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setClientId(client_id);
		resource.setClientSecret(clientSecret);
		resource.setGrantType(grantType);		
		
		return resource;
	}
}

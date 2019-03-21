package com.afkl.cases.df.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	
	
	@Autowired
	@Qualifier("myClientOnlyRestTemplate")
	private OAuth2RestOperations clientOnlyrestTemplate;
	
	
	@Autowired
	OauthConnectionService oauthConnectionService;

    
    public Map getFare(String from, String to) {

    	return oauthConnectionService.getClientOnlyResults("http://localhost:8080/fares/"+from+"/"+to, Map.class, clientOnlyrestTemplate);    	
    }
    
    
    
    
   /* @Autowired
    private TokenEndpoint tokenEndpoint;

    public ResponseEntity<?> createToken() {

    		Principal principal = new UsernamePasswordAuthenticationToken("","");
    	
    		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
            resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
            resourceDetails.setClientId("travel-api-client");
            resourceDetails.setClientSecret("psw");
            resourceDetails.setGrantType("client_credentials");
            resourceDetails.setScope(Arrays.asList("read", "write"));

            return tokenEndpoint.getAccessToken(principal, resourceDetails);
    }*/
    
    
}

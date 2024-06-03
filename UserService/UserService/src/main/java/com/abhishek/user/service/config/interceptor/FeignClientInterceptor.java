package com.abhishek.user.service.config.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor  implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // registration id is added in the yml file and even the principal is nothing but the scope
       String token = oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
        requestTemplate.header("Authorization","Bearer "+token);
    }
}

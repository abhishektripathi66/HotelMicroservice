package com.abhishek.apigateway.models;


import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthResponse {

    private  String userId;
    private String accessToken;
    private String refreshToken;
    private long expireAt;
    private Collection<String> authorities;
}

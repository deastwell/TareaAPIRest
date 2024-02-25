package com.example.tareaapirest;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean validateToken(String token){return (token.equals("token"));}

}

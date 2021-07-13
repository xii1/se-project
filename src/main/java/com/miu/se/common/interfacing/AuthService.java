package com.miu.se.common.interfacing;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.miu.se.common.entity.User;

public interface AuthService {
    public Long getUserId(String token);
    public String generateToken(User user);
    public DecodedJWT verifyToken(String token);
    public String authenticate(String email, String password) throws Exception;
    public Long getCurrentUserId();
}

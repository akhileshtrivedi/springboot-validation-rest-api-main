package com.springboot.at.service;

import com.springboot.at.payload.LoginDto;
import com.springboot.at.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}

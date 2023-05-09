package com.sansa.FundacioEsplaiHackathon.security;

import com.sansa.FundacioEsplaiHackathon.payloads.AuthResponse;
import com.sansa.FundacioEsplaiHackathon.payloads.LoginRequest;
import com.sansa.FundacioEsplaiHackathon.payloads.RegisterRequest;
import com.sansa.FundacioEsplaiHackathon.security.jwt.JwtService;
import com.sansa.FundacioEsplaiHackathon.user.Role;
import com.sansa.FundacioEsplaiHackathon.user.User;
import com.sansa.FundacioEsplaiHackathon.user.UserRepository;
import com.sansa.FundacioEsplaiHackathon.user.dto.UserToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserToDto userToDto;

    public User createUser(RegisterRequest request){

        User user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date())
                .role(Role.PLAYER)
                .build();

        return userRepository.save(user);
    }

    public AuthResponse authenthicate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user, new HashMap<>());
        return AuthResponse.builder()
                .user(userToDto.apply(user))
                .token(token)
                .build();

    }
}
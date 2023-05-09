package com.sansa.FundacioEsplaiHackathon.controller;

import com.sansa.FundacioEsplaiHackathon.exception.EmptyPasswordException;
import com.sansa.FundacioEsplaiHackathon.exception.UsernameAlreadyTakenException;
import com.sansa.FundacioEsplaiHackathon.payloads.AuthResponse;
import com.sansa.FundacioEsplaiHackathon.payloads.LoginRequest;
import com.sansa.FundacioEsplaiHackathon.payloads.RegisterRequest;
import com.sansa.FundacioEsplaiHackathon.security.AuthenticationService;
import com.sansa.FundacioEsplaiHackathon.security.jwt.JwtService;
import com.sansa.FundacioEsplaiHackathon.user.User;
import com.sansa.FundacioEsplaiHackathon.user.UserService;
import com.sansa.FundacioEsplaiHackathon.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> newPlayer(@RequestBody RegisterRequest request) throws UsernameAlreadyTakenException, EmptyPasswordException {
        User user = authService.createUser(cleanRegisterRequest(request));
        String token = jwtService.generateToken(user, new HashMap<>());
        UserDto userDto = userService.userToDTO(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .user(userDto)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenthicate(request));
    }






    private RegisterRequest cleanRegisterRequest(RegisterRequest request) throws UsernameAlreadyTakenException, EmptyPasswordException {
        if(userService.exists(request.getName())){
            throw new UsernameAlreadyTakenException("This Username is Already Taken");
        }

        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new EmptyPasswordException("The password cannot be empty");
        }

        return request;

    }

}

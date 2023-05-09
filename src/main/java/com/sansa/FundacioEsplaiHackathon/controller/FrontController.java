package com.sansa.FundacioEsplaiHackathon.controller;

import com.sansa.FundacioEsplaiHackathon.payloads.AuthResponse;
import com.sansa.FundacioEsplaiHackathon.payloads.LoginRequest;
import com.sansa.FundacioEsplaiHackathon.payloads.RegisterRequest;
import com.sansa.FundacioEsplaiHackathon.user.User;
import com.sansa.FundacioEsplaiHackathon.user.dto.UserToDto;
import com.sansa.FundacioEsplaiHackathon.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class FrontController {
    private final UserToDto userToDto;

    @GetMapping
    public String home(Model model){
        User user = getUser();
        model.addAttribute("username",user.getUsername());


        return "index";
    }




    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error){
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return "redirect:/";
        }
        if(error != null){
            model.addAttribute("errorMessage","The username or password are incorrect");
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) String error){
        if(error != null){
            model.addAttribute("errorMessage", "This username is already taken, please try again");
        }
        model.addAttribute("registerRequest",new RegisterRequest());
        return "register";
    }

    @PostMapping("/authenticate")
    public String authenticate(
            LoginRequest loginRequest,
            HttpServletResponse response) throws UnsupportedEncodingException {
        String url = "http://localhost:8080/api/v1/auth/login";
        RestTemplate restTemplate = new RestTemplate();


        try {
            ResponseEntity<AuthResponse> authResponse = restTemplate.postForEntity(url, loginRequest, com.sansa.FundacioEsplaiHackathon.payloads.AuthResponse.class);
            String token = Objects.requireNonNull(authResponse.getBody()).getToken();
            CookieUtil.addCookie(response, "Authorization", token, 86400);
            return "redirect:/?continue";
        } catch (HttpClientErrorException e){
            return "redirect:/login?error=badcredentials";
        }
    }

    @PostMapping("/save")
    public String save(
            RegisterRequest registerRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        String url = "http://localhost:8080/api/v1/auth/register";
        RestTemplate restTemplate = new RestTemplate();


        try{
            ResponseEntity<AuthResponse> authResponse = restTemplate.postForEntity(url,registerRequest, com.sansa.FundacioEsplaiHackathon.payloads.AuthResponse.class);
            String token = Objects.requireNonNull(authResponse.getBody()).getToken();
            CookieUtil.addCookie(response,"Authorization", token, 86400);

            return "redirect:/?continue";
        } catch(HttpClientErrorException e){
            return "redirect:/register?error=alreadytaken";
        }


    }

    private User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}

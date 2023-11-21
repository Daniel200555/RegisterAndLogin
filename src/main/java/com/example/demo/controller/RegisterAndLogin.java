package com.example.demo.controller;

import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.SingUpDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.auth.AuthService;
import com.example.demo.service.jwt.UserDetailsServiceImpl;
import com.example.demo.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RegisterAndLogin {

    @Autowired
    private AuthService authService;


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sing-up")
    public ResponseEntity<?> register(@RequestBody SingUpDTO singUpDTO) {
        UserDTO createUser = authService.createUser(singUpDTO);
        if (createUser == null)
            return new ResponseEntity<>("User not created, because user with nickname: " + singUpDTO.getNickname() + " is exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException,
            DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getNickname(), authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getNickname());

        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);
    }

}

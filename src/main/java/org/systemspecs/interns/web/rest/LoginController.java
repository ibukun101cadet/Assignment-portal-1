package org.systemspecs.interns.web.rest;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.systemspecs.interns.config.jwt.util.JwtUtil;
import org.systemspecs.interns.dto.response.LoginRequest;
import org.systemspecs.interns.dto.response.LoginResponse;
import org.systemspecs.interns.service.AppUserService;


@RestController
@RequestMapping(path = "/api/login")
public class LoginController {

    private final JwtUtil jwtTokenUtil;
    private final AppUserService userDetailsService;

    public LoginController(JwtUtil jwtTokenUtil, AppUserService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping
    @RequestMapping("")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}

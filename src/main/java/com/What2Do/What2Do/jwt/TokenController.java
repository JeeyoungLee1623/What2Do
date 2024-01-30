//package com.What2Do.What2Do.jwt;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/token")
//public class TokenController {
//
//    private final TokenProvider tokenProvider;
//
//    @Value("${jwt.token-validity-in-seconds}")
//    private long tokenValidityInSeconds;
//
//    public TokenController(TokenProvider tokenProvider) {
//        this.tokenProvider = tokenProvider;
//    }
//
//    @PostMapping("/generate")
//    public ResponseEntity<TokenDto> generateToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            // Assuming the user is already authenticated
//            String token = tokenProvider.createToken(authentication);
//            return ResponseEntity.ok(new TokenDto(token));
//        } else {
//            // Handle authentication error
//            return ResponseEntity.badRequest().build();
//        }
//    }
//}

package com.communipute.api.auth;

import com.communipute.api.dto.AuthenticationRequestDTO;
import com.communipute.api.dto.AuthenticationResponseDTO;
import com.communipute.api.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController used for authenticating and registering users.
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(authenticationService.register(requestDTO));
    }

}

package com.communipute.api.auth;

import com.communipute.api.config.JwtService;
import com.communipute.api.dto.AuthenticationRequestDTO;
import com.communipute.api.dto.AuthenticationResponseDTO;
import com.communipute.api.dto.RegisterRequestDTO;
import com.communipute.api.endUser.EndUser;
import com.communipute.api.endUser.EndUserRepository;
import com.communipute.api.token.Token;
import com.communipute.api.token.TokenRepository;
import com.communipute.api.utils.TokenType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final EndUserRepository endUserRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     *
      * @param repository
     * @param jwtService
     * @param passwordEncoder
     * @param authenticationManager
     */
    public AuthenticationService(EndUserRepository repository,
                                 JwtService jwtService,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository) {
        this.endUserRepository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    /**
     * Authenticate a user
     * @param requestDTO
     * @return
     */
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getUsername(),
                        requestDTO.getPassword()
                )
        );
        // If we get to this part, email and password are correct
        EndUser user = endUserRepository.findEndUserByUsername(requestDTO.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponseDTO(jwtToken);
    }

    /**
     * Register a user
     * @param requestDTO
     * @return
     */
    public AuthenticationResponseDTO register(RegisterRequestDTO requestDTO) {
        // TODO: Validate registerRequestDTO parameters
        EndUser user = new EndUser(
                requestDTO.getUsername(),
                requestDTO.getFirstName(),
                requestDTO.getLastName(),
                passwordEncoder.encode(requestDTO.getPassword()),
                requestDTO.getEmail()
        );
        endUserRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        saveUserToken(user, jwtToken);

        return new AuthenticationResponseDTO(jwtToken);
    }

    /**
     * Revoke all tokens for a user. This allows us to log out a user from all devices and to ensure that only a single
     * token is issued at a time.
     * @param user
     */
    private void revokeAllUserTokens(EndUser user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    /**
     * Save a user token to the database
     * @param user
     * @param jwtToken
     */
    private void saveUserToken(EndUser user, String jwtToken) {
        Token token = new Token(
                jwtToken,
                TokenType.BEARER,
                false,
                false,
                user
        );

        tokenRepository.save(token);
    }

}

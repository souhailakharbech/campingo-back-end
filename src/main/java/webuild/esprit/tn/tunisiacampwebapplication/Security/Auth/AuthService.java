package webuild.esprit.tn.tunisiacampwebapplication.Security.Auth;

import lombok.RequiredArgsConstructor;

import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;

import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import webuild.esprit.tn.tunisiacampwebapplication.Security.Configuration.JwtService;
import webuild.esprit.tn.tunisiacampwebapplication.Security.Token.Token;
import webuild.esprit.tn.tunisiacampwebapplication.Security.Token.TokenRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Security.Token.TokenType;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Iuser iuser ;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public User register(RegisterRequest request) {
        var user = User.builder()
                .nameUser(request.getNameUser())
                .familyName(request.getFamilyName())
                .birthday(request.getBirthday())
                .adress(request.getAdress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
               // .userType(UserType.OWNER)
                //.userType(UserType.DELIVERYPERSON)
                .build();
        var savedUser = iuser.save(user);
      var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return //AuthResponse.builder()
                //.token(jwtToken)
                user ;//.build();
    }




    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);

    }

    private void revokeAllUserTokens(Optional<User> user) {
       var validUserTokens = tokenRepo.findAllValidTokenByUser(user.get().getIdUser());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = iuser.findByEmail(request.getEmail()).orElse(null);
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(Optional.ofNullable(user));
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
    }


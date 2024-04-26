package com.kog.emp.service;

import com.kog.emp.dto.AuthRequest;
import com.kog.emp.dto.UserDto;
import com.kog.emp.entity.AuthenticationResponse;
import com.kog.emp.entity.Token;
import com.kog.emp.entity.User;
import com.kog.emp.repository.TokenRepository;
import com.kog.emp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    public AuthenticationResponse register(UserDto request){
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user=userRepository.save(user);
        String token=jwtService.generateToken(user);
       // saving the generated token
        saveUserToken(token, user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.generateToken(user);
        revokeAllTokensByUsers(user);
        saveUserToken(token,user);
        return new AuthenticationResponse((token));
    }


    private void saveUserToken(String token, User user) {
        Token token1=new Token();
        token1.setToken(token);
        token1.setLoggedOut(false);
        token1.setUser(user);
        tokenRepository.save(token1);
    }
    private void revokeAllTokensByUsers(User user) {
        List<Token> validTokenListByUser=tokenRepository.findAllTokenByUser(user.getId());
        if(!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t->{
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}

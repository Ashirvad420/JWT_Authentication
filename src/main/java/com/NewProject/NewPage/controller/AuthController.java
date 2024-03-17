package com.NewProject.NewPage.controller;

import com.NewProject.NewPage.entity.Role;
import com.NewProject.NewPage.entity.User;
import com.NewProject.NewPage.payload.LoginDto;
import com.NewProject.NewPage.payload.SignUpDto;
import com.NewProject.NewPage.repository.RoleRepository;
import com.NewProject.NewPage.repository.UserRepository;
import com.NewProject.NewPage.security.JWTAuthResponse;
import com.NewProject.NewPage.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired   // AuthenticationManager get that UserName Password
    private AuthenticationManager authenticationManager;  // bean annotation inject in this AuthenticationManager

    // this is signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

        // this is using for userRepository

        // if is true the userExit then returrn the message to post man
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // check  the Email exits in database
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // this is using for password Encoder
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Role Repository layer in singup ManyToMany
//        Role roles = roleRepository.findByName("ROLE_ADMIN").get(); // it is use role object for role table
        Role roles = roleRepository.findByName(signUpDto.getRoleType()).get();
        Set<Role> convertRoleToSet = new HashSet<>();
        convertRoleToSet.add(roles);
        user.setRoles(convertRoleToSet);

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }


    // how to develop Authenticate singin with username and password
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);


        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}

// Date :- 2024-02-05 to 2024-02-07 to 2024-02-08 to 2024-03-13  SingUp and SingIn
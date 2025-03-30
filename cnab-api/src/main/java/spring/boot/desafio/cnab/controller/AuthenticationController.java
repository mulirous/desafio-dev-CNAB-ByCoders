package spring.boot.desafio.cnab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.boot.desafio.cnab.dto.LoginResponseDTO;
import spring.boot.desafio.cnab.dto.SignInDTO;
import spring.boot.desafio.cnab.dto.SignUpDTO;
import spring.boot.desafio.cnab.entity.User;
import spring.boot.desafio.cnab.enums.UserRole;
import spring.boot.desafio.cnab.infra.security.TokenService;
import spring.boot.desafio.cnab.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000") // Permite CORS para este controlador
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponseDTO> signIn(@RequestBody SignInDTO data) {


        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(usernamePassword); // Faz o encode e a comparação da senha

        var token = tokenService.generateToken((User)authentication.getPrincipal()); // Pega os dados do usuário com o getPrincipal e faz o cast (conversão) para o tipo User para não reclamar

        System.out.println("Token " + token + " do usuário " + data.email() + " criado com suscesso!");
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO data) {
        if(this.userRepository.findByEmail(data.email())  != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), encryptedPassword, UserRole.USER, data.email());
        String message = "Usuário registrado com sucesso!";

        userRepository.save(newUser);
        return ResponseEntity.ok(message);
    }
}

package spring.boot.desafio.cnab.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.desafio.cnab.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.secret.security.token.secret:minha-chave-secreta")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Encriptação para gerar o token
            String token = JWT.create()
                    .withIssuer("auth-api") // Identificação de quem gerou o token
                    .withSubject(user.getLogin()) // Usuário que recebe o token para ao descriptografar identificar o ususário
                    .withExpiresAt(generateExpirationDate()) // Tempo de expiração
                    .sign(algorithm); // Algoritmo de encriptação

            return token;
        }
        catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
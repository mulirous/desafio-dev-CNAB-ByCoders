package spring.boot.desafio.cnab.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.boot.desafio.cnab.repository.UserRepository;

import java.io.IOException;

@Component // Um componente da aplicaão
public class SecurityFilter extends OncePerRequestFilter {// Extende uma interface que ocorre uma vez a cada requisição
    @Autowired(required = false)
    TokenService tokenService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var login = tokenService.validateToken(token); // Valida o token
            UserDetails userDetails = userRepository.findByLogin(login); // Encontra o Usuário

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Instancia a classe com os dados que o spring precisa
            SecurityContextHolder.getContext().setAuthentication(authentication); // É feita a autenticação e é salva no contexto da aplicação, caso contrário, salva nada
        }

        filterChain.doFilter(request, response); // Se deu certo, ele passa para o próximo filtro (autenticação de login e senha)
    } // Ocorre antes da chamada da requisição lá no SecurityConfiguration

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) return null;
        return authorizationHeader.replace("Bearer ", "");
    } // Verifica se há token e limpa o "Barear" dele
}

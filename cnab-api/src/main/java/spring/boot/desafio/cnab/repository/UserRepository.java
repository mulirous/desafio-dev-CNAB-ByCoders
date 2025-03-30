package spring.boot.desafio.cnab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.desafio.cnab.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public UserDetails findByEmail(String email);
    public UserDetails findByName(String name);
}

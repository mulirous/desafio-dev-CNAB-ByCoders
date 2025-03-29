package spring.boot.desafio.cnab.dto;

import spring.boot.desafio.cnab.enums.UserRole;

public record SignUpDTO (String login, String password, UserRole role, String email) {
}

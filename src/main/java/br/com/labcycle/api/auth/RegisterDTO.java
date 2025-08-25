package br.com.labcycle.api.auth;
import br.com.labcycle.api.user.UserRole;
public record RegisterDTO(String name, String email, String password, UserRole role) {
}
package tech.lastbox.dtos.auth;

import tech.lastbox.entities.User;

public record RegisterResponse(User user, String token) {
}

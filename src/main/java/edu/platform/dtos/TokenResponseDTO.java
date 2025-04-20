package edu.platform.dtos;

public record TokenResponseDTO(String access_token, String refresh_token, Long expires_in) {
}

package edu.platform.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TokenResponseDTO(String access_token, String refresh_token, Long expires_in) {
}

package com.app.entity;

import com.app.payload.UserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Jwt Response Model Information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtResponse {

	@Schema(description = "Token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWh1bEBnbWFpbC5jb20iLCJpYXQiOjE3MDgwNzE5OTcsImV4cCI6MTcwODA4OTk5N30.uxwg715LnqxrRhcFjd40liWgt55E-jkQU04EtJuFB_0bdkE2sYjOvAdyPwy5boXbslYvvl4ckehKBPL-gi7ORQ")
	private String jwtToken;

	@Schema(description = "User", example = "abc@gmail.com")
	private UserDto user;
}

package com.app.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Jwt Request Model Information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
	
	@Schema(description = "Email", example = "abc@gmail.com")
	private String email;
	
	@Schema(description = "Password", example = "********")
	private String password;

}

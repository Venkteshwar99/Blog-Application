package com.app.payload;

import java.util.HashSet;
import java.util.Set;

import com.app.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "User Model Information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User Id", example = "1")
	private int id;
	
	@Schema(description = "User Name", example = "Rohit")
	@NotEmpty
	@Size(min = 4,message = "Username must be of 4 characters!")
	private String name;
	
	@Schema(description = "Email", example = "rohit@gmail.com")
	@Email(message = "Email address is not valid!!")
	private String email;
	
	@Schema(description = "Password", example = "*****")
	@NotEmpty
	@Size(min = 8,max = 10,message = "Password must be minimum of 8 characters and maximum of 10 characters!")
	private String password;
	
	@Schema(description = "About", example = "I am a developer")
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}

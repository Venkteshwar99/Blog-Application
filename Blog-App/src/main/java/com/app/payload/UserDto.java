package com.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be of 4 characters!")
	private String name;
	
	@Email(message = "Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min = 8,max = 10,message = "Password must be minimum of 8 characters and maximum of 10 characters!")
	private String password;
	
	@NotEmpty
	private String about;

}

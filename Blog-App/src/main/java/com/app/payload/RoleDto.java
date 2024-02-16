package com.app.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Role Model Information")
@Data
public class RoleDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Role Id", example = "501")
	private int id;
	
	@Schema(description = "Name", example = "Normal User")
	private String name;
}

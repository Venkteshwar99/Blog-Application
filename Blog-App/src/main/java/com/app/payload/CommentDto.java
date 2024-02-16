package com.app.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Comment Model Information")
@Getter
@Setter
public class CommentDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Comment Id", example = "1")
	private int id;
	
	@Schema(description = "Content", example = "This comment is of post no 1")
	private String content;
	
}

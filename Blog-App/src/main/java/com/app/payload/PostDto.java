package com.app.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Post Model Information")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Post Id", example = "1")
	private int postId;

	@Schema(description = "Title", example = "Role of Java Developer")
	private String title;
	
	@Schema(description = "Content", example = "Java Developer's role is to develop web applications")
	private String content;

	@Schema(description = "Image Name", example = "abc.png")
	private String imageName;

	@Schema(description = "Date", example = "2024-02-14T11:14:51.781+00:00")
	private Date date;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

}

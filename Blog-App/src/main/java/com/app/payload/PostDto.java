package com.app.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {

	private int postId;

	private String title;

	private String content;

	private String imageName;

	private Date date;

	private CategoryDto category;

	private UserDto user;

}

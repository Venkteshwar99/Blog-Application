package com.app.payload;

import jakarta.validation.constraints.NotEmpty;
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
public class CategoryDto {

	private int categoryId;
	
	@NotEmpty
	@Size(min = 4,message = "Min size of category title is 4")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10,message = "Min size of category title is 10")
	private String categoryDescription;
	
	
}

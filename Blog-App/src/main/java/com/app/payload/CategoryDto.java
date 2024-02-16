package com.app.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Category Model Information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "category Id", example = "1")
	private int categoryId;
	
	@Schema(description = "Title", example = "Full Stack Developer")
	@NotEmpty
	@Size(min = 4,message = "Min size of category title is 4")
	private String categoryTitle;
	
	@Schema(description = "Description", example = "This Category involves Full Stack Developer")
	@NotEmpty
	@Size(min = 10,message = "Min size of category title is 10")
	private String categoryDescription;
	
	
}

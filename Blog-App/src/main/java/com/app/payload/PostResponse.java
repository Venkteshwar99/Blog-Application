package com.app.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Post Response Model Information")
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	@Schema(description = "Page Number", example = "1")
	private int pageNumber;
	@Schema(description = "Page Size", example = "5")
	private int pageSize;
	@Schema(description = "Total Elements", example = "5")
	private long totalElements;
	@Schema(description = "Total Pages", example = "2")
	private int totalPages;
	@Schema(description = "Last Page", example = "true")
	private boolean lastPage;
	
	

}

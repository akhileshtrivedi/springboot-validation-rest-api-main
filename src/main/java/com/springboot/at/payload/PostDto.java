package com.springboot.at.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private long id;

    @Schema(
            description = "Validation Post Title"
    )
    // title should not be null  or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @Schema(
            description = "Validation Post Description"
    )
    // post description should be not null or empty
    // post description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @Schema(
            description = "Validation Post Content"
    )
    // post content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    @Schema(
            description = "Validation Post Category"
    )
    private Long categoryId;
}

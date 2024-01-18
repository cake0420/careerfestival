package careerfestival.career.comments.dto;

import careerfestival.career.domain.mapping.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private Long id;
    private String commentContent;
    private String parentContent;
    @Builder
    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .commentContent(commentContent)
                .parentContent(parentContent)
                .build();
    }

}

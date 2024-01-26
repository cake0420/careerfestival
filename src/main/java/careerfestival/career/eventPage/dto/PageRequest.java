package careerfestival.career.eventPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequest {
    private Integer page;
    private Integer size;



    public static PageRequest of(Integer page, Integer size){
        return PageRequest.builder()
                .page(page)
                .size(size)
                .build();
    }
}

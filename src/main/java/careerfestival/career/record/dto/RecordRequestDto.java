//package careerfestival.career.record.dto;
//
//import careerfestival.career.domain.enums.Category;
//import jakarta.persistence.Column;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class RecordRequestDto {
//
//    @Getter
//    public static class AddRecordConferenceRequestDto{
//        @NotNull
//        private String eventName;
//        @NotNull
//        private LocalDate deadline;
//        @NotNull
//        private Category category;
//        @NotNull
//        private LocalDateTime eventDate;
//        //checkpoint 세션과 부스 어차피 같은 형태로 그냥 제목부스이름이던 세션이름이던 같이 받을 수 있음 그래서 event description 제목 받을 거 하나 생성
//        @NotNull
//        private String eventDescription;
//        private String networkingName;
//        private String networkingContact;
//    }
//}

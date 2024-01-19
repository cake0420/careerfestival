//package careerfestival.career.record.converter;
//import careerfestival.career.domain.Record;
////import careerfestival.career.record.dto.RecordRequestDto;
////import careerfestival.career.record.dto.RecordResponseDto;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;



//public class RecordConverter {
//    public static Record toRecord(RecordRequestDto.AddRecordConferenceRequestDto request){
//
//        return Record.builder()
//                .eventName(request.getEventName())
//                .eventDate(request.getEventDate())
//                .eventDescription(request.getEventDescription())
//                .networkingName(request.getNetworkingName())
//                .networkingContact(request.getNetworkingContact())
//                .build();
//    }
//    public static RecordResponseDto.AddRecordResponseDto toAddRecordResponseDto(Record record){
//        return RecordResponseDto.AddRecordResponseDto.builder()
//                .recordId(record.getId())
//                .createdAt(LocalDate.from(LocalDateTime.now()))
//                .build();
//    }
//checkpoint localdatetime

//
//}

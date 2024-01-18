package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.record.converter.RecordConverter;
import careerfestival.career.record.dto.RecordRequestDto;
import careerfestival.career.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import careerfestival.career.repository.UserRepository;



@Service
@RequiredArgsConstructor
@Transactional
public class RecordCommandServiceImpl implements RecordCommandService{
    private final UserRepository  userRepository;
    private final RecordRepository recordRepository;
    @Override
    public Record addRecord(RecordRequestDto.AddRecordConferenceRequestDto request, Long userId){
        Record newRecord = RecordConverter.toRecord(request);
        //checkpoint userid에 맞게 저장하는방법?
        //Optional<User> user = userRepository.findById(userId);
        //checkpoint 리스트
       // newRecord.user(user);
        return recordRepository.save(newRecord);
    };

}

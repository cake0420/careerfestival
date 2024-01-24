package careerfestival.career.register.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.global.S3Uploader;
import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.register.dto.RegisterMainResponseDto;
import careerfestival.career.register.dto.RegisterOrganizerDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.OrganizerRepository;
import careerfestival.career.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;

    @Autowired
    private S3Uploader s3Uploader;

    public void registerEvent(Long userId, RegisterEventDto registerEventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id" + userId));
        Event event = registerEventDto.toEntity();
        event.setUser(user);

        eventRepository.save(event);
    }

    // 행사 대표이미지 업로드
    @Transactional
    public void registerEventMainImage(Long userId, MultipartFile eventmainimage) throws IOException{
        Event event = eventRepository.findByUserId(userId);

        if(!eventmainimage.isEmpty()){
            String storedFileName = s3Uploader.upload(eventmainimage, "event_main");
            event.setEventMainFileUrl(storedFileName);
        }
        eventRepository.save(event);
    }

    // 행사 정보 이미지 업로드 (수정 보완 필요)
    @Transactional
    public void registerEventInformImage(Long userId, MultipartFile eventinformimage) throws IOException{
        Event event = eventRepository.findByUserId(userId);
        if(!eventinformimage.isEmpty()){
            String storedFileName = s3Uploader.upload(eventinformimage, "event_inform");
            event.setEventMainFileUrl(storedFileName);
        }
        eventRepository.save(event);
    }

    public void registerOrganizer(Long userId, RegisterOrganizerDto registerOrganizerDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id" + userId));

        Organizer organizer = registerOrganizerDto.toEntity();
        organizer.setUser(user);

        organizerRepository.save(organizer);
    }
    @Transactional
    public void registerOrganizerImage(Long userId, MultipartFile image) throws IOException {
        Organizer organizer = organizerRepository.findByUserId(userId);

        if(!image.isEmpty()){
                String storedFileName = s3Uploader.upload(image, "organizer_profile");
                organizer.setFileUrl(storedFileName);
        }
        organizerRepository.save(organizer);
    }

    public List<RegisterMainResponseDto> getRegisterByUserId(Long userId) {
        List<Event> events = eventRepository.findAllByUserId(userId);

        return events.stream()
                .map(RegisterMainResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}

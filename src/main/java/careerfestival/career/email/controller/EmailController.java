package careerfestival.career.email.controller;

import careerfestival.career.email.EmailDto;
import careerfestival.career.email.service.EmailService;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/signup/emailConfirm")
    public ResponseEntity<Integer> mailConfirm(@RequestBody @Valid EmailDto joinRequest) {
        int num = emailService.sendEmail(joinRequest.getEmail());
        System.out.println(num+"확인");
        return new ResponseEntity<>(num,HttpStatus.OK);
    }

}

package careerfestival.career.jeongyeon.controller;

import careerfestival.career.service.JoinService;
import careerfestival.career.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String signUp(JoinDTO joinDTO) {
        joinService.join(joinDTO);
        return "200";
    }
}

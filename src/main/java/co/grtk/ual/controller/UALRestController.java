package co.grtk.ual.controller;

import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.dto.UserActivityLogRequestDTO;
import co.grtk.ual.elastic.UserActivityLogDocumentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@SecurityRequirement(name = "ual")
public class UALRestController {

    private final UserActivityLogDocumentService userActivityLogService;
    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @PostMapping("/api/logUserActivity")
    public void log(@RequestBody UserActivityLogDTO dto) {
        userActivityLogService.logUserActivity(dto);
    }

    @GetMapping("/api/listUserActivity")
    public List<UserActivityLogDTO> list(@ModelAttribute UserActivityLogRequestDTO userActivityLogRequestDTO) {
        return userActivityLogService.list(userActivityLogRequestDTO);
    }
}

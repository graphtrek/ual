package co.grtk.ual.controller;

import co.grtk.ual.dto.UserActivityLogDTO;
import co.grtk.ual.elastic.UserActivityLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class UALRestController {

    private final UserActivityLogService userActivityLogService;
    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @PostMapping("/api/log/add")
    public void log(@RequestBody UserActivityLogDTO dto) {
        userActivityLogService.saveActivityLog(dto);
    }

    @GetMapping("/api/log/list")
    public List<UserActivityLogDTO> list() {
        return userActivityLogService.list();
    }
}

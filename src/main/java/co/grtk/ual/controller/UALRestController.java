package co.grtk.ual.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UALRestController {

    @GetMapping("/api/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("OK",HttpStatus.OK);

    }
}

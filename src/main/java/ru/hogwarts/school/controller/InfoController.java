package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.PortService;

@RestController
@RequestMapping("/getPort")
public class InfoController {

    private final PortService portService;

    public InfoController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping
    public ResponseEntity<Integer> getPort() {
        int port = portService.getPort();
        return ResponseEntity.ok(port);
    }
}
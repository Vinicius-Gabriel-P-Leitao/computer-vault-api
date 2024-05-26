package tech.vault.server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.core.service.ComputerCountMemoryRamService;

import java.util.Map;

@RestController
@RequestMapping("/v1/computer")
public class ComputerCountMemoryRamController {
    @Autowired
    ComputerCountMemoryRamService service;

    @GetMapping("/memory-ram")
    public ResponseEntity<Map<Integer, Long>> countComputersByLocation() {
        return ResponseEntity.status(HttpStatus.OK).body(service.countComputerByMemoryRam());
    }
}

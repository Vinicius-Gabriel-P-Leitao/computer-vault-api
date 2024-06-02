package tech.vault.server.application.controller.computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.application.service.strategy.ComputerCountService;
import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

@RestController
@RequestMapping("/v1/computer")
public class ComputerCountLocationController {
    @Autowired
    ComputerCountService service;

    @GetMapping("/location")
    public ResponseEntity<Map<LocationComputer, Long>> countComputerLocation() {
        return ResponseEntity.status(HttpStatus.OK).body(service.computerCountLocation());
    }
}

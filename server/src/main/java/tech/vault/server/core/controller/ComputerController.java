package tech.vault.server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.core.dto.ComputerResponseBuilder;
import tech.vault.server.core.service.ComputerService;

import java.util.List;

@RestController
@RequestMapping("/computer")
public class ComputerController {
    @Autowired
    private ComputerService service;

    @GetMapping()
    public ResponseEntity<List<ComputerResponseBuilder>> getAllComputers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllComputers());
    }
}

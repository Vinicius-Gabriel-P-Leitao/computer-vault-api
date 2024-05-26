package tech.vault.server.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.core.service.ComputerCountAllService;

@RestController
@RequestMapping("/v1/computer")
public class ComputerCountAllController {
    @Autowired
    ComputerCountAllService service;

    @GetMapping("/all-stock")
    public ResponseEntity<Integer> countComputersByLocation() {
        return ResponseEntity.status(HttpStatus.OK).body(service.countAllComputers());
    }
}

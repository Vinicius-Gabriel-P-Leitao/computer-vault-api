package tech.vault.server.core.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPage {

    @GetMapping("/v1/login")
    public String login() {
        return "login";
    }
}
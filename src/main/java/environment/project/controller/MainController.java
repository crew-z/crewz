package environment.project.controller;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@Controller
public class MainController {
        @GetMapping(path = { "/", "/main" })
        public String main() {
            return "main";
        }
}


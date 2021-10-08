package dawidowski.kamil.praca.dyplomowa.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
            public String sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName="";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            return currentUserName;
        }
        return currentUserName;
    }
    @GetMapping("/hello2")
    public String sayHello2()
    {
        return "Hello2";
    }
}

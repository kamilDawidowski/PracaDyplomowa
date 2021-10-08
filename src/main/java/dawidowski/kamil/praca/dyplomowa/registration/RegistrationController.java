package dawidowski.kamil.praca.dyplomowa.registration;

import dawidowski.kamil.praca.dyplomowa.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
@PostMapping
    public String registration(@RequestBody RegistrationForm form)
    {
        return  registrationService.register(form);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}

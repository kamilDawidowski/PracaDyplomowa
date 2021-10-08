package dawidowski.kamil.praca.dyplomowa.registration;

import dawidowski.kamil.praca.dyplomowa.appuser.AppUser;
import dawidowski.kamil.praca.dyplomowa.appuser.AppUserRole;
import dawidowski.kamil.praca.dyplomowa.registration.token.ConfirmationToken;
import dawidowski.kamil.praca.dyplomowa.registration.token.ConfirmationTokenService;
import dawidowski.kamil.praca.dyplomowa.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidation emailValidation;
    private final ConfirmationTokenService confirmationTokenService;



    public String register(RegistrationForm form) {
        boolean isValidEmail = emailValidation
                .test(form.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException(("Email is not valid!!!"));
        }
        String token = appUserService.signUpUser(
                new AppUser(
                        form.getFirstName(),
                        form.getLastName(),
                        form.getEmail(),
                        form.getPassword(),
                        AppUserRole.USER

                )
        );


        return token;

    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedTime() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getEndTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }




}

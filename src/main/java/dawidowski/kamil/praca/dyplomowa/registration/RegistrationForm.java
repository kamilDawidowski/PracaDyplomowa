package dawidowski.kamil.praca.dyplomowa.registration;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationForm {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;


}

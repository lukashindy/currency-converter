package lukashindy.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserRegistration {

    private String username;
    private String password;
    private String confirmedPassword;

    public UserRegistration() {
    }
}

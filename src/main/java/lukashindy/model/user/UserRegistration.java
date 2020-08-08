package lukashindy.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistration {

    private String username;
    private String password;
    private String confirmedPassword;

}

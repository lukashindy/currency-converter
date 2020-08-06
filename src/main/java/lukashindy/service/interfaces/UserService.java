package lukashindy.service.interfaces;

import lukashindy.model.user.User;
import lukashindy.model.user.UserRegistration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistration registration);
}

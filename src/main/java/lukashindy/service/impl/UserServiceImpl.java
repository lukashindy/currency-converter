package lukashindy.service.impl;

import lukashindy.model.user.User;
import lukashindy.model.user.UserPrincipal;
import lukashindy.model.user.UserRegistration;
import lukashindy.repository.UserRepository;
import lukashindy.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistration userRegistration) {

        if (!userRegistration.getPassword().equals(userRegistration.getConfirmedPassword())) {
            throw new RuntimeException("Пароли не совпадают!");
        }

        User user = new User(userRegistration.getUsername(), passwordEncoder.encode(userRegistration.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }


}

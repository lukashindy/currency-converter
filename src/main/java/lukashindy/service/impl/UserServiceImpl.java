package lukashindy.service.impl;

import lombok.extern.slf4j.Slf4j;
import lukashindy.model.user.User;
import lukashindy.model.user.UserPrincipal;
import lukashindy.model.user.UserRegistration;
import lukashindy.repository.UserRepository;
import lukashindy.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(UserRegistration userRegistration) {

        User newUser = userRepository.findByUsername(userRegistration.getUsername());
        if (newUser != null) {
            throw new RuntimeException("Введенный вами логин уже существует");
        }

        if (!userRegistration.getPassword().equals(userRegistration.getConfirmedPassword())) {
            throw new RuntimeException("Пароли не совпадают!");
        }

        User user = new User(userRegistration.getUsername(), passwordEncoder.encode(userRegistration.getPassword()));
        userRepository.save(user);

        log.info("New user: " + userRepository.findByUsername(user.getUsername()));
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

package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder encoder;

  @Autowired
  public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder encoder) {
    this.encoder = encoder;
    this.usersRepository = usersRepository;
  }

  @Override
  public void signUp(@NonNull String username, @NonNull String password) {
    if (username.isEmpty() || password.isEmpty()) {
      throw new RuntimeException("Username/password is empty!");
    } else if (usersRepository.findByName(username).isPresent()) {
      throw new RuntimeException("User with such name already exists!");
    }

    usersRepository.save(new User(username, encoder.encode(password)));
  }
}

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

  private void checkArgs(String username, String password) {
    if (username == null || username.isEmpty()
        || password == null ||  password.isEmpty()) {
      throw new RuntimeException("Username/password is empty!");
    }
  }

  @Override
  public void signUp(@NonNull String username, @NonNull String password) {
    checkArgs(username, password);
    if (usersRepository.findByName(username).isPresent()) {
      throw new RuntimeException("User with such name already exists!");
    }

    usersRepository.save(new User(username, encoder.encode(password)));
  }

  @Override
  public User signIn(String username, String password) {
    checkArgs(username, password);

    User user = usersRepository.findByName(username).get();

    if (encoder.matches(password, user.getPassword())) {
      return user;
    }
    throw new RuntimeException("Wrong password!");
  }
}

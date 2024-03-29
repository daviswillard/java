package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class Session extends Thread {
  private final BufferedReader in;
  private final BufferedWriter out;
  private final Socket userSocket;
  private final Server server;

  private boolean isLoggedIn;
  private User user;

  private final UsersService usersService;
  private final MessagesRepositoryImpl messagesRepository;


  public Session(
      Socket socket,
      UsersService usersService,
      MessagesRepositoryImpl messagesRepository,
      Server server) throws IOException {

    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    userSocket = socket;
    this.usersService = usersService;
    this.messagesRepository = messagesRepository;
    this.server = server;
    isLoggedIn = false;
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  private void end() {
    try {
      in.close();
      out.close();
      userSocket.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  void messageToClient(String message)
      throws IOException {
    out.write(message + '\n');
    out.flush();
  }

  private boolean serverSignUp() throws IOException {
    String username;
    String password;

    try {
      messageToClient("Enter username:");
      username = in.readLine();
      messageToClient("Enter password:");
      password = in.readLine();
      usersService.signUp(username, password);

    } catch (RuntimeException ex) {
      messageToClient(ex.getMessage());
      return false;
    }
    messageToClient("Successfully signed up!\nProceed to signing in");
    return true;
  }

  private void serverSignIn() throws IOException {
    String username;
    String password;

    try {

      messageToClient("Enter username:");
      username = in.readLine();
      messageToClient("Enter password:");
      password = in.readLine();
      user = usersService.signIn(username, password);
      isLoggedIn = true;
    } catch (RuntimeException ex) {
      messageToClient(ex.getMessage());
      return;
    }
    messageToClient("Successfully signed in!");
  }

  private boolean authenticator() throws IOException {
    String message;

    while (!isLoggedIn) {
      message = in.readLine();
      if (message.equalsIgnoreCase("Sign up")) {
        if (!serverSignUp()) {
          return false;
        }
        serverSignIn();
      } else if (message.equalsIgnoreCase("Sign in")) {
        serverSignIn();
      } else if (message.equalsIgnoreCase("exit")){
        return false;
      }
    }
    return true;
  }

  private void readMessages() throws IOException {
    String message;

    while (isLoggedIn) {
      message = in.readLine();
      if (message.equalsIgnoreCase("exit")) {
        messageToClient("You have left the chat.");
        return;
      }
      message = user.getUsername() + ":" + message;
      server.printToAll(message);
      messagesRepository.save(
          new Message(
            user.getId(),
            message,
            LocalDateTime.now()
          )
      );
    }
  }


  private void startSession() {

    try {
      messageToClient("Hello from server");
      messageToClient("Sign up or sign in");
      if (!authenticator()) {
        messageToClient("Authentication failed! Closing connection now");
        end();
        return;
      }
      readMessages();
      end();
    } catch (IOException ex) {
      ex.printStackTrace();

      try {
        if (userSocket != null) {
          userSocket.close();
        }
        if (in != null) {
          in.close();
        }
        if (out != null) {
          out.close();
        }
      } catch (IOException exception) {
        exception.printStackTrace();
        throw new RuntimeException(exception.getMessage());
      }
    }
  }

  @Override
  public void run() {
    startSession();
  }
}

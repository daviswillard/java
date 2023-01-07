package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Parameters(separators = "=")
public class Server {
  @Parameter(names = "--port")
  private int port;


  private BufferedReader in;
  private BufferedWriter out;

  private final UsersService usersService;
  private final MessagesRepositoryImpl messagesRepository;

  @Autowired
  public Server(UsersService usersService, MessagesRepositoryImpl messagesRepository) {
    this.usersService = usersService;
    this.messagesRepository = messagesRepository;
  }

  private void messageToClient(String message)
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
    } catch (RuntimeException ex) {
      messageToClient(ex.getMessage());
      return false;
    }
    usersService.signUp(username, password);
    return true;
  }

  private boolean readMessages() throws IOException {
    String message;

    while (true) {
      message = in.readLine();
      if (message.equalsIgnoreCase("work complete")) {
        return true;
      }
      if (message.equals("serverSignUp")) {
        if (serverSignUp()){
          messageToClient("Successful!");
        }
      } else {
        messageToClient("Message " + message + " received!");
      }
    }
  }

  public void serve()
  throws IOException{
    try (ServerSocket socket = new ServerSocket(port)) {

      while (true) {
        Socket clientSocket = socket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        messageToClient("Hello from Server!");

        if (readMessages()) {
          break;
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      in.close();
      out.close();
    }
  }
}

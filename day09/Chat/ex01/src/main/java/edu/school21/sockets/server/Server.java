package edu.school21.sockets.server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Parameters(separators = "=")
public class Server {

 synchronized void printToAll(String message) throws IOException {
    for (Session iter: sessionList) {
      iter.messageToClient(message);
    }
 }

  @Parameter(names = "--port")
  private int port;

  private final UsersService usersService;
  private final MessagesRepositoryImpl messagesRepository;

  private final List<Session> sessionList = new LinkedList<>();

  @Autowired
  public Server(UsersService usersService, MessagesRepositoryImpl messagesRepository) {
    this.usersService = usersService;
    this.messagesRepository = messagesRepository;
  }

  public void serve()
  throws IOException{
    try (ServerSocket socket = new ServerSocket(port)) {

      while (true) {
        Session session = new Session(socket.accept(), usersService, messagesRepository, this);
        sessionList.add(session);
        session.start();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

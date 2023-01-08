package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    if (args.length == 0) {
      throw new RuntimeException("No arguments were provided!");
    } else if (args.length > 1) {
      throw new RuntimeException("You need exactly one argument: f.e. --server-port=8081");
    }


    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

    Server server = context.getBean(Server.class);
    JCommander.newBuilder().addObject(server).build().parse(args);
    try {
      server.serve();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

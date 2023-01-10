package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import edu.school21.sockets.client.Client;
import java.io.IOException;

public class Mains1 {

  public static void main(String[] args) {
    if (args.length == 0) {
      throw new RuntimeException("No arguments were provided!");
    } else if (args.length > 1) {
      throw new RuntimeException("You need exactly one argument: f.e. --server-port=8081");
    }

    Client client  = new Client();

    JCommander.newBuilder().addObject(client).build().parse(args);
    try {
      client.connect();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

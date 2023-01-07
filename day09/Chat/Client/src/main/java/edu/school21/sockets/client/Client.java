package edu.school21.sockets.client;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

@Parameters(separators = "=")
public class Client {

  private static final Scanner console = new Scanner(System.in);

  @Parameter(names = "--port")
  private int port;

  private Socket client;

  private BufferedReader in;
  private BufferedWriter out;

  private void messageToServer(String message)
      throws IOException {
    out.write(message + '\n');
    out.flush();
  }

  private boolean signUp(String message) throws IOException{

    while (true) {
      if (message.equalsIgnoreCase("successful!")) {
        return true;
      } else if (message.equalsIgnoreCase("Sign up failed!")) {
        return false;
      }
      messageToServer(console.nextLine());
      message = in.readLine();
      System.out.println(message);
    }
  }
  public void connect() throws IOException {
    try {
      client = new Socket("localhost", port);
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

      System.out.println(in.readLine());

      while (true) {
        String message = console.nextLine();
        messageToServer(message);
        message = in.readLine();
        System.out.println(message);
        if (message.equalsIgnoreCase("enter username:")) {
          if (signUp(message)) {
            break;
          }
        }
      }
    } finally {
      messageToServer("Work complete");
      client.close();
      in.close();
      out.close();

    }
  }
}

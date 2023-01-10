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
  private boolean isConnected;

  @Parameter(names = "--server-port")
  private int port;

  private Socket client;

  private BufferedReader in;
  private BufferedWriter out;

  private void messageToServer(String message)
      throws IOException {
    out.write(message + '\n');
    out.flush();
  }

  private void dialogWithServer() {
    Runnable read = () -> {
      String message;
      try {
        while (true) {
          message = in.readLine();
          if (message.equals("Authentication failed! Closing connection now")
              || message.equals("You have left the chat.")) {
            System.out.println(message);
            break;
          }
          if (!message.isEmpty()) {
            System.out.println(message);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      isConnected = false;
    };

    Runnable write = () -> {
      while (true) {
        String message;
        try {
          message = in.readLine();
          if (!isConnected) {
            break;
          } else {
            messageToServer(message);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };

    Thread writer = new Thread(write);
    Thread reader = new Thread(read);

    writer.start();
    reader.start();
    try {
      writer.join();
      reader.join();
    } catch (InterruptedException expected) {

    }
  }



  public void connect() throws IOException {
    try {
      client = new Socket("localhost", port);
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

      isConnected = true;

      dialogWithServer();
    } finally {
      client.close();
      in.close();
      out.close();
    }
  }
}

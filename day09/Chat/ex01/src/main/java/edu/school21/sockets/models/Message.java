package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
  private Long id;
  private Long author;
  private String body;
  private LocalDateTime timestamp;

  public Message() {
  }

  public Message(Long author, String body, LocalDateTime timestamp) {
    this.author = author;
    this.body = body;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAuthor() {
    return author;
  }

  public void setAuthor(Long author) {
    this.author = author;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", author=" + author +
        ", body='" + body + '\'' +
        ", timestamp=" + timestamp +
        '}';
  }
}

package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
    "edu.school21.sockets.server",
    "edu.school21.sockets.services",
    "edu.school21.sockets.repositories"
})
public class SocketsApplicationConfig {
  @Value("${db.url}")
  private String url;

  @Value("${db.user}")
  private String username;

  @Value("${db.password}")
  private String password;

  @Value("${db.driver.name}")
  private String driver;
  @Bean
  public HikariDataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();

    dataSource.setJdbcUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(driver);

    return dataSource;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}

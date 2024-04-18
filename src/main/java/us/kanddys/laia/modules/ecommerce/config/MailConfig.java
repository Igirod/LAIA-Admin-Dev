package us.kanddys.laia.modules.ecommerce.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

   @Value("${credential.mail.username}")
   private String credentialMailUser;
   @Value("${credential.mail.password}")
   private String credentialMailPassword;
   @Value("${credential.mail.port}")
   private Integer credentialMailPort;
   @Value("${credential.mail.host}")
   private String credentialMailHost;

   @Bean
   public JavaMailSender getJavaMailSender() {
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost(credentialMailHost);
      mailSender.setPort(credentialMailPort);
      mailSender.setUsername(credentialMailUser);
      mailSender.setPassword(credentialMailPassword);
      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.debug", "true"); // Activa el modo de depuración para ver los logs
      return mailSender;
   }

}

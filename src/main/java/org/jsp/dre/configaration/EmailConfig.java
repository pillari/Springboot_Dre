package org.jsp.dre.configaration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	
	
	@Bean
	JavaMailSender javaMailSender() {
		
		
		//implementaton class javaemailsender
		
		JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
		
		
		jmsi.setHost("smtp.gmail.com");
		jmsi.setPort(587);									
		jmsi.setUsername("pillariyaswanth@gmail.com");

		jmsi.setPassword("gbcc trhm grey dezy"); 
				
		Properties props = jmsi.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		
		
		return jmsi;
	}
	
	

}

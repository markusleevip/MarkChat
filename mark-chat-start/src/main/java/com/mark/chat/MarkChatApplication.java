package com.mark.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

/**
 * @author Markus
 */
@SpringBootApplication
public class MarkChatApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(MarkChatApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}

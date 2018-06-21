package com.company.project;

import com.company.project.Controllers.MailMail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectApplication {

	public static void main(String[] args) {

	/*	ApplicationContext context =
				new ClassPathXmlApplicationContext("context.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("biblioteka.information.mail@gmail.com",
				"iwona.strubczewska@gmail.com",
				" Temat	",
				"Tresc maila");
*/

		SpringApplication.run(ProjectApplication.class, args);
	}
}

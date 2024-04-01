package com.pocemssanar.app.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.pocemssanar.app.documents")
public class PocemssanarApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocemssanarApplication.class, args);
	}

}

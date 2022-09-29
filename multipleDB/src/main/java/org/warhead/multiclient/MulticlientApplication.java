package org.warhead.multiclient;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MulticlientApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MulticlientApplication.class, args);
		System.out.println("multiple DB Initiated");
	}

}

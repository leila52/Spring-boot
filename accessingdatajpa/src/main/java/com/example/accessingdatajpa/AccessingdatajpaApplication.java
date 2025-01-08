package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingdatajpaApplication {
	private static final Logger log = LoggerFactory.getLogger(AccessingdatajpaApplication.class);

	public static void main(String[] args) {
	  SpringApplication.run(AccessingdatajpaApplication.class);
	}
  
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
	  return (args) -> {
		// save a few customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));
		repository.save(new Customer("David", "Guerrero"));
		repository.save(new Customer("Luna", "Dessler"));
  
		// fetch all customers
		log.info("todos los clientes:");
		log.info("-------------------------------");
		repository.findAll().forEach(customer -> {
		  log.info(customer.toString());
		});
		log.info("");
  
		// fetch an individual customer by ID
		Customer customer = repository.findById(1L);
		log.info("cliente con el id(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");
  
		// fetch customers by last name
		log.info("cliente con appellido Gerrero:");
		log.info("--------------------------------------------");
		repository.findByLastName("Guerrero").forEach(bauer -> {
		  log.info(bauer.toString());
		});
		log.info("");
		// fetch customers by last name
		log.info("cliente con appellido Dessler:");
		log.info("--------------------------------------------");
		repository.findByLastName("Dessler").forEach(bauer -> {
		  log.info(bauer.toString());
		});
		log.info("");
	  };
	}
  
  }

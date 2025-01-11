package com.tutomysql.tutomysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TutomysqlApplication {
private static final Logger log = LoggerFactory.getLogger(TutomysqlApplication.class);

	public static void main(String[] args) {
	  SpringApplication.run(TutomysqlApplication.class);
	}
  
	@Bean
	public CommandLineRunner demo(CostumerRepositorio repository) {
	  return (args) -> {
		// guardar los nuevos costumers
		/*repository.save(new Costumer("Jack", "Bauer"));
		repository.save(new Costumer("Chloe", "O'Brian"));
		repository.save(new Costumer("Kim", "Bauer"));
		repository.save(new Costumer("David", "Palmer"));
		repository.save(new Costumer("Michelle", "Dessler"));
  */
		 
		//repository.save(new Costumer("lorena", "garvin"));
		
		// listar todos los costumers
		log.info("Costumers utilizando el findALL():");
		log.info("-------------------------------");
		repository.findAll().forEach(customer -> {
		  log.info(customer.toString());
		});
		log.info("");
  
		// listar según el id
		Costumer customer = repository.findById(1L);
		//el 1l es que indica que es de tipo long. si se quita y lo toma como int sigue funcionando
		log.info("Costumer según su id findById(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");
  
		// listar los costumers segun su apellido
		log.info("Costumers según el apellido('Bauer'):");
		log.info("--------------------------------------------");
		repository.findByapellido("Bauer").forEach(bauer -> {
		  log.info(bauer.toString());
		});
		log.info("");
	  };
	}

}

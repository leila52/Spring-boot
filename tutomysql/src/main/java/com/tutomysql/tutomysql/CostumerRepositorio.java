package com.tutomysql.tutomysql;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CostumerRepositorio extends CrudRepository<Costumer, Long> {
    List<Costumer> findByapellido(String apellido);

    Costumer findById(long id);
  
    
}

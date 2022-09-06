package br.com.bennytech.projeto_baeck_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.bennytech.projeto_baeck_end.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


}

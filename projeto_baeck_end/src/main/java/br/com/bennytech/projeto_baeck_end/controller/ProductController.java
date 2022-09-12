package br.com.bennytech.projeto_baeck_end.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bennytech.projeto_baeck_end.model.Product;
import br.com.bennytech.projeto_baeck_end.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> obterTodos(){
        return productService.findAll();
    }

   @GetMapping("/{id}") 
   public Optional<Product> obterPorId(@PathVariable Integer id){
    return productService.findById(id);
   }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "Produto com id "+ id +" foi deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable Integer id){
        return productService.update(id, product);
    }

}




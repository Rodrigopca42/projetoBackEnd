package br.com.bennytech.projeto_baeck_end.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.bennytech.projeto_baeck_end.model.Product;

@Repository
public class ProductRepository {
    
    private List <Product> products = new ArrayList<>();
    private Integer lastId = 0;

    
    /**
     * Retorna uma lista de produtos.
     * @return Lista de produtos.
     */
    public List<Product> obterTodos(){
        return products;
    }


    /**
     * Método que retorna o produto encontrado pelo seu id.
     * @param id do produto que será encontrado.
     * @return  Retorna um preduto caso seja encontrado.
     */
    public Optional<Product> obeterPorId(Integer id){
        return products
               .stream()
               .filter(product -> product.getId() == id)
               .findFirst();
    }

    /**
     * Metodo para adicionar um produto a lista.
     * @param product que será adicionado.
     * @return Retorna o produto que foi adicionado a lista.
     */
    public Product addProduct(Product product){

        lastId++;
        product.setId(lastId);
        products.add(product);
        return product;
    }

    /**
     * Método para excluir um produto por id.
     * @param id do produto que será excluido.
     */ 
    public void delete(Integer id){
        products.removeIf(product -> product.getId() == id);
     }

     /**
      * Método para fazer a atualização do produto.
      * @param product que será atualizado.
      * @return um produto atualizado.
     */
    public Product update(Product product){

        //Encontrar o produto da lista.
        Optional<Product> productFound = obeterPorId(product.getId());
            if(productFound.isEmpty()){
            throw new InputMismatchException("Produto não encontrado.");
            }

            //Remover o produto antigo da lista.
            delete(product.getId());
       
            //Depois adicionar o novo produto.
            products.add(product);
       
            return product;
    }


}

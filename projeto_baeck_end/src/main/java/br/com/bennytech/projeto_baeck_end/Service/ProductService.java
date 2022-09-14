package br.com.bennytech.projeto_baeck_end.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bennytech.projeto_baeck_end.model.Product;
import br.com.bennytech.projeto_baeck_end.repository.ProductRepository;
import br.com.bennytech.projeto_baeck_end.shared.ProductDTO;



@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    /**
     * Retorna uma lista de produtos.
     * @return Lista de produtos.
     */
    public List<ProductDTO> findAll(){
        return productRepository.findAll();
    }
    
     /**
     * Método que retorna o produto encontrado pelo seu id.
     * @param id do produto que será encontrado.
     * @return  Retorna um preduto caso seja encontrado.
     */
    public Optional<ProductDTO> findById(Integer id){
        return productRepository.findById(id);
    }

    /**
     * Metodo para adicionar um produto a lista.
     * @param product que será adicionado.
     * @return Retorna o produto que foi adicionado a lista.
     */
    public ProductDTO addProduct(ProductDTO productDto){
        return productRepository.save(product);
       
    }

     /**
     * Método para excluir um produto por id.
     * @param id do produto que será excluido.
     */ 
    public void delete(Integer id){
        productRepository.deleteById(id);
     }

     /**
      * Método para fazer a atualização do produto.
      * @param product que será atualizado.
      * @return um produto atualizado.
     */
    public ProductDTO update(Integer id, ProductDTO product){

        product.setId(id);
        return productRepository.saveAndFlush(product);
    }

}

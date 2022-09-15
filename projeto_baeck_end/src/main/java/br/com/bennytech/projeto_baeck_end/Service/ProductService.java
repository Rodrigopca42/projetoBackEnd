package br.com.bennytech.projeto_baeck_end.service;

import java.text.Collator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bennytech.projeto_baeck_end.model.Product;
import br.com.bennytech.projeto_baeck_end.model.exception.ResourceNotFoundException;
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
        List<Product> products = productRepository.findAll();

       return products.stream().map(product -> new ModelMapper().map(product, ProductDTO.class)).collect(Collectors.toList());
    }
    
     /**
     * Método que retorna o produto encontrado pelo seu id.
     * @param id do produto que será encontrado.
     * @return  Retorna um preduto caso seja encontrado.
     */
    public Optional<ProductDTO> findById(Integer id){
        //Obtendo optional de productdto
      Optional<Product> product = productRepository.findById(id);

      //Se não encontrar, lança excepotion
      if( product.isEmpty()){
        throw new ResourceNotFoundException("Produto com o id "+ id + " não foi encontrado.");
      }

      // Convertendo meu optional de product em productDto
      ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);

      //Criando e retornando um optional de productDto
      return Optional.of(dto);

    }

    /**
     * Metodo para adicionar um produto a lista.
     * @param product que será adicionado.
     * @return Retorna o produto que foi adicionado a lista.
     */
    public ProductDTO addProduct(ProductDTO productDto){
        //Removendo o id para conseguir fazer o cadastro
        productDto.setId(null);

        //Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter o productDto em um prodruct.
        Product product = mapper.map(productDto, Product.class);
        
        //Salvar o product no Banco de Dados
        product = productRepository.save(product);

        productDto.setId(product.getId());

        //Retornar o ProductDTO atualizado
        return productDto;
       
    }

     /**
     * Método para excluir um produto por id.
     * @param id do produto que será excluido.
     */ 
    public void delete(Integer id){

        //Verificar se o produto existe
        Optional<Product> product = productRepository.findById(id);

        //Se não encontrar, lança excepotion
      if( product.isEmpty()){
        throw new ResourceNotFoundException("Não foi possível deletar o produto com o id "+ id + ", produto não foi existe.");
      }

        //Deleta o produto pelo id.
        productRepository.deleteById(id);
    }

     /**
      * Método para fazer a atualização do produto.
      * @param product que será atualizado.
      * @return um produto atualizado.
     */
    public ProductDTO update(Integer id, ProductDTO productDto){

        //Passar o id para o productDto.
        productDto.setId(id);

        //Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter o ProductDTO em um Product.
        Product product = mapper.map(productDto, Product.class);
        
        //Atualizar o produto no Banco de Dados.
        productRepository.save(product);

        //Retornar o productDto atualizado.
        return productDto;
    }


}

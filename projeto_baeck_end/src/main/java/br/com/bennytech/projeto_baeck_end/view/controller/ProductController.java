package br.com.bennytech.projeto_baeck_end.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.com.bennytech.projeto_baeck_end.shared.ProductDTO;
import br.com.bennytech.projeto_baeck_end.view.model.ProductRequest;
import br.com.bennytech.projeto_baeck_end.view.model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponse>> obterTodos(){

        List<ProductDTO> products = productService.findAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> reply = products.stream()
        .map(productDto -> mapper.map(productDto, ProductResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

 
@GetMapping("/{id}") 
   public ResponseEntity<Optional<Product>> obterPorId(@PathVariable Integer id){

    try {
        Optional<ProductDTO> dto = productService.findById(id);
    
        ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);
        
        return new ResponseEntity(Optional.of(product), HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


   }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productReq){
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDto = mapper.map(productReq, ProductDTO.class);
        productDto =  productService.addProduct(productDto);

        return new ResponseEntity<>(mapper.map(productDto, ProductResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(productReq, ProductDTO.class);
         
        productDTO = productService.update(id, productDTO);

        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
    }

}




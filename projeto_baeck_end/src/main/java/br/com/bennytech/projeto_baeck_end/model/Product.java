package br.com.bennytech.projeto_baeck_end.model;

public class Product {
    
    private Integer id;
    private String name;
    private Integer quantit;
    private Double price;
    private String note;

    public Integer getId() {
        return id;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
   
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }
   
    public Integer getQuantit() {
        return quantit;
    }
   
    public void setQuantit(Integer quantit) {
        this.quantit = quantit;
    }
   
    public Double getPrice() {
        return price;
    }
   
    public void setPrice(Double price) {
        this.price = price;
    }
   
    public String getNote() {
        return note;
    }
   
    public void setNote(String note) {
        this.note = note;
    }

    

}

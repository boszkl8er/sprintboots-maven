package hello.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Stock {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
    private final String stock;
	private Double price;
	
	public Stock(String stock, Double price) {
		this.stock = stock;
		this.price = price;
	}

    public Stock(String id, String stock, Double price) {
    	this.id = id;
        this.stock = stock;
		this.price = price;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }

    public String getStock() {
        return stock;
    }
	
	public Double getPrice(){
		return price;
	}
	
	public void setPrice(Double price){
		this.price = price;
	}
}
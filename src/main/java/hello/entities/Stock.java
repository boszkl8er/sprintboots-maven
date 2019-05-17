package hello;

public class Stock {

    private final String stock;
	private Double price;

    public Stock(String stock, Double price) {
        this.stock = stock;
		this.price = price;
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
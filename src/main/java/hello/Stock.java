package hello;

public class Stock {

    private final long id;
    private final String stock;
	private String price;

    public Stock(long id, String stock) {
        this.id = id;
        this.stock = stock;
		this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getStock() {
        return stock;
    }
	
	public String getPrice(){
		return price;
	}
	
	public void setPrice(String price){
		this.price = price;
	}
}
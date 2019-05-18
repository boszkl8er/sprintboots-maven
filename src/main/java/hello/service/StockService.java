package hello.service;
import hello.entities.Stock;
import hello.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getAllStock(){
			return stockRepository.findAll();
	}
	
	public void addStock() {
		Stock tempStock = new Stock("AKK",12.32);
		stockRepository.save(tempStock);
	}

	public void addStock(Stock stock){
		stockRepository.save(stock);
	}

	public void deleteStock(String id) {
		stockRepository.deleteById(id);
	}
}
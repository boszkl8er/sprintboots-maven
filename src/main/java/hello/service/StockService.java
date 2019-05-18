package hello.service;
import hello.entities.Stock;
import hello.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockService {

	@Autowired
	private static StockRepository stockRepository;
	
	public static List<Stock> getAllStock(){
			return stockRepository.findAll();
	}

	public static void addStock(Stock stock){
		stockRepository.save(stock);
	}

	public static void deleteStock(String id) {
		stockRepository.deleteById(id);
	}
}
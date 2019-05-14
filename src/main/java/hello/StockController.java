package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    //private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/stock")
    public Stock stock(@RequestParam(value="price", defaultValue="0") String name) {
        return new Stock(counter.incrementAndGet(),
                            String.format(name));
    }
}
package microservices.example.cryptocurrencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CryptocurrencyExchangeController {

	@Autowired
	private Environment environment;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getExchangeValueFallback")
	@GetMapping("/cryptocurrency-exchange/name/{name}/convertTo/{convertTo}")
	public ResponseEntity<CoinBean> getExchangeValue(@PathVariable String name, @PathVariable String convertTo) {
		ResponseEntity<CoinBean[]> responseEntity = restTemplate
				.getForEntity(environment.getProperty("coin.market.cap.api") + "/" + name, CoinBean[].class);
		Object[] coinBeans = responseEntity.getBody();
		System.out.println(coinBeans);
		CoinBean coin = (CoinBean) coinBeans[0];
		coin.setConvertTo(convertTo);
		coin.setPort(Integer.parseInt(environment.getProperty("server.port")));
		return ResponseEntity.status(HttpStatus.OK).body(coin);
	}

	// fall back method
	public ResponseEntity<CoinBean> getExchangeValueFallback(@PathVariable String name,
			@PathVariable String convertTo) {

		// fault tolerance

		System.out.println("Oppsh something went wrong");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

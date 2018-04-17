package microservices.example.cryptocurrencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CryptocurrencyExchangeController {

	@Autowired
	private Environment environment;

	@GetMapping("/cryptocurrency-exchange/name/{name}/convertTo/{convertTo}")
	public CoinBean getExchangeValue(@PathVariable String name, @PathVariable String convertTo) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CoinBean[]> responseEntity = restTemplate
				.getForEntity(environment.getProperty("coin.market.cap.api") + "/" + name, CoinBean[].class);
		Object[] coinBeans = responseEntity.getBody();
		CoinBean coin = (CoinBean) coinBeans[0];
		coin.setConvertTo(convertTo);
		coin.setPort(Integer.parseInt(environment.getProperty("server.port")));
		return coin;
	}
}

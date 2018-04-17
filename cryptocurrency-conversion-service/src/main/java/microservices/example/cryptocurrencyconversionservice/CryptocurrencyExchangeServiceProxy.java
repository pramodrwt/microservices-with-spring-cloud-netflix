package microservices.example.cryptocurrencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cryptocurrency-exchange-service", url = "localhost:8000")
public interface CryptocurrencyExchangeServiceProxy {

	@GetMapping("/cryptocurrency-exchange/name/{name}/convertTo/{convertTo}")
	public CryptoCurrencyConvertionBean getExchangeValue(@PathVariable("name") String name, @PathVariable("convertTo") String convertTo);
}

package microservices.example.cryptocurrencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CryptoCurrencyConversionController {
	@Autowired
	CryptocurrencyExchangeServiceProxy exchangeProxy;

	@HystrixCommand(fallbackMethod = "convertCurrencyFallback")
	@GetMapping("/cryptocurrency-converter/name/{name}/convertTo/{convertTo}/quantity/{quantity}")
	public ResponseEntity<CryptoCurrencyConvertionBean> convertCurrency(@PathVariable String name,
			@PathVariable String convertTo, @PathVariable BigDecimal quantity) {
		CryptoCurrencyConvertionBean response = exchangeProxy.getExchangeValue(name, convertTo);
		BigDecimal totalValue = null;
		if (convertTo.equals("usd")) {
			totalValue = quantity.multiply(response.getPrice_usd());
		} else {
			totalValue = quantity.multiply(response.getPrice_btc());
		}
		return ResponseEntity.status(HttpStatus.OK).body(new CryptoCurrencyConvertionBean(response.getId(), name,
				convertTo, response.getPrice_btc(), response.getPrice_usd(), quantity, totalValue, response.getPort(),response.getSymbol()));
	}

	// fall back method
	public ResponseEntity<CryptoCurrencyConvertionBean> convertCurrencyFallback(@PathVariable String name,
			@PathVariable String convertTo, @PathVariable BigDecimal quantity) {
		// fault tolerance
		System.out.println("Something went wrong with exchange service");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}

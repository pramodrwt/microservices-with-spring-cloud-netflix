package microservices.example.cryptocurrencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoCurrencyConversionController {
	@Autowired
	CryptocurrencyExchangeServiceProxy exchangeProxy;

	@GetMapping("/cryptocurrency-converter/name/{name}/convertTo/{convertTo}/quantity/{quantity}")
	public CryptoCurrencyConvertionBean convertCurrencyFeign(@PathVariable String name, @PathVariable String convertTo,
			@PathVariable BigDecimal quantity) {
		CryptoCurrencyConvertionBean response = exchangeProxy.getExchangeValue(name, convertTo);
		BigDecimal totalValue = null;
		if (convertTo.equals("usd")) {
			totalValue = quantity.multiply(response.getPrice_usd());
		} else {
			totalValue = quantity.multiply(response.getPrice_btc());
		}
		return new CryptoCurrencyConvertionBean(response.getId(), name, convertTo, response.getPrice_btc(),
				response.getPrice_usd(), quantity, totalValue, response.getPort(), response.getSymbol());

	}
}

package microservices.example.cryptocurrencyexchangeservice;

import java.math.BigDecimal;

public class CoinBean {
	private String id;
	private String name;
	private String convertTo;
	private BigDecimal price_btc;
	private BigDecimal price_usd;
	private int port;
	private String symbol;

	public CoinBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoinBean(String id, String name, String convertTo, BigDecimal price_btc, BigDecimal price_usd, int port,
			String symbol) {
		super();
		this.id = id;
		this.name = name;
		this.convertTo = convertTo;
		this.price_btc = price_btc;
		this.price_usd = price_usd;
		this.port = port;
		this.symbol = symbol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConvertTo() {
		return convertTo;
	}

	public void setConvertTo(String convertTo) {
		this.convertTo = convertTo;
	}

	public BigDecimal getPrice_btc() {
		return price_btc;
	}

	public void setPrice_btc(BigDecimal price_btc) {
		this.price_btc = price_btc;
	}

	public BigDecimal getPrice_usd() {
		return price_usd;
	}

	public void setPrice_usd(BigDecimal price_usd) {
		this.price_usd = price_usd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "CoinBean [id=" + id + ", name=" + name + ", convertTo=" + convertTo + ", price_btc=" + price_btc
				+ ", price_usd=" + price_usd + ", port=" + port + ", symbol=" + symbol + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getConvertTo()=" + getConvertTo() + ", getPrice_btc()="
				+ getPrice_btc() + ", getPrice_usd()=" + getPrice_usd() + ", getPort()=" + getPort() + ", getSymbol()="
				+ getSymbol() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

let elem = document.querySelector('.search');
let instance = M.Autocomplete.init(elem);
let name;
/*
 * let elem = document.querySelector('select'); let instance =
 * M.FormSelect.init(elem);
 */
let url = "http://localhost:8765";

$(document).ready(()=> {
    $('select').formSelect();
	// getData();
	$.getJSON("./cryptocurrencies.json", function(cryptocurrencies){
		initilizeAutocompleteConfig(cryptocurrencies);
	})
});

// initialize autocomplete configration
function initilizeAutocompleteConfig(cryptocurrencies) {
$('input.search').autocomplete({
	data: cryptocurrencies,
	onAutocomplete : function(coinName) {
		name=(coinName.trim().replace(/ /g, "-")).toLowerCase();
		getCoinDetails(name);
	},
	limit : 20,
});
}

function getCoinDetails(coinName) {
	coinInfo={};
	$.ajax({
		dataType : 'json',
		url : url+`/cryptocurrency-exchange-service/cryptocurrency-exchange/name/${coinName}/convertTo/usd`,
		type : 'GET',
		success : function(coinInfo) {
			if(coinInfo) {
				appendCoinDetails(coinInfo);
			}
		}
	});
}
 
  // get all the coins
function getData() { 
	 $.ajax({
		 url: "https://api.coinmarketcap.com/v1/ticker/?limit=50", 
		 success: function(result){ 
			 let p={}; 
			 result.map(e=> { p[e.name]=null; })
			 console.log(p); 
		 }
	 });
}

// append coin details
function appendCoinDetails(coinInfo) {
	 let htmlText=`
	 <div class="col s12">
	  <div class="row">
	    <div class="card col s4 push-s4">
	      <div class="card-content white-text" style="padding: 15px 0px 6px 8px;">
	        <span class="card-title blue-text text-darken-2">${coinInfo.name} (${coinInfo.symbol})</span>
	      </div>

	      <div class="col s12">
	        <div class="col s6">
	          <label for="disabled">Price(In USD) : </label>
	          <i class="fa fa-dollar"> ${coinInfo.price_usd}</i>
	        </div>
	        <div class="col s6">
	          <label for="disabled">Price(In BTC) : </label>
	          <i class="fa fa-bitcoin"> ${coinInfo.price_btc}</i>
	        </div>
	      </div>

	      <div class="col s12 input-field">
	        <div class="input-field col s6">
	          <input placeholder="Amount" id="quantity" type="text" value="1">
	          <label>Amount</label>
	        </div>

	        <div class="input-field col s6">
	          <select class="blue-text text-darken-2">
	            <option  selected value="USD">USD</option>
	            <option value="BITCOIN">BITCOIN</option>
	          </select>
	          <label>Convert To</label>
	        </div>

	        <div class="col s12 input-field">
	        <div class="col s6 tot_amt" >
	        </div>
	        <div class="col s6">
	          <button class="btn waves-effect waves-light" type="submit" onClick="calculateTotalAmount()">Calculate
	            <i class="material-icons right">send</i>
	          </button>
	          </div>
	        </div>
	      </div>
	    </div>
	  `;
	  $('.base').html('');
	  $('.base').append(htmlText);
	  M.updateTextFields();
	  $('select').formSelect();
	  $('.collapsible').collapsible();
	}

// calculate amount
function calculateTotalAmount() {
	let quantity= $('#quantity').val();
	let convertTo=$('.select-dropdown').val().toLowerCase();
	if(!quantity) {
	return	M.toast({html: 'Please enter amount!'})	
	}
	
	$.ajax({
		dataType : 'json',
		url : url+`/cryptocurrency-conversion-service/cryptocurrency-converter/name/${name}/convertTo/${convertTo}/quantity/${quantity}`,
		type : 'GET',
		success : function(calculateInfo) {
			if(calculateInfo) {
				let innerText=`<label for="disabled">Total Amount : </label>`;
				 $('.tot_amt').html('');
				 if(calculateInfo.convertTo==="usd") {
					 innerText += `<i class="fa fa-dollar"> ${calculateInfo.calculatedAmount}</i>`;
				 }else {
					 innerText += `<i class="fa fa-bitcoin"> ${calculateInfo.calculatedAmount}</i>`;
				 }
				  $('.tot_amt').append(innerText);
			}
		}
	});
	
}


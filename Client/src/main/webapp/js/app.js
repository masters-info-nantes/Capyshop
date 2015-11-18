var cart = [];
var products = [];

$(document).ready(function() {
    $(".only-numbers").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) ||
             // Allow: Ctrl+C
            (e.keyCode == 67 && e.ctrlKey === true) ||
             // Allow: Ctrl+X
            (e.keyCode == 88 && e.ctrlKey === true) ||
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});

var getProducts = function()
{
	$.soap({
	    url: 'http://localhost:9763/services/Shop/',
	    namespaceURL:'http://shop.service.alma.com'
	});
	$.soap({
	    method: 'getProducts',
	    data: {
	    },
	    soap12: true,
	    success: function (soapResponse) {
	        // do stuff with soapResponse
	    	$('.loading').hide();
	        updateProducts(soapResponse.toJSON());
	    },
	    error: function (soapResponse) {
	        console.error('that other server might be down... or the CORS...');
	        error();
	    }
	});
};

var placeOrder = function()
{
	var data = {
	    	products:["Capybara roux","Capybara roux"],
	    	name:$('#name').val(),
	    	address:$('#address').val(),
	    	postCode:$('#post-code').val(),
	    	city:$('#city').val()
	    };
	console.log("validate order ");
	console.log(data);
	$.soap({
	    url: 'http://localhost:9763/services/Shop/',
	    namespaceURL:'http://shop.service.alma.com'
	});
	$.soap({
	    method: 'placeOrder',
	    data:data,
	    soap12: true,
	    success: function (soapResponse) {
	        // do stuff with soapResponse
		    	alert(soapResponse.toString());
		        console.log(soapResponse.toJSON());
	    },
	    error: function (soapResponse) {
	    	console.error(soapResponse.toString());
	        console.error('that other server might be down... or the CORS...');
	        error();
	    }
	});
};

var error = function()
{
	$('.products').html('<img src="img/error.png"/><h3>Something capywrong happend...</h3><button onclick="getProducts()">Retry</button>');
};

var cleanProducts = function(o)
{
	return o['#document']['ns:getProductsResponse']['ns:return'];
};

var updateProducts = function(p)
{
	products=cleanProducts(p);
	
	console.log(p);
	$('.products').html("<h3>Products</h3><ul></ul>");
	for(var i =0; i<products.length;i++)
	{
		$('.products ul').append().append('<li><div>'+products[i].name["_"]+'</div><div>Quantity: '+products[i].quantity["_"]+'</div><div>Price: '+products[i].price["_"]+'e</div><div><button onclick="add(\''+i+'\')">Ajouter</button><div/></li>');
	}
};

var updateCart = function()
{
	if(cart.length==0)
		$('.cart').html("<h3>Your cart is empty</h3>");
	else
	{
		$('.cart').html("<h3>My Cart</h3><ul></ul>");
		for(var i =0; i<cart.length;i++)
		{
			$('.cart ul').append('<li><div>'+cart[i].name["_"]+'</div><div><button onclick="rem(\''+i+'\')">Remove</button></div></li>');
		}
		$('.cart').append('<button onclick="order()">Order</button>');
	}
};

var rem = function(id)
{
	cart.splice(id,1);
	updateCart();
}

var add = function(id)
{
	cart.push(products[id]);
	updateCart();
};

var dismiss = function()
{
	$('.popup').hide();
};

var order = function()
{
	$('.order').show();
};

var validate = function()
{
	placeOrder();
	$('.popup').hide();
	$('.payment').show();
};

var pay = function()
{
};

getProducts();
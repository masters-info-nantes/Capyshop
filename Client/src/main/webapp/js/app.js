var cart = [];
var products = [];
var total = 0;
var order = null;

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
	    	products:cart.map(function(obj){return obj.id["_"];}),
	    	name:$('#name').val(),
	    	address:$('#address').val(),
	    	postCode:$('#post-code').val(),
	    	city:$('#city').val()
	    };
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
		    console.log(soapResponse.toJSON());
		    order = cleanOrder(soapResponse.toJSON());
	    },
	    error: function (soapResponse) {
	    	console.error(soapResponse.toString());
	    }
	});
};

var pay = function()
{
	var data = {
	    	orderId:order,
	    	number:$('#card-number').val(),
	    	expire:$('#card-expire').val(),
	    	crypt:$('#card-crypt').val()
	    };
	$.soap({
	    url: 'http://localhost:9763/services/Shop/',
	    namespaceURL:'http://shop.service.alma.com'
	});
	$.soap({
	    method: 'pay',
	    data:data,
	    soap12: true,
	    success: function (soapResponse) {
	        // do stuff with soapResponse
		    console.log(soapResponse.toJSON());
		    if(cleanPayed(soapResponse.toJSON()))
		    {
		    	success();
		    }
		    else
		    {
		    	$('#error-card').show();
		    }
	    },
	    error: function (soapResponse) {
	    	console.error(soapResponse.toString());
	    }
	});
};

var error = function()
{
	$('.loading').hide();
	$('.products').html('<img src="img/error.png"/><h3>Something capywrong happend...</h3><button onclick="getProducts()">Retry</button>');
};

var success = function()
{
	$('.popup').hide();
	$('.error').hide();
	$('.cart').html('<img src="img/success.png"/><h3>Order successfully payed!</h3>');
	cart = [];
	total = 0;
	order= null;
};

var cleanOrder = function(o)
{
	return o['#document']['ns:placeOrderResponse']['ns:return'];
};

var cleanProducts = function(o)
{
	return o['#document']['ns:getProductsResponse']['ns:return'];
};

var cleanPayed = function(o)
{
	return o['#document']['ns:payResponse']['ns:return'];
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
		$('.cart').append('<h3>Total: '+total+' euros</h3>');
		$('.cart').append('<button onclick="order()">Order</button>');
	}
};

var rem = function(id)
{
	cart.splice(id,1);
	total-=parseFloat(products[id].price["_"]);
	updateCart();
}

var add = function(id)
{
	cart.push(products[id]);
	total+=parseFloat(products[id].price["_"]);
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
	if($('#name').val() != "" && $('#address').val() != "" && $('#post-code').val() != "" && $('#city').val() != "")
	{
		$('.error').hide();
		placeOrder();
		showPayment();
		$('.cart').html('<h3>You have 1 unpaid order</h3><img src="img/pending.png"/><h3>Click on the button below to pay and finish it!</h3><h3>Total: '+total+' euros</h3><button onclick="showPayment()">Pay</button>');
	}
	else
	{
		$('.error').show();
	}
};

var showPayment = function()
{
	$('.popup').hide();
	$('.payment').show();
};

var validatePayment = function()
{
	if( $('#card-number').val() != "" && $('#card-expire').val() != "" && $('#card-crypt').val() != "" )
	{
		$('.error').hide();
		pay();
	}
	else
	{
		$('#error-fill').show()
	}
}

getProducts();
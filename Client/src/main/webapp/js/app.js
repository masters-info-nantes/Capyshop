var cart = [];
$('.loading').show();

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
        console.error(soapResponse);
    }
});



var cleanProducts = function(o)
{
	return o['#document']['ns:getProductsResponse']['ns:return'];
};

var updateProducts = function(products)
{
	products=cleanProducts(products);
	console.log(products);
	$('.products').html("<ul></ul>");
	for(var i =0; i<products.length;i++)
	{
		$('.products ul').append().append('<li><label>'+products[i]+'</label><span>Quantity: 5</span><button onclick="add(\''+products[i]+'\')">Ajouter</button></li>');
	}
};

var updateCart = function()
{
	if(cart.length==0)
		$('.cart').html("<h3>Your cart is empty</h3>");
	else
	{
		$('.cart').html("<ul></ul>");
		for(var i =0; i<cart.length;i++)
		{
			$('.cart ul').append('<li><label>'+cart[i]+'</label><button onclick="rem(\''+cart[i]+'\')">Remove</button></li>');
		}
		$('.cart').append('<button onclick="order()">Order</button>');
	}
};

var rem = function(name)
{
	cart.splice(cart.indexOf(name),1);
	updateCart();
}

var add = function(name)
{
	cart.push(name);
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

var payment = function()
{
	$('.popup').hide();
	$('.payment').show();
};

var pay = function()
{
};
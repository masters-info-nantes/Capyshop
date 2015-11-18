<html>
<head>
	<meta charset="utf-8"/>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="bower_components/jquery.xml2json/src/xml2json.js"></script>
	<script type="text/javascript" src="bower_components/jquery.soap/jquery.soap.js"></script>
	<link rel="stylesheet" href="css/main.css"/>
</head>
<body class="flex-container">
<div class="flex-item">
	
	<h2><img src="img/loading.png" height="50"> Capyshop</h2>
</div>
<div class="flex-item cart">
	<h3>Your cart is empty</h3>
</div>
<div class="flex-item">
	<div class="loading">
		<img class="rotating" src="img/loading.png"/>
		<h4>Loading...</h4>
	</div>
	<div class="products">
	</div>
</div>
<div class="popup order">
	<div class="content">
		<h3>Order: Infos</h3>
		<label>Name: </label><input type="text" placeholder="John Doe" id="name"><br>
		<label>Address: </label><input type="text" placeholder="1 Foo Street" id="address"><br>
		<label>Post Code: </label><input type="text" placeholder="42000" id="post-code"><br>
		<label>City: </label><input type="text" placeholder="BarTown" id="city"><br>
		<button onclick="validate()">Validate</button>
		<button onclick="dismiss()">Cancel</button>
	</div>
</div>
<div class="popup payment">
	<div class="content">
		<h3>Payment: Bank Card</h3>
		<label >Number: </label><input type="text" placeholder="4586214953125478" class="only-numbers "><br>
		<label>Month: </label><input type="text" placeholder="12" class="only-numbers"><br>
		<label>Year: </label><input type="text" placeholder="17" class="only-numbers"><br>
		<label>Crypt: </label><input type="text" placeholder="124" class="only-numbers"><br>
		<button onclick="pay()">Pay</button>
		<button onclick="dismiss()">Cancel</button>
	</div>
</div>

<script type="text/javascript" src="js/app.js"></script>
</body>
</html>

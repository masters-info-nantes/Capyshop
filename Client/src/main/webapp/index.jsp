<html>
<head>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="bower_components/jquery.soap/jquery.soap.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<script type="text/javascript">
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
        console.log(soapResponse);
        console.log(soapResponse.toString());
    },
    error: function (soapResponse) {
        console.log('that other server might be down...');
        console.log(soapResponse);
        console.log(soapResponse.toString());
    }
});
</script>
</body>
</html>

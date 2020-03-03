<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<div id="Cart">

<h2>Shopping Cart</h2>

	<table id="Order">
		<tr>
			<th><b>Item ID</b></th>
			<th><b>Product ID</b></th>
			<th><b>Description</b></th>
			<th><b>In Stock?</b></th>
			<th><b>Quantity</b></th>
			<th><b>List Price</b></th>
			<th><b>Total Cost</b></th>
			<th>&nbsp;</th>
		</tr>

		<c:if test="${sessionScope.cart.numberOfItems == 0}">
			<tr>
				<td colspan="8"><b>Your cart is empty.</b></td>
			</tr>
		</c:if>

		<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
			<tr>
				<td>
					<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
				</td>
				<td>
					${cartItem.item.product.productId}
				</td>
				<td>
					${cartItem.item.attribute1} ${cartItem.item.attribute2}
					${cartItem.item.attribute3} ${cartItem.item.attribute4}
					${cartItem.item.attribute5} ${cartItem.item.product.name}
				</td>
				<td>${cartItem.inStock}</td>
				<td>
					<input  type="number"  min="0" class="Quantity" onkeyup="updataNum(event)" name="${cartItem.item.itemId}" value="${cartItem.quantity}"/>
				</td>
				<td>
                    <input class="Price" value="${cartItem.item.listPrice}" size="8" readonly/>
				</td>
				<td>
                    <input class="Amount" value="${cartItem.total}" size="8" readonly/>
				</td>
				<td>
					<a href="removeItemFormCart?cartItem=${cartItem.item.itemId}">Remove</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7">
				Sub Total:<span id="total">${cartItem.total}</span>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>

	<c:if test="${sessionScope.cart.numberOfItems > 0}">
		<a href="viewOrderForm">Proceed to Checkout</a>
	</c:if></div>

<div id="Separator">&nbsp;</div>
</div>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
    $("#Order").on('input', '.Quantity', function () {
        var self = $(this);
        var tr = self.closest("tr");

        var quantity = self.val();
        var Price = tr.find(".Price").val();

        var amount = 0;
        if ($.isNumeric(quantity) && $.isNumeric(Price)) {
            amount = quantity * Price;
        }
        tr.find(".Amount").val(amount);

        CalcSum();
    });

    function CalcSum()
    {
        var total_sum = 0;
        $("#Order .Amount").each(function () {
            var val = $(this).val();
            if ($.isNumeric(val)) {
                total_sum += parseFloat(val);
            }
        });

        $("#total").html(total_sum);
    }

	var xmlHttpRequest;
	function createXMLHttpRequest()
	{
		if (window.XMLHttpRequest) //非IE浏览器
		{
			xmlHttpRequest = new XMLHttpRequest();
		}
		else if (window.ActiveObject)//IE6以上版本的IE浏览器
		{
			xmlHttpRequest = new ActiveObject("Msxml2.XMLHTTP");
		}
		else //IE6及以下版本IE浏览器
		{
			xmlHttpRequest = new ActiveObject("Microsoft.XMLHTTP");
		}
	}

	function sendRequest(url) {
		createXMLHttpRequest();
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.onreadystatechange = processResponse;
		xmlHttpRequest.send(null);
	}

	function updataNum(event) {
		event = event ? event : window.event;
		var obj = event.srcElement ? event.srcElement : event.target;
		var workingItemId = obj.name;
		var num = obj.value;
		console.log(num);
		sendRequest("ChangeNum?workingItemId=" + workingItemId + "&value=" + num);
	}

	processResponse = function () {
		if(xmlHttpRequest.readyState==4){
			if(xmlHttpRequest.status==200){
				console.log(xmlHttpRequest.responseText);
			}
		}

	}

</script>

<%@ include file="../common/IncludeBottom.jsp"%>
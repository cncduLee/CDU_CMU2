<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction"/>
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>

<!-- Include Script in folder common -->
<jsp:include page="../common/script.jsp" />
<jsp:include page="../common/cartScript.jsp" />
 
</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="../common/header.jsp" />

<div id="templatemo_main_wrapper">
    <div id="templatemo_main">
    	<div class="w cart">
			<div class="cart-hd group">
				<img src="images/shopping_cart.png" alt="" style="width: 700px; height:30px; float: left;"/>
				<h2>My shopping cart</h2>
				<span class="fore" id="show2"> </span>
			</div>
			<%for(CartTransaction tra : ((Cart)session.getAttribute("myCart")).getCartTransaction()){
				%>
					productid:<%=tra.getSellerProduct().getId() %>
					name:<%=tra.getSellerProduct().getProduct().getLocalName() %>
					amount:<%=tra.getAmount() %>
					price:<%=tra.getSellerProduct().getPrice() %>
				<%
			} %>
			
			<div id="show">
				
				<div class="cart-frame">
					<div class="tl"></div>
					<div class="tr"></div>
				</div>
				<div class="cart-inner">
					<div class="cart-thead clearfix">
						<div class="column t-checkbox form">
							<input type="checkbox" value="" checked="" id="toggle-checkboxes"data-cart="toggle-cb" onclick="check_all(this,'checkItem')" />
							<label for="toggle-checkboxes">Select all</label>
						</div>
						<div class="column t-goods">Goods</div>
						<div class="column t-price">Price</div>						
						<div class="column t-quantity">Number</div>
						<div class="column t-action">Edit</div>
					</div>
					<div class="cart-tbody" id="product-list">
						<!-- ******************************************** -->
						
						<c:forEach items="${sessionScope.myCart.cartTransaction}" var="myCartx"> 
							<div class="item item_selected ">
							<div class="item_form clearfix">
								<div class="cell p-checkbox">
									<input type="checkbox"  checked="" name="checkItem" class="checkbox" data-bind="cbid:1"/>
								</div>
								<div class="cell p-goods">
									<div class="p-img">
										<a target="_blank" href="#">									
										<c:forEach items="${myCartx.sellerProduct.images}" var="img" varStatus="status">
											<c:if test="${status.index==0}">
												<img src="./productpics/showpic/${img.id}"  alt="${img.localDescription}" />
											</c:if>
										</c:forEach>
										</a>
									</div>
									<div class="p-name">
										<a target="_blank"
											href="#">${myCartx.sellerProduct.product.localName }</a>
									</div>
								</div>
								<div class="cell p-price">
									<label id = "lblPrice" class = "price">${myCartx.sellerProduct.price * myCartx.amount}</label>
								</div>
								
								<div class="cell p-quantity">
									<div data-bind="" class="quantity-form">
										<a class="decrement" href="cartRest/productId=${myCartx.sellerProduct.id}&type=2">-</a><!-- javascript:subCount() -->
										 
											<input	type="text" id="changeQuantity1" value="${myCartx.amount}" class="quantity-text"/>
											 
										<a class="increment" href="cartRest/productId=${myCartx.sellerProduct.id}&type=1"/>+</a><!-- javascript:addCount() -->
									</div>
								</div>
								<div class="cell p-remove">
									<a href="cartRemove/productId=${myCartx.sellerProduct.id}" class="cart-remove" id="remove-1005367078-1">delete</a>
								</div>
							</div>
						</div>
						</c:forEach>


					</div>
					
					<div class="cart-total clearfix">
						<div class="total fr">
						<span id="finalPrice"><label id="lblTotalPrice" >${sessionScope.totalPrice }</label></span>
							Totally money
						</div>
					</div>
				</div>
	
	

				<div class="cart-frame">
					<div class="bl"></div>

					<div class="br"></div>
				</div>
				<div class="cart-button clearfix">
					<a id="continue" href="productList" class="btn continue">
						<span class="btn-text">Continue Shopping</span></a> 
						<a id="toSettlement" href="#" class="checkout">gotoOrderInfo</a>
				</div>
			</div>

			
		</div>
    	
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_main_wrapper -->
</div> <!-- END of templatemo_main -->

	<!-- Include footer in folder common -->
	<jsp:include page="../common/footer.jsp" />

</body>
</html>

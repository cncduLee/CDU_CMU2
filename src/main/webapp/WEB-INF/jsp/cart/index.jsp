<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
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
						<div class="column t-promotion">Integral</div>
						<div class="column t-inventory">
							<div clstag="clickcart|keycount|xincart|kucunshai" id="inventory">
								<span title="Inside 3rd rings of Chengdu" id="location">Beijing</span>
							</div>
						</div>
						<div class="column t-quantity">Number</div>
						<div class="column t-action">Edit</div>
					</div>
					<div class="cart-tbody" id="product-list">
						<!-- ************************商品开始********************* -->
						<!-- 主商品 -->
						<div class="item item_selected ">
							<div class="item_form clearfix">
								<div class="cell p-checkbox">
									<input type="checkbox"  checked="" name="checkItem" class="checkbox" data-bind="cbid:1"/>
								</div>
								<div class="cell p-goods">
									<div class="p-img">
										<a target="_blank" href="#"><img
											src="images/portfolio/01.jpg"
											clstag="clickcart|keycount|xincart|p-imglistcart"/></a>
									</div>
									<div class="p-name">
										<a target="_blank" clstag="clickcart|keycount|xincart|productnamelink"
											href="#">supper specy pepper in the world</a>
									</div>
								</div>
								<div class="cell p-price">
									<label id = "lblPrice" class = "price">99</label>
								</div>
								<div class="cell p-promotion"></div>
								<div class="cell p-inventory stock-1005367078">Cash Return</div>
								<div class="cell p-quantity">
									<div data-bind="" class="quantity-form">
										<a id="decrement-1005367078-1-1"
											clstag="clickcart|keycount|xincart|diminish1"
											class="decrement" href="javascript:subCount()">-</a> <input	type="text" id="changeQuantity1" value="1" class="quantity-text"/> 
										<a id="increment-1005367078-1-1-0"
											clstag="clickcart|keycount|xincart|add1" class="increment"	href="javascript:addCount()"/>+</a>
									</div>
								</div>
								<div class="cell p-remove">
									<a href="javascript:void(0);" class="cart-remove"
										clstag="clickcart|keycount|xincart|btndel318558"
										data-more="removed-99.00-1"
										data-name="super specy pepper in the world"
										id="remove-1005367078-1">delete</a>
								</div>
							</div>
							<div class="item_extra">
								<div data="skuYb_1005367078_0" class="sku-yanBao">
									<!-- 延保服务按钮异步加载 -->
								</div>

								<!-- 延保 -->
							</div>
							<!-- 延保和赠品 -->

						</div>
						<!-- ************************商品结束***************** -->



						<div class="item item_selected " data-bind="rowid:1">
							<div class="item_form clearfix">
								<div class="cell p-checkbox">
									<input type="checkbox" value="1005367078-1" checked=""
										name="checkItem" class="checkbox" data-bind="cbid:1"/>
								</div>
								<div class="cell p-goods">
									<div class="p-img">
										<a target="_blank" href="#"><img
											alt="super specy pepper in the world"
											src="images/portfolio/02.jpg"
											clstag="clickcart|keycount|xincart|p-imglistcart"/></a>
									</div>
									<div class="p-name">
										<a target="_blank"
											clstag="clickcart|keycount|xincart|productnamelink"
											href="http://www.cdu.edu.cn">super specy pepper in the world</a>
									</div>
								</div>
								<div class="cell p-price">
									<!--<span class="price">¥99.00</span>-->
									<label id = "lblPriceTwo" class = "price">99</label>
								</div>
								
								<div class="cell p-promotion"></div>
								
								<div class="cell p-inventory stock-1005367078">Cash Return</div>
								<div class="cell p-quantity">
									<div data-bind="" class="quantity-form">
										<a  id="decrement-1005367078-1-1"
											
											
											class="decrement" href="javascript:subCountTwo()">-</a> 
				<input type="text" id="changeQuantity2" value="1" class="quantity-text"></input>
									<a id="increment-1005367078-1-1-0"
								clstag="clickcart|keycount|xincart|add1" class="increment"
											href="javascript:addCountTwo()">+</a>
									</div>
								</div>
								<div class="cell p-remove">
									<a 	onclick="deletitem(this);"
										href="javascript:void(0);" 
										class="cart-remove"
										clstag="clickcart|keycount|xincart|btndel318558"
										data-more="removed-99.00-1"
										data-name="super specy pepper in the world"
										id="remove-1005367078-1">delete</a>
								</div>
							</div>
							<div class="item_extra">
								<div data="skuYb_1005367078_0" class="sku-yanBao">
									<!-- 延保服务按钮异步加载 -->
								</div>

								<!-- 延保 -->
							</div>
							<!-- 延保和赠品 -->

						</div>
					

					</div>
					
					<div class="cart-total clearfix">
						<div class="control fl clearfix">
						</div>
						<div class="total fr">
					<span data-bind="99.00" id="finalPrice"><label id="lblTotalPrice" >$198.00</label></span>
Totally money(without transportation expense)
						</div>
					</div>
				</div>
				<!-- cart-inner结束 -->

				<div class="cart-frame">
					<div class="bl"></div>

					<div class="br"></div>
				</div>
				<div class="cart-button clearfix">
					<a id="continue" clstag="clickcart|keycount|xincart|continueBuyBtn"
						href="index.html" 
						
						class="btn continue"><span
						class="btn-text">Continue Shopping</span></a> 
						
						<a id="toSettlement"
						clstag="clickcart|keycount|xincart|gotoOrderInfo"
						href="order.html" class="checkout">gotoOrderInfo</a>
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

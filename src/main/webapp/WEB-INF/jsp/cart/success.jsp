<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction"/>
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>
<%
long total = 0l;
Cart cart = (Cart) session.getAttribute("myCart");

for(CartTransaction t : cart.getCartTransaction()){
	total += t.getAmount() * t.getSellerProduct().getPrice();
}

%>
<!-- Include Script in folder common -->
<jsp:include page="../common/script.jsp" />
<jsp:include page="../common/orderSuccessScript.jsp" />
 
</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="../common/header.jsp" />


	<div id="templatemo_main_wrapper">
	 <div id="templatemo_main">
	 <img src="images/Submit_successfully.png" alt="" style="width: 700px; height:30px; float: left;"/>
		<div class="w main">
			<div class="m m3" id="qpay">
				<div class="mc">
					<s class="icon-succ02"></s>
					<div class="fore">
						<h3 class="ftx-02">Orders submitted successfully,please pay for it on timeÅ</h3>
						<ul class="list-h">
							<li class="fore1">Orders' number ö362821854</li>
							<li class="fore3">Amout payable <strong class="ftx-01"><%=total %> $</strong></li>
							
						</ul>
						<p id="p_show_info">&nbsp;</p>
					</div>
				</div>
			</div>
			<div id="qpay02" class="m">
				<div class="mt">
					<h3>
						Only after pay for<strong class="ftx-01"><%=total %> $</strong>can you finish your orderÄÇ
					</h3>
					<div class="extra">
						Please pay for your order within<strong class="ftx-01">24 hours</strong>,otherwise, the order will be automatically canceledÄÇ
					</div>
					<div class="fr">
						<s class="icon-ques05"></s> <a href="#" target="_blank">Manage my fast payment </a>
						&nbsp; <a href="#" target="_blank">manual</a>&nbsp;
					</div>
				</div>
			</div>
			<input id="savingsFlag" name="savingsFlag" type="hidden" value="0" />
			<input id="creditFlag" name="creditFlag" type="hidden" value="0" />
			<input id="ruDebitFlag" name="ruDebitFlag" type="hidden" value="0" />
			<input id="ruCreditFlag" name="ruCreditFlag" type="hidden" value="0" />

			<div class="o-mb">
				After finish orders ,you can look over ö <a href="makeOrder" target="_blank">Order details</a>
				&nbsp;&nbsp;<a href="ruleDetailPage" target="_blank">Pay survey</a>
			</div>
		</div>
		
		<div id="cleaner"></div>
	</div>
	</div>
	<!-- END of templatemo_slider_wrapper -->

	<!-- Include footer in folder common -->
	<jsp:include page="../common/footer.jsp" />

</body>
</html>

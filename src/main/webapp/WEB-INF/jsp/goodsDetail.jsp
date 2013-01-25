<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>

<!-- Include Script in folder common -->
<jsp:include page="./common/goodsDetailScript.jsp" />

</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />

	<div id="templatemo_main_wrapper">
		<div id="templatemo_main">
			<div id="contentt" class="float_l">
				<div class="post">
					<a href="./productpics/showpic/${pics.id}"
						alt="${pics.localDescription}" class="jqzoom" rel='gal1'
						title="triumph"><img
						src="./productpics/showpic/${pics.id}" class="fs"
						jqimg="./productpics/showpic/${pics.id}" id="bigImg" /></a>
					<span> <a href="./productpics/showpic/${pics.id}"
						id="thickImg" title="introduction" class="thickbox"> <img
							alt="see big pic" src="images/look.gif" />
					</a>
					</span>
					<div id="cdu-spec-list">
						<div class="cdu-spec-items"
							style="position: absolute; width: 480px; height: 54px; overflow: hidden;">

							<ul class="imgList">
								<li>
									<c:forEach items="piclist" var="p">
											<img alt="products pictrue"
												src="./productpics/showpic/${pics.id}">
									</c:forEach>
								</li>
							</ul>

						</div>
					</div>
				</div>
			</div>
			<!-- END of content -->

			<div id="sidebarr" class="float_r">
				<h1>${product.name.localDescription}</h1>
				<div style="height: 30px;"></div>
				<ul class="cdu_list">
					<li>Price ：<em> 20$/kg</em></li>
					<li>Promotion : <em style="color: #16b7df;"> 20%</em></li>
					<li>Evaluation : <em style="color: #0bda4a"> 350 times</em></li>
					<li>Quantity : <input type="text" name="quantity"
						class="quantity_text" /> kg
					</li>
				</ul>
				<ul class="cdu_list_1">
					<li>Harbor ：<select>
							<option>KunMing</option>
							<option>ShangHai</option>
					</select>
					</li>
					<li>Destination ：<select>
							<option>GuangZhou</option>
							<option>ShangHai</option>
					</select>
					</li>
					<li>Titally Price ：<em> 37$</em></li>
					<li>Riping Time ：<em style="color: #64BC68"> 2 days
							later</em></li>
					<li>Inventory ：<em style="color: #F03C3E"> No</em></li>
				</ul>


				<a href="cartAdd/productId=${productid} &amount=1"> <img
					src="images/blog/shoppingcart.jpg" border="0"
					style="margin-left: 9px;"></img>
				</a>
			</div>
			<!-- END of sidebar -->
		</div>
		<div class="cleaner"></div>

		<div class="goods_main">
			<div class="introduce">
				<ul>
					<li class="introduce_li">Detail Information</li>
				</ul>
			</div>
			<div class="product_parameters">
				<ul>
					<li class="product_parameters_li">Product Parameters</li>
				</ul>
			</div>
			<div class="product_parameters_info">

				<table>
					<tr>
						<td>Product Name: <em>Orange</em></td>
						<td id="product_parameters_info_right">Product Company:
							ChiangMai University and ChengDu University</td>
					</tr>
					<tr>
						<td>Size: 100</td>
						<td id="product_parameters_info_right">Product Address:
							ChiangMai University and ChengDu University</td>
					</tr>
					<tr>
						<td>Storage Method: XXXXX</td>
						<td id="product_parameters_info_right">Food Additives: XXXXX</td>
					</tr>
					<tr>
						<td>Place of Origin: XXXXX</td>
						<td id="product_parameters_info_right">Weight: XXXXX</td>
					</tr>
					<tr>
						<td>Cultivation type: XXXXX</td>
						<td id="product_parameters_info_right">Package: XXXXX</td>
					</tr>
					<tr>
						<td>Grade: XXXXX</td>
						<td id="product_parameters_info_right">Telephone Number:
							XXXXX</td>
					</tr>
					<tr>
						<td>Producer Company: XXXXX</td>
						<td id="product_parameters_info_right">保质期: XXXXX</td>
					</tr>
				</table>

			</div>

			<div style="height: 10px;"></div>

			<div class="process_of_product">

				<div class="process_of_name">Process of product</div>

				<div class="cleaner"></div>

				<div class="process_of_info">
					<table>
						<tr>
							<td>Add the information you want to introduce.</td>
							<td>Add the information you want to introduce.</td>
						</tr>
						<tr>
							<tr>
								<td>Add the information you want to introduce.</td>
								<td>Add the information you want to introduce.</td>
							</tr>
							<tr>
								<tr>
									<td>Add the information you want to introduce.</td>
									<td>Add the information you want to introduce.</td>
								</tr>
								<tr>
									<tr>
										<td>Add the information you want to introduce.</td>
										<td>Add the information you want to introduce.</td>
									</tr>
									<tr>
					</table>
				</div>
				<div class="cleaner"></div>

				<div class="product_images">
					<img alt="products pictrue" src="images/templatemo_image_01.jpg">
						<img alt="products pictrue" src="images/templatemo_image_02.jpg">
				</div>



<!-- comment List start -->

			<div class="comment">
		
			<div class="comment_title">Comment</div>
			
			
			<c:forEach items="${commentList }" var="commentx">
				
				<div class="comment_info">
					<div class="people_left">
						<img  src="images/templatemo_image_01.jpg">
					</div>
					<div class="people_right">
					<table>
						<tr>
							<td class="people_height">
								<span class="people_name">${commentx.commenter.localName }</span>
							
								<span class="people_time">${commentx.submitDate }</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="people_say">${commentx.comment }</span>
							</td>
						</tr>
					</table>
					</div>
				</div>
				
			
			</c:forEach>
			
		<div class="cleaner"></div>
	</div>
<!-- comment List start -->
		

<!-- Leave your comment start -->
		<div class="leave_comment">
			<form action="addComment" method="get">
			<div class="leave_comment_title">Leave your comment</div>
			
			<div class="leave_comment_name">Name(*required)<br/>
				<input type="text"  class="comment_text"/>
				<input type="hidden" name="productid" value="${productid}"/>
				<input type="hidden" name="sellerid" value="${seller.id}"/>
			</div>
			
			
			<div class="cleaner"></div>
			
			<div class="leave_comment_textarea">Comment<br/>
				<input type="text" rows="10" cols="80" class="comment_textarea" name="cotent"></textarea>
			</div>
			<div class="leave_comment_submit">
				<input type="submit" value="Submit" class="comment_submit"/>
			</div>
			</form>
		</div>		
	</div>
<!-- Leave your comment end -->	
	
	<div class="cleaner"></div>
	
    </div> 
</div> 



			<!-- Include footer in folder common -->
			<jsp:include page="./common/footer.jsp" />
</body>
</html>

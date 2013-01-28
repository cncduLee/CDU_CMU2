<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>
<meta name="keywords"
	content="fresh zone, fresh, fresh fruit, CMU, CDU, " />
<meta name="description"
	content="We sell fresh fruit directly from farmer to your hand. [supported by CDU&CMU University]" />

<!-- Include Script in folder common -->
<jsp:include page="./common/script.jsp" />

</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />
	

	<div id="templatemo_slider_wrapper">
		<div id="templatemo_slider">
			<div id="carousel">
				<div class="panel">

					<div class="details_wrapper">

						<div class="details">
							<c:forEach items="${products}" var="productx">
								<div class="detail">
									<h2>
										<a href="#">${productx.name }</a>
									</h2>
									<p>${productx.description}</p>
									<a href="#" title="Read more" class="more">Read more</a>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="paging">
						<div id="numbers"></div>
						<a href="javascript:void(0);" class="previous" title="Previous">Previous</a>
						<a href="javascript:void(0);" class="next" title="Next">Next</a>
					</div>
					<!-- /paging -->

					<a href="javascript:void(0);" class="play" title="Turn on autoplay">Play</a>
					<a href="javascript:void(0);" class="pause"
						title="Turn off autoplay">Pause</a>

				</div>
				<!-- /panel -->

				<div id="slider-image-frame">
					<div class="backgrounds">
						<c:set var="i" value="1"></c:set>
					
						 <c:forEach items="${picAndSellers}" var="item">
							<div class="item item_${i}">
								<a href="./goodsDetail?productId=${item['pic'].id }&sellerId=${item['seller'] }">
									<img src="./productpics/showpic/${item['pic'].id }" alt="${item['pic'].localDescription }" />
								</a>
							</div>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>					

						<!-- <div class="item item_3">
                        <img src="images/slider/01.jpg" alt="Image 03" />
                    </div> -->
						<!-- /item -->

					</div>
					<!-- /backgrounds -->
				</div>
			</div>
		</div>
		<!-- END of templatemo_slider -->
	</div>
	<!-- END of templatemo_slider_wrapper -->


	<div id="templatemo_main_wrapper">
    <div id="templatemo_main">
    	<div class="homepage_post col half float_l">
            <h2>About our website</h2>
            <div class="post_meta">Made by Animation of CMU and CDU</div>
            		 <!-- flash -->
			<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"   
             codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0"   
             width="650" height="350" align="middle"><param name="allowScriptAccess" value="sameDomain" />   
             <param name="movie" value="system/writingBoard.swf" />   
             <param name="quality" value="high" />   
             <param name="bgcolor" value="#ffffff" />   
             <param name="flashvars" value='&id=1&userId=1&sdata=&tdata=&action=1&height=300'/>  
             <embed src="media/part1.wmv" autostart="false" loop="2" width=440px; height=225px; class="video_embed">
             </embed>

			</object>
			

				<p><em> You can get more information about our website through this video.</em></p>
           <p><a href="#" target="_parent">This website</a> is made by CMU and CDU.In order to make it more convience for Chinese people to buy Thai fruits and make people buy cheaper fruit,we set up this website.People can use this web to open a on line shop or 
               buy fruit.</p>

           
           <a href="videoPage" rel="nofollow"><strong>read more</strong></a>
		</div>
        
       <div class="col half float_r">
            <h2>Regulations</h2>
			<p><em>If you do not very clearly about relative rules or regulations,<a href="rulesPage"><Strong>please click there and read it</Strong></a>.</em></p> 
			<p>All the users must obey the rules and relative regulations no matter who you are and what you do .In order to make 
			   a very friendly shopping atmosphere,we suggust users should read the rules first and only you accept the rules 
			   and go through the test that you can be allown as a VIP.
			</p>
			
		</div>
        
        <div class="cleaner h40"></div>

<!-- food  gallery begain -->
			<div id="food-gallery">
				<h2>Food Gallery</h2>
			            
				<c:forEach items="${gallery }" var="productx" varStatus="status">
					<c:if test="${status.index < 4}">
						<div class="col one_fourth">
						<a href="./goodsDetail?productId=${productx.id }&sellerId=${productx.seller.id } ">
							<c:forEach items="${productx.images }" var="pic" varStatus="sta">
								<c:if test="${sta.index == 0}">
									<img src="./productpics/showpic/${pic.id } " alt="Image 02" class="imgage-with-frame" border="0" />
								</c:if>
							</c:forEach>
						</a>
						<h5>${productx.product.name.localDescription }</h5>
						<p>${productx.product.description.localDescription }</p>
						
						</div>
					</c:if>
				</c:forEach>
			</div>
			<!-- 
			
						<div class="col one_fourth">
            	<a href="goodsDetail.html">
                <img src="images/fruits_images/Durian.jpg" alt="Image 02" class="imgage-with-frame" border="0" />
                </a>
                <h5>Web Design</h5>
                <p>Donec sit amet sem in urna posuere interdum ac in arcu. Nulla porttitor pharetra, et metus venenatis.</p>
            </div>
			
            <div class="col one_fourth fp_rw">
				<a href="goodsDetail.html">
                <img src="images/fruits_images/Mango.jpg" alt="Image 02" class="imgage-with-frame" border="0" />
                </a>                <h5>Ecommerce Solution</h5>
                <p>Proin consectetur porttitor tincidunt. Ut fermentum arcu eget lacus placerat molestie.</p>
            </div>
			
            <div class="col one_fourth fp_rw">
                <img src="images/fruits_images/Mangosteen.jpg" alt="Image 04" class="imgage-with-frame" />
                <h5>Gallery Player</h5>
                <p>Donec arcu orci, dictum id commodo eget, volutpat eu lorem nec  arcu  in ulla ut erat arcu.</p>
            </div>
			
            <div class="col one_fourth fp_rw no_margin_right">
                <img src="images/fruits_images/Mango.jpg" alt="Image 05" class="imgage-with-frame" />
                <h5>Customer Support</h5>
                <p>Sed laoreet  lorem, in porta massa varius eu varius lacus eget ligula facilisis rutrum.</p>
            </div>
			
			 -->
			
			
			
<!-- food  gallery begain -->
			<div class="cleaner"></div>
		</div>
	<!-- END of templatemo_main_wrapper -->



	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>

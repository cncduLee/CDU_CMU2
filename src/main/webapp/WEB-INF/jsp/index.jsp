<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags">
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

							<!-- <div class="detail">
							<h2><a href="#">${product.localName }</a></h2>
                            <p>${product.localDescription}</p>
							<a href="#" title="Read more" class="more">Read more</a>
							</div> -->
							<!-- /detail -->

							<c:forEach items="${products}" var="productx">
								<div class="detail">
									<h2>
										<a href="#">${productx.localName }</a>
									</h2>
									<p>${productx.localDescription}</p>
									<a href="#" title="Read more" class="more">Read more</a>
								</div>
								<!-- /detail -->
							</c:forEach>

						</div>
						<!-- /details -->

					</div>
					<!-- /details_wrapper -->

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
						<c:forEach items="${pics}" var="pic">
							<div class="item item_${i}">
								<a href="./goodsDetail?productId=${pic.product.id}"> <img
									src="./productpics/showpic/${pic.id}"
									alt="${pic.localDescription}" />
								</a>
							</div>
							<!-- /item -->
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
						<!-- <div class="item item_2">
                        <img src="images/slider/03.jpg" alt="Image 02" />
                    </div>  -->
						<!-- /item -->

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

				<!-- <img src="images/templatemo_image_02.jpg" class="image_fl imgage-with-frame" alt="Image 02"/>-->
				<div class="image_fl imgage-with-frame">

					<!-- flash -->
					<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
						codebase="http://fpdownload.macromedia.com/pub/shockwave  
                                                       /cabs/flash/swflash.cab#version=7,0,0,0"
						width="650" height="350" align="middle">
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="movie" value="system/writingBoard.swf" />
						<param name="quality" value="high" />
						<param name="bgcolor" value="#ffffff" />
						<param name="flashvars"
							value='&id=${twoKey.key.questionId}&  
                                  userId=1&sdata=&tdata=&action=1&height=300' />
						<embed src="media/fruit.swf" quality="high" bgcolor="#ffffff"
							name="mymovie"
							flashvars='&id=${twoKey.key.questionId}&userId=1&sdata=&  
                                               tdata=&action=1&height=300'
							align="middle" allowScriptAccess="sameDomain"
							type="application/x-shockwave-flash"
							pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>

					<!-- avi
			<object id="video" border="0" classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA"> 
			<param name="ShowDisplay" value="0"> 
			<param name="ShowControls" value="1"> 
			<param name="AutoStart" value="1"> 
			<param name="AutoRewind" value="0"> 
			<param name="PlayCount" value="0"> 
			<param name= "fullScreen" value="-1"> 
			<param name="Appearance" value="0"> 
			<param name="BorderStyle" value="0"> 
			<param name="MovieWindowHeight" value="240"> 
			<param name="MovieWindowWidth" value="320"> 
			<param name="FileName" value="01.avi"> 
			<embed width="400" height="200" border="0" showdisplay="0" showcontrols="1" autostart="1" autorewind="0" playcount="0" moviewindowheight="240" moviewindowwidth="320" filename="01.avi" src="01.avi"> 
			</embed> 
			</object>  -->
				</div>

				<p>
					<em> You can get more information about our website through
						this video.</em>
				</p>
				<p>
					<a href="http://www.cdu.edu.cn" target="_parent">This website</a>
					is made by CMU and CDU.In order to make it more convience for
					Chinese people to buy Thai fruits and make people buy cheaper
					fruit,we set up this website.People can use this web to open a on
					line shop or buy fruit.
				</p>
				<a href="http://www.cdu.edu.cn" rel="nofollow"><strong>read
						more</strong></a>
			</div>

			<div class="col half float_r">
				<h2>Regulations</h2>
				<p>
					<em>If you do not very clearly about relative rules or
						regulations,please click there and read it.</em>
				</p>
				<p>All the users must obey the rules and relative regulations no
					matter who you are and what you do .In order to make a very
					friendly shopping atmosphere,we suggust users should read the rules
					first and only you accept the rules and go through the test that
					you can be allown as a VIP.</p>
			</div>

			<div class="cleaner h40"></div>

			<div id="food-gallery">
				<h2>Food Gallery</h2>
				<div class="col one_fourth">
					<a href="goodsDetail.html"> <img
						src="images/templatemo_image_02.jpg" alt="Image 02"
						class="imgage-with-frame" border="0" />
					</a>
					<h5>Web Design</h5>
					<p>Donec sit amet sem in urna posuere interdum ac in arcu.
						Nulla porttitor pharetra, et metus venenatis.</p>

				</div>

				<div class="col one_fourth fp_rw">
					<a href="goodsDetail.html"> <img
						src="images/templatemo_image_02.jpg" alt="Image 02"
						class="imgage-with-frame" border="0" />
					</a>
					<h5>Ecommerce Solution</h5>
					<p>Proin consectetur porttitor tincidunt. Ut fermentum arcu
						eget lacus placerat molestie.</p>
				</div>

				<div class="col one_fourth fp_rw">
					<img src="images/templatemo_image_04.jpg" alt="Image 04"
						class="imgage-with-frame" />
					<h5>Gallery Player</h5>
					<p>Donec arcu orci, dictum id commodo eget, volutpat eu lorem
						nec arcu in ulla ut erat arcu.</p>
				</div>

				<div class="col one_fourth fp_rw no_margin_right">
					<img src="images/templatemo_image_05.jpg" alt="Image 05"
						class="imgage-with-frame" />
					<h5>Customer Support</h5>
					<p>Sed laoreet lorem, in porta massa varius eu varius lacus
						eget ligula facilisis rutrum.</p>
				</div>
			</div>

			<div class="cleaner"></div>
		</div>

		<div class="col half float_r">
			<h2>Regulations</h2>
			<p>
				<em>If you do not very clearly about relative rules or
					regulations,please click there and read it.</em>
			</p>
			<p>All the users must obey the rules and relative regulations no
				matter who you are and what you do .In order to make a very friendly
				shopping atmosphere,we suggust users should read the rules first and
				only you accept the rules and go through the test that you can be
				allown as a VIP.</p>
		</div>

		<div class="cleaner h40"></div>


		<!-- start food gallery -->
		<div id="food-gallery">
			<h2>Food Gallery</h2>
			<div class="col one_fourth">
				<a href="goodsDetail.html"> <img
					src="images/templatemo_image_02.jpg" alt="Image 02"
					class="imgage-with-frame" border="0" />
				</a>
				<h5>Web Design</h5>
				<p>Donec sit amet sem in urna posuere interdum ac in arcu. Nulla
					porttitor pharetra, et metus venenatis.</p>

			</div>

			<div class="col one_fourth fp_rw">
				<a href="goodsDetail.html"> <img
					src="images/templatemo_image_02.jpg" alt="Image 02"
					class="imgage-with-frame" border="0" />
				</a>
				<h5>Ecommerce Solution</h5>
				<p>Proin consectetur porttitor tincidunt. Ut fermentum arcu eget
					lacus placerat molestie.</p>
			</div>

			<div class="col one_fourth fp_rw">
				<img src="images/templatemo_image_04.jpg" alt="Image 04"
					class="imgage-with-frame" />
				<h5>Gallery Player</h5>
				<p>Donec arcu orci, dictum id commodo eget, volutpat eu lorem
					nec arcu in ulla ut erat arcu.</p>
			</div>

			<div class="col one_fourth fp_rw no_margin_right">
				<img src="images/templatemo_image_05.jpg" alt="Image 05"
					class="imgage-with-frame" />
				<h5>Customer Support</h5>
				<p>Sed laoreet lorem, in porta massa varius eu varius lacus eget
					ligula facilisis rutrum.</p>
			</div>
		</div>
		<!-- end food gallery -->


		<div class="cleaner"></div>
	</div>
	<!-- END of templatemo_main_wrapper -->
	</div>
	<!-- END of templatemo_main -->

	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>

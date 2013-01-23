<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
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
	            <a href="images/blog/orange.jpg" class="jqzoom" rel='gal1'  title="triumph"><img src="images/blog/orange.jpg" class="fs" alt=""  jqimg="images/blog/orange.jpg" id="bigImg"/></a>
	            <span>
	                <a href="images/blog/orange.jpg" id="thickImg" title="introduction" class="thickbox">
	                   <img alt="see big pic" src="images/look.gif" />
                	</a>
            	</span>
			    <div id="cdu-spec-list">
			    <div class="cdu-spec-items" style="position: absolute; width: 480px; height: 54px; overflow: hidden; ">
			      
			       <ul class="imgList">
					<li><img src="images/templatemo_image_01.jpg" alt="" /></li>
					<li><img src="images/templatemo_image_02.jpg" alt="" /></li>
					<li><img src="images/templatemo_image_03.jpg" alt="" /></li>
					<li><img src="images/templatemo_image_03.jpg" alt="" /></li>
				  </ul>
				
			    </div>
               </div>
            </div>
        </div> <!-- END of content -->
        
        <div id="sidebarr" class="float_r">
        	<h1>${product.name.localDescription}</h1>
        	<div style="height: 30px;"></div>
            <ul class="cdu_list">
                <li>Price ：<em> 20$/kg</em></li>
                <li>Promotion : <em style="color:#16b7df;"> 20%</em></li>
				<li>Evaluation : <em style="color:#0bda4a"> 350 times</em></li>
				<li>Quantity : <input type="text" name="quantity" class="quantity_text"/>  kg</li>
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
                <li>Riping Time ：<em style="color: #64BC68"> 2 days later</em></li>
                <li>Inventory ：<em style="color: #F03C3E"> No</em></li>
            </ul>
        
          
         <a href="CT_shoppingcart.html">
         	<img src="images/blog/shoppingcart.jpg" border="0" style="margin-left:9px;"></img>
         </a>
        </div> <!-- END of sidebar -->
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
				<td id="product_parameters_info_right">Product Company: ChiangMai University and ChengDu University</td>
			</tr>
			<tr>
				<td>Size: 100</td>
				<td id="product_parameters_info_right">Product Address: ChiangMai University and ChengDu University</td>
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
				<td id="product_parameters_info_right">Telephone Number: XXXXX</td>
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
				<td>Add the information you want to introduce.</td></tr>
			<tr>
				<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			</table>
		</div>
		<div class="cleaner"></div>
		
		<div class="product_images">
			<img alt="products pictrue" src="images/templatemo_image_01.jpg">
			<img alt="products pictrue" src="images/templatemo_image_02.jpg">
		</div>
		
		<div class="comment">
		
			<div class="comment_title">Comment</div>
			<!-- people comment one -->
			<div class="comment_info">
				<div class="people_left">
					<img  src="images/templatemo_image_01.jpg">
				</div>
				
				<div class="people_right">
				<table>
					<tr>
						<td class="people_height">
							<span class="people_name">Steven</span>
							<span class="people_date">November 22.2048</span>
							<span class="people_time">10:52 pm</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="people_say">This is stage 4 sleep.You do not remain at this deep forth atage all night long. but instead zbout 80 minutes after you fall into slumber.</span>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<!-- end people comment one -->
			<!-- people comment two -->
			<div class="comment_info">
				<div class="people_left">
					<img  src="images/templatemo_image_01.jpg">
				</div>
				
				<div class="people_right">
				<table>
					<tr>
						<td class="people_height">
							<span class="people_name">Steven</span>
							<span class="people_date">November 22.2048</span>
							<span class="people_time">10:52 pm</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="people_say">This is stage 4 sleep.You do not remain at this deep forth atage all night long. but instead zbout 80 minutes after you fall into slumber.</span>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<!-- end people comment two -->
			<!-- people comment three -->
			<div class="comment_info">
				<div class="people_left">
					<img  src="images/templatemo_image_01.jpg">
				</div>
				
				<div class="people_right">
				<table>
					<tr>
						<td class="people_height">
							<span class="people_name">Steven</span>
							<span class="people_date">November 22.2048</span>
							<span class="people_time">10:52 pm</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="people_say">This is stage 4 sleep.You do not remain at this deep forth atage all night long. but instead zbout 80 minutes after you fall into slumber.</span>
						</td>
					</tr>
				</table>
				</div>
			</div><!-- end people comment three -->
			<!-- 123 -->
			<div class="comment_number">
				<ul>
					<li><a href="#" target="_parent">Previous</a></li>
					<li><a href="#" target="_parent">1</a></li>
					<li><a href="#" target="_parent">2</a></li>
					<li><a href="#" target="_parent">3</a></li>
					<li><a href="#" target="_parent">Next</a></li>
				</ul>
			</div>
			<!-- end 123 -->
			<div class="cleaner"></div>
		</div>
		<!-- Leave your comment -->
		<div class="leave_comment">
			
			<div class="leave_comment_title">Leave your comment</div>
			
			<div class="leave_comment_name">Name(*required)<br/>
				<input type="text" name="comment_name" class="comment_text"/>
			</div>
			<div class="leave_comment_email">Email(*required)<br/>
				<input type="text" name="comment_email" class="comment_text"/>
			</div>
			
			<div class="cleaner"></div>
			
			<div class="leave_comment_textarea">Comment<br/>
				<textarea rows="10" cols="80" class="comment_textarea"></textarea>
			</div>
			<div class="leave_comment_submit">
				<form action="#">
					<input type="submit" value="Submit" name="comment_submit" class="comment_submit"/>
				</form>
			</div>
			
		</div>
		
	</div>
	
	<div class="cleaner"></div>
	
    </div> 
</div> 


	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>
=======
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>goods</title>
<meta name="keywords" content="fresh zone, full post, blog, theme, free templates, templatemo, CSS, HTML" />
<meta name="description" content="Fresh Zone, Full Blog Post, free CSS template by templatemo.com" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />
<link rel="stylesheet" type="text/css" href="css/goodsDetail.css" />
<link rel="stylesheet" href="css/jquery.jqzoom.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="css/CT_information_files/CT_common.css" />
<link rel="stylesheet" type="text/css" href="css/CT_information_files/CT_orderInfo.css" />
<link rel="stylesheet" type="text/css" href="css/CT_information_files/CT_shoppingcart.css" />
<link rel="stylesheet" type="text/css" href="css/add_detail.css" />


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="js/goodsDetail.js"></script>


<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

<link rel="stylesheet" type="text/css" media="all" href="css/jquery.dualSlider.0.2.css" />

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/jquery.timers-1.2.js" type="text/javascript"></script>
<script src="js/jquery.dualSlider.0.3.min.js" type="text/javascript"></script>

<script src="js/jquery.livequery.js" type="text/javascript"></script>
<script src="js/jquery.thickbox.js" type="text/javascript"></script>

<script src="js/jquery.jqzoom-core.js" type="text/javascript"></script>
<script src="js/use_jqzoom.js" type="text/javascript"></script>
<script src="js/switchImg.js" type="text/javascript"></script>

<script type="text/javascript">
    
    $(document).ready(function() {
        
        $("#carousel").dualSlider({
            auto:true,
            autoDelay: 6000,
            easingCarousel: "swing",
            easingDetails: "easeOutBack",
            durationCarousel: 1000,
            durationDetails: 600
        });
        
    });    
    
</script>

</head>
<body>

<div id="templatemo_header_wrapper">
	<div id="templatemo_header">
	    <div  id="templatemo_login">
	    <select style="width: 80px" size="1"
						name="selectManufacturer">
							<option value="0">English</option>
							<option value="20">Chinese</option>
							<option value="21">Thai</option>
					</select>
    <a href="CT_Login.html">Login</a> | <a href="CT_Register.html" target="_parent">Register</a></div>
    	<div id="site_title"><a href="http://www.cdu.edu.cn">CDMU</a></div>
    	<div id="templatemo_seach">
        <input type="text" value="serach fruits" size="20" maxlength="20" class="text"></input>
           <input type="submit" value="Go" class="sub"></input>
        </div>
    	
        <div id="templatemo_menu" class="ddsmoothmenu">
             <ul>
                <li><a href="index.html" class="selected">Home</a></li>
                 
                <li><a href="portfolio.html">Products</a>
                    <ul>
                        <li><a href="http://www.cdu.edu.cn">Sub menu 1</a></li>
					</ul>
                </li>
                  <li><a href="blog.html">Recommend</a>
                    <ul>
						<li><a href="http://www.cdu.edu.cn">Sub menu 1</a></li>
					</ul>
                </li>
             
                <li><a href="about.html">About</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of templatemo_menu -->
    </div> <!-- END of templatemo_header -->
</div> <!-- END of templatemo_header_wrapper -->

<div id="templatemo_main_wrapper">
    <div id="templatemo_main">
        <div id="contentt" class="float_l">
        	<div class="post">
	            <a href="images/blog/orange.jpg" class="jqzoom" rel='gal1'  title="triumph"><img src="images/blog/orange.jpg" class="fs" alt=""  jqimg="images/blog/orange.jpg" id="bigImg"/></a>
	            <span>
	                <a href="images/blog/orange.jpg" id="thickImg" title="introduction" class="thickbox">
	                   <img alt="see big pic" src="images/look.gif" />
                	</a>
            	</span>
			    <div id="cdu-spec-list">
			    <div class="cdu-spec-items" style="position: absolute; width: 380px; height: 54px; overflow: hidden; ">
			      
			       <ul class="imgList">
					<li><img src="images/templatemo_image_01.jpg" alt="" /></li>
					<li><img src="images/templatemo_image_02.jpg" alt="" /></li>
					<li><img src="images/templatemo_image_03.jpg" alt="" /></li>
				  </ul>
				
			    </div>
               </div>
            </div>
        </div> <!-- END of content -->
        
        <div id="sidebarr" class="float_r">
        	<h1>orange</h1>
        	<div style="height: 30px;"></div>
            <ul class="cdu_list">
                <li>Price ：<em> 20$/kg</em></li>
                <li>Promotion : <em style="color:#16b7df;"> 20%</em></li>
				<li>Evaluation : <em style="color:#0bda4a"> 350 times</em></li>
				<li>Quantity : <input type="text"/>  kg</li>
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
                <li>Riping Time ：<em style="color: #64BC68"> 2 days later</em></li>
                <li>Inventory ：<em style="color: #F03C3E"> No</em></li>
            </ul>
        
          
         <a href="CT_shoppingcart.html">
         	<img src="images/blog/shoppingcart.jpg" border="0" style="margin-left:9px;"></img>
         </a>
        </div> <!-- END of sidebar -->
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
				<td id="product_parameters_info_right">Product Company: ChiangMai University and ChengDu University</td>
			</tr>
			<tr>
				<td>Size: 100</td>
				<td id="product_parameters_info_right">Product Address: ChiangMai University and ChengDu University</td>
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
				<td id="product_parameters_info_right">Telephone Number: XXXXX</td>
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
			<div class="process_of_info">
			<table>
			<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
				<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			<tr>
				<td>Add the information you want to introduce.</td>
				<td>Add the information you want to introduce.</td></tr>
			<tr>
			</table>
		</div>
	</div>
	
	<div class="cleaner"></div>
	
    </div> <!-- END of templatemo_main_wrapper -->
</div> <!-- END of templatemo_main -->


<div id="templatemo_footer_wrapper">
	<div id="templatemo_footer">
    	 Copyright © 2048 <a href="#">CDU CMU</a> | <a href="http://www.cdu.edu.cn" target="_parent">Fresh Fruit </a>
    </div> <!-- END of templatemo_footer -->
</div> <!-- END of templatemo_footer_wrapper -->

</body>
</html>
>>>>>>> solve product detail page to link with css file

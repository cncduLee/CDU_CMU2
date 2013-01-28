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
	

<div id="templatemo_main_wrapper">
    <div id="templatemo_main">
    	
        <h2>Contact Information</h2>
        <div class="half float_l">
			<h4>Send us a message now!</h4>
                <p>For the next half hour or so, as you relax more and more, you after you fall into slumber, your brain activity level will increase again slightly. This is stage 4 sleep.</p>
            <div id="contact_form">
				<form method="post" name="contact" action="#">
					
					<label for="author">Name:</label> <input type="text" id="author" name="author" class="required input_field" />
					<div class="cleaner h10"></div>
													
					<label for="email">Email:</label> <input type="text" class="validate-email required input_field" name="email" id="email" />

					<div class="cleaner h10"></div>
											
					<label for="subject">Subject:</label> <input type="text" class="validate-subject required input_field" name="subject" id="subject"/>				               
					<div class="cleaner h10"></div>
							
					<label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>
					<div class="cleaner h10"></div>				
												
					<input type="submit" value="Send" id="submit" name="submit" class="submit_btn float_l" />
					<input type="reset" value="Reset" id="reset" name="reset" class="submit_btn float_r" />

				</form>
            </div>
		</div>
        <div class="half float_r">
			<h4>Our Location</h4>
            
             <!--  <div style="width: 460px;height: 320px;" id="localtion">-->
          	 <iframe width="460" height="320" 
            frameborder="0" scrolling="no"
             marginheight="0" marginwidth="0" 
             src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;
             geocode=&amp;q=Central+Park,+New+York,+NY,+USA&amp;aq=0&amp;
             sll=14.093957,1.318359&amp;sspn=69.699334,135.263672&amp;
             vpsrc=6&amp;ie=UTF8&amp;hq=Central+Park,+New+York,+NY,+USA&amp;
             ll=40.778265,-73.96988&amp;spn=0.033797,0.06403&amp;t=m&amp;output=embed">
            
            </iframe> 
            </div>
           
            <div class="cleaner h40"></div>
            <h6><strong>Company Name</strong></h6>

				205-230 In pellentesque pharetra, <br />
                In pellentesque eleifend, 10520<br />
                Suspendisse metus quam<br /><br />
                
            <strong>Phone:</strong> 082-447-0850<br />
			<strong>Email:</strong> <a href="mailto:info@yoursite.com">info@yoursite.com</a>
        </div>
        
        <div class="cleaner h40"></div>
        
    </div> <!-- END of templatemo_main_wrapper -->
</div> <!-- END of templatemo_main -->

	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>

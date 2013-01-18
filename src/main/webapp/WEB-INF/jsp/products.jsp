<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>

<!-- Include Script in folder common -->
<jsp:include page="./common/script.jsp" />
 
</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />

<div id="templatemo_main_wrapper">
    <div id="templatemo_main">
         
  
		<c:forEach items="${products}" var="productx"> 
			<div class="gallery_box">
				   <c:forEach items="${productx.images}" var="pic"> 
				   		<a href="./productpics/showpic/${pic.id}" rel="lightbox[portfolio]"><img src="./productpics/showpic/${pic.id}" class="imgage-with-frame" width="200px" height="112px"/></a>	
				   </c:forEach>
				     
                    
                    <h5>${productx.localName}</h5>
                    <p>${productx.localDescription}</p>
					<a href="#" title="Read more" class="more">Read more</a>
			</div>
		</c:forEach>
		
	
        <div class="cleaner"></div>
        <div class="pagging">
            <ul>
                <c:if test="${currentPage != 1}">
                	<li><a href="productList?page=${currentPage-1}" target="_parent">Previous</a></li>
                </c:if>
                
                <c:if test="${totle <= 10 }">
                	<c:forEach var="i" begin="1" end="${totle}" step="1"> 
			      		<li><a href="productList?page=${i}" target="_parent"> ${i} </a></li> 
			    	</c:forEach>	
                </c:if>
                  
                <c:if test="${totle > 10}">
                	<c:forEach var="i" begin="1" end="5" step="1"> 
			      		<li><a href="productList?page=${i}" target="_parent"> ${i} </a></li> 
			    	</c:forEach>	
			    	
			    	<c:forEach var="j" begin="${totle - 4 }" end="${totle}" step="1"> 
			      		<li><a href="productList?page=${j}" target="_parent"> ${j} </a></li> 
			    	</c:forEach>
                </c:if>
                
                <c:if test="${totle != currentPage}">
                	<li><a href="productList?page=${currentPage + 1}" target="_parent">Next</a></li>
                </c:if>
                
                
            </ul>
        </div>   
        
        <div class="cleaner"></div>
                
        <div class="cleaner"></div>
    </div> <!-- END of templatemo_main_wrapper -->
</div> <!-- END of templatemo_main -->



	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>

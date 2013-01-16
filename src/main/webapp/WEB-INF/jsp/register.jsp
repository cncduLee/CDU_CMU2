<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Fresh Zone Theme - Free CSS Template-www.mianfeimoban.com</title>
<meta name="keywords"
	content="fresh zone, free theme, free templates, templatemo, dualSlider, CSS, HTML" />
<meta name="description"
	content="Fresh Zone Theme, free CSS template provided by templatemo.com" />

<!-- Include Script in folder common -->
 <jsp:include page="./common/script.jsp" />

 
</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />

	<!-- regist info begain -->	
	<div class="Rmain">
		<form:form  modelAttribute="registform" target="_self" >
			<h2 class="f24red">Register</h2>
				<ul id="tab1" class="Rm_input">
						<li><label>Username</label>
					<form:input id="username" class="Rtext w300" type="text" path="Username"/>
					</li>
					
					<li><label>Password</label> 
					<form:input id="password" class="Rtext w300" type="password" path="Password"/>
					</li>
					
					<li><label>Confirm</label> 
					<form:input id="confirm" class="Rtext w300" type="password" path="Confirm"/>
					</li>
					
					<li><label>Type</label>
					<form:select id="type" class="Rtext w300" path="Type">
						<option>Seller</option>
						<option>Customer</option>
					</form:select>
					</li>
					
					<li><label>E-Mail</label>
					<form:input id="email" class="Rtext w300" type="text" path="email"/>
					<span id="emailtip"></span>
					</li>
					
					<li><label>Full name</label> 
					<form:input id="fullname" class="Rtext w300" type="text" path="FullName"/>
					</li>
					
					<li><label>Address</label> 
					<input id="address" class="Rtext w300" type="text" name="Address"/>
					</li>
					
					<li><label>Telephone</label> 
					<form:input id="telephone" class="Rtext w300" type="text" path="Telephone"/>
					</li>
					
					<li><label>Verification code</label> 
					<form:input id="verifycode" class="Rtext w300" type="text" path="VerificationCode"/>
					<span class="yzm"></span>
					</li>
				</ul>
				
				<div class="Rm_submit">
					<p>
						<input id="sz" class="pact_check" type="checkbox" checked="checked" value=""/>
						I have read and agree 
						<a class="red" target="_blank">(Service agreements)</a> 
						<span id="ch"></span>
					</p>
					<div id="Service_agreements" style="display: none;">
						<pre>
								   ======
							===================
							===================
						</pre>
					</div>
					<input class="Rms_btn" type="submit" value="Register Now"/>
				</div>
		</form:form>
	</div>
	<!--end regist info begain 
	<c:forEach var="item" items="${all}">  
				    <tr><td><c:out value="${item.name}"/></td>  
				        <td><c:out value="${item.description}"/></td></tr>  
				   </c:forEach> 
	-->


	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>
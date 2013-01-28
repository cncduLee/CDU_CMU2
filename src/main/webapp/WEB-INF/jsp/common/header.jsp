<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="edu.cmucdu.ecommerce.domain.user.UserDetail"%>

<div id="templatemo_header_wrapper">
    
	<div id="templatemo_header">
	<div  id="templatemo_login">
	<select style="width: 80px" size="1"
						name="selectManufacturer">
							<option value="0">English</option>
							<option value="20">Chinese</option>
							<option value="21">Thai</option>
					</select>
				<%
				UserDetail user = (UserDetail)session.getAttribute("logined_user");
				if(user != null){
					%>
					<%=user.getLocalName() %>|
					<%
					}
				%>
								
				<a href="toLoginPage">Login</a>| <a href="toRegister" target="_parent">Register</a></div>
				
    	<div id="site_title"><a href=".">CDMU</a></div>
    	<div id="templatemo_seach">
        	<form action="searchFoods" action="get">
        		<input type="text" name="foodsName" placeholder="search fruits" size="20" maxlength="20" class="text"></input>
            	<input type="submit" value="Go" class="sub"></input>
        	</form>
        </div>
        <div id="templatemo_menu" class="ddsmoothmenu">
            <ul>
                <li><a href=".">Home</a></li>
                <li><a href="productList">Products</a></li>
                <li><a href="recomment">Recommend</a></li>
                <li><a href="aboutPage">About</a></li>
                <li><a href="contact">Contact</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of templatemo_menu -->
    </div> <!-- END of templatemo_header -->
</div> <!-- END of templatemo_header_wrapper -->




<!--  code security from tile -->
<div style="display:none" id="header"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:jsp="http://java.sun.com/JSP/Page">
	

	<spring:url var="banner" value="/resources/images/banner-graphic.png" />
	<spring:url var="home" value="/" />
	<spring:message code="button_home" var="home_label" htmlEscape="false" />
	<a href="${home}" name="${fn:escapeXml(home_label)}"
		title="${fn:escapeXml(home_label)}"> <img src="${banner}" />
	</a>
	<sec:authorize access="isAnonymous()">
		<div style="margin-left:280px;margin-top: 20px;">
			<spring:url value="/resources/j_spring_security_check" var="form_url" />

			<form name="f" action="${fn:escapeXml(form_url)}" method="POST">
				<div>
					<label for="j_username" style="margin-left: 80px; margin-right: -30px;"> <spring:message
							code="security_login_form_name" />
					</label> <input id="j_username" type='text' name='j_username'
						style="width: 100px;" />
					<spring:message code="security_login_form_name_message"
						var="name_msg" htmlEscape="false" />
					<script type="text/javascript">
          <c:set var="sec_name_msg">
            <spring:escapeBody javaScriptEscape="true">${name_msg}</spring:escapeBody>
          </c:set>
          Spring.addDecoration(new Spring.ElementDecoration({elementId : "j_username", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "${sec_name_msg}", required : true}})); 
        </script>
				</div>
				<div style="margin-top: -23px; margin-left: 220px;">
					<label for="j_password" style="margin-left: 50px; margin-right: -30px;"> <spring:message
							code="security_login_form_password" />
					</label> <input id="j_password" type='password' name='j_password'
						style="width: 100px;" />
					<spring:message code="security_login_form_password_message"
						var="pwd_msg" htmlEscape="false" />
					<script type="text/javascript">
          <c:set var="sec_pwd_msg">
            <spring:escapeBody javaScriptEscape="true">${pwd_msg}</spring:escapeBody>
          </c:set>
          Spring.addDecoration(new Spring.ElementDecoration({elementId : "j_password", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "${sec_pwd_msg}", required : true}})); 
        </script>
				</div>
				<div class="submit" style="margin-left: 450px; margin-top: -22px;">
					<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'proceed', event:'onclick'}));</script>
					<spring:message code="button_submit" var="submit_label"
						htmlEscape="false" />
					<input id="proceed" type="submit"
						value="${fn:escapeXml(submit_label)}" />
				</div>
			</form>
		</div>
	</sec:authorize>
	<div style="margin-left: 630px; margin-top: 10px;">
		<sec:authorize access="isAuthenticated()">
			<c:out value="Welcome back, " />
			<sec:authentication property="principal.user.localName" />
			<c:out value=" | " />
			<span> <spring:url value="/resources/j_spring_security_logout"
					var="logout" /> <a href="${logout}"> <spring:message
						code="security_logout" />
			</a>
			</span>
		</sec:authorize>
	</div>
</div>

	<!-- end code security -->

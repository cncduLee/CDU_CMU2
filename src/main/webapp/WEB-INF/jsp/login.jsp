<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>

	
<!-- Include Script in folder common -->
 <jsp:include page="./common/script.jsp" />

</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="./common/header.jsp" />

	<div id="templatemo_slider_wrapper">


		<div id="right">
			<div id="left"></div>
			<div id="body">
				<form action="login" method="post">
				<table>
					<tr class="clear">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="title">Login</td>
						<td style="color: red"> ${error }</td>
					</tr>
					<tr class="clear">
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" value=""
							class="button"></input></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" class="button"></input></td>
					</tr>
					<tr>
						<td>seller:<input type="radio" name="typeName" checked="checked" value="1"></input></td>
						<td>buyer:<input type="radio" name="typeName" value="2"></input></td>
					</tr>
					<tr>
						<td>Verification code:</td>
						<td>
							<input type="text" name="chechcode" class="code"></input>
							<img src="./image.jsp" alt="chechcode" border="1"/>
						</td>
					</tr>
					<tr>

						<td></td>
						<td><input type="submit" name="submit" value="Submit"
							class="button1"></input><input type="reset" name="reset"
							value="Reset" class="button1"></input></td>
					</tr>
				</table></form>
			</div>
		</div>
	</div>
	<!-- END of templatemo_slider_wrapper -->


	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>
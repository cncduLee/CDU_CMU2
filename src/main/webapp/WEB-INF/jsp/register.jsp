<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<form id="registform"  action="registInfo" method="post" name="registform">
			<h2 class="f24red">Register</h2>
				<ul id="tab1" class="Rm_input">
					
					<li><label>Username</label>
					<input id="username" class="Rtext w300" type="text" name="Username"/>
					</li>
					
					<li><label>Password</label> 
					<input id="password" class="Rtext w300" type="password" name="Password"/>
					</li>
					
					<li><label>Confirm</label> 
					<input id="confirm" class="Rtext w300" type="password" name="Confirm"/>
					</li>
					
					<li><label>Type</label>
					<select id="type" class="Rtext w300" name="Type">
						<option>Seller</option>
						<option>Customer</option>
					</select>
					</li>
					
					<li><label>E-Mail</label>
					<input id="email" class="Rtext w300" type="text" name="email"/>
					<span id="emailtip"></span>
					</li>
					
					<li><label>Full name</label> 
					<input id="fullname" class="Rtext w300" type="text" name="Full name"/>
					</li>
					
					<li><label>Address</label> 
					<input id="address" class="Rtext w300" type="text" name="Address"/>
					</li>
					
					<li><label>Telephone</label> 
					<input id="telephone" class="Rtext w300" type="text" name="Telephone"/>
					</li>
					
					<li><label>Verification code</label> 
					<input id="verifycode" class="Rtext w300" type="text" name="Verification code"/>
					<span class="yzm"></span>
					</li>
				</ul>
				
				<div class="Rm_submit">
					<p>
						<input id="sz" class="pact_check" type="checkbox" checked="checked" value=""/>
						I have read and agree 
						<a class="red" target="_blank">《Service agreements》</a> 
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
		</form>
	</div>
	<!--end regist info begain -->


	<!-- Include footer in folder common -->
	<jsp:include page="./common/footer.jsp" />

</body>
</html>
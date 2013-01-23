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
        <input type="text" placeholder="search fruits" size="20" maxlength="20" class="text"></input>
           <input type="submit" value="Go" class="sub"></input>
        </div>
        <div id="templatemo_menu" class="ddsmoothmenu">
            <ul>
                <li><a href=".">Home</a></li>
                <li><a href="productList">Products</a></li>
                <li><a href="rulesPage">Regulations</a></li>
                <li><a href="">About</a></li>
                <li><a href="">Contact</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of templatemo_menu -->
    </div> <!-- END of templatemo_header -->
</div> <!-- END of templatemo_header_wrapper -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:spring="http://www.springframework.org/tags">
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction"/>
<jsp:directive.page import="edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMU_CDU</title>

<!-- Include Script in folder common -->
<jsp:include page="../common/orderScript.jsp" />
 
</head>
<body>
	<!-- Include header in folder common -->
	<jsp:include page="../common/header.jsp" />

<div id="templatemo_main_wrapper">
		<div id="templatemo_main">

			<!-- cart begain -->

			<div class="Wrap_cart">
				<div class="List_cart marginb10" id="show">

					<img src="images/Input_information.png" alt=""
						style="width: 700px; height: 30px; text-align: center;" />
					<h2>
						<strong>Please check you order information</strong>
					</h2>
					<div class="cart_table">
						<!--收货人地址开始-->
						<div id="part_consignee">
							<div class="o_write">
								<h1>
									Receiver Information &nbsp; <span><a
										style="color: #FF6600; display: {addAddr_Show" href="#"
										id="new_address">[Create a new address]</a></span>
								</h1>

								<div class="middle">
									<div id="nomal_address">
										<table>
											<tr>
												<td style="width: 155px;" align="left">Receiver Name：</td>
												<td>Jone</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">Province：</td>
												<td>Heibei</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">Address：</td>
												<td>Chengdu City</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">Mobile Number：</td>
												<td>74854675</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">Telephone
													Number：</td>
												<td>13540449833</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">E-mail：</td>
												<td>1187495@qq.com</td>
											</tr>
											<tr>
												<td style="width: 155px;" align="left">Postal Code：</td>
												<td>6475654</td>
											</tr>
										</table>
									</div>
									<div id="consignee_addr" style="display: none;">
										<table border="0" cellspacing="0" width="100%">
												<tr>
													<td align="right" valign="middle" width="85"><font
														color="red">*</font>Receiver name：</td>
													<td align="left" valign="middle"><input
														onblur="check_addressName()" value="wang" maxlength="20"
														class="txt" id="consignee_addressName" type="text" />&nbsp;

													</td>
												</tr>
												<tr>
													<td align="right" valign="middle"><font color="red">*</font>Province：</td>
													<td align="left" valign="middle"><span
														id="consignee_arae">
															<table>
																<tr>
																	<td>
																		<div id="div_Province">
																			<select onchange="changeProvince(this)"
																				id="consignee_province"><option value="-22">select</option>
																				<option value="1">Beijing</option>
																				<option selected="selected" value="2">Shanghai</option>
																				<option value="3">Tianjin</option>
																				<option value="4">Chongqing</option>
																				<option value="5">Hebei</option>
																				<option value="6">Shanxi</option>
																				<option value="7">Henan</option>
																				<option value="8">liaoning</option>
																				<option value="9">Jilin</option>
																				<option value="10">Heilongjiang</option>
																				<option value="11">Neimenggu</option>
																				<option value="12">Jiangsu</option>
																				</select>
																		</div>
																	</td>
																	<td>
																		<div id="div_City">
																			<select onchange="changeCity(this)"
																				id="consignee_city"><option
																					selected="selected" value="-22">select</option>
																				<option value="72">朝阳区</option>
																				<option value="2800">海淀区</option>
																				<option value="2801">西城区</option>
																				<option value="2802">东城区</option>
																				<option value="2803">崇文区</option>
																				</select>
																		</div>
																	</td>
																	<td>
																		<div id="div_County">
																			<select onchange="changeCounty(this)"
																				id="consignee_county"><option
																					selected="selected" value="-22">select</option>
																				<option value="2799">三环以内*</option>
																				<option value="2819">三环到四环之间*</option>
																				<option value="2839">四环到五环之间*</option>
																				<option value="2840">五环到六环之间*</option>
																				</select>
																		</div>
																	</td>
																	<td>
																		<div id="div_Towns" style="display: none">
																			<select onchange="changeTowns(this)"
																				id="consignee_towns">
																				<option selected="selected" value="-22">select</option>
																			</select>
																		</div>
																	</td>
																	<td>&nbsp;&nbsp;Note:Mark"*"means the areas of
																		supporting cash on delivery,check the areas of
																		supporting cash on delivery<a class="link_005AA0"
																		target="_blank"
																		clstag="checkout|keycount|jiesuan|link005aa0"
																		href="#">Check
																			the area of cash on delivery.</a>
																	</td>
																</tr>
															</table>
													</span></td>
												</tr>
												<tr>
													<td align="right" valign="middle"><font color="red">*</font>Address：</td>
													<td align="left" valign="middle"><span
														id="consigneeShow_addressName">Inner Ring
															3,Chaoyang District ,Beijing</span> <input
														onblur="check_address()" value="pen"
														style="margin-left: 2px; width: 327px" maxlength="50"
														class="txt" id="consignee_address" type="text" />&nbsp;</td>
												</tr>
												<tr>
													<td align="right" valign="middle"><font color="red">*</font>Mobile
														Number：</td>
													<td align="left" valign="middle"><input
														onblur="check_message()" maxlength="20"
														value="12343322222" class="txt" id="consignee_message"
														type="text" /> &nbsp;or Telephone Number： <input
														onblur="check_phone()" maxlength="20" value="3333333"
														class="txt" id="consignee_phone" type="text" />&nbsp;<font
														color="#000000">Be used for short message of
															dispatching and confirmation before dispatching.</font></td>
												</tr>
												<tr>
													<td align="right" valign="middle">Email：</td>
													<td align="left" valign="middle"><input
														onblur="check_email()" maxlength="50" class="txt"
														id="consignee_email" type="text" /> &nbsp;<font
														color="#000000">Be used for receiving reminding
															mails of order list,making you know the status of order
															list.</font></td>
												</tr>
												<tr>
													<td align="right" valign="middle">Postal Code：</td>
													<td align="left" valign="middle"><input maxlength="20"
														id="consignee_postcode" onblur="check_postcode()"
														style="width: 77px" class="txt" type="text" />&nbsp;<font
														style="margin-left: 53px" color="#000000">Be
															helpful of fast confirming the address of dispatching.</font></td>
												</tr>
											</tbody>
										</table>
										<div style="padding: 10px 0 20px 45px;">
											<a href="#none" id="add_2_nomal">[Add to the commonly
												used address]</a>
										</div>

									</div>
								</div>


								<div class="footer">
									<input onclick="savePart_consignee()" id="partButton"
										value="Store receiver info" class="btn" type="button" />
								</div>

							</div>
						</div>
						<!--收货人地址结束-->
						
						<!--支付方式及配送方式开始-->
						
						<div id="part_payTypeAndShipType">
							<div class="o_show">
								<h1>
									Way of Payment and dispaching&nbsp; 
								</h1>
								<div id="updateInfo_payType"></div>
								<div class="middle">
									<div id="choose_pay" >

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px"><div class="grouptitle">Way
															of Payment</div></td>
													<td style="border-bottom: #eee 1px solid;">Remark</td>
												</tr>
										</table>

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType1"
														onclick="changePayType(1);" value="1" type="radio" /><label
														style="margin-left: 2px;" for="IdPaymentType1">Cash
															on delivery </label></td>
													<td class="gray" valign="top">You can use cash,POS or
														check payments to pay for you goods after home delivery<a
														target="_blank" href="#">Check the transportation fee
															and dispaching area</a>
													</td>
												</tr>
										</table>

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType4"
														onclick="changePayType(4);" value="4" type="radio"
														checked="checked" /><label style="margin-left: 2px;"
														for="IdPaymentType4">Online Payment </label></td>
													<td class="gray" valign="top">Arrives account on
														time,support vast majority of bank debit card and part of
														the bank credit card.<a target="_blank" href="#">Check
															bank and limits</a>
													</td>
												</tr>
										</table>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType3"
														onclick="changePayType(3);" value="3" type="radio" /><label
														style="margin-left: 2px;" for="IdPaymentType3">Website
															since mention </label></td>
													<td class="gray" valign="top">Support cash,POS or
														check payments <a target="_blank" href="#">Check since
															mention address</a>
													</td>
												</tr>
										</table>

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType16"
														onclick="changePayType(16);" value="16" type="radio" /><label
														style="margin-left: 2px;" for="IdPaymentType16">Campus
															business hall since mention </label></td>
													<td class="gray" valign="top">Support cash,POS or
														check payments <a target="_blank"
														clstag="checkout|keycount|jiesuan|ziti1" href="#">Check
															campus business hall address</a>
													</td>
												</tr>
										</table>

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType5"
														onclick="changePayType(5);" value="5" type="radio" /><label
														style="margin-left: 2px;" for="IdPaymentType5">Company
															transfers </label></td>
													<td class="gray" valign="top">1-3 working days arrive
														the account by the quick money platform. <a
														target="_blank" href="#"> Check account Information</a>
													</td>
												</tr>
										</table>

										<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td style="width: 240px" align="left" valign="top"><input
														name="IdPaymentType" id="IdPaymentType2"
														onclick="changePayType(2);" value="2" type="radio" /><label
														style="margin-left: 2px;" for="IdPaymentType2">Postal
															remittance </label></td>
													<td class="gray" valign="top">1-3 working days arrive
														the account by the quick money platform.<a target="_blank"
														href="#">Manual</a>
													</td>
												</tr>
										</table>
									</div>
								</div>
								<div class="footer"></div>
							</div>
						</div>
						<!--支付方式及配送方式结束-->
						<!--发票信息开始-->
						<div id="part_invoice">

							<div class="o_show">
								<h1>Invoice information&nbsp;</h1>
								<div class="middle">
									<table style="width: 100%;">
											<tr>
												<td style="text-align: left; width: 155px;">Invoice
													Type：</td>
												<td>commom</td>
											</tr>
											<tr>
												<td style="text-align: left;">Invoice Title：</td>
												<td>Personal</td>
											</tr>
											<tr>
												<td style="text-align: left;">Invoice Content：</td>
												<td></td>
											</tr>
									</table>
									<table style="width: 100%; display: none">
											<tr>
												<td style="text-align: left; padding-left: 22px">NO
													Invoiced</td>
											</tr>
									</table>

								</div>
								<div class="footer"></div>
							</div>
						</div>
						<!--发票信息结束-->
						<!--Cart信息开始-->
						<div id="part_cart">
							<div class="o_show">
								<h1>
									<span style="margin-right: 580px;">Commodity list</span><a
										id="backToCartBtn" href="javascript:void();"
										onclick="toCart();"
										clstag="checkout|keycount|jiesuan|backtocartbtn"
										style="color: #0070D7">Return/Modify Shopping Cart </a>
								</h1>
								<div id="promiseDate" style="color: #FF9900"></div>
								<div class="middle">
									<table class="Table" cellpadding="0" cellspacing="0"
										width="100%">
											<tr class="align_Center Thead">
												<td width="20%">Commodity Number</td>
												<td width="40%">Commodity Name</td>
												<td width="10%">Price In</td>
												<td width="8%">Cash Return</td>
												<td width="8%">Points Bonus</td>
												<td width="9%">Status of Stocks</td>
												<td width="9%">Commodity Quantity</td>
											</tr>


											<tr class="align_Center">
												<td style="padding: 5px 0 5px 0;">1005367078</td>
												<td><a target="_blank" href="#" onclick="this.blur();"
													clstag="clickcart|keycount|shoppingcartpop|productnamelinklistcart">super
														specy pepper in the world</a></td>
												<td><span class="price">￥99.00</span></td>
												<td>￥0.00</td>
												<td>0</td>
												<td>right now</td>
												<td>1</td>
											</tr>
									</table>
								</div>
								<!-- </div> -->
							</div>
						</div>
						<!--Cart信息结束-->

						<!--有货先发开始-->
						<div id="part_FirstInstockShip"
							style="padding: 8px 0 8px 30px; color: red; display: none;"></div>
						<!--有货先发结束-->

						<!-- 结算信息开始 -->
						<div id="ware_info">
							<div
								style="background: #fff; font-size: 14px; font-weight: bold; padding-left: 2px;">Settlement
								Information</div>
							<h1></h1>
							<div class="middle">
								<!--金额信息-->
								<span id="moneyArea">
									<ul id="part_info">
										<li class="info1" style="padding-bottom: 5px">Commodity
											Value：99.00 RMB + Transportation Charge：0.00 RMB - Coupon：
											0.00 RMB - Gift Card：0.00 RMB - Cash Return：0.00 RMB <br/>
										</li>
									</ul>
								</span>
								<!--金额信息结束-->


								<!--提交-->
								<div class="o_show submit">
									<div>
										<span id="submitWaitInfo"></span>
									</div>
									<div id="show_error"></div>
									<div id="submit_btn">
										<span id="ccPanel" ></span>
										<table cellpadding="0" cellspacing="0" width="100%">
											<tr>
												<td
													style="padding: 0; text-align: left; vertical-align: top;">
													<div style="width: 600px;" id="part_remark">
														<div id="show_remark" style="display: none;"></div>
													</div>
												</td>
												<td style="width: 100px; padding: 0;"><input
													id="Consignee_Hidden_Type" value="1" type="hidden" /> <input
													id="PayType_Hidden_Type" value="0" type="hidden" /> <input
													id="PartInvoice_Hidden_Type" value="0" type="hidden" /> 
													<a href="submitOrder"> 
													<input id="submit" onclick="submitOrder" clstag="checkout|keycount|jiesuan|sumbit"
														style="margin-top: 2px; border: none; cursor: pointer; width: 160px; height: 53px; background: url(images/cartsubmit.jpg)"
														type="button" />
												</a></td>
											</tr>
										</table>
									</div>
								</div>
								<!--提交结束-->

							</div>
							<div class="round">
								<div class="lround"></div>
								<div class="rround"></div>
							</div>
						</div>
						<div class="Footer_Nav">
							<div id="footer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- cart end -->
			<div class="cleaner"></div>
		<!-- END of templatemo_main_wrapper -->
	</div>
	<!-- END of templatemo_main -->

	<!-- Include footer in folder common -->
	<jsp:include page="../common/footer.jsp" />

</body>
</html>
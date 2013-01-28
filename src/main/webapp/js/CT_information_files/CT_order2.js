var ffAlertTxt="含有非法字符！";
var errorUrl ="http://cart.360buy.com/order/orderBack.html?rid=";
var jingSafeType = "jingSafeType";
var dongSafeType = "dongSafeType";
var lpkSafeType = "lpkSafeType";
var preNoCheckInvoice = null;//记录取消发票上一次状态
var preNoCheckBookInvoice = null;//记录取消书发票上一次记录
//--------------------------余额---------------------------
function useOrCancelBalance(obj)
{
	var checked = (obj.checked)?"1":"0";
	$(obj).attr("disabled",true);
	if(checked == 1){
		$.getJSON(PurchaseAppConfig.OrderDomain + "/useBalance.action?rt="+Math.random(),function(result){
			if(result != null){
				if(result.error != null && result.error != ""){
					if(result.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}    
			
			$(obj).attr("disabled",false);
			refreshMoneyArea(result);
		});
	}else{
		$.getJSON(PurchaseAppConfig.OrderDomain + "/cancelBalance.action?rt="+Math.random(),function(result){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
			$(obj).attr("disabled",false);
			refreshMoneyArea(result);
		});
	}
	
}

//--------------------------余额---------------------------
//----------------------------优惠券--------------------------
function showTicket()
{
   if(g('part_ticket').style.display=='none')
   {
       g('part_ticket').style.display='';
       if($("#couponsArea").html()==null || $("#couponsArea").html()==""){
    	   queryCoupons();
       }
   }else{
       g('part_ticket').style.display='none';
   }
   setCouponStateShow();
}
function setCouponStateShow()
{
  if(g('part_ticket').style.display=='')
  {
     g('couponStateShow').innerHTML='-';
  }else{g('couponStateShow').innerHTML='+';}
}
function queryCoupons(flag){
	//第一次查询优惠券列表时，显示"正在获取优惠卷，请等待"，以后每次操作优惠券，都都不显示"正在获取优惠卷，请等待"
	if(flag != 1){
		$("#couponsArea").html("<span class='waitInfo' id='waitInfo'>正在获取优惠卷，请等待！</span>");
	}
	$.getJSON(PurchaseAppConfig.OrderDomain + "/queryCoupons.action?rt="+Math.random(),function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
		coupons = result;//保存起来，后面使用或取消优惠劵使用
		var param = processResult(result);
		var data = TrimPath.processDOMTemplate("couponsData", param);
		$("#couponsArea").html(data);
		$.getJSON(PurchaseAppConfig.OrderDomain + "/checkUseCouponPwd.action?rt="+Math.random(),function(result){
			if(result.checkJingDongLpkSafe==false)
				jingDongLpkNoUse(jingSafeType);
		});	
		$.getJSON(PurchaseAppConfig.OrderDomain + "/checkUseDongCouponPwd.action?rt="+Math.random(),function(result){
			if(result.checkJingDongLpkSafe==false)
				jingDongLpkNoUse(dongSafeType);
		});	
	});
}
var coupons ;//保存用户最初的优惠劵信息

//预处理action返回的json串，定义时间格式化标签
function processResult(result){
	//自定义一个标识符 formatDate
	var myModifiers = {
		parseDate : function(thisvalue) {
			return formatDate(thisvalue,"yyyy-MM-dd");
		}
	};
	var arr = function(arg){ return getLoops(arg,true); };
	var param = {"result":result,"func":arr};  
	if (null == result){
		param = {"result":"","func":arr};  
	}   
	param._MODIFIERS = myModifiers;//放到_MODIFIERS对象
	return param;
}
//日期格式化函数
function formatDate(str,format){
	try{
		if(str != null && str != "" && str.split(" ").length > 0){
			var date = new Date(str.split(" ")[0]);
			var o = { 
			    "M+" : date.getMonth()+1, //month 
			    "d+" : date.getDate(),    //day 
			    "h+" : date.getHours(),   //hour 
			    "m+" : date.getMinutes(), //minute 
			    "s+" : date.getSeconds(), //second 
			    "q+" : Math.floor((date.getMonth()+3)/3),  //quarter 
			    "S" : date.getMilliseconds() //millisecond 
			};
			if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
			    (date.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			for(var k in o)if(new RegExp("("+ k +")").test(format)) 
			    format = format.replace(RegExp.$1, 
			      RegExp.$1.length==1 ? o[k] : 
			        ("00"+ o[k]).substr((""+ o[k]).length)); 
			return format; 
		}
	}catch(e){}
}
function addShiTiCoupon(){
	var key = $("#txtInputKey").val();
	useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/useShiTiCoupon.action?rt="+Math.random()+"&cardKey="+key,null,1,null);
}
function removeShiTiCoupon(id){
	useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/cancelCoupon.action?rt="+Math.random()+"&cardId="+id,null,0,null);
}
function selectJing(obj,key,id){
	var checked = (obj.checked)?"1":"0";
	//$(obj).attr("disabled",true);
	if(checked == 1){
		useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/useCoupon.action?rt="+Math.random()+"&cardKey="+key+"&cardType=0",obj,checked,jingSafeType);
	}else{
		useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/cancelCoupon.action?rt="+Math.random()+"&cardId="+id,obj,checked,jingSafeType);
	}
}



function cancelDong(id){
	$("#dong_"+id).click();
}
//东券是单选，但是可以取消选择
function selectDong(obj,key,id){
	//如果原先是选中，则变成不选中；否则变成选中
	var checked = $(obj).parent("div").attr("class")=="couponSelect" ? "0" : "1";
	//$(obj).attr("disabled",true);
	if(checked == 1){
		$(obj).attr("checked",true);
		$(obj).parent("div").siblings("div").attr("class","couponNoSelect");
		useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/useCoupon.action?rt="+Math.random()+"&cardKey="+key+"&cardType=1",obj,checked,dongSafeType);
	}else{
		$(obj).removeAttr("checked");
		useOrCancelCoupon(PurchaseAppConfig.OrderDomain + "/cancelCoupon.action?rt="+Math.random()+"&cardId="+id,obj,checked,dongSafeType);
	}
}




function useOrCancelCoupon(url,obj,checked,jingdDongLpkType){
	
	//设置(+)(-)
	setCouponStateShow();
	//京券东券全部disabled，取消按钮不可见
	$("[id^='CouponCancel_']").css("display","none");
//	$("input[type=radio][id^='dong_']").attr("disabled",true);
	$("input[type=checkbox][id^='jing_']").attr("disabled",true);
	$.getJSON(url,function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
//		$("input[type=radio][id^='dong_']").attr("disabled",false);
		$("input[type=checkbox][id^='jing_']").attr("disabled",false);
		
		//如果在勾选京东券时返回不安全时，则将相应的优惠券、礼品卡置为不能使用
		if(result.checkJingDongLpkSafe==false){
			//将勾选状态取消
			$(obj).attr("checked",false);
			$(".couponSelect > label").children("[id^=CouponCancel]").css("display","");
			jingDongLpkNoUse(jingdDongLpkType);			
			return;
		}		
		if(result.errorMsg!=null && result.errorMsg!=""){
			alert(result.errorMsg);
			if(checked == 1){
				$(obj).attr("checked",false);
			}
			$(".couponSelect > label").children("[id^=CouponCancel]").css("display","");
			
			return;
		}
		if(obj == null){
			//刷新实体券列表
			var usedCoupons = result.order.usedCoupons;
			if(usedCoupons!=null){
				var data = TrimPath.processDOMTemplate("shitiData", result);
				$("#shitiList").html(data);
			}else{
				$("#shitiList").html("");
			}
			
		}
		var couponCount = $("#coupon_count").html();
		
		
		//改变选中按钮
		if(checked==1){//选中
			if(obj != null){
				$(obj).parent("div").removeClass("couponNoSelect");
				$(obj).parent("div").addClass("couponSelect");
				$(obj).siblings("label").children("[id^=CouponCancel]").css("display","");
				isNeedPaymentPassword();
			}
		}else{
			if(obj != null){//未选中
				$(obj).parent("div").removeClass("couponSelect");
				$(obj).parent("div").addClass("couponNoSelect");
				//$(obj).siblings("label").children("[id^=CouponCancel]").css("display","none");
				g('paypasswordPanel').style.display='none';
			}
		}
		queryCoupons(1);
		useCancelCouponCommon(result);
	});
}

//刷新金额
function refreshMoneyArea(result){
	var balanceAmount;
	if((result.balance != null || result.balance != "" )){
		balanceAmount = result.balance;
	}
	var data = TrimPath.processDOMTemplate("moneyData", result);
	$("#moneyArea").html(data); 
	//优惠券金额
	var totalCouponDiscount = "${order.priceInfo.varTotalCouponDiscount}".process(result);
	$("#coupon_discount").html(totalCouponDiscount);
	//礼品卡金额
	var totalLiPinKaDiscount = "${order.priceInfo.varTotalLiPinKaDiscount}".process(result);
	//
	$("#lipinka_discount").html(totalLiPinKaDiscount);
	exceedCoupon(result);
	isNeedPaymentPassword();
	isUserSafeVerifyOk(balanceAmount);
	//设置(+)(-)
	setCouponStateShow();
}


function exceedCoupon(result){
	var totalUserCoupons = 0 ;//已使用优惠卷总面值
	if(result.order != 'undefined' && result.order != null){
		if(result.order.usedCoupons!='undefined' && result.order.usedCoupons!=null ){
			for(var i = 0 ; i < result.order.usedCoupons.length; i ++){
				if(result.order.usedCoupons[i].varDiscount != 'undefined' && result.order.usedCoupons[i].varDiscount != null)
					totalUserCoupons = totalUserCoupons+result.order.usedCoupons[i].varDiscount;
			}
			//当前已使用优惠面值
			if(result.order.priceInfo !='undefined' && result.order.priceInfo != null
					&& result.order.priceInfo.varTotalCouponDiscount !='undefined' 
					&& result.order.priceInfo.varTotalCouponDiscount != null)
				
				var preUserCoupons = result.order.priceInfo.varTotalCouponDiscount;
			if(totalUserCoupons > preUserCoupons){
				g('couponWaste').style.display='';
			}else{ 
				g('couponWaste').style.display='none';
			}
		}else{
			g('couponWaste').style.display='none';
		}
	}
}
//------------------------优惠券------------------------

//----------------------礼品卡--------------------------
function showLipinka()
{
   if(g('part_lipinka').style.display=='none')
   {
       g('part_lipinka').style.display='';
       if($("#lipinkasArea").html()==null || $("#lipinkasArea").html()==""){
    	   queryLipinkas();
       }
       
   }else{
       g('part_lipinka').style.display='none';
   }
   setLiPinKaStateShow();
}
function setLiPinKaStateShow()
{
  if(g('part_lipinka').style.display=='')
  {
     g('lipinkaStateShow').innerHTML='-';
  }else{g('lipinkaStateShow').innerHTML='+';}
}

function queryLipinkas(){
	$("#lipinkasArea").html("<span class='waitInfo' id='waitInfo'>正在获取礼品卡，请等待！</span>");
	jQuery.ajax({  
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain + "/queryLipinkas.action?rt="+Math.random(),
		success : function(result){
			if(result != null){
				if(result.error != null && result.error != ""){
					if(result.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			var param = processResult(result);
			var data = TrimPath.processDOMTemplate("lipinkasData", param);
			$("#lipinkasArea").html(data);  
			$.getJSON(PurchaseAppConfig.OrderDomain + "/checkUseLpkPwd.action?rt="+Math.random(),function(result){
				if(result.checkJingDongLpkSafe==false)
					jingDongLpkNoUse(lpkSafeType);
			});			
		}
	});
}
function addLipinka(){
	if($('#lpkKeyPressFirst').val()==""
		&& $('#lpkKeyPressSecond').val()==""
		&& $('#lpkKeyPressThird').val()==""
		&& $('#lpkKeyPressForth').val()==""){
		alert("请输入礼品卡密码");
		return;
	}
	var key = $("#lpkKeyPressFirst").val()+"-"
		+$("#lpkKeyPressSecond").val()+"-"
		+$("#lpkKeyPressThird").val()+"-"
		+$("#lpkKeyPressForth").val();
	useOrCancelLipinka(PurchaseAppConfig.OrderDomain + "/useLipinka.action?rt="+Math.random()+"&cardKey="+key+"&isBinded="+false,null,1,true,key);
}
function selectLipinka(obj,isBinded,key,id){
	var checked = (obj.checked)?"1":"0";
	//$(obj).attr("disabled",true);
	if(checked == 1){
		useOrCancelLipinka(PurchaseAppConfig.OrderDomain + "/useLipinka.action?rt="+Math.random()+"&cardKey="+key+"&isBinded="+isBinded,obj,checked,false,null);
	}else{
		useOrCancelLipinka(PurchaseAppConfig.OrderDomain + "/cancelLipinka.action?rt="+Math.random()+"&cardId="+id+"&cardKey="+key,obj,checked,false,null);
	}
}

function isNeedPaymentPassword(){
	//是否需要支付密码
	$.getJSON(PurchaseAppConfig.OrderDomain + "/isNeedPaymentPassword.action?rt="+Math.random(),function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
		if(result.nppr != null ){
			if(result.nppr.flag){
				if(result.nppr.need != null && result.nppr.need){ 
					g('paypasswordPanel').style.display='';
				}else{
					g('paypasswordPanel').style.display='none';
				}
			}
		}
	});
}


function useOrCancelLipinka(url,obj,checked,isAddItem,lipinkakey){
	$("input[type=checkbox][id^='lpkItem_']").attr("disabled",true);
	$.getJSON(url,function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
		$("input[type=checkbox][id^='lpkItem_']").attr("disabled",false);
		if(result.checkJingDongLpkSafe==false){
			$(obj).attr("checked",false);
			jingDongLpkNoUse(lpkSafeType);
			return;
		}
		if(result.errorMsg!=null && result.errorMsg!=""){
			alert(result.errorMsg);
			if(checked == 1)
				$(obj).attr("checked",false);
			
			return;
		}
		
		
		
		//勾选或者取消的礼品卡 当前使用金额变化
		if(result.changedLipinka!=null){
			if(isAddItem){
				//添加礼品卡 增加一条礼品卡信息
				if(result.changedLipinka!=null){
					//如果礼品卡没有被绑定
					if(result.changedLipinka.pin==null){
						//用户是否选择绑定
						if(confirm("密码正确！是否将该礼品卡绑定至当前账号？")){
							//异步判断是否绑定成功
							$.getJSON(PurchaseAppConfig.OrderDomain 
									+ "/bindLiPinka.action?rt="+Math.random()
									+"&liPinkKaId="+result.changedLipinka.id
									+"&cardKey="+lipinkakey,function(bindResult){
								if(bindResult.bindLiPinKaResult.flag && bindResult.bindLiPinKaResult.bindSuccess){
									result.changedLipinka.isBindedNow = true;
									processUseOrCancelLipinka(result);	
								}else{
									alert("绑定失败！");
									result.changedLipinka.isBindedNow = false;
									processUseOrCancelLipinka(result);
								}
								doAddLiPinKaCount(obj,checked);
							});
						}else{
							result.changedLipinka.isBindedNow = false;
							processUseOrCancelLipinka(result);
							doAddLiPinKaCount(obj,checked);
						}
					}else{
						result.changedLipinka.isBindedNow = true;
						processUseOrCancelLipinka(result);	
						doAddLiPinKaCount(obj,checked);
					}
				}
			}else{
				var discountCurUsed = "${changedLipinka.varDiscountCurUsed.toFixed(2)}".process(result);
				var leaveMoney = "${changedLipinka.leaveMoney.amount.toFixed(2)}".process(result);
				var id = $(obj).val();
				$("#lipinkaCurUsed_"+id).html(discountCurUsed);
				$("#lipinkaLeaveMoney_"+id).html(leaveMoney);
				doAddLiPinKaCount(obj,checked);
			}
		}else{
			doAddLiPinKaCount(obj,checked);
		}
		refreshMoneyArea(result);
	});
}

function doAddLiPinKaCount(obj,checked){
	var lipinkaCount = $("#lipinka_count").html();
	if(checked==1){
		lipinkaCount = parseInt(lipinkaCount)+1;
		isNeedPaymentPassword();
	}else{
		lipinkaCount = parseInt(lipinkaCount)-1;
		
		var id = $(obj).attr("id").split("_")[1];
		var discount = $("#lpkItem_"+id).attr("name");
		//未绑定的礼品卡删除
		$("#unbinded_"+id).remove();
		//已绑定的礼品卡当前使用金额变0
		$("#lipinkaCurUsed_"+id).html("0.00");
		$("#lipinkaLeaveMoney_"+id).html(discount);
		g('paypasswordPanel').style.display='none';
	}
	$("#lipinka_count").html(lipinkaCount+'');
}

function processUseOrCancelLipinka(result){
	var template = "{var changedLipinka = result.changedLipinka}{if changedLipinka.isBindedNow==true}{var isBinded = true}{else}{var isBinded = false}{/if}"
		+"{if isBinded==false}<tr id='unbinded_${changedLipinka.id}'>{else}<tr>{/if}<td style='text-align:left;padding-left:3px;'>"
		+"<input id='lpkItem_${changedLipinka.id}' type='checkbox' checked value='${changedLipinka.id}' onclick=selectLipinka(this,'${isBinded}','${changedLipinka.key}','${changedLipinka.id}');>"
		+"<label for='lpkItem_${changedLipinka.id}'>${changedLipinka.id}</label>"
		+"</td>"
		+"<td>${changedLipinka.varDiscount.toFixed(2)}</td>"
		+"<td id='lipinkaCurUsed_${changedLipinka.id}'>${changedLipinka.varDiscountCurUsed.toFixed(2)}</td>"
		+"<td>${changedLipinka.leaveMoney.amount.toFixed(2)}</td>"
		+"<td>${changedLipinka.putOutTime|parseDate}~${changedLipinka.timeEnd|parseDate}</td>"
		+"<td>{if isBinded==true}已绑定{else}未绑定{/if}</td>"
		+"</tr>";
	var param = processResult(result);
	var data = template.process(param);
	$('#lipinkaTable').append(data);
}

function refreshLipinkaArea(result,obj){
	//勾选或者取消的礼品卡 当前使用金额变化
	if(result.changedLipinka!=null){
		var discountCurUsed = "${changedLipinka.varDiscountCurUsed.toFixed(2)}".process(result);
		var id = $(obj).val();
		$("#lipinkaCurUsed_"+id).html(discountCurUsed);
	}
	refreshMoneyArea(result);
}
//---------------------------礼品卡------------------------
function myorder()
{
    this.order=null;
    this.balance=null;
    this.areaName='0';
}
//初始化订单页面
function call(url,param,errorMsg){
	jQuery.ajax({  
		type : "POST",
		dataType : "json",
		url : url,
		data : param,
		success : function(data) {
			if(data != null){
				if(data.errorMessage != 'undefined' && data.errorMessage != null && data.errorMessage != ''){
					alert(data.errorMessage);
				}
				/**
				 * login、cookie拦截器返回错误
				 */
				if(data.error != 'undefined' && data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
					if(data.error == 'NotLogin'){
						alert("登录超时请重新登录.");
						window.location.href=PurchaseAppConfig.OrderLoginUrl;
						return;
					}
				} 
				
				if(data.resultCode != 'undefined' && data.resultCode != null && data.resultCode != ""){
					if(data.resultCode == "0600001"){
						goCart();
					}
					if(data.resultCode =="SkuChanged" || data.resultCode =="GoToShoppingPage"){
						if(data.errorMessage != 'undefined' && data.errorMessage != null){
							alert(data.errorMessage);
						}
						else{
							alert("请注意：根据您最新的收货地址，您购物车中的商品或价格发生了变化！");
						}
						if(data.resultCode =="GoToShoppingPage"){
							goCart();
						}
					}
				}
				
				if(data.error=='undefined' || data.error==null || data.error==''){
					var o=new myorder();
					o.order=data;
					if(data.balance != 'undefined' && data.balance != null){
						o.balance=data.balance;
					}
					if(data.areaName != 'undefined' && data.areaName != null){
						o.areaName=data.areaName;
					}
					var result = TrimPath.processDOMTemplate("order_jst", o); 
					g("show").innerHTML = result;
					lipinkaInputEventInit();
					//设置验证码
					if(data.isNeedCheckCode != 'undefined' && data.isNeedCheckCode != null){
						if(data.isNeedCheckCode == false ){
							//不显示
						}else if(data.isNeedCheckCode== true){
							refreshCheckCode();
						}
					}
					processCart();//加载购物车
					isSupInStockFirstShip();//有货先发
					exceedCoupon(data);//超出优惠面额提示信息 
					showOrderRemark();
					//是否余额开启了支付密码等.
					if(data.balance != 'undefined' && data.balance!=null ){
						var balanceAmount = data.balance;
						isUserSafeVerifyOk(balanceAmount);
					}
					isNeedPaymentPassword();//是否存在支付密码
					if(data.areaName == "0"){
						Edit_Consignee();//如果不存在收货人信息 默认打开编辑功能
					}
					else if(data.shipIsIn == false){
						Edit_PayType();
					}
				}
			}
		},
		complete:function(XMLHttpRequest, textStatus){
			if(  XMLHttpRequest.status==400 ||
					XMLHttpRequest.status==408 ||
					XMLHttpRequest.status==500 ||
					XMLHttpRequest.status==501 ||
					XMLHttpRequest.status==502 ||
					XMLHttpRequest.status==503 ||
					XMLHttpRequest.status==504 ||
					XMLHttpRequest.status==505 ||
					XMLHttpRequest.status==506 ||
					XMLHttpRequest.status==507 ||
					XMLHttpRequest.status==508){
				window.location.href=errorUrl+Math.random();
			}
		}
	});
}

function goCart(){
	window.location.href='http://cart.360buy.com/cart/cart.html?r='+Math.random();
}

function clearWaitInfo(){
   var newd=g("waitInfo");
   if(newd!=null)
   {
      newd.parentNode.removeChild(newd);
   }
}



/*****************************************收货人信息***************************************/
/*****************************************************************************************/
var OldConsignee = "";
//编辑收货人信息
function Edit_Consignee(obj){
	clearWaitInfo();
	OldConsignee = g("part_consignee").innerHTML;
	showWaitInfo('正在获取收货人信息，请等待。。。',obj);
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getAreaInfo.action?rt="+Math.random(),
		data : "",
		success : function(data) {
			if(data != null){
				
				/**
				 * 拦截器错误返回
				 */
				if(data.error != 'undefined' && data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
					if(data.error == 'NotLogin'){
						alert("登录超时请重新登录.");
						window.location.href=PurchaseAppConfig.OrderLoginUrl;
					}
				} 
				
				if(data.areaName=="undefined" || data.areaName == null || data.areaName == ""){
					$("#addressListPanel").hide();
				}
				if(data.flag==true && data.consigneeInfo !='undefined' && data.consigneeInfo != null ){
					var result = TrimPath.processDOMTemplate("consignee_jst", data); 
					g("part_consignee").innerHTML = result;
					if(data.areaName!="undefined" && data.areaName!=null){
						g('consigneeShow_addressName').innerHTML=data.areaName;
					}
					//设置省市县地址
					if(data.consigneeInfo.area != 'undefined' && data.consigneeInfo.area != null){
						g('Consignee_Hidden_Type').value=1;//设置隐藏编辑状态为打开
						//获取常用收货人信息
						getAddressList('');
						//选中的省
						if(data.provincesHtml != 'undefined' && data.provincesHtml != null && data.provincesHtml != ""){
							g('div_Province').innerHTML=data.provincesHtml;
							$("#consignee_province>option").each(function(i){
								if(data.consigneeInfo.area.provinceId == this.value){
									this.selected = true;
									return false;
								}
							});
						}
						//选中的市
						if(data.citysHtml != 'undefined' && data.citysHtml != null && data.citysHtml != ""){
							g('div_City').innerHTML=data.citysHtml;
							$("#consignee_city>option").each(function(i){
								if(data.consigneeInfo.area.cityId == this.value){
									this.selected = true;
									return false;
								}
							});
						}
						//选中的县
						if(data.areasHtml != 'undefined' && data.areasHtml != null && data.areasHtml != ""){
							g('div_County').innerHTML=data.areasHtml;
							$("#consignee_county>option").each(function(i){
								if(data.consigneeInfo.area.countyId == this.value){
									this.selected = true;
									return false;
								}
							});
						}
						//选中的四级地址址
						if(data.townsHtml != 'undefined' && data.townsHtml != null && data.townsHtml != ""){
							if(data.townsHtml.length < 100){//四级地址为空
								g('div_Towns').style.display="none";
								return ;
							}
							g('div_Towns').innerHTML=data.townsHtml;
							g('div_Towns').style.display='';
							$("#consignee_towns>option").each(function(i){
								if(data.consigneeInfo.area.townId == this.value){
									this.selected = true;
									return false;
								}
							});
						}
				}
				else{
					if(data.errorMessage != 'undefined' && data.errorMessage != null && data.errorMessage != ""){
						alert(data.errorMessage);
					}
				}
			}else{
				if(data.errorMessage != 'undefined' && data.errorMessage != null && data.errorMessage != ""){
					alert(data.errorMessage);
				}
			}
		}
	  }
	});
}

//获取常用收货人信息
function  getAddressList(type){
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getAddressList.action?rt="+Math.random()+"&type="+type,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null ){
				if(data.addressStr != null){
					g("addressListPanel").innerHTML = data.addressStr;
				}else{
					g("addressListPanel").innerHTML = "";
				}
				
			}
			
		}
//	,
//		error:function(){
//		}
	});
	
}
//保存常用收货人信息
function addNewAddress(){
	if(check_all()){
		date = "ocName="+encodeURIComponent(encodeURIComponent(g('consignee_addressName').value))+"&ocAddress="+encodeURIComponent(encodeURIComponent(g('consigneeShow_addressName').innerHTML+g('consignee_address').value))+"&ocMessage="+g('consignee_message').value+"&ocPhone="+g('consignee_phone').value+"&ocEmail="+g('consignee_email').value+"&ocPost="+g('consignee_postcode').value+"&provinceId="+g('consignee_province').value+"&cityId="+g('consignee_city').value+"&countyId="+g('consignee_county').value+"&townId="+g('consignee_towns').value+"";
		jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : PurchaseAppConfig.OrderDomain+"/addNewAddress.action?rt="+Math.random(),
			data : date,
			success : function(data) {
				if(data != null){
					if(data.error != null && data.error != ""){
						if(data.error == "NoCart"){
							goCart();
							return;
						}
					} 
					if(data.error != null && data.error == 'NotLogin'){
						alert("登录超时请重新登录.");
						window.location.href=PurchaseAppConfig.OrderLoginUrl;
					}
					if(data.aar != null){
						if(data.aar.flag){//正确保存
					   		getAddressList('1');
					   		showOrderRemark();
					   	}else{
					   		if(data.aar.errorMessage != null && data.aar.errorMessage != ""){
						   			alert(data.aar.errorMessage);
						   	}
					   	}
					}
				}
			}
		});
	}
	
	
}

function check_all(){
	if(check_addressName('consignee_addressName')&& check_area()
			&& check_address('consignee_address')&& check_message() 
			&& check_phone() && check_email() && check_postcode()){
		return true;
	}else{
		return false;
	}
}

//检查收货人姓名
function check_addressName(){  
   removeAlert('addressName_empty');
   removeAlert('addressName_ff');
   var pNode=g('consignee_addressName').parentNode;
   if(isEmpty('consignee_addressName')){
	   showAlert('收货人姓名不能为空！',pNode,'addressName_empty');
	   return false;
   }
   if(!is_forbid(g('consignee_addressName').value)){
	   showAlert(ffAlertTxt,pNode,'addressName_ff');
	   return false;
   }
   return true;
}

//点击"添加常用地址"按钮时，验证省份、市、区是否为空
function check_area(){
	removeAlert('area_check');
	var proValue = $("#consignee_province").val();
	var cityValue = $("#consignee_city").val();
	var countryValue = $("#consignee_county").val();
	var townValue = $("#consignee_towns").val();
	var parentNode = g('consignee_arae').parentNode;
	if(!$("#div_Towns").is(":hidden") && townValue =='-22'){//有四级地址,但是没选择
		showAlert('地区信息不完整！',parentNode,'area_check');
		return false;
	}
	if(proValue == '-22' || cityValue == '-22' || countryValue == '-22' 
		|| proValue == "undefined" || proValue == null || proValue == ""
		|| countryValue == "undefined" || countryValue == null  || countryValue == ""   
		|| cityValue == "undefined" || cityValue == null || cityValue == ""){
		showAlert('地区信息不完整！',parentNode,'area_check');
		return false;
	}
	return true;
}

//检查收货人地址
function check_address(){  
   removeAlert('address_empty');
   removeAlert('address_ff');
   var pNode=g('consignee_address').parentNode;
   if(isEmpty('consignee_address')){showAlert('收货地址不能为空！',pNode,'address_empty');return false;}
   if(!is_forbid(g('consignee_address').value)){showAlert(ffAlertTxt,pNode,'address_ff');return false;}
   return true;
}
//检查邮政编码
function check_postcode(){  
   removeAlert('postcode_ff');
   var postCode = $.trim($("#consignee_postcode").val());
   if(postCode == null || postCode == ""){
 	  return true;
   }
   if(g('consignee_postcode').value!=''){
   var pNode=g('consignee_postcode').parentNode;
   var myReg=/(^\s*)\d{6}(\s*$)/;
   if(!myReg.test(g('consignee_postcode').value)){showAlert('邮编格式不正确',pNode,'postcode_ff');return false;}
   }
   return true;
}

//检查固定电话
function check_phone(){  
  removeAlert('phone_ff');
  var pNode=g('consignee_phone').parentNode;
  var phoneNum = $.trim($("#consignee_phone").val());
  if(phoneNum == null || phoneNum == ""){
	  return true;
  }
  if(!isNumber(phoneNum)){
	  showAlert('固定电话格式不正确',pNode,'phone_ff');
	  return false;
  }
  return true;
}
//检查手机号
function check_message(){  
	removeAlert('message_ff');
//	var regu = /^(130|131|132|133|134|135|136|137|138|139|150|152|153|157|158|159|180|187|188|189)\d{8}$/;
	var regu = /^\d{11}$/;
	var telnum = $("#consignee_message").val();
	var re = new RegExp(regu);
	var pNode=g('consignee_message').parentNode;
	if(!re.test(telnum)){
		showAlert('手机号格式不正确',pNode,'message_ff');
		return false;
	}
	return true;
}

//检查Email
function check_email(){  
   var iSign='email';
   removeAlert(iSign+'_ff');
   var email = $.trim($("#consignee_email").val());
   if(email == null || email == ""){
 	  return true;
   }
   if(g('consignee_'+iSign).value!=''){
   var pNode=g('consignee_'+iSign).parentNode;
   var myReg=/(^\s*)\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\s*$)/;
   if(!myReg.test(g('consignee_'+iSign).value)){showAlert('电子邮件格式不正确',pNode,iSign+'_ff');return false;}
   }
   return true;
}


//检查省市区
function check_con_area(){
 removeAlert('area_check');
 if(g('consignee_arae').childNodes[2].value=='-22')
 {
    showAlert('地区信息不完整！',g('consignee_arae').parentNode,'area_check');
    return false;
 }
 return true;
}

//检查电话和手机是否都填写了
function check_phoneAndMob(){
 removeAlert('phone_empty');
 var pNode=g('consignee_phone').parentNode;
 if(isEmpty('consignee_phone') && isEmpty('consignee_message')){showAlert('固定电话和手机号码请至少填写一项！',pNode,'phone_empty');return false;}
 return true;
}

var selAddressId='';
var isEasyBuy=false;
//选择常用收货人信息
function changeConsignee(cid,type){
	//清除错误提示
	removeAlert('addressName_ff');
	removeAlert('area_check');
	removeAlert('address_ff');
	removeAlert('phone_ff');
	removeAlert('message_ff');
	removeAlert('email_ff');
	removeAlert('postcode_ff');
	
	if(g('addrId_'+selAddressId)!=null)
	      g('addrId_'+selAddressId).className='';
	selAddressId=cid;
	if(type=="0")
	{
		isEasyBuy=true;
	}
	g('addrId_'+cid).className='xz';
	
	clearWaitInfo();
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/changeConsignee.action?rt="+Math.random()+"&areaId=1&areaType=1&cid="+cid,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
				if(data.error == 'NotLogin'){
					alert("登录超时请重新登录.");
					window.location.href=PurchaseAppConfig.OrderLoginUrl;
				}
				if(data.oc.name!=null){
					g("consignee_addressName").value=data.oc.name;
				}
				if(data.oc.address!=null){
					g("consignee_address").value=data.oc.address;
				}
				if(data.oc.mobile!=null){
					g("consignee_message").value=data.oc.mobile;
				}
				if(data.oc.postcode!=null){
					g("consignee_postcode").value=data.oc.postcode;
				}
				if(data.oc.email!=null){
					g("consignee_email").value=data.oc.email;
				}
				if(data.oc.phone!=null){
					g("consignee_phone").value=data.oc.phone;
				}
				//选中的省
				if(data.oc != null && data.oc.area!=null){
					$("#consignee_province>option").each(function(i){
						if(data.oc.area.provinceId == this.value){
							this.selected = true;
							return false;
						}
					});
					//选中的市
					if(data.cityList != null && data.cityList != ""){
						g('div_City').innerHTML=data.cityList;
						$("#consignee_city>option").each(function(i){
							if(data.oc.area.cityId == this.value){
								this.selected = true;
								return false;
							}
						});
					}
					//选中的县
					if(data.countyList != null && data.countyList != ""){
						g('div_County').innerHTML=data.countyList;
						$("#consignee_county>option").each(function(i){
							if(data.oc.area.countyId == this.value){
								this.selected = true;
								return false;
							}
						});
					}
					//选中的四级地址
					if(data.townList != null && data.townList != ""){
						if(data.townList.length > 100){
							g('div_Towns').style.display="";
							g('div_Towns').innerHTML=data.townList;
							$("#consignee_towns>option").each(function(i){
								if(data.oc.area.townId == this.value){
									this.selected = true;
									return false;
								}
							});
						}else{
							g('div_Towns').style.display="none";
						}
					}
				}
				showAreaName();
			}
		}
	});
}


//删除常用收货人信息
function DelAddress(obj,aid){
	clearWaitInfo();
	jQuery.ajax({
		type : "POST", 
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/delAddress.action?rt="+Math.random()+"&aid="+aid,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data.error == 'NotLogin'){
				alert("登录超时请重新登录.");
				window.location.href=PurchaseAppConfig.OrderLoginUrl;
			}
			if(data.dar != null){
				if(data.dar.flag){
					getAddressList('1');
				}else{
					if(data.dar.errorMessage != null && data.dar.errorMessage !=""){
						alert(data.dar.errorMessage);
					}
				}
			}
		}
//	,error:function(){
//				alert('系统繁忙，请稍后再试.');
//		}
	});
}


function moreAddress(){
	g('cydz_more').style.display==""?g('cydz_more').style.display='none':g('cydz_more').style.display="";
}

/******************三级联动****************/
/**
 * 更改省
 */
function changeProvince(obj,defaultCitys,defaultCountys,defaultTown){
	removeAlert('area_check');
	//隐藏四级地址
	$("#div_Towns").hide();
	cid = obj.value;
	g('div_County').innerHTML="<select onchange='changeCounty(this)' id='consignee_county'><option value='-22'>请选择</option></selected>";
	g('div_Towns').innerHTML="<select onchange='changeTowns(this)' id='consignee_towns'><option value='-22'>请选择</option></selected>";
	g('div_City').innerHTML="<select onchange='changeCity(this)' id='consignee_city'><option value='-22'>请选择</option></selected>";
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getCitys.action?rt="+Math.random()+"&defaultValue="+defaultCitys+"&areaId="+cid,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			
			if(data != null && data.areaStr != ""){
				if(g('div_Towns')!=null){
					g('div_Towns').style.display="none";
				}
				g('div_City').innerHTML=data.areaStr;
				changeCity(document.getElementById("consignee_city"),defaultCountys,defaultTown);
				showAreaName();
			}else{  
				g('div_City').innerHTML="<select ><option value='-22'>请选择</option></selected>";
				g('div_County').innerHTML="<select  ><option value='-22'>请选择</option></selected>";
			}
		}
	});
}

/**
 * 更改市
 * @param obj
 * @param defaultCountys
 * @param defaultTown
 */
function changeCity(obj,defaultCountys,defaultTown){
	
	removeAlert('area_check');
	//隐藏四级地址
	$("#div_Towns").hide();
	cid = obj.value;
	if(cid == -22){cid=1;}
	g('div_County').innerHTML="<select onchange='changeCounty(this)' id='consignee_county'><option value='-22'>请选择</option></selected>";
	g('div_Towns').innerHTML="<select onchange='changeTowns(this)' id='consignee_towns'><option value='-22'>请选择</option></selected>";
	
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getCountys.action?rt="+Math.random()+"&defaultValue="+defaultCountys+"&areaId="+cid,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null && data.areaStr != null){
				if(g('div_Towns')!=null){
					g('div_Towns').style.display="none";
				}
				g('div_County').innerHTML=data.areaStr;
				changeCounty(document.getElementById("consignee_county"),defaultTown);
				showAreaName();
			}else{
				g('div_County').innerHTML="<select  ><option value='-22'>请选择</option></selected>";
			}
		}
	});
}

function changeCounty(obj,defaultTown){
	removeAlert('area_check');
	//隐藏四级地址
	$("#div_Towns").hide();
	cid = obj.value;
	if(cid == -22){cid=1;}
	g('div_Towns').innerHTML="<select onchange='changeTowns(this)' id='consignee_towns'><option value='-22'>请选择</option></selected>";
	
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getTowns.action?rt="+Math.random()+"&defaultValue="+defaultTown+"&areaId="+cid,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null){
				if(data.areaStr.length > 100){
					g('div_Towns').style.display="";
					g('div_Towns').innerHTML=data.areaStr;
				}else{
					g('consignee_towns').value =-22;
					g('div_Towns').style.display="none";
				}
				showAreaName();
			}
		}
	});
}

function changeTowns(){
	showAreaName();
}
//显示当前收货人区域名称
function showAreaName(){
	pname='';
	cname='';
	ctname='';
	tname='';
	if(g('consignee_province').value!=-22){
		pname = g('consignee_province').options[g('consignee_province').selectedIndex].text.replace('*','');
	}
	if(g('consignee_city').value!=-22){
		cname = g('consignee_city').options[g('consignee_city').selectedIndex].text.replace('*','');
	}
	if(g('consignee_county').value!=-22){
		ctname = g('consignee_county').options[g('consignee_county').selectedIndex].text.replace('*','');
	}
	if(g('consignee_towns').value!=-22){
		tname = g('consignee_towns').options[g('consignee_towns').selectedIndex].text.replace('*','');
	}
	g('consigneeShow_addressName').innerHTML=pname+cname+ctname+tname;
}
/******************END 三级联动****************/

//保存收货人信息
function savePart_consignee(obj){
	clearWaitInfo();
   if(check_all()){
	   data = "ocName="+encodeURIComponent(encodeURIComponent(g('consignee_addressName').value))+"&ocAddress="+encodeURIComponent(encodeURIComponent(g('consigneeShow_addressName').innerHTML+g('consignee_address').value))+"&ocMessage="+g('consignee_message').value+"&ocPhone="+g('consignee_phone').value+"&ocEmail="+g('consignee_email').value+"&ocPost="+g('consignee_postcode').value+"&provinceId="+g('consignee_province').value+"&cityId="+g('consignee_city').value+"&countyId="+g('consignee_county').value+"&townId="+g('consignee_towns').value+"&easyBuy="+isEasyBuy+"&addressId="+selAddressId;
	   showWaitInfo('正在提交收货人信息，请等待。。。',g('partButton'));
	   jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : PurchaseAppConfig.OrderDomain+"/saveOrderConsignee.action?rt="+Math.random(),
			data : data,
			success : function(data) {
				if(data != null){
					if(data.error != null && data.error != ""){
						if(data.error == "NoCart"){
							goCart();
							return;
						}
					} 
				}
			   	if(data.error == 'NotLogin'){
					alert("登录超时请重新登录.");
					window.location.href=PurchaseAppConfig.OrderLoginUrl;
				}
			   	if(data.flag){//正确保存
			   		if(data.message != null){
			   			alert(data.message);
			   		}
			   		if(isEasyBuy)
			   		{
			   			window.location.href=PurchaseAppConfig.EasyBuy;
			   			return;
			   		}
			   		//我的购物车
			   		if(data.scr.resultCode == "GoToShoppingPage"){
			   			goCart();
			   			return;
			   		}
			   		if(data.balance == 0)
			   			data.balance =null;
			   		var result = TrimPath.processDOMTemplate("order_jst", data); 
					g("show").innerHTML = result;
					lipinkaInputEventInit();
					isSupInStockFirstShip();//有货先发
					isNeedPaymentPassword();//支付密码
					
					//设置验证码
					if(data.needCheckCode.flag == false && data.needCheckCode.resultCode == null){
						//不显示
					}else if(data.needCheckCode.flag == true){
						refreshCheckCode();
					}
			   		//call(PurchaseAppConfig.OrderDomain+"/getOrderInfo.action?rt="+Math.random());
					//更改地址时提示用户更改支付方式和配送方式
			   		Edit_PayType(g('linkPayTypeShipType'),1);//打开编辑支付信息
					g('Consignee_Hidden_Type').value=0;//设置隐藏编辑状态为关闭
					processCart();//加载购物车
					showOrderRemark();
			   	}else{
			   		if(data.message != null && data.message != ""){
			   			alert(data.message);
			   		}
			   		clearWaitInfo();
			   		return;
			   		//g('lbtnConsigneeWrite').disabled='disabled';
			   		//call(PurchaseAppConfig.OrderDomain+"/getOrderInfo.action?rt="+Math.random());
			   	}
			}
//	   ,
//			error:function(){
//				alert("系统繁忙，请稍后再试.");
//				//call(PurchaseAppConfig.OrderDomain+"/getOrderInfo.action?rt="+Math.random());
//			}
		});
	   
   }
}

//关闭编辑收货人信息
function Close_Consignee(){
	$("#submitWaitInfo").html("");
	if(OldConsignee!=""){
		g("part_consignee").innerHTML = OldConsignee;
		g('Consignee_Hidden_Type').value=0;//设置隐藏编辑状态为关闭
	}
}

/*****************************************收货人信息结束***************************************/
/*********************************************************************************************/










//显示等待信息
function showWaitInfo(info,obj){
 try{
	   if(obj==null)return;
	   var newd=document.createElement("span");
	   newd.className='waitInfo';
	   newd.id='waitInfo';
	   newd.innerHTML=info;
	   obj.parentNode.appendChild(newd);
 }catch(e){}
}



//获取Object
function g(nodeId){
 return document.getElementById(nodeId);
} 

//删除提示信息
function removeAlert(infoSign){
 if(g(infoSign)==null){return;}
 g(infoSign).parentNode.removeChild(g(infoSign));
}

//检查是否为空
function isEmpty(inputId){
 if(trimTxt(g(inputId).value)==''){return true;}
 return false;
}

//正则
function trimTxt(txt){
 return txt.replace(/(^\s*)|(\s*$)/g, "");
}
//非法字符


function is_forbid(temp_str)
{
    temp_str=trimTxt(temp_str);
	temp_str = temp_str.replace('*',"@");
	temp_str = temp_str.replace('--',"@");
	temp_str = temp_str.replace('/',"@");
	temp_str = temp_str.replace('+',"@");
	temp_str = temp_str.replace('\'',"@");
	temp_str = temp_str.replace('\\',"@");
	temp_str = temp_str.replace('$',"@");
	temp_str = temp_str.replace('^',"@");
	temp_str = temp_str.replace('.',"@");
	temp_str = temp_str.replace('#',"@");
	temp_str = temp_str.replace(';',"@");
	temp_str = temp_str.replace('<',"@");
	temp_str = temp_str.replace('>',"@");
	temp_str = temp_str.replace('"',"@");
	temp_str = temp_str.replace('=',"@");
	temp_str = temp_str.replace('{',"@");
	temp_str = temp_str.replace('}',"@");
	var forbid_str=new String('@,%,~,&');
	var forbid_array=new Array();
	forbid_array=forbid_str.split(',');
	for(i=0;i<forbid_array.length;i++)
	{
		if(temp_str.search(new RegExp(forbid_array[i])) != -1)
		return false;
	}
	return true;
}


//显示提示信息
function showAlert(info,obj,infoSign){
 if(g(infoSign)!=null){return;}
 var newd=document.createElement("span");
 newd.id=infoSign;
 newd.className='alertInfo';
 newd.innerHTML=info;
 obj.appendChild(newd);
}



//根据name获取对象数组
function gn(tag,eltname){ 
	var elts=document.getElementsByTagName(tag); 
	var count=0; 
	var elements=[]; 
	for(var i=0;i<elts.length;i++){ 
	     if(elts[i].getAttribute("name")==eltname){ 
	        elements[count++]=elts[i]; 
	     } 
	} 
	return elements; 
} 


//显示提示信息
function showAlert(info,obj,infoSign){
   if(g(infoSign)!=null){return;}
   var newd=document.createElement("span");
   newd.id=infoSign;
   newd.className='alertInfo';
   newd.innerHTML=info;
   obj.appendChild(newd);
}

//删除提示信息
function removeAlert(infoSign){
   if(g(infoSign)==null){return;}
   g(infoSign).parentNode.removeChild(g(infoSign));
}

function clearSubmitError(obj){
   if(obj.parentNode.childNodes.length>0){
	   if(obj.parentNode.lastChild.name=='errorInfo'){
	     obj.parentNode.removeChild(obj.parentNode.lastChild);
	   }
   }
}




















/**********************************支付配送方式**************************/
/***********************************************************************/

var OldPayType="";
//选择支付方式
function Edit_PayType(obj,flag){
	clearWaitInfo();
	OldPayType = g("part_payTypeAndShipType").innerHTML;
	showWaitInfo('正在读取支付方式及配送方式，请等待。。。',obj);
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getPayType.action?rt="+Math.random(),
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null && data.error == 'NotLogin'){
				alert("登录超时请重新登录.");
				window.location.href=PurchaseAppConfig.OrderLoginUrl;
			}
			if(data != null && data.gptr != null && data.gptr.flag==true){  
				var result = TrimPath.processDOMTemplate("payType_jst", data); 
				g("part_payTypeAndShipType").innerHTML = result; 
				//自动获取配送信息
				var payTypeRadio = gn("input","IdPaymentType");
				var pid = 0;
				for(i=0;i<payTypeRadio.length;i++){
					if(payTypeRadio[i].checked){
						pid = payTypeRadio[i].value;
						break;
					}
				}
				if(flag == 1){
					$("#updateInfo_payType1").append("<span class='payTypeChangeAlert'>由于您更改了收货人信息，请重新填写支付方式和配送方式！</span>");
				}
				changePayType(pid);
				g('PayType_Hidden_Type').value=1;//设置编辑支付方式为打开
			}else{
				if(data.gptr != null){
					if(data.gptr.errorMessage != null && data.gptr.errorMessage !=""){
						alert(data.gptr.errorMessage);
					}
				}
			}
		}
	});
}

//关闭支付方式
function Close_PayType(){
	$("#submitWaitInfo").html("");
	if(OldPayType!=""){
		g("part_payTypeAndShipType").innerHTML = OldPayType;
		g('PayType_Hidden_Type').value=0;//设置编辑支付方式为关闭
	}
}





//选择支付方式后 自动匹配配送方式
function changePayType(payType){
	removeAlert('payType_SubWayLine');
	removeAlert('payType_selectPayType');
	removeAlert("pickSiteId_UnCabinet");
	if(payType == 4){
		g('payRemark_4').style.display='';
	}else{
		g('payRemark_4').style.display='none';
	}
	if(payType == 8){
		g('payRemark_8').style.display='';
	}else if(g('payRemark_8')!=null){
		g('payRemark_8').style.display='none';
	}
   $("#part_shipType").html("<span class='waitInfo' id='waitInfo'>正在加载配送方式信息，请等待。。。</span>");
   jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getShipmentTypes.action?rt="+Math.random()+"&pid="+payType,
		data : '',
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
				if(data.sts != null)
					g("part_shipType").innerHTML = data.sts;
			}
	   		if(payType==15){//地铁自提
	   			ChangeSubWay(g('SubWayNames'));
	   		}
		}
	});
}




//选择相应地点路线，展示对应地铁线路
function ChangeSubWay (obj){
	var select = g('SubWayNames');
	for(i = 0 ; i < select.length ; i++){
		var temp = "div_"+select.options[i].value;
		if(g(temp)!=null){
			g(temp).style.display="none";
		}
	} 
	if(obj.value == '0')return;
	g("div_"+obj.value).style.display='';
}

//保存支付方式与收货地址
function savePayTypeAndShipType(obj){
	clearWaitInfo();
	var pNode = obj.parentNode;
	removeAlert('payType_SubWayLine');
	removeAlert('payType_selectPayType');
	removeAlert('payType_selectPayType');
	//removeAlert('payType_selectPayType');
	//货到付款1；邮局汇款2；在线支付4；公司转账5
	//以上三种都有默认值 不需验证
	var payTypeRadio = gn("input","IdPaymentType");
	var pid = 0;
	for(i=0;i<payTypeRadio.length;i++){
		if(payTypeRadio[i].checked){
			pid = payTypeRadio[i].value;
			break;
		}
	}
	if(pid == 0){//未选支付方式
		showAlert('请选择支付方式！',pNode,'payType_selectPayType');
		return;
	}
	if(pid == 15){//地铁自提-未选地铁线路
		var select = g('SubWayNames').value;
		if(select == 0){
			showAlert('请选择地铁线路！',pNode,'payType_SubWayLine');
			return;
		}
	}
	if(g('payType_BigTick')!=null){
		if(g('payType_BigTick').value == -1){
			showAlert('请选择大件商品送货时间！',pNode,'payType_selectPayType');
			return;
		}
	}
	if(pid == 19){
		var pickSiteId='';
		var IdPickSite = gn("input","IdCabinetType");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		if(pickSiteId==''){
			showAlert("无空闲提货柜，请选择其他支付方式或稍后重试！",g('test333'),"pickSiteId_UnCabinet");
			return;
		}
	}
	var data = buildPSData(pid);
	
	showWaitInfo('正在保存支付配送信息,请等待...',g('test333'));
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/savePickSitesAndShipment.action?rt="+Math.random(),
		data : data,
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
				if(data.error == 'NotLogin'){
					alert("登录超时请重新登录.");
					window.location.href=PurchaseAppConfig.OrderLoginUrl;
				}
		   		if(data.flag){
		   			var o=new myorder();
					o.order=data.order;
					if(data.balance && data.balance!=null){
						o.balance=data.balance;
					}
					o.areaName=data.areaName;
		   			var result = TrimPath.processDOMTemplate("order_jst", o); 
		   			g("show").innerHTML = result;
					lipinkaInputEventInit();
		   			// 设置验证码
					if(data.needCheckCode.flag == false && data.needCheckCode.resultCode == null){
						// 不显示
					}else if(data.needCheckCode.flag == true){
						refreshCheckCode();
					}
		   			g('PayType_Hidden_Type').value=0;// 设置编辑支付方式为关闭
		   			processCart();// 加载购物车
		   			isNeedPaymentPassword();// 支付密码
		   			showOrderRemark();
		   		}else{
		   			if(data.errorMsg != null && data.errorMsg != ""){
			   			alert(data.errorMsg);
			   		}
		   		}
			}
		}
	});
}

//构造支付配送信息数据
function buildPSData(pid){
	var data='';
	//货到付款1；邮局汇款2；在线支付4；公司转账5 分期付款 8
	if(pid==1 || pid==2 || pid==4 || pid==5 || pid==8){
		//夜间配送
		var shipSend = g("nightShipId");
		var nightShip = 0;//0表示不支持夜间配送
		if(shipSend!=null&&shipSend.checked){//用户选择了夜间配送
			nightShip = 1;//1表示支持夜间配送
			shipSend.checked = true;
		}
		
		var stype = g('IdShipmentType70').value;
		var codId='';
		codTime = gn("input","CODTime");
		for(i=0;i<codTime.length;i++){
			if(codTime[i].checked){
				codId = codTime[i].value;
				break;
			}
		}
		var isInformRadId='';
		var isInformRad = gn("input","isInformRad");
		for(i=0;i<isInformRad.length;i++){
			if(isInformRad[i].checked){
				isInformRadId = isInformRad[i].value;
				break;
			}
		}
		data="codId="+codId+"&informId="+isInformRadId+"&stype="+stype+"&nightShip="+nightShip;
		var paymentWayId = "0";
		if(pid==1){
			var PaymentWay = gn("input","PaymentWay");
			for(i=0;i<PaymentWay.length;i++){
				if(PaymentWay[i].checked){
					paymentWayId = PaymentWay[i].value;
					break;
				}
			}
			data=data+"&pwid="+paymentWayId;
		}
		
		if(g('payType_BigTick') !=null){
			data=data+"&payType_BigTick="+g('payType_BigTick').value;
		}
	}else if(pid==3){//京东自提
		var pickSiteId='';
		var IdPickSite = gn("input","IdPickSite");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var cdate = "";
		if(g('payType_CodDate')!=null){
		 cdate = g('payType_CodDate').value;
		}
		data="psid="+pickSiteId+"&cdate="+cdate;
	}else if(pid == 17){//好邻居自提
		var pickSiteId='';
		var IdPickSite = gn("input","IdGnSite");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var cdate = "";
		if(g('payType_CodDate')!=null){
		 cdate = g('payType_CodDate').value;
		}
		data="psid="+pickSiteId+"&cdate="+cdate;
	}else if(pid == 18){//社区自提
		var pickSiteId='';
		var IdPickSite = gn("input","IdCommunitySite");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var cdate = "";
		if(g('payType_CodDate')!=null){
		 cdate = g('payType_CodDate').value;
		}
		data="psid="+pickSiteId+"&cdate="+cdate;
	}else if(pid == 19){//自提柜
		var pickSiteId='';
		var IdPickSite = gn("input","IdCabinetType");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		data="psid="+pickSiteId+"&cdate=";
	}
	else if(pid==16){//校园自提
		var pickSiteId='';
		var IdPickSite = gn("input","IdSchoolPickSite");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var cdate = "";
		
		if(g('School_payType_CodDate')!=null){
		 cdate = g('School_payType_CodDate').value;
		}
		
			
		data="psid="+pickSiteId+"&cdate="+cdate;
	}else if(pid==15){//地铁自提
		var pickSiteId='';
		var swName = g('SubWayNames').value;
		var IdPickSite = gn("input","SubWayPickSiteId"+swName);
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var cdate = "";
		if(g('SubWay_payType_CodDate')!=null){
		 cdate = g('SubWay_payType_CodDate').value;
		}
		data="psid="+pickSiteId+"&cdate="+cdate+"&swName="+encodeURIComponent(encodeURIComponent(swName));
	}else if(pid==11 || pid==10){//校园代理
		var pickSiteId='';
		var IdPickSite = gn("input","IdAgent");
		for(i=0;i<IdPickSite.length;i++){
			if(IdPickSite[i].checked){
				pickSiteId = IdPickSite[i].value;
				break;
			}
		}
		var sid = g('School_Id_Hidden').value;
		var psnid = "";
		if(g('psnid')!=null){
			psnid = g('psnid').value;
		}
		data="psaid="+pickSiteId+"&sid="+sid+"&psnid="+psnid;
	}
	return data+"&ptid="+pid;
}


/*******************************支付配送方式结束**************************/
/************************************************************************/






/***************************编辑发票信息***********************************/
/*************************************************************************/
var OldPartInvoice="";
var selInvoiceId;
//编辑发票信息
function showForm_invoice(obj){
	preNoCheckInvoice = null;//记录取消发票上一次状态
	preNoCheckBookInvoice = null;//记录取消书发票上一次记录
	var consigenn = g('Consignee_Hidden_Type').value;
//	if(consigenn == 1){
//		alert("请先保存收货人信息!");
//		return;
//	}
	invoiveHtml='';
	VAThtml='';
	clearWaitInfo();
	OldPartInvoice = g("part_invoice").innerHTML;
	showWaitInfo('正在加载发票信息，请等待。。。',obj);
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/getPartInvoiceInfo.action?rt="+Math.random(),
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null && data.error == 'NotLogin'){
				alert("登录超时请重新登录.");
				window.location.href=PurchaseAppConfig.OrderLoginUrl;
			}
			if(data != null && data.result != null ){
				g("part_invoice").innerHTML = data.result;  
				g('PartInvoice_Hidden_Type').value=1;//设置编辑发票信息为打开
				selInvoiceId = 0;
				if($("#invoince_InvoiceType_2").is(':checked') == true && $("#invoice_VAT_flag").val() == "1"){//增值税发票类型,且有增值税发票列表
					setVatDisabled();
				}
				getInvoiceList();
			}else{
				if(data.gitr != null && data.gitr.errorMessage != null && data.gitr.errorMessage != ""){
					alert(data.gitr.errorMessage);
				}
			}
		}
//	,
//		error:function(){
//			alert('系统繁忙，请稍后再试..');
//		}
	});
}

/**
 * 设置增值税发票相应字段不可以编辑
 */
function setVatDisabled(){
	$("#invoice_Ivc_TitName").attr("disabled",true).css("color","gray");
	$("#invoice_Ivc_NsrCode").attr("disabled",true).css("color","gray");
	$("#invoice_Ivc_Address").attr("disabled",true).css("color","gray");
	$("#invoice_Ivc_Phone").attr("disabled",true).css("color","gray");
	$("#invoice__Ivc_Bank").attr("disabled",true).css("color","gray");
	$("#invoice_Ivc_BankCode").attr("disabled",true).css("color","gray");
}

/**
 * 设置增值税发票相应字段可以编辑
 */
function setVatEnabled(){
	$("#invoice_Ivc_TitName").remove("disabled");
	$("#invoice_Ivc_NsrCode").remove("disabled");
	$("#invoice_Ivc_Address").remove("disabled");
	$("#invoice_Ivc_Phone").remove("disabled");
	$("#invoice__Ivc_Bank").remove("disabled");
	$("#invoice_Ivc_BankCode").remove("disabled");
}


function setBookPutType(obj, invoiceType){
	if(obj.checked){
		//g('invoice_BookInvoicePutType').value='0';
		g('InvoiceContentBookPanel_1').style.display='';
		g('InvoiceContentBookPanel_2').style.display='';
		g('invoice_IsPutBookInvoice_1').checked=true;
		g('invoice_IsPutBookInvoice_2').checked=true;
		if(g('invoice_IsContainBook').value=='1' && g('invoice_IsContainGeneral').value=='0'){
			g('invoiceContentPanel_1').style.display='';
			g('invoiceListPanel').style.display='';
		}
		if(preNoCheckBookInvoice!=null){
			g('invoice_BookInvoicePutType').value = preNoCheckBookInvoice;
		}else{
			g('invoice_BookInvoicePutType').value = '0';
		}
		g('invoice_WhetherBookInvoice').value='1';
	}else{
		g('InvoiceContentBookPanel_1').style.display='none';
		g('InvoiceContentBookPanel_2').style.display='none';
		g('invoice_IsPutBookInvoice_1').checked=false;
		g('invoice_IsPutBookInvoice_2').checked=false;
		if(g('invoice_IsContainBook').value=='1' && g('invoice_IsContainGeneral').value=='0'){
			g('invoiceContentPanel_1').style.display='none';
			g('invoiceListPanel').style.display='none';
		}
		preNoCheckBookInvoice = g('invoice_BookInvoicePutType').value;
		g('invoice_BookInvoicePutType').value='3';
		g('invoice_WhetherBookInvoice').value='0';
	}
}

//选择发票内容
function invoince_setContent(selValue)
{
   g('invoice_Invoice_Content').value=selValue;
}
//选择图书发票内容
function invoince_setContentBook(selValue)
{
   g('invoice_Invoice_ContentBook').value=selValue;
}

//选择发票类型
function invoince_setType(selValue)
{
	g('invoice_InvoiceType').value=selValue;
	if(selValue==1){//普通发票
		setDisplay('invoice_ivc_Tr','none');
		
		setDisplay('invoice_titleTr','');
		//增值税相关信息(单位名称....)
		setDisplay('invoice_ivc_Tr_1','none');
		//普通发票抬头
		setDisplay('invoice_titleTr_1','');
		//增值税发票内容
		setDisplay('invoicecontentlist_2','none');
		//普通发票内容
		setDisplay('invoicecontentlist_1','');
		//添加到常用发票按钮
		setDisplay('addInvoiceButton','');
		//普通发票列表
		g("invoiceListPanel").innerHTML='';
		if(invoiveHtml == ''){
			getInvoiceList();
		}else{
			g("invoiceListPanel").innerHTML = invoiveHtml;
		}
	}
	if(selValue==2){//增值税发票
		//获取增值税发票列表
		g("invoiceListPanel").innerHTML='';
		if(VAThtml == ''){
			getInvoiceList();
		}else{
			g("invoiceListPanel").innerHTML = VAThtml;
		}
		setDisplay('invoice_ivc_Tr_1','');
		setDisplay('invoice_titleTr_1','none');
		setDisplay('invoice_titleTr_1','none');
		setDisplay('invoicecontentlist_1','none');
		setDisplay('invoicecontentlist_2','');
		setDisplay('addInvoiceButton','none');
		if($("#invoice_VAT_flag").val() == "1"){//增值税发票列表有值，不可修改
			setVatDisabled();
			
			
		}else{//增值税发票列表没有值，可修改
			setVatEnabled();
		}
		
	}
	DoOnInvoiceTypeChange();
}

//保存发票
function savePart_invoice(obj)
{
	clearWaitInfo();
	clearSubmitError(obj);
	if(check_invoice()){
			var data = budilInvoiceData();
			showWaitInfo('正在保存发票信息,请等待...',obj);
			jQuery.ajax({
				type : "POST",
				dataType : "json",
				url : PurchaseAppConfig.OrderDomain+"/savePartInvoiceInfo.action?rt="+Math.random(),
				data : data,
				success : function(result) {
					if(result != null){
						if(result.error != null && result.error != ""){
							if(result.error == "NoCart"){
								goCart();
								return;
							}
						} 
						if(result.flag){
							call(PurchaseAppConfig.OrderDomain + "/getNewOrderInfo.action?rt="+Math.random());
							g('PartInvoice_Hidden_Type').value=0;//设置编辑发票信息为关闭
						}else{
							if(result.errorMsg != null && result.errorMsg !=""){
								alert(result.errorMsg);
							}
						}
					}
					
				}
//			,
//				error: function() {
//					alert('系统繁忙，请稍后再试.');
//				}
			});
	}
}
/**
 * 添加到常用发票
 */

function addInvoice(){
	if(check_invoice()){
		var data = budilInvoiceData();
		jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : PurchaseAppConfig.OrderDomain+"/addCommonlyInvoice.action?rt="+Math.random(),
			data : data,
			success : function(result) {
				if(result != null){
					if(result.error != null && result.error != ""){
						if(result.error == "NoCart"){
							goCart();
							return;
						}
					} 
				}
				if(result.flag){
				   if(result.gir2 != null  && result.gir2.invoiceInfos.length>0){
					   invoiveHtml=baseGetInvoiceList(eval(result.gir2.invoiceInfos));
					   g("invoiceListPanel").innerHTML = invoiveHtml;
				   }
				}else{
					if(result.gir2.errorMessage != null){
						alert(result.gir2.errorMessage);
					}
				}
				
			}
//			,
//				error: function() {
//					
//					alert('添加发票信息错误.');
//				}
			});
		}
	
}


//保存发票时的检查
function check_invoice()
{
   if(g('invoice_InvoicePutType').value!='0')return true;//即时开取验证
   if(g('invoice_InvoiceType').value=='1')
   {
//	   if(!$("#invoice_IsPutBookInvoice_2").is(':checked') && !($("#invoice_IsPutBookInvoice_2")[0].style.display='none')){
//		   return true;
//	   }
		var invoinceContentBookRaoid = gn("input","invoince_contentBook_1");
		for(i=0;i<invoinceContentBookRaoid.length;i++){
			if(invoinceContentBookRaoid[i].checked){
				g('invoice_Invoice_ContentBook').value = invoinceContentBookRaoid[i].value;
				break;
			}
		}
		var invoinceContentRaoid = gn("input","invoince_content_1");
		for(i=0;i<invoinceContentRaoid.length;i++){
			if(invoinceContentRaoid[i].checked){
				g('invoice_Invoice_Content').value = invoinceContentRaoid[i].value;
				break;
			}
		}
      //普通发票
      if(g('invoice_InvoiceTitle').value=='5')
      {
         //选择了单位
          return check_invoice_unit();
      }
      else
      {
         return true;
      }
      
   }
   if(g('invoice_InvoiceType').value=='2')
   {
//	   if(!$("#invoice_IsPutBookInvoice_2").is(':checked') && !($("#invoice_IsPutBookInvoice_2")[0].style.display='none')){
//		   return true;
//	   }
	   var invoinceContentBookRaoid = gn("input","invoince_contentBook_2");
		for(i=0;i<invoinceContentBookRaoid.length;i++){
			if(invoinceContentBookRaoid[i].checked){
				g('invoice_Invoice_ContentBook').value = invoinceContentBookRaoid[i].value;
				break;
			}
		}
		var invoinceContentRaoid = gn("input","invoince_content_2");
		for(i=0;i<invoinceContentRaoid.length;i++){
			if(invoinceContentRaoid[i].checked){
				g('invoice_Invoice_Content').value = invoinceContentRaoid[i].value;
				break;
			}
		}
      var res=true;
      if(!check_Ivc_TitName()){res=false;}
      if(!check_NsrCode()){res=false;}
      if(!check_InvoiceAddress()){res=false;}
      if(!check_InvoicePhone()){res=false;}
      if(!check_InvoiceBank()){res=false;}
      if(!check_InvoiceBankCode()){res=false;}
      return res;
   }
}

function budilInvoiceData(){
	var data='';
	var invoinceTypeId=g("invoice_InvoiceType").value;//发票类型
	var invoincePtttId = g("invoice_InvoiceTitle").value; //获取发票抬头
	var invoinceContentId = g("invoice_Invoice_Content").value; //获取一般发票内容
	var invoinceBookContentId = g("invoice_Invoice_ContentBook").value; //获取图书发票内容
	var hasBook = g("invoice_IsContainBook").value; //是否有图书
	var hasGeneral = g("invoice_IsContainGeneral").value; //是否有一般商品
	var invoincePutType = g("invoice_InvoicePutType").value; //一般商品发票开取方式
	var invoinceBookPutType = g("invoice_BookInvoicePutType").value; //图书发票开取方式
	var whetherInvoice = g("invoice_WhetherInvoice").value;//是否开去发票
	var whetherBookInvoice = g("invoice_WhetherBookInvoice").value;
	var invoinceTitleName = ''; //抬头名称
	var titName = '';//单位名称
	var code = '';//纳税人识别号
	var address = '';//注册地址
	var phone = '';//注册电话
	var bank = '';//开户银行
	var bankCode = '';//银行帐户
	if(invoinceTypeId==1){//普通发票
		if(g('invoice_Unit_TitName')){//添加公司名称
			invoinceTitleName = encodeURIComponent(encodeURIComponent(g('invoice_Unit_TitName').value));
		}
	}else if(invoinceTypeId==2){//增值发票
		titName = encodeURIComponent(encodeURIComponent(g('invoice_Ivc_TitName').value));
		code = encodeURIComponent(encodeURIComponent(g('invoice_Ivc_NsrCode').value));
		address = encodeURIComponent(encodeURIComponent(g('invoice_Ivc_Address').value));
		phone = encodeURIComponent(encodeURIComponent(g('invoice_Ivc_Phone').value));
		bank = encodeURIComponent(encodeURIComponent(g('invoice__Ivc_Bank').value));
		bankCode = encodeURIComponent(encodeURIComponent(g('invoice_Ivc_BankCode').value));
	}
	data="iType="+invoinceTypeId+"&iTitleId="+invoincePtttId+"&iContentId="+invoinceContentId;
	data=data+"&pttt="+invoinceTitleName;
	data=data+"&titName="+titName+"&code="+code+"&address="+address+"&phone="+phone+"&bank="+bank+"&bankCode="+bankCode+"&iContentId="+invoinceContentId;
	data =data+"&bookId="+invoinceBookContentId+'&hasBook='+hasBook+'&hasGeneral='+hasGeneral+'&invoincePutType='+invoincePutType+'&invoinceBookPutType='+invoinceBookPutType+'&whetherInvoice='+whetherInvoice+"&whetherBookInvoice="+whetherBookInvoice;
	return data;
}

//做些什么在发票类型发生变化的时候
function DoOnInvoiceTypeChange(){
	//查询相应的发票列表
     var panelremark=g('panel_invoicetypeRemark');
     if(panelremark!=null)
     {   
         panelremark.className='gray';
         var ivoitype=g('invoice_InvoiceType').value;
         if(ivoitype==1)
         {
            panelremark.innerHTML='';
         }
         if(ivoitype==2)
         {
            panelremark.innerHTML="<b>提示：</b>尊敬的客户您好，如果您需要开具增值税发票，建议您联系<a href='https://passport.360buy.com/new/registcompany.aspx' style='color: rgb(0, 112, 215);' target='_blank'>企业客户</a>代表，将有专人为您处理！";
         }
     }
}

//选择发票Title
function invoince_setPttt(tid){
	g("invoice_InvoiceTitle").value=tid;
	if(tid==5){//公司
		setDisplay('invoice_unitNameTr','');
	}else{
		setDisplay('invoice_unitNameTr','none');
	}
}

function close_invoice(obj)
{
   $("#submitWaitInfo").html("");
   g("part_invoice").innerHTML=OldPartInvoice;
   g('PartInvoice_Hidden_Type').value = 0;
   isInvoiceOpen=false;
}

//普票时的单位名称
function check_invoice_unit()
{
   removeAlert('Invoice_UnitEmpty');
   removeAlert('Invoice_UnitFf');
  var pNode=g('invoice_Unit_TitName').parentNode;
  if(isEmpty('invoice_Unit_TitName')){showAlert('单位名称不能为空！',pNode,'Invoice_UnitEmpty');return false;}
  if (g('invoice_Unit_TitName').value < 2) {
	showAlert('请填写完整单位名称！',pNode,'Invoice_UnitFf');
	return false;
  }else if (checkLength(g('invoice_Unit_TitName').value) > 100) {
	showAlert('单位名称过长,请重新输入!',pNode,'Invoice_UnitFf');
	return false;
  }
  if(!is_forbid(g('invoice_Unit_TitName').value)){showAlert(ffAlertTxt,pNode,'Invoice_UnitFf');return false;}
  return true;
}

//增票的单位名称
function check_Ivc_TitName()
{
  removeAlert('Invoice_TitNameEmpty');
  removeAlert('Invoice_TitNameFf');
  var pNode=g('invoice_Ivc_TitName').parentNode;
  if(isEmpty('invoice_Ivc_TitName')){showAlert('单位名称不能为空！',pNode,'Invoice_TitNameEmpty');return false;}
  if (g('invoice_Ivc_TitName').value < 2) {
	showAlert('请填写完整单位名称！',pNode,'Invoice_TitNameFf');
	return false;
  }else if (checkLength(g('invoice_Ivc_TitName').value) > 100) {
	showAlert('单位名称过长,请重新输入!',pNode,'Invoice_TitNameFf');
	return false;
  }
  if(!is_forbid(g('invoice_Ivc_TitName').value)){showAlert(ffAlertTxt,pNode,'Invoice_TitNameFf');return false;}
  return true;
}

//增票的纳税人标识
function check_NsrCode()
{
  removeAlert('Invoice_NsrCodeEmpty');
  removeAlert('Invoice_NsrCodeFf');
  var pNode=g('invoice_Ivc_NsrCode').parentNode;
  if(isEmpty('invoice_Ivc_NsrCode')){showAlert('纳税人识别号不能为空！',pNode,'Invoice_NsrCodeEmpty');return false;}
  var reg_number=/^([a-zA-Z0-9]){15,20}$/;
  var lenght=checkLength(g('invoice_Ivc_NsrCode').value);
  if (!reg_number.test(g('invoice_Ivc_NsrCode').value)) {
	showAlert('税纳税识别号错误，请检查！',pNode,'Invoice_NsrCodeFf');
	return false;
  }
  if(!is_forbid(g('invoice_Ivc_NsrCode').value)){showAlert(ffAlertTxt,pNode,'Invoice_NsrCodeFf');return false;}
  return true;
}


//增票的注册地址
function check_InvoiceAddress()
{
  removeAlert('Invoice_addressEmpty');
  removeAlert('Invoice_addressFf');
  var pNode=g('invoice_Ivc_Address').parentNode;
  if(isEmpty('invoice_Ivc_Address')){showAlert('注册地址不能为空！',pNode,'Invoice_addressEmpty');return false;}
  if (g('invoice_Ivc_Address').value.replace(/[^\x00-\xff]/g, "**").length < 2) {
	showAlert('注册地址错误！',pNode,'Invoice_addressFf');
	return false;
  }else if (checkLength(g('invoice_Ivc_Address').value) > 250) {
	showAlert('注册地址过长，请重新输入!',pNode,'Invoice_addressFf');
	return false;
  }
  if(!is_forbid(g('invoice_Ivc_Address').value)){showAlert(ffAlertTxt,pNode,'Invoice_addressFf');return false;}
  return true;
}


//增票的注册电话
function check_InvoicePhone()
{
  removeAlert('Invoice_PhoneEmpty');
  removeAlert('Invoice_PhoneFf');
  var pNode=g('invoice_Ivc_Phone').parentNode;
  if(isEmpty('invoice_Ivc_Phone')){showAlert('注册电话不能为空！',pNode,'Invoice_PhoneEmpty');return false;}
  
  /*
  var reg_number = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;	
  var reg_mobile=/^0?(13|15|18)[0-9]{9}$/;
  if (!reg_number.test(g('invoice_Ivc_Phone').value) && !reg_mobile.test(g('invoice_Ivc_Phone').value)) {
	showAlert('请填写正确的电话号码！',pNode,'Invoice_PhoneFf');
	return false;
  }
  */
  
  if (checkLength(g('invoice_Ivc_Phone').value) > 50) {
	showAlert('电话号码过长，请重新输入!',pNode,'Invoice_PhoneFf');
	return false;
  }
  if(!is_forbid(g('invoice_Ivc_Phone').value)){showAlert(ffAlertTxt,pNode,'Invoice_PhoneFf');return false;}
  return true;
}

//验证是否为数字
function isNumber(s){
	var regu = /^[0-9]+$/;
	var re = new RegExp(regu);
	if(!re.test(s)){
		return false;
	}
	return true;
}

//增票的开户银行
function check_InvoiceBank()
{
  removeAlert('Invoice_bankEmpty');
  removeAlert('Invoice_bankFf');
  var pNode=g('invoice__Ivc_Bank').parentNode;
  if(isEmpty('invoice__Ivc_Bank')){showAlert('开户银行不能为空！',pNode,'Invoice_bankEmpty');return false;}
  if (g('invoice__Ivc_Bank').value.replace(/[^\x00-\xff]/g, "**").length < 2) {
	showAlert('开户银行错误！',pNode,'Invoice_bankFf');
	return false;
  }else if (checkLength(g('invoice__Ivc_Bank').value)>100) {
	showAlert('开户银行过长，请重新输入!',pNode,'Invoice_bankFf');
	return false;
  }
  if(!is_forbid(g('invoice__Ivc_Bank').value)){showAlert(ffAlertTxt,pNode,'Invoice_bankFf');return false;}
  return true;
}
//增票的银行帐户
function check_InvoiceBankCode()
{
  removeAlert('Invoice_bankCodeEmpty');
  removeAlert('Invoice_bankCodeFf');
  var pNode=g('invoice_Ivc_BankCode').parentNode;
  if(isEmpty('invoice_Ivc_BankCode')){showAlert('银行帐户不能为空！',pNode,'Invoice_bankCodeEmpty');return false;}
  if(!is_forbid(g('invoice_Ivc_BankCode').value)){showAlert(ffAlertTxt,pNode,'Invoice_bankCodeFf');return false;}
  return true;
}

function checkLength(txtObj){
	var val=txtObj;
	var valLength=0;
	for(var ii=0;ii<val.length;ii++){
		var word=val.substring(ii,1);
		if(/[^\x00-\xff]/g.test(word)){
			valLength+=2;
		}else{
			valLength++;
		}
	}
	return valLength;
}

var maxInvoiceShowCount=5;
var isInvoiceMoreOpen=false;
var isVATInvoiceMoreOpen=false;

/**
 * 拼装普通发票头列表信息
 */
function baseGetInvoiceList(invoices){
	  var html="";
	  html+="<div class='cydz'>";
      html+="<b>常用发票</b>";
      html+="<ul>";
      var isHaveMore=false;
      for(var i=0;i<invoices.length;i++)
      {
         if(i+1>maxInvoiceShowCount && !isHaveMore)
         {
             isHaveMore=true;
             html+="</ul>";
             html+="<div style='text-align:center'><a href='#none' class='remark' onclick=\"var dobj=g('cyfp_more');if(isInvoiceMoreOpen){isInvoiceMoreOpen=false;dobj.style.display='none'}else{isInvoiceMoreOpen=true;dobj.style.display=''}\">更多常用发票</a></div>";
             html+="<ul id='cyfp_more' "+(isInvoiceMoreOpen?"":"style='display:none'")+">";
         }
         html+="<li id='invIi"+invoices[i].id+"'"+((invoices[i].id==selInvoiceId)?" class='xz'":"")+">";
         html+="<table cellspacing='0' cellpadding='0' border='0' style='width:98%'><tr>";
         html+="<td style='width:20px;'><input id='inv_"+invoices[i].id+"' type='radio' name='rbtnInv' onclick=\"changeInv(this,'"+invoices[i].id+"');\" "+((invoices[i].id==selInvoiceId)?" checked":"")+" /></td>";
         html+="<td><label for='inv_"+invoices[i].id+"'>发票抬头：<strong>"+invoices[i].invoiceHeader+"</strong>&nbsp;（"+invoices[i].nameInvoiceType+"）</label></td>";
         html+="<td style='width:40px'><a href='#none' class='remark' onclick=\"DelInv(this,'"+invoices[i].id+"');\">[删除]</a></td>";
         html+="</tr></table>";
         html+="</li>";
       }
      html+="</ul>";
      html+="</div>";
      return html;
}

/**
 * 拼装增值税发票头列表信息
 * @param invoices
 * @returns {String}
 */
var invoiceListLength = 0;
var name=null;
function baseGetVATInvoiceList(invoices){
	 var html="";
	 html += "<div class='cydz'>";
	 html += "<b>常用增票资质</b>";
	 html+="<ul>";
	 var isHaveMore=false;
	 invoiceListLength = invoices.length;
	 for(var i=0;i<invoices.length;i++)
	 {
	    if(i+1>maxInvoiceShowCount && !isHaveMore){
	        isHaveMore=true;
	        html+="</ul>";
	        html+="<div style='text-align:center'><a href='#none' class='remark' onclick=\"var dobj=g('cyfp_more');if(isVATInvoiceMoreOpen){isVATInvoiceMoreOpen=false;dobj.style.display='none'}else{isVATInvoiceMoreOpen=true;dobj.style.display=''}\">更多增值税发票</a></div>";
	        html+="<ul id='cyfp_more' "+(isVATInvoiceMoreOpen?"":"style='display:none'")+">";
	    }
		html+="<li id='invIi"+invoices[i].id+"'"+((invoices[i].code==$("#invoice_Ivc_NsrCode").val())?" class='xz'":"")+">";
		html+="<table cellspacing='0' cellpadding='0' border='0' style='width:98%'><tr>";
		html+="<td style='width:20px;'><input id='inv_"+invoices[i].id+"' type='radio' name='rbtnInv' onclick=\"changeVat(this,'"+invoices[i].id+"');\" "+((invoices[i].code==$("#invoice_Ivc_NsrCode").val())?" checked":"")+" /></td>";
	    html+="<td><label for='inv_"+invoices[i].id+"'>发票抬头：<strong>"+invoices[i].invoiceHeader+"</strong></label></td>";
	    html+="<td><input type='text' id='nsrCode" + i +"' style='display:none' value='"+invoices[i].code +"'/></td>";
	    html+="<td><input type='text' id='invoiceHeader" + i +"' style='display:none' value= '"+invoices[i].invoiceHeader +"'/></td>";
	    html+="</tr></table>";
	    html+="</li>";
	    if((invoices[i].code==$("#invoice_Ivc_NsrCode").val())){
	    	$("#invoice_Ivc_TitName").val(invoices[i].invoiceHeader);
	    }
	  }
	 html+="</ul>";
	 html+="</div>";
	 return html;
	
}

/**
 * 选择增值税发票列表
 */
function changeVat(obj,index){
	if(g('invIi'+selInvoiceId)!=null)
	       g('invIi'+selInvoiceId).className='';
	    selInvoiceId=index;
	    g('invIi'+selInvoiceId).className='xz';
	    jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : PurchaseAppConfig.OrderDomain+"/getQualificationInvoiceById.action?rt="+Math.random(),
			data : "invoiceId="+index,
			success : function(data) {
				if(data != null){
					if(data.error != null && data.error != ""){
						if(data.error == "NoCart"){
							goCart();
							return;
						}
					} 
				}
				if(data != null && data.error == 'NotLogin'){
					alert("登录超时请重新登录.");
					window.location.href=PurchaseAppConfig.OrderLoginUrl;
				}
				if(data.vatResult != null){
					g("part_invoice").innerHTML = data.vatResult;
					setVatDisabled();
					getInvoiceList();
				}else{
					if(data.gitr.errorMessage != null && data.gitr.errorMessage != ""){
						alert(data.gitr.errorMessage);
					}
				}
			}
//	    ,
//			error:function(){
//				alert('系统繁忙，请稍后再试..');
//			}
		});
}


//选择常用发票列表
function changeInv(obj,index)
{
    if(g('invIi'+selInvoiceId)!=null)
       g('invIi'+selInvoiceId).className='';
    selInvoiceId=index;
    g('invIi'+selInvoiceId).className='xz';
    jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/findInvoice.action?rt="+Math.random(),
		data : "invoiceId="+index,
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(data != null && data.error == 'NotLogin'){
				alert("登录超时请重新登录.");
				window.location.href=PurchaseAppConfig.OrderLoginUrl;
			}
			if(data.result != null){
				g("part_invoice").innerHTML = data.result;
				getInvoiceList();
			}else{
				if(data.gitr.errorMessage != null && data.gitr.errorMessage != ""){
					alert(data.gitr.errorMessage);
				}
			}
		}
//    ,
//		error:function(){
//			alert('系统繁忙，请稍后再试..');
//		}
	});
}

//获取常用发票(增值税发票)列表
var invoiveHtml='';
var VAThtml='';
//var hasListFlag = false;//是否有增值税发票列表

function  getInvoiceList(){
	var urlName;
	if($("#invoince_InvoiceType_1").attr("checked")){//普通发票
		urlName = PurchaseAppConfig.OrderDomain+"/getInvoiceList.action?rt="+Math.random();
	}
	if($("#invoince_InvoiceType_2").attr("checked")){//增值税发票
		urlName = PurchaseAppConfig.OrderDomain+"/findQualificationByMemberName.action?rt="+Math.random();	
	}
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : urlName,
		data : "",
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
		   if(data.gir != null && data.gir.flag && data.gir.invoiceInfos.length>0){
				 invoiveHtml=baseGetInvoiceList(eval(data.gir.invoiceInfos));
				 g("invoiceListPanel").innerHTML = invoiveHtml;
		   }
		   if(data.vat != null && data.vat.flag && data.vat.invoiceInfos.length>0){
				VAThtml=baseGetVATInvoiceList(eval(data.vat.invoiceInfos));
				g("invoiceListPanel").innerHTML = VAThtml;
		   }
		}
	});
}

function DelInv(obj,id)
{
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain+"/deleteInvoice.action?rt="+Math.random(),
		data : "invoiceId="+id,
		success : function(data) {
			if(data != null){
				if(data.error != null && data.error != ""){
					if(data.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
		   var html='';
		   if(data.flag)
		   {
			   if($("#invoiceListPanel li").length ==1)
			   {
				   $("#invoiceListPanel").html("");
				   $("#invoiceListPanel").hide();
			   } 
			   else
			   {
				   var elementId = "#invIi" + id;
				   $(elementId).remove();
			   }   
		   }   
		}
//	,
//		error:function(){
//		}
	});
}
/***************************编辑发票信息结束***********************************/
/*****************************************************************************/




//订单备注
function ShowForm_remark(obj){
	if(obj.checked){
		g('remark_remark').style.display='';
		g('remark_remark_div').style.display='';
	}else{
		g('remark_remark').style.display='none';
		g('remark_remark_div').style.display='none';
	}
}


//提交订单
function Submit_Order(){
	removeAlert('message_ff222');
	
	clearWaitInfo();
	if(check_submit_isClose()){//验证
		var data="''";
		if(g('remark_checkbox')!=null&&g('remark_checkbox').checked){//获取订单备注
			data="remark="+encodeURIComponent(g('remark_remark').value);
		}
		if(g('ccPanel').innerHTML.length > 10){//获取验证码
			if(data == "''"){
				data="checkcodeTxt="+g('checkcodeTxt').value;
			}else{
				data=data+"&checkcodeTxt="+g('checkcodeTxt').value;
			}
		}
		if(g('paypasswordPanel')!=null && g('paypasswordPanel').style.display==''){//获取支付密码
			if(data == "''"){ 
				data="paypassword="+encodeURIComponent(g('txt_paypassword').value);
			}else{ 
				data=data+"&paypassword="+encodeURIComponent(g('txt_paypassword').value);
			}
		}
		if($("#TrackID").val() != null){//恶意订单管控系统标识
			if(data == "''"){ 
				data = "TrackID=" + $("#TrackID").val();
			}else{ 
				data = data + "&TrackID=" + $("#TrackID").val();
			}
		}
		 showWaitInfo("正在提交订单，请等待！",g("submitWaitInfo"));
		 g('submit_btn').style.display='none';
		 jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : PurchaseAppConfig.OrderDomain+"/submitOrder.action?rt="+Math.random(),
			data : data,
			success : function(result) {
				if(result != null){
					if(result.error != null && result.error != ""){
						if(result.error == "NoCart"){
							goCart();
							return;
						}
					} 
				}
		   		if(result.sor != null && result.sor.flag){
		   			//提交订单成功，但写入缓存时出错
					if("SubmitSuccess_CacheError" == result.sor.resultCode){
						window.location.href=PurchaseAppConfig.Domain+"/successOrder/successDefault.action?orderid="+result.sor.orderId;
						return ;
					 }
			 		window.location.href=PurchaseAppConfig.Domain+"/successOrder/getSuccessOrder.action?orderid="+result.sor.orderId;
		   		}else{
		   			clearWaitInfo();
		   			if(result.sor.errorMessage!=null){
			   			if(result.sor.errorMessage.indexOf("商品无货")!=-1){
			   				var a = result.sor.errorMessage.indexOf("订单中编号为");
			   				var b = result.sor.errorMessage.indexOf("的商品无货");
			   				var temp = result.sor.errorMessage.substring(a+6,b);
			   				window.location.href=PurchaseAppConfig.Cart+'/cart.html?outSkus='+temp;
			   			}
			   			showAlert(result.sor.errorMessage,g("submitWaitInfo"),'message_ff222');
			   			if(result.sor.errorMessage.indexOf("验证码不正确") > -1){
			   				refreshCheckCodeNew();
			   			}
		   			}
		   			else{
		   				showAlert('亲爱的用户请不要频繁点击, 请稍后重试...',g("submitWaitInfo"),'message_ff222');
		   			}
		   			g('submit_btn').style.display='';
		   		}
			}
		 ,error: function(error) {
				clearWaitInfo();
				//refreshCheckCode();
	          alert('亲爱的用户请不要频繁点击, 请稍后重试...');
	          g('submit_btn').style.display='';
	        },
		complete:function(XMLHttpRequest, textStatus){
			if(  XMLHttpRequest.status==400 ||
					XMLHttpRequest.status==408 ||
					XMLHttpRequest.status==500 ||
					XMLHttpRequest.status==501 ||
					XMLHttpRequest.status==502 ||
					XMLHttpRequest.status==503 ||
					XMLHttpRequest.status==504 ||
					XMLHttpRequest.status==505 ||
					XMLHttpRequest.status==506 ||
					XMLHttpRequest.status==507 ||
					XMLHttpRequest.status==508){
				window.location.href=errorUrl+Math.random();
			}
		}
		});
	}
}
//验证订单页是否全部关闭
function check_submit_isClose(){
	$("#submitWaitInfo").html("");
	//验证验证码是否正确
	if(g('ccPanel')!=null){
		if(g('ccPanel').innerHTML.length > 10){
			if(g('checkcodeTxt').value == ""){
				 showAlert("请先填写验证码.",g("show_error"),"message_ff222");
				 return false;
			}
		}
	}
	if(g('paypasswordPanel')!=null){
		if(g('paypasswordPanel').style.display == ''){
			if(g('txt_paypassword').value == ""){
				 showAlert("请先填写支付密码.",g("show_error"),"message_ff222");
				 return false;
			}
		}
	}
	var consigenn = g('Consignee_Hidden_Type').value;
	var payType = g('PayType_Hidden_Type').value;
	var partInvoice = g('PartInvoice_Hidden_Type').value;
	var errInfo="";
	   //检查是否都关闭
	   if(consigenn==1){errInfo+="“收货人信息”";}
	   if(payType==1){if(errInfo!=''){errInfo+=",";}errInfo+="“支付方式及配送方式”";}
	   if(partInvoice==1){if(errInfo!=''){errInfo+=",";}errInfo+="“发票信息”";}
	   if(errInfo!=""){
	      showAlert("请先保存"+errInfo,g("submitWaitInfo"),"");
	      return false;
	   }
	   return true;
}


function toCart(){
	goCart();
}





/**
 * 购物车拆单显示处理
 */
function processCart(){ 
	jQuery.ajax({
		type : "POST",
		dataType : "json",
		url : PurchaseAppConfig.OrderDomain + "/skuList.action?rt="+Math.random(),
		data : "",
		success : function(result){
			if(result != null){
				if(result.error != null && result.error != ""){
					if(result.error == "NoCart"){
						goCart();
						return;
					}
				} 
			}
			if(result != null && result.cart !=null ){
				  // ---------商品清单的信息------
				  var data = TrimPath.processDOMTemplate("skuList_jst", result); 
				  document.getElementById("part_cart").innerHTML = data;  
		     }
		 }
	});
   
}



function getPromiseInfo(param, node){	
    $.getJSON(
        //"http://jd2008.360buy.com/purchaseservice/ajaxServer/ForPromiseDate.aspx?action=GetOrderPrmoise&callback=?",
        //"http://jd2008.360buy.com/purchaseservice/ajaxServer/ForPromiseDate.aspx?action=GetOrderPrmoise&callback=?",
    		"http://10.10.238.253/ajaxServer/ForPromiseDate.aspx?action=GetOrderPrmoise&callback=?",
        {
            JsonInfo : param
        },
        function(json) {
        	alert(json.promise);
        	if(json.promise!="" ){
        		if(json.promise == 1){//显示拆分
        			$('sbox_1').display='none';
        		}else{//显示不拆分与时间
        			$('sbox_2').display='none';
        			$('promiseDate').innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;"+json.promise;
        		}
        	}
        }
    );
}

/**
 * 控制套装显示隐藏
 */
var openSuitList="";
function setSuitShow(suitId)
{
   if(g('suit_'+suitId)==null){return;}
   if(g('suit_'+suitId).style.display=='none')
   {
      if(openSuitList.indexOf('{'+suitId+'}')<0)
      {
         openSuitList+='{'+suitId+'}';
      }
      g('suit_'+suitId).style.display='';
      g('suitA_'+suitId).innerHTML='[收起套装商品]';
      g('suitImg_'+suitId).src='http://www.360buy.com/purchase/skin/images/bag_close.gif';
   }else{
      openSuitList=openSuitList.replace('{'+suitId+'}','');
      g('suit_'+suitId).style.display='none';
      g('suitA_'+suitId).innerHTML='[查看套装商品]';
      g('suitImg_'+suitId).src='http://www.360buy.com/purchase/skin/images/bag_open.gif';
   }
}


function isSupInStockFirstShip(){
	
	$.getJSON(PurchaseAppConfig.OrderDomain + "/isSupInStockFirstShip.action?rt="+Math.random(),function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
		if(result.supportInStock){
			$("#part_FirstInstockShip").show();
			var checked = "";
			if(result.order.firstInstock)
				checked = "checked";
			var html =   "<input type='checkbox' id='chk_FirstInstockShip' " + checked + " onclick='SetTag_FirstInstockShip(this)'  />&nbsp;"
			           + "<Label for='chk_FirstInstockShip'>先发有货商品（如果订单部分商品缺货，优先配送有货商品）</Label>";
			$("#part_FirstInstockShip").html(html);
            
		} else {
			$("#part_FirstInstockShip").html("");
			$("#part_FirstInstockShip").hide();
		}	
	});
}


function SetTag_FirstInstockShip(obj){
		$.getJSON(PurchaseAppConfig.OrderDomain + "/checkInStockFirstShip.action?rt="+Math.random()+"&inStock="+obj.checked);
}

/**
 * 调用函数则显示图片验证码
 */
function showCheckCode(){
	   g('ccPanel').innerHTML="<div style='margin-bottom:4px'><span style='color:red'></span><img id='orderCheckCodeImg' src='http://cart.360buy.com/order/getCheckCode.action?rt="+Math.random()+"'  onclick=\"getCheckCode(this)\" alt=\"点击更换验证码\" title=\"点击更换验证码\" style=\"display:inline;cursor:pointer;border:#ebcca0 1px solid;\" /><input id='checkcodeTxt' onkeydown=\"if(event.keyCode==13)document.getElementById('submit').click();\" type='text' style='width:50px;margin:0 5px 0 3px;height:22px;padding-left:2px;font-size:17px;font-weight:bold' /></div>";
	   document.getElementById('checkcodeTxt').focus();
   
}

/**
 * 获取下一张验证码
 * @param obj
 */
function getCheckCode(obj){
	cleanCheckCode();
    obj.src = "http://cart.360buy.com/order/getCheckCode.action?id=" + Math.random();
    $('#checkcodeTxt').val("");
}

/**
 * 获取下一张验证码,不删除提示
 * @param obj
 */
function getCheckCodeNew(obj){
    obj.src = "http://cart.360buy.com/order/getCheckCode.action?id=" + Math.random();
    $('#checkcodeTxt').val("");
}

/**
 * 清除验证码提示
 */
function cleanCheckCode(){
 var obj=document.getElementById("submitWaitInfo");
  if(obj != null){
	if(obj.innerHTML!= null && obj.innerHTML.indexOf("验证码")>-1){
	  removeAlert("submitWaitInfo");
	 }
   }
}

/**
 * 刷新获取验证码程序
 */
function refreshCheckCode(){
   var a=document.getElementById('orderCheckCodeImg');
   if(a==null){
	  cleanCheckCode();
      showCheckCode();
   }
   else{
       getCheckCode(a);
       var b=document.getElementById('checkcodeTxt');
       if(b!=null){
    	   //ie版本兼容
    	   try{
    		  b.value='';
    	      b.focus();
    	   } catch (e){} 
       }
    } 
}

/**
 * 刷新获取验证码程序不删除div提示
 */
function refreshCheckCodeNew(){
   var a=document.getElementById('orderCheckCodeImg');
   if(a==null){
      showCheckCode();
   }
   else{
	   getCheckCodeNew(a);
       var b=document.getElementById('checkcodeTxt');
       if(b!=null){
    	   //ie版本兼容
    	   try{
    		  b.value='';
    	      b.focus();
    	   } catch (e){} 
       }
    } 
}


/**
 * 是否验证余额是否开启支付密码
 */
function isUserSafeVerifyOk(balanceAmount){
	$.getJSON(PurchaseAppConfig.OrderDomain + "/checkBalancePwd.action?rt="+Math.random(),function(result){
		if(result != null){
			if(result.error != null && result.error != ""){
				if(result.error == "NoCart"){
					goCart();
					return;
				}
			} 
		}
		if(result.checkFundsPwdResult.flag){
			if(balanceAmount > 0 && result.checkFundsPwdResult.fundsPwd == false){
				//如果上次订单保存为 选中状态
				if(g('chkUseBalance')!=null){
					if(g('chkUseBalance').checked == true){
						//JS模拟取消
						g('chkUseBalance').click();
						//重新加载
						isUserSafeVerifyOk(111);
						return;
					}
				}
				if(g('chkUseBalance')!=null){
					g('chkUseBalance').disabled = true;
				}
				if(g('safeVerciryPromptPart') !=null){
					g('safeVerciryPromptPart').style.display="";
				}
				//g('chkUseBalance').disabled = true;
			}
		}else{
			if(result.checkFundsPwdResult != null){
				if(result.checkFundsPwdResult.errorMessage != null && result.checkFundsPwdResult.errorMessage !=""){
					alert(result.checkFundsPwdResult.errorMessage);
				}
			}
		}
	});
}

//用户京券是否安全(是否开启支付密码或动态支付密码)不安全的话取消所有使用 的京券并disable 显示不可使用
function jingDongLpkNoUse(jingDongLpkType){
	if(jingDongLpkType==jingSafeType){
		$("input[type=checkbox][id^='jing_']").attr("disabled",true);
		//如果是不安全的，即用户未设置支付密码或动态支付密码
		var  checkCancelJing = "";
		$('.jingCheckPwd').each(function(){
			var checked = ($(this).attr('checked'))?"1":"0";
			//如果被选中，则加入
			if(checked=="1"){
				var cardid = $(this).attr('id').split('_')[1];			
				checkCancelJing += cardid;
				checkCancelJing += ",";
				$(this).parent('div').removeClass('couponSelect');
				$(this).parent('div').addClass('couponNoSelect');
			}
			//取消选择
			$(this).attr('checked',false);
			
		});
		//将所有京券不能勾选
		$("input[type=checkbox][id^='jing_']").attr("disabled",true);
		
		//发请求取消所有京券的使用
		if(checkCancelJing!=""){
			checkCancelJing = checkCancelJing.substring(0, checkCancelJing.length-1);
			$.getJSON(PurchaseAppConfig.OrderDomain + "/cancelUseAllJing.action?rt="+Math.random()+"&checkCancelJing="+checkCancelJing,function(cancelUseAllJingResult){
				if(cancelUseAllJingResult.cancelUseAllJingSuccess==true){
					g('safeYouHuiQuanPart').style.display="";
					useCancelCouponCommon(cancelUseAllJingResult);
				}
			});
		}else{
			g('safeYouHuiQuanPart').style.display="";
		}
	}else if(jingDongLpkType==dongSafeType){
		$("input[type=radio][id^='dong_']").attr("disabled",true);
		var  checkCancelDong = "";
		$('.dongCheckPwd').each(function(){
			var checked = ($(this).attr('checked'))?"1":"0";
			if(checked=="1"){
				checkCancelDong = $(this).attr('id').split('_')[1];	
				$(this).parent('div').removeClass('couponSelect');
				$(this).parent('div').addClass('couponNoSelect');
			}
			//取消选择
			$(this).attr('checked',false);
		});
		$(".couponNoSelect > label").children("[id^=CouponCancel]").css("display","none");
		//将所有京券不能勾选
		$("input[type=checkbox][id^='dong_']").attr("disabled",true);		
		//发请求取消所有京券的使用
		if(checkCancelDong!=""){
			$.getJSON(PurchaseAppConfig.OrderDomain + "/cancelUseAllDong.action?rt="+Math.random()+"&checkCancelDong="+checkCancelDong,function(cancelUseAllDongResult){
				if(cancelUseAllDongResult.cancelUseAllDongSuccess==true){
					g('safeYouHuiQuanPart').style.display="";
					useCancelCouponCommon(cancelUseAllDongResult);					
				}
			});			
		}else{
			g('safeYouHuiQuanPart').style.display="";
		}
	}else if(jingDongLpkType==lpkSafeType){
		$("input[type=checkbox][id^='lpkItem_']").attr("disabled",true);
		
		g('safeLpkPart').style.display="";
		var  checkCancelLpk = "";
		var lipinkaCount = $("#lipinka_count").html();
		$('.lpkCheckPwd').each(function(){
			var checked = ($(this).attr('checked'))?"1":"0";
			//如果被选中，则加入
			if(checked=="1"){
				var cardid = $(this).val();						
				checkCancelLpk += cardid;
				checkCancelLpk += ",";
				
				$('#unbinded'+cardid).remove();
				$("#lipinkaCurUsed_"+cardid).html("0.00");
				lipinkaCount = parseInt(lipinkaCount)-1;
				
			}
			//取消选择
			$(this).attr('checked',false);
		});
		$("#lipinka_count").html(lipinkaCount+'');
		//发请求取消所有京券的使用
		if(checkCancelLpk!=""){
			checkCancelLpk = checkCancelLpk.substring(0, checkCancelLpk.length-1);
			$.getJSON(PurchaseAppConfig.OrderDomain + "/cancelUseAllLpk.action?rt="+Math.random()+"&checkCancelLpk="+checkCancelLpk,function(cancelUseAllLpkResult){
				if(cancelUseAllLpkResult.cancelUseAllLpkSuccess==true){
					g('safeLpkPart').style.display="";
					refreshMoneyArea(cancelUseAllLpkResult);
				}
			});
		}else{
			g('safeLpkPart').style.display="";
		}
		
	}
	
}

function useCancelCouponCommon(result){
	//要恢复原样,或者在发送一次(不可取)，使用全局变量：coupons(原始json)
//	for(var i = 0;i< coupons.orderCoupons.length;i++){
//		var cou = coupons.orderCoupons[i];
//		//不满足优惠金额的东券置成灰色不可选
//		if(cou.canUsed == false && cou.checked == false){
//			g("dong_"+cou.id).disabled=true;
//		}
//	}
	//计算优惠券使用张数
	var dongCount = $("input[type=radio][id^='dong_']:checked").length;
	var jingCount = $("input[type=checkbox][id^='jing_']:checked").length;
	var shitiCount = $("div[name=shitiquan]").length;
	
	if(parseInt(dongCount) == 0 && parseInt(jingCount)==0 && parseInt(shitiCount)==0){
		$("#coupon_count").html("0");
	}else{
		$("#coupon_count").html(parseInt(dongCount)+parseInt(jingCount)+parseInt(shitiCount));
	}
	
	var param = processResult(result);
	var data = TrimPath.processDOMTemplate("lipinkasData", param);
	$("#lipinkasArea").html(data);
	refreshMoneyArea(result);
	var waste = (result.order.priceInfo.varTotalCouponDiscount*100) - ((result.order.priceInfo.varPromoPrice*100) + (result.order.priceInfo.varFreight*100));
	if(waste>0){
		$("#couponWaste").show();
	}
}

/**
 * 礼品卡输入事件
 */
function lipinkaInputEventInit(){
	$('.lpkKeyPress').keyup(function(){
		var $this = $(this);
		$this.val($this.val().replace(/[^a-zA-Z0-9]/g,'').toUpperCase());
		$this.val($this.val().replace('O','0'));
		if($this.val().length==4 && $this.attr('id')!='lpkKeyPressForth'){
			$this.next().next().focus();
		}
	});
}
/**
 * 是否显示订单备注
 */
function showOrderRemark(){
	$.getJSON(PurchaseAppConfig.OrderDomain + "/isShowOrderRemark.action?rt="+Math.random(),function(result){
		if(result== null){
			if(g('show_remark').style.display!='none'){
				g('show_remark').style.display='none';
				$("#show_remark").html("");
			}
			return;
		}
		
		
		
		if(result.showRemark){
			if(g('show_remark').style.display=='none') {
			       g('show_remark').style.display='';
			       $("#show_remark").html("<input type='checkbox' onclick='ShowForm_remark(this)' id="
						+"\""+"remark_checkbox"+"\""+"> 订单备注<input id="+"\""+"remark_remark"
						+"\""+" style="+"\""+"width:220px; color:#cccccc;display:none;"+"\""
						+" class="+"\""+"txt"+"\""+" value="+"\""+"限15个字"+"\""
						+" onblur="+"\""+"if(this.value==''||this.value=='限15个字'){this.value='限15个字';this.style.color='#cccccc'}"+"\""
						+"onfocus="+"\""+"if(this.value=='限15个字') {this.value='';};this.style.color='#000000';"
						+"\""+" maxlength="+"\""+"15"+"\""+" type="+"\""+"text"+"\""+"><div class="+"\""+"middle"+"\""
						+" style='display:none' id="+"\""+"remark_remark_div"+"\""+"><font color="
						+"\""+"red"+"\""+">*提示</font><font color="+"\""+"#cccccc"+"\""+">：请勿填写有关支付、收货、方面的信息。</font> </div>");
			       }
		} else {
			if(g('show_remark').style.display!='none'){
				g('show_remark').style.display='none';
				$("#show_remark").html("");
		}}});
}
call(PurchaseAppConfig.OrderDomain + "/getNewOrderInfo.action?rt="+Math.random());

/**
 * 设置增票类型
 * @param selValue
 * @return
 */
function vatInvoice_setType(selValue){
	g('invoice_InvoicePutType').value=selValue;
	g('invoice_BookInvoicePutType').value=selValue;
}

function chkWhetherInvoice(obj){
	if(obj.checked){
		g('invoiceFormPanel').style.display='';
		if(preNoCheckInvoice!=null){
			g('invoice_InvoicePutType').value = preNoCheckInvoice;
		}else{
			g('invoice_InvoicePutType').value = '0';
		}
		g('invoice_WhetherInvoice').value='1';
	}else{
		g('invoiceFormPanel').style.display='none';
		preNoCheckInvoice = g('invoice_InvoicePutType').value;
		g('invoice_InvoicePutType').value='3';
		g('invoice_WhetherInvoice').value='0';		
	}
}

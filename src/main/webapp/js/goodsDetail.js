$(function(){
	//1、validate all input number must not null
	//2、get the value
//	var all = document.getElementById("cart").getElementsByTagName("input");
//	var totle = 0;
//	for(var i=0;i<all.length;i++){
//		if(all[i].type == "text"){
//	
//			if(all[i].value == null || chckspace(all[i].value)){
//				
//				alert("input num for product!");
//			}else{
//				//not null,get the price of every one
//				totle += parseFloat(all[i].trim);
//			}
//		}
//	}
//	
//	//get the node of totle price
//	$("#totleprice").html("totle： ￥"+totle);
	
	
	
	//选择电表编号
	$("#mselectManufacturer").change(function(){
		var routType = $("#mselectManufacturer").find("option:selected").val();
		
//		alert("aaaaaaaaaaaaaaa");
//		alert(routType);
		$("#rountting1").slideDown();
		
		if(routType == 2){
			$("#rountting1").hide();
			$("#rountting2").slideDown();
		}
		if(routType == 1){
			$("#rountting2").hide();
			$("#rountting1").slideDown();
		}
		
	});
	
	
});

function chckspace(inputStr){
	//
	var str = " "; 

		for(var i = 0;i<inputStr.length;i++) { 
			str = str + " "; 
		} 
		return (str == inputStr);
	
	
//	
//	try{
//		parseInt(inputStr);
//		return true;
//	}catch (e) {
//		return false;
//	}
}
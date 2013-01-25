ddsmoothmenu.init({
	mainmenuid: "templatemo_menu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
});

$(document).ready(function() {
	 $("#carousel").dualSlider({
	        auto:true,
	        autoDelay: 6000,
	        easingCarousel: "swing",
	        easingDetails: "easeOutBack",
	        durationCarousel: 1000,
	        durationDetails: 600
	    });
	
	

    $("#new_address").click(function(){
    	$("#nomal_address").hide();
    	$("#consignee_addr").slideToggle(1000);
  
    });
    
    $("#add_2_nomal").click(function(){
		 $("#consignee_addr").hide();
	    $("#nomal_address").show();
//	    $("#new_address").innerHTML = " [鍒涘缓涓�釜鏂板湴鍧�";
   });
    
    $("#linkPayTypeShipType").click(function(){
	
	    $("#nomal_pay").hide();
	    $("#choose_pay").slideToggle(1000);
	    
	    //$("#linkPayTypeShipType").attr=("id","linkPayTypeShipType2");
	   // $("#linkPayTypeShipType").html = "[淇濆瓨]";
	    
    });
    
    
//    $("#finalPrice").html="aaaaaaaa";
    //totleprice();
    
//    $("#linkPayTypeShipType2").click(function(){
//   	 	$("#choose_pay").hide();
//	    $("#nomal_pay").show();
//	    
//	    
//	    $("#linkPayTypeShipType2").attr=("id","linkPayTypeShipType");
//	    $("#linkPayTypeShipType").html = "淇敼";
//  });
//    
   
   
});   

/***
 * add count
 * @param attrid
 */
function addcount(attrid){
	//alert("---!!!------"+$("#changeQuantity-1005367078-1-1-0").attr("value"));"#changeQuantity2"
	var odlnum = $(attrid).attr("value");
	odlnum++;
	$(attrid).attr("value",odlnum) ;
//	$("#changeQuantity-1005367078-1-1-0").html = parseInt($("#increment-1005367078-1-1-0").value) ++;
	//totleprice();
}

/**
 * desc count
 * @param attrid
 */
function descount(attrid){
	
	var oldnum = $(attrid).attr("value");
	
	if(oldnum >= 1){
		oldnum--;
		$(attrid).attr("value",oldnum) ;
	}else{
		alert("con't decs now!");
	}
	//totleprice();
}

function deletitem(thisform){
	alert("=====");
}

function check_all(obj,cName)
{
    var checkboxs = document.getElementsByName(cName);
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}	


function totleprice(){
	
	
	
	//get every item
	var items = $(".price");
	
	alert("-------"+items.length); 
	
	 $.each(items,function(n,value) { 
		alert(n+"-------"+value); 
		
		
	 });
}






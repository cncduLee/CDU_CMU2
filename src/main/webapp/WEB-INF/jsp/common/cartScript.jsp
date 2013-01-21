<link rel="stylesheet" type="text/css" href="css/CT_information_files/CT_base.css" />
<link rel="stylesheet" type="text/css" href="css/CT_information_files/CT_purchase.2012.css" />



<script language="javascript">

function addCount(){
	//计算第一个商品的总价
	var count = parseFloat(document.getElementById("changeQuantity1").value);
	var newCount = count + 1;
	document.getElementById("changeQuantity1").value = newCount;
	newCount = newCount * 99;
	document.getElementById("lblPrice").innerText = newCount;

	//计算所有商品的总价
	var countTwo =document.getElementById("lblPriceTwo").innerText;
	
	var totalCount = newCount + parseFloat(countTwo);
	
	document.getElementById("lblTotalPrice").innerText = "￥" + totalCount.toString() ;

}


function addCountTwo()
{
	//计算第二个商品的总价
	var count = parseFloat(document.getElementById("changeQuantity2").value);
	var newCount = count + 1;
	document.getElementById("changeQuantity2").value = newCount;
	newCount = newCount * 99;
	document.getElementById("lblPriceTwo").innerText = newCount;

	//计算所有商品的总价
	var countOne =document.getElementById("lblPrice").innerText;
	
	var totalCount = newCount + parseFloat(countOne);
	
	document.getElementById("lblTotalPrice").innerText = "￥" + totalCount.toString() ;
}


function subCount(){
	//计算第一个商品的总价
	var count = parseFloat(document.getElementById("changeQuantity1").value);
	if(count >= 1)
	{
		var newCount = count - 1;
		document.getElementById("changeQuantity1").value = newCount;
		newCount = newCount * 99;
		document.getElementById("lblPrice").innerText = newCount;

		//计算所有商品的总价
		var countTwo =document.getElementById("lblPriceTwo").innerText;
	
		var totalCount = newCount + parseFloat(countTwo);
	
		document.getElementById("lblTotalPrice").innerText = "￥" + totalCount.toString() ;
	}
		
	else
		alert("The number of this product is the minimum!")

	

}


function subCountTwo()
{
	//计算第一个商品的总价
	var count = parseFloat(document.getElementById("changeQuantity2").value);
	if(count >= 1)
	{
		var newCount = count - 1;
		document.getElementById("changeQuantity2").value = newCount;
		newCount = newCount * 99;
		document.getElementById("lblPriceTwo").innerText = newCount;

		//计算所有商品的总价
		var countOne =document.getElementById("lblPrice").innerText;
	
		var totalCount = newCount + parseFloat(countOne);
	
		document.getElementById("lblTotalPrice").innerText = "￥" + totalCount.toString() ;
	}
		
	else
		alert("The number of this product is the minimum!")
}

function check_all(obj,cName)
{
    var checkboxs = document.getElementsByName(cName);
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}	
</script>
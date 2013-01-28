function PurchaseAppConfig(){
	
}

PurchaseAppConfig.Domain = 'http://cart.360buy.com/order';
PurchaseAppConfig.OrderDomain = 'http://cart.360buy.com/order/orderSop';
PurchaseAppConfig.LoginUrl = 'http://passport.360buy.com/new/login.aspx?ReturnUrl=';
PurchaseAppConfig.SplitOrderLoginUrl=PurchaseAppConfig.LoginUrl+PurchaseAppConfig.Domain+"/order/splitOrder.html";
PurchaseAppConfig.OrderLoginUrl=PurchaseAppConfig.LoginUrl+PurchaseAppConfig.Domain+"/orderInfoSop.html";
PurchaseAppConfig.Cart = 'http://cart.360buy.com/cart/';
PurchaseAppConfig.SuccessOrder = 'http://cart.360buy.com/order/';
PurchaseAppConfig.ShoppingCart = 'http://cart.360buy.com/cart/cart.html';
PurchaseAppConfig.EasyBuy = 'http://cart.360buy.com/order/orderInfoSop.html';
	window.history.forward();
    function noBack() { window.history.forward(); }
    

		
<!--More1-->

		
		function whocalled()
			{
				window.Main.whocalled();
			}	
		
		
<!--main menu-->

				
				function postpaid_english()
					{
						window.Main.postpaid_english();
					}	
		
<!--Pre_Paid_Servies-->
			function getUssdBalance()
			{
				window.Main.DialUSSD("*888");
			}	
			function subscribe_in_broadband(){
				window.Main.subscribe_in_broadband();
			}
		
			function getUssdCallBackRequest()
			{
				window.Main.getUssdCallBackRequest("09");
			}
			function getUssdRechargeBalance()
			{
				window.Main.getUssdRechargeBalance();
			}
			function migration_eezee(){
				window.Main.DialUSSD("*100*111");
			}	
			function migration_month(){
				window.Main.DialUSSD("*100*30");
			}	
			function migration_quarter(){
				window.Main.DialUSSD("*100*90");
			}	
			function migration_year(){
				window.Main.DialUSSD("*100*356");
			}	
			
			
		
<!--Post_Paid_Servies-->
			function getUssdRemainingPackets(){
				window.Main.DialUSSD("*510");
			}
			function getUssdPayTheBill(){
				window.Main.getUssdPayTheBill("500");
			}	
			function getUssdBillInquiry(){
				window.Main.DialUSSD("*500");
			}
			function gobackfrompostpaidservices(){
				 window.Main.goBack();
			}	
			
			
<!--Other_Servies-->
			function balanceTransfer(){
				window.Main.getUssdBalanceTransfer("09");
			}
			function getUssdEditPassword(){
				window.Main.getUssdEditPassword();
			}
			function getUssdShowFF(){
				window.Main.DialUSSD("*555");	
			}
			function getUssdEditFF(){
				window.Main.getUssdEditFF("09");
			}
			
			function getUssdShowFF_post(){
				window.Main.DialUSSD("*550");	
			}
			function getUssdEditFF_post(){
				window.Main.getUssdEditFF_post("09");
			}
			function infomsg_ar(){
				window.Main.DialUSSD("*187*2");
			}
			function infomsg_en(){
				window.Main.DialUSSD("*187*1");
			}
		
		
<!--Subscribe_In_Brodband-->

			function getUssdSubscribInBrodband(BroadBandType){	
				window.Main.DialUSSD("*124*"+BroadBandType);
			}
			
<!--Subscribe_In_Brodband_post-->

			function getUssdSubscribInBrodband_post(BroadBandType){	
				window.Main.DialUSSD("*137*"+BroadBandType);
			}

			function successAlert(){alert('pass');}
			function errorAlert(){alert('err');}
			
<!--contact us-->

			function sendSMS(){	
					window.Main.sendSMS();
			}
			
			function callcenter(){	
				window.Main.DialUSSD('123');
			}
	
			function sendmail(){	
				window.Main.sendmail();
			}
			
<!--internet and mms-->

			function activateinternet(){	
					window.Main.activateinternetmms('INTERNET');
			}
			
			
			function activatemms(){	
					window.Main.activateinternetmms('MMS');
		}
			
<!--Daleele-->
			function dal_sub(){	
				window.Main.SMS('YP','2200');
			}
			
			function dal_unsub(){	
				window.Main.SMS('UNYP','2200');
			}
			function dal_loc(){	
				window.Main.SMS('HELP','2222');
			}
			
			
			
			function contacts(){
				window.Main.callc();
			}
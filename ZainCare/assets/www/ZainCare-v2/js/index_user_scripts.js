(function()
{
 "use strict";
 /*
   hook up event handlers 
 */
 function register_event_handlers()
 {
    
    
         $(".uib_w_9").click(function(evt)
        {
         /* Other possible functions are: 
           uib_sb.open_sidebar($sb)
           uib_sb.close_sidebar($sb)
           uib_sb.toggle_sidebar($sb)
            uib_sb.close_all_sidebars()
          See js/sidebar.js for the full sidebar API */
        
         uib_sb.toggle_sidebar($(".uib_w_2"));  
        });
        $(".uib_w_14").click(function(evt)
        {
         activate_subpage("#Profile_page"); 
        });
        $("#home-btn").click(function(evt)
        {
         activate_subpage("#mainsub"); 
        });
        $("#favorites-btn").click(function(evt)
        {
         activate_subpage("#Favorite_page"); 
        });
        $("#messages-btn").click(function(evt)
        {
         activate_subpage("#Messages_page"); 
        });
        $("#profile-btn").click(function(evt)
        {
         activate_subpage("#Profile_page"); 
        });
		 $("#BB-btn").click(function(evt)
        {
         activate_subpage("#BB"); 
        });
		$("#BBPRE-btn").click(function(evt)
        {
         activate_subpage("#BBPRE"); 
        });

		$("#shops-btn").click(function(evt)
        {
         activate_subpage("#zain-shops"); 
        });
		$("#internetMMS-btn").click(function(evt)
        {
         activate_subpage("#internetMMS"); 
        });
		$("#aboutzain-btn").click(function(evt)
        {
         activate_subpage("#aboutzain"); 
        });
		$("#contactus-btn").click(function(evt)
        {
         activate_subpage("#contactus"); 
        });
		$("#daleele-btn").click(function(evt)
        {
         activate_subpage("#daleele"); 
        });
		$("#infomsg-btn").click(function(evt)
        {
         activate_subpage("#infomsg"); 
        });
		$("#migration-btn").click(function(evt)
        {
         activate_subpage("#migration"); 
        });
		$("#la7inna-btn").click(function(evt)
        {
         activate_subpage("#la7inna"); 
        });
		$("#my-btn").click(function(evt)
        {
         activate_subpage("#my_account"); 
        });
		$("#ui-id-1").click(function(evt)
        {
         activate_subpage("#my_account"); 
        });
		$("#ui-id-2").click(function(evt)
        {
         activate_subpage("#my_account2"); 
        });
		$("#ui-id-3").click(function(evt)
        {
         activate_subpage("#my_account3"); 
        });
		$("#ui-id-11").click(function(evt)
        {
         activate_subpage("#my_account"); 
        });
		$("#ui-id-33").click(function(evt)
        {
         activate_subpage("#my_account3"); 
        });
		$("#ui-id-111").click(function(evt)
        {
         activate_subpage("#my_account"); 
        });
		$("#ui-id-222").click(function(evt)
        {
         activate_subpage("#my_account2"); 
        });


		








}
 $(document).ready(register_event_handlers);
})();

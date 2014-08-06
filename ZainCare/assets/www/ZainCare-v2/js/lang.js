
      	// load I18N bundles
		jQuery(document).ready(function() {
			loadBundles('en');
					
			// configure language combo box
			jQuery('#lang').change(function() {
				var selection = jQuery('#lang option:selected').val();
				loadBundles(selection != 'browser' ? selection : null);
				jQuery('#langBrowser').empty();
				if(selection == 'browser') {
					jQuery('#langBrowser').text('('+jQuery.i18n.browserLang()+')');
				}
			});
			
			// load files just for display purposes...
			jQuery('h4').each(function() {
				var file = 'bundle/' + jQuery(this).text();
				var code = jQuery(this).next().next('code');
				jQuery.get(file, function(data) {
					data = data.replace(/\n/mg, '<br/>');
					code.html(data);
				});
			});
			// ... and configure links to show/hide them
			jQuery('a.toggle').bind('click', function() {
			  jQuery(this).next('code').slideToggle();
				return false;
			});
		});
		
		function loadBundles(lang) {
			jQuery.i18n.properties({
			    name:'Messages', 
			    path:'bundle/', 
			    mode:'both',
			    language:lang, 
			    callback: function() {
			        updateExamples();
			    }
			});
		}
		
		function updateExamples() {
			// Accessing values through the map
			var ex1 = 'msg_hello';
			var ex2 = 'msg_complex'; var ex2P = 'John';
			var ex3 = 'msg_url'; var ex3P = 'solution_1';
			var ex4 = 'inexistent_key';
            var ex5 = 'msg_multi_placeholder'; var ex5P1 = 'beautiful'; var ex5P2 = 'fishing';
            var ex6 = 'msg_multi_placeholder_corrected'; var ex6P1 = 'beautiful'; var ex6P2 = 'fishing';
			jQuery('#mapExamples')
				.empty()
				.append('Sample:  '+jQuery.i18n.prop(ex1)+'</li>')

		
			// Accessing values through a JS variable
			var about ='about_zain';
			var shops= 'zain_shops';
			var contactus= 'contact_us';
			var mainmenu = 'menu_';
			var side_zain = 'zain_btn';
			var side_my = 'my_btn';
			var side_post = 'postpaid_btn';
			var side_pre = 'prepaid_btn';
			var side_others = 'others_btn';
			var post_stylee = 'stylee';
			var post_paybill = 'pay_bill';
			var post_billinq = 'bill_inq';
			var post_bb = 'broadband';
			var post_showff = 'showfnf';
			var post_editff ='editfnf';
			var post_bb_title = 'internet_title';
			var post_bb_act = 'activate_';
			var post_bb_deact = 'deactivate';
			
			var pre_bal = 'pre_balance';
			var pre_rech = 'pre_recharge';
			var pre_trans_ = 'pre_trans';
			var pre_cb_ = 'pre_cb';
			var pre_showff_ = 'pre_showff';
			var pre_editff_ = 'pre_editff';
			var pre_mig_ = 'pre_mig';
			var pre_bb_ = 'pre_bb';
			
			var bb_pre_stat_ = 'bb_pre_stat';
			var bb_pre_day_ = 'bb_pre_day';
			var bb_pre_week_ = 'bb_pre_week';
			var bb_pre_mon_ = 'bb_pre_mon';
			
			var mig_title_ = 'mig_title';
			var mig_eezee_ = 'mig_eezee';
			var mig_mob_mon_ = 'mig_mob_mon';
			var mig_mob_q_ = 'mig_mob_q';
			var mig_mob_y_ = 'mig_mob_y';
			
			var who_called_ = 'who_called';
			var rbt_ = 'rbt';
			var internet_mms_ = 'internet_mms';
			var dal_ = 'dal';
			var sml_ = 'sml';
			
			var dal_sub_ = 'dal_sub';
			var dal_unsub_ = 'dal_unsub';
			var dal_loc_ = 'dal_loc';
			
			var im_title_ = 'im_title';
			var im_line1_ = 'im_line1';
			var im_int_btn_ = 'im_int_btn';
			var im_mms_btn_ = 'im_mms_btn';
			var im_line2_ = 'im_line2';
			var im_line3_ = 'im_line3';
			var im_line4_ = 'im_line4';



				
				jQuery('#aboutzain-btn')
						.empty()
						.append(eval(about)+'</li>')
				jQuery('#shops-btn')
						.empty()
						.append(eval(shops)+'</li>')
				jQuery('#contactus-btn')
						.empty()
						.append(eval(contactus)+'</li>')
				jQuery('#settingsbtn')
						.empty()
						.append(eval(mainmenu)+'</li>')
				jQuery('#home-btn')
						.empty()
						.append('<a class="button widget uib_w_9">'+eval(side_zain)+'</a>')
				jQuery('#my-btn')
						.empty()
						.append('<a class="button widget uib_w_9">'+eval(side_my)+'</a>')
				jQuery('#favorites-btn')
						.empty()
						.append('<a class="button widget uib_w_9">'+eval(side_post)+'</a>')
				jQuery('#messages-btn')
						.empty()
						.append('<a class="button widget uib_w_9">'+eval(side_pre)+'</a>')
				jQuery('#profile-btn')
						.empty()
						.append('<a class="button widget uib_w_9">'+eval(side_others)+'</a>')
				jQuery('#getUssdRemainingPackets')
						.empty()
						.append(eval(post_stylee))
				jQuery('#paybill')
						.empty()
						.append(eval(post_paybill))
				jQuery('#billinq')
						.empty()
						.append(eval(post_billinq))
				jQuery('#BB-btn')
						.empty()
						.append(eval(post_bb))
				jQuery('#showff')
						.empty()
						.append(eval(post_showff))
				jQuery('#editff')
						.empty()
						.append(eval(post_editff))
				jQuery('#bb_post_title')
						.empty()
						.append(eval(post_bb_title))
				jQuery('#bb_post_act')
						.empty()
						.append(eval(post_bb_act))
				jQuery('#bb_post_deact')
						.empty()
						.append(eval(post_bb_deact))
						
				jQuery('#pre_bal')
						.empty()
						.append(eval(pre_bal))
				jQuery('#pre_rech')
						.empty()
						.append(eval(pre_rech))
				jQuery('#pre_trans')
						.empty()
						.append(eval(pre_trans_))
				jQuery('#pre_cb')
						.empty()
						.append(eval(pre_cb_))
				jQuery('#pre_showff')
						.empty()
						.append(eval(pre_showff_))
				jQuery('#pre_editff')
						.empty()
						.append(eval(pre_editff_))
				jQuery('#migration-btn')
						.empty()
						.append(eval(pre_mig_))
				jQuery('#BBPRE-btn')
						.empty()
						.append(eval(pre_bb_))
						
				jQuery('#bb_pre_stat')
						.empty()
						.append(eval(bb_pre_stat_))
				jQuery('#bb_pre_day')
						.empty()
						.append(eval(bb_pre_day_))
				jQuery('#bb_pre_week')
						.empty()
						.append(eval(bb_pre_week_))
				jQuery('#bb_pre_mon')
						.empty()
						.append(eval(bb_pre_mon_))
						
				jQuery('#mig_title')
						.empty()
						.append(eval(mig_title_))
				jQuery('#mig_ez')
						.empty()
						.append(eval(mig_eezee_))
				jQuery('#mig_mon')
						.empty()
						.append(eval(mig_mob_mon_))
				jQuery('#mig_q')
						.empty()
						.append(eval(mig_mob_q_))
				jQuery('#mig_y')
						.empty()
						.append(eval(mig_mob_y_))
						
				jQuery('#whocalled_')
						.empty()
						.append(eval(who_called_))
				jQuery('#la7inna-btn')
						.empty()
						.append(eval(rbt_))
				jQuery('#internetMMS-btn')
						.empty()
						.append(eval(internet_mms_))
				jQuery('#daleele-btn')
						.empty()
						.append(eval(dal_))
				jQuery('#infomsg-btn')
						.empty()
						.append(eval(sml_))
						
				jQuery('#dal_sub_btn')
						.empty()
						.append(eval(dal_sub_))
				jQuery('#dal_unsub_btn')
						.empty()
						.append(eval(dal_unsub_))
				jQuery('#dal_loc_btn')
						.empty()
						.append(eval(dal_loc_))
						
				jQuery('#im_title')
						.empty()
						.append(eval(im_title_))
				jQuery('#im_1')
						.empty()
						.append(eval(im_line1_))
				jQuery('#im_btn1')
						.empty()
						.append(eval(im_int_btn_))
				jQuery('#im_btn2')
						.empty()
						.append(eval(im_mms_btn_))
				jQuery('#im_2')
						.empty()
						.append(eval(im_line2_))
				jQuery('#im_3')
						.empty()
						.append(eval(im_line3_))
				jQuery('#im_4')
						.empty()
						.append(eval(im_line4_))
						
						


						
		}

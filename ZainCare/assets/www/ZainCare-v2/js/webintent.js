/**
 * cordova Web Intent plugin
 * Copyright (c) Boris Smus 2010
 *
 */
 (function(cordova){
    var WebIntent = function() {

    };

    WebIntent.prototype.ACTION_SEND = "android.intent.action.SEND";
    WebIntent.prototype.ACTION_VIEW= "android.intent.action.VIEW";
    WebIntent.prototype.EXTRA_TEXT = "android.intent.extra.TEXT";
    WebIntent.prototype.EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
    WebIntent.prototype.EXTRA_STREAM = "android.intent.extra.STREAM";
    WebIntent.prototype.EXTRA_EMAIL = "android.intent.extra.EMAIL";
	

    WebIntent.prototype.startActivity = function(params, success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'WebIntent', 'startActivity', [params]);
    };

    WebIntent.prototype.hasExtra = function(params, success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'WebIntent', 'hasExtra', [params]);
    };

    WebIntent.prototype.getUri = function(success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'WebIntent', 'getUri', []);
    };

    WebIntent.prototype.getExtra = function(params, success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'WebIntent', 'getExtra', [params]);
    };


    WebIntent.prototype.onNewIntent = function(callback) {
        return cordova.exec(function(args) {
            callback(args);
        }, function(args) {
        }, 'WebIntent', 'onNewIntent', []);
    };

    WebIntent.prototype.sendBroadcast = function(params, success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'WebIntent', 'sendBroadcast', [params]);
    };

    cordova.addConstructor(function() {
        window.webintent = new WebIntent();
        
        // backwards compatibility
        window.plugins = window.plugins || {};
        window.plugins.webintent = window.webintent;
    });
	
	WebIntent.prototype.call = function(params, success, fail)
		{
		return cordova.exec(function(args)
		{success(args);},
		function(args) {fail(args);
		},'WebIntent','call',[params]
	);};
	
	Android.showMap = function (address) {
		  window.plugins.webintent.startActivity({
			action: WebIntent.ACTION_VIEW,
			url: 'geo:0,0?q=' + address,
		  }, function () {}, function () {
			alert('Failed to open URL via Android Intent');
		  });
		};
		
Android.sendEmail = function(subject, body) { 
  var extras = {};
	  extras[WebIntent.EXTRA_SUBJECT] = subject;
	  extras[WebIntent.EXTRA_TEXT] = body;
	  window.plugins.webintent.startActivity({ 
		  action: WebIntent.ACTION_SEND,
		  type: 'text/plain', 
		  extras: extras 
		}, 
		function() {}, 
		function() {
		  alert('Failed to send email via Android Intent');
		}
	  ); 
	};
	
	var MyPlugin = function () {};
			MyPlugin.prototype.foo = function (params, success, fail) {
  	return cordova.exec(success, fail, 'MyPlugin', 'startActivity', [params]);
    };
	
	cordova.addConstructor(function () {
  	// Creates window.plugins.myplugin, an instance of MyPlugin
  		cordova.addPlugin('myplugin', new MyPlugin()); 
  	// Binds MyPlugin to the Java class com.example.MyPlugin 
  		PluginManager.addService('MyPlugin', 'sd.zain.care');
	});

})(window.PhoneGap || window.Cordova || window.cordova);

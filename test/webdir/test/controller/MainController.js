Ext.define('Test.controller.MainController', {
    extend: 'Ext.app.Controller',
    init: function () {
    	console.log("main init...");
        console.log("show view port");
        Ext.Function.defer(function(){
            Ext.create('Test.view.Viewport').show();
        }, 500);
    }
});
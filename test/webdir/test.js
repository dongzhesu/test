Ext.Loader.setConfig({disableCaching: false});
Ext.Loader.setConfig({enabled: true});
Ext.Loader.loadLang('libs/extjs/locale/ext-lang-', 'test/lang/MainLang_');
Ext.Loader.loadScript('test/test.js');
//prevent quit or reload without saving
Ext.application({
    name: 'Test',
    appFolder: 'test',
    serverErrCount: 0,
    controllers: [
        'Test.controller.MainController',
        'Test.controller.NavigatorController'
    ],
    launch: function() {
    	console.log("ERP launch...");
    }
});
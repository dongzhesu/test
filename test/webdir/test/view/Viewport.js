Ext.define('Test.view.Viewport',{
    extend: 'Ext.Viewport',
    requires : [
        'Ext.window.*',
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.form.*',
        'Test.view.Navigator',
        'Test.view.ContentPanel'//,
    ],
    initComponent : function(){
        Ext.apply(this, {
		    id: 'viewport',
		    autoScroll: true,
		    layout: 'border',
			items: [
                    Ext.create('Test.view.Navigator'),
                    Ext.create('Test.view.ContentPanel'),
                    {
                        title: 'Elbro Wine ERP Management System',
                        region: 'south'
                    }
            ]
        });
        this.callParent(arguments);
    }
});
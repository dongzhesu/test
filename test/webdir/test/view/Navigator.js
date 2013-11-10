Ext.define('Test.view.Navigator',{
    extend: 'Ext.Panel',
	alias: 'widget.navpanel',
    initComponent : function(){
	    
        Ext.apply(this,{
            id: 'navPanel',
            title: 'Elbro Wine'.i18n(),
			layout:'accordion',
            region:'west',
			margins : '0 0 0 0',
            split: true,
            width: 212,
            border : true,
            enableDD : false,
            collapsible : true,
			rootVisible: false
			
        });
        this.callParent(arguments);
    }
});

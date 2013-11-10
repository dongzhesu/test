Ext.define('Test.view.ContentPanel', {
    extend: 'Ext.tab.Panel',
    initComponent : function(){
        Ext.apply(this,{
            id: 'contentPanel',
            region: 'center', 
            plain: false,
            defaults: {
               bodyPadding: 10
            },
            activeTab: 0,
            cls: 'contentpanel',
            plugins: [{
                ptype: 'tabscrollermenu'
            }]
        });
        this.callParent(arguments);
    }
});
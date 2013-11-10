Ext.define('Test.view.sub.StorageList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.storageList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'Storage Name'.i18n(),
        dataIndex: 'storage_name',
        align: 'center',
        flex: 1,
        minWidth: 100
    },{
        header: 'CreateTime'.i18n(),
        dataIndex: 'createdDate',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    }, {
        header: 'Last Modified Time'.i18n(),
        dataIndex: 'lastModifiedDate',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    }  ],

    menuAlarm: [],

    initComponent: function () {
        console.log("init component");
        Ext.apply(this, {
            title: Test.getTitle('Storage', 'Storage list'), 
            gridStore: Ext.create('Test.store.StorageStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '10';
        this.callParent(arguments);
    }
});

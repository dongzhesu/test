Ext.define('Test.view.sub.SalesList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.salesList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'Sales Name'.i18n(),
        dataIndex: 'sales_name',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Email Address'.i18n(),
        dataIndex: 'sales_email',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer: function(value){
        	return Ext.String.format('<a href="mailto:{0}">{1}</a>',value,value);
        }
    }, {
        header: 'Phone Number'.i18n(),
        dataIndex: 'sales_phone',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
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
    }],

    menuAlarm: [],

    initComponent: function () {
        console.log("init component");
        Ext.apply(this, {
            title: Test.getTitle('Sales', 'Sales list'), 
            gridStore: Ext.create('Test.store.SalesStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '200';
        this.callParent(arguments);
    }
})

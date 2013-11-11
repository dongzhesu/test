Ext.define('Test.view.sub.OrderList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.orderList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'Customer Name'.i18n(),
        dataIndex: 'customer_name',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Order Number'.i18n(),
        dataIndex: 'order_number',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Order Type'.i18n(),
        dataIndex: 'order_type',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Order Price'.i18n(),
        dataIndex: 'order_price',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    },{
        header: 'Remarks'.i18n(),
        dataIndex: 'remarks',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Order Date'.i18n(),
        dataIndex: 'order_date',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    },{
        header: 'CreateTime'.i18n(),
        dataIndex: 'createdDate',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    },{
        header: 'Delivery Time'.i18n(),
        dataIndex: 'delivery_date',
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
            title: Test.getTitle('Order', 'Order list'), 
            gridStore: Ext.create('Test.store.OrderStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '10';
        this.callParent(arguments);
    }
});

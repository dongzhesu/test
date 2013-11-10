Ext.define('Test.view.sub.BookingList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.bookingList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'Product Name'.i18n(),
        dataIndex: 'product_name',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Order Number'.i18n(),
        dataIndex: 'order_number',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Booking Price'.i18n(),
        dataIndex: 'booking_price',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Unit Price'.i18n(),
        dataIndex: 'unit_price',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Booking Qty'.i18n(),
        dataIndex: 'booking_qty',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Discount'.i18n(),
        dataIndex: 'discount',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    },  {
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
            title: Test.getTitle('Booking', 'Booking list'), 
            gridStore: Ext.create('Test.store.BookingStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '10';
        this.callParent(arguments);
    }
})

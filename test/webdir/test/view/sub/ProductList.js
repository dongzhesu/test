Ext.define('Test.view.sub.ProductList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.productList',
    toolbarItems: [],
    store: 'Products',
    gridColumns: [ {
        header: 'Product Name'.i18n(),
        dataIndex: 'product_name',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Product Code'.i18n(),
        dataIndex: 'product_code',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Product Type'.i18n(),
        dataIndex: 'product_type',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Product Country'.i18n(),
        dataIndex: 'product_country',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Year'.i18n(),
        dataIndex: 'product_year',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Retail Price'.i18n(),
        dataIndex: 'product_price',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Trade Price'.i18n(),
        dataIndex: 'product_price2',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Qty'.i18n(),
        dataIndex: 'product_qty',
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
            title: Test.getTitle('Product', 'Product info list'), 
            gridStore: Ext.create('Test.store.Products'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '200';
        this.callParent(arguments);
    }
});

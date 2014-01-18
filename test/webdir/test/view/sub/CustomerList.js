Ext.define('Test.view.sub.CustomerList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.customerList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'Customer Number'.i18n(),
        dataIndex: 'customer_number',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Customer Name'.i18n(),
        dataIndex: 'customer_name',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'Contact'.i18n(),
        dataIndex: 'customer_contact',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Customer Type'.i18n(),
        dataIndex: 'customer_type',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer: function(value){
        	if(value=='1')
        		return 'Retail(A)';
        	if(value=='2')
        		return 'Retail(B)';
        	if(value=='3')
        		return 'Retail(C)';
        	if(value=='4')
        		return 'Trade(A)';
        	if(value=='5')
        		return 'Trade(B)';
        	if(value=='6')
        		return 'Trade(C)';
        	return value;	
        }
    }, {
        header: 'Payment type'.i18n(),
        dataIndex: 'payment_type',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Phone Number'.i18n(),
        dataIndex: 'customer_phone',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Fax Number'.i18n(),
        dataIndex: 'customer_fax',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    }, {
        header: 'Email Address'.i18n(),
        dataIndex: 'customer_email',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer: function(value){
        	return Ext.String.format('<a href="mailto:{0}">{1}</a>',value,value);
        }
    }, {
        header: 'Address'.i18n(),
        dataIndex: 'customer_address',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
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
    }],

    menuAlarm: [],

    initComponent: function () {
        console.log("init component");
        Ext.apply(this, {
            title: Test.getTitle('Customer', 'Customer list'), 
            gridStore: Ext.create('Test.store.CustomerStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '10';
        this.callParent(arguments);
    }
});


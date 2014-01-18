Ext.define('Test.view.edit.Customer', {
    extend: 'Ext.window.Window',
    alias: 'widget.customerEdit',
    title: 'Edit Customer',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'hiddenfield',
                name : 'customer_id'
            },
//            {
//                xtype: 'hiddenfield',
//                name : 'sales_id'
//            },
            {
                xtype: 'textfield',
                name : 'customer_number',
                fieldLabel: 'Customer Number',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name : 'customer_name',
                fieldLabel: 'Customer Name',
                allowBlank: false
            },
            {
                xtype: 'combo',
                name : 'sales_id',
                fieldLabel: 'Sales',
                editable: false,
                emptyText: 'Please select',
                store: Ext.create('Test.store.SalesStore').load(),
                valueField: 'sales_id',
                displayField: 'sales_name',
                allowBlank: false,
                
                listeners:{
                	expand: function(c){
                		this.store.load();
                	}
                }
            },
            {
                xtype: 'combobox',
                name : 'customer_type',
                fieldLabel: 'Type',
                valueField : 'value',
                displayField : 'name',
                store : user_type
            },
            {
                xtype: 'textfield',
                name : 'payment_type',
                fieldLabel: 'Payment Type'
            },
            {
                xtype: 'textfield',
                name : 'customer_contact',
                fieldLabel: 'Contact'
            },
            {
                xtype: 'textfield',
                name : 'customer_phone',
                fieldLabel: 'Phone'
            },
            {
                xtype: 'textfield',
                name : 'customer_fax',
                fieldLabel: 'Fax'
            },
            {
                xtype: 'textfield',
                name : 'customer_email',
                fieldLabel: 'Email'
            },
            {
                xtype: 'textfield',
                name : 'customer_address',
                fieldLabel: 'Address'
            }]
        }];
        this.buttons = [{
            text: 'Save',
			action: 'save'
        }, {
            text: 'Cancel',
			action: 'cancel',
			scope: this,
			handler: this.close
        }];
        this.callParent(arguments);
    },

    loadRecord: function(rec,isNew){
       var form = this.down('form');
	    console.log('hello 1');
       form.loadRecord(rec);
       if(isNew){
       	  form.getForm().findField('customer_name').enable();
       }
       else{
       	  form.getForm().findField('customer_name').disable();
       }
       form.getForm().clearInvalid();
    },
    getRecord: function(){
    	var form   = this.down('form'),
            record = form.getRecord(),
            values = form.getValues();
	    record.set(values);
	    return record;
    }
});

//The data store containing the list of user type
var user_type = Ext.create('Ext.data.Store', {
    fields: ['value', 'name'],
    data : [
        {"value":"1", "name":"Retail(A)"},
        {"value":"2", "name":"Retail(B)"},
        {"value":"3", "name":"Retail(C)"},
        {"value":"4", "name":"Trade(A)"},
        {"value":"5", "name":"Trade(B)"},
        {"value":"6", "name":"Trade(C)"}
    ]
});

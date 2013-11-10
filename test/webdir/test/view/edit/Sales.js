Ext.define('Test.view.edit.Sales', {
    extend: 'Ext.window.Window',
    alias: 'widget.salesEdit',
    title: 'Edit Sales',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'hiddenfield',
                name : 'sales_id'
            },
            {
                xtype: 'textfield',
                name : 'sales_name',
                fieldLabel: 'Name',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name : 'sales_email',
                fieldLabel: 'Email'
            },
            {
                xtype: 'textfield',
                name : 'sales_phone',
                fieldLabel: 'Phone',
                allowBlank: false
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
       	  form.getForm().findField('sales_name').enable();
       }
       else{
       	  form.getForm().findField('sales_name').disable();
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

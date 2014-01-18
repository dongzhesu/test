Ext.define('Test.view.edit.Product', {
    extend: 'Ext.window.Window',
    alias: 'widget.productEdit',
    title: 'Edit Product',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'hiddenfield',
                name : 'product_id'
            },
            {
                xtype: 'textfield',
                name : 'product_name',
                fieldLabel: 'Product Name',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name : 'product_code',
                fieldLabel: 'Product Code',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name : 'product_type',
                fieldLabel: 'Product Type'
            },
            {
                xtype: 'textfield',
                name : 'product_country',
                fieldLabel: 'Product Country'
            },
            {
                xtype: 'textfield',
                name : 'product_year',
                fieldLabel: 'Year'
            },
            {
                xtype: 'textfield',
                name : 'product_price',
                fieldLabel: 'Retail Price'
            },
            {
                xtype: 'textfield',
                name : 'product_price2',
                fieldLabel: 'Trade Price'
            },
            {
                xtype: 'textfield',
                name : 'product_qty',
                fieldLabel: 'Qty'
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
       	  form.getForm().findField('product_name').enable();
       }
       else{
       	  form.getForm().findField('product_name').disable();
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

Ext.define('Test.view.edit.Booking', {
    extend: 'Ext.window.Window',
    alias: 'widget.bookingEdit',
    title: 'Edit Booking',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [
			{
			    xtype: 'combo',
			    name : 'order_id',
			    fieldLabel: 'Order Number',
			    editable: false,
			    emptyText: 'Please select',
			    store: Ext.create('Test.store.OrderStore').load(),
			    valueField: 'order_id',
			    displayField: 'order_number',
			    allowBlank: false,
			    
			    listeners:{
			    	expand: function(c){
			    		this.store.load();
			    	}
			    }
			},
//			{
//			    xtype: 'combo',
//			    name : 'product_id',
//			    fieldLabel: 'Product Name',
//			    editable: false,
//			    emptyText: 'Please select',
//			    store: Ext.create('Test.store.Products'),
//			    valueField: 'product_id',
//			    displayField: 'product_name',
//			    allowBlank: false,
//			    
//			    listeners:{
//			    	expand: function(c){
//			    		this.store.load();
//			    	}
//			    }
//			},
			{
			    xtype: 'combo',
			    name : 'product_id',
			    fieldLabel: 'Product Name',
			    emptyText: 'Please select',
			    displayField: 'product_name',
			    store: Ext.create('Test.store.Products').load(),
			    queryMode: 'local',
			    typeAhead: true,
			    valueField: 'product_id',
			    allowBlank: false
			    
			},
            {
                xtype: 'textfield',
                name : 'booking_price',
                fieldLabel: 'Price'
            },
            {
                xtype: 'textfield',
                name : 'unit_price',
                fieldLabel: 'Unit Price'
            },
            {
                xtype: 'textfield',
                name : 'booking_qty',
                fieldLabel: 'Qty'
            },
            {
                xtype: 'textfield',
                name : 'discount',
                fieldLabel: 'Discount'
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
       form.loadRecord(rec);
//       if(isNew){
//       	  form.getForm().findField('order_id').enable();
//       }
//       else{
//       	  form.getForm().findField('order_id').disable();
//       }
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

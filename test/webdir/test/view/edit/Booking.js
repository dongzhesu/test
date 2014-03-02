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
			    disabled: true,
			    listeners:{
			    	expand: function(c){
			    		this.store.load();
			    	}
			    }
			},
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
			    allowBlank: false,
			    disabled: true
			},
            {
                xtype: 'textfield',
                name : 'booking_price',
                itemId: 'tprice',
                fieldLabel: 'Price'
            },
            {
                xtype: 'textfield',
                name : 'unit_price',
                itemId: 'unit_price',
                fieldLabel: 'Unit Price',
                listeners:{
					change: function(field, newValue, oldValue, eOpts ){
						
						//get quantity
						var bookingQty = field.up('container').down('#booking_qty');
						var quantity = bookingQty.getValue();
						
						//get discount
						var disc = field.up('container').down('#discount');
						var discount = disc.getValue();
						
						if(quantity!=null&&quantity!=''&&discount!=null&&discount!=''){
							var newPrice = (parseFloat(quantity)*parseFloat(newValue))-(parseFloat(quantity)*parseFloat(newValue)*parseFloat(discount)/100);
							newPrice.toFixed(2);
							
							//set total price
							var tprice = field.up('container').down('#tprice');
							tprice.setValue(newPrice);
						}
					}
				}
            },
            {
                xtype: 'textfield',
                name : 'booking_qty',
                itemId: 'booking_qty',
                fieldLabel: 'Qty',
                listeners:{
					change: function(field, newValue, oldValue, eOpts ){
						
						//get unit price
						var uprice = field.up('container').down('#unit_price');
						var unitPrice = uprice.getValue();
						
						//get discount
						var disc = field.up('container').down('#discount');
						var discount = disc.getValue();
						
						if(unitPrice!=null&&unitPrice!=''&&discount!=null&&discount!=''){
							var newPrice = (parseFloat(unitPrice)*parseFloat(newValue))-(parseFloat(unitPrice)*parseFloat(newValue)*parseFloat(discount)/100);
							newPrice.toFixed(2);
							
							//set total price
							var tprice = field.up('container').down('#tprice');
							tprice.setValue(newPrice);
						}
					}
				}
            },
            {
                xtype: 'numberfield',
                name : 'discount',
                allowNegative: false,
                minValue: 0,
                maxValue: 99.99,
                itemId: 'discount',
                fieldLabel: 'Discount',
                listeners:{
					change: function(field, newValue, oldValue, eOpts ){
						
						//get unit price
						var uprice = field.up('container').down('#unit_price');
						var unitPrice = uprice.getValue();
						
						//get quantity
						var bookingQty = field.up('container').down('#booking_qty');
						var quantity = bookingQty.getValue();
						
						if(unitPrice!=null&&unitPrice!=''&&quantity!=null&&quantity!='') {
							var newPrice = (parseFloat(unitPrice)*parseFloat(quantity))-(parseFloat(unitPrice)*parseFloat(quantity)*parseFloat(newValue)/100);
							newPrice.toFixed(2);
							
							//set total price
							var tprice = field.up('container').down('#tprice');
							tprice.setValue(newPrice);
						}
					}
				}
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

Ext.define('Test.view.edit.Order', {
	extend: 'Ext.window.Window',
	alias: 'widget.orderEdit',
	title: 'Edit Order',
	layout: 'fit',
	closeAction: 'hide',
	initComponent: function () {
		this.setWidth(500);
		this.items = [{
			xtype: 'form',
			items: [{
				xtype:'fieldset',
				columnWidth:0.5,
				title:'Order Info',
				collapsible: true,
				defaultType:'textfield',
				defaults:{anchor:'100%'},
				layout:'anchor',
				items:[
				       {
				    	   xtype: 'hiddenfield',
				    	   name : 'order_id'
				       },
						{
							xtype: 'combo',
							name : 'customer_id',
							fieldLabel: 'Customer Name',
							editable: false,
							emptyText: 'Please select',
							store: Ext.create('Test.store.CustomerStore'),
							valueField: 'customer_id',
							displayField: 'customer_name',
							allowBlank: false,
						
							listeners:{
								expand: function(c){
									this.store.load();
								}
							}
						},
						{
							xtype: 'textfield',
							name : 'order_number',
							fieldLabel: 'Order Number',
							allowBlank: false
						},
						{
							xtype: 'textfield',
							name : 'order_type',
							fieldLabel: 'Order Type',
							allowBlank: false
						},
						{
							xtype: 'textfield',
							name : 'order_price',
							fieldLabel: 'Order Price'
						},
						{
							xtype: 'textfield',
							name : 'remarks',
							fieldLabel: 'Remarks'
						}
						]			
				}
			]
		}];
		this.buttons = [{
			text: 'Save',
			action: 'save'
		}, {
			text: 'Cancel',
			action: 'cancel',
			scope: this,
			handler: this.close
		},{
			text: 'Add Booking',
			action: 'add',
			scope: this,
			handler: this.addEmptyBooking
		},{
			text: 'Remove Booking',
			action: 'remove',
			scope: this,
			handler: this.removeBooking
		}];
		this.callParent(arguments);
	},

	addBooking: function(booking){
		var form = this.down('form');
		form.add([{
			xtype:'fieldset',
			title:'Booking Info',
			collapsible: true,
			columnWidth:0.5,
			defaultType:'textfield',
			defaults:{anchor:'100%'},
			layout:'anchor',
			items:[
			       {
						xtype: 'combo',
						name : 'product_id',
						fieldLabel: 'Product Name',
						editable: false,
						emptyText: 'Please select',
						store: Ext.create('Test.store.Products'),
						valueField: 'product_id',
						displayField: 'product_name',
						allowBlank: false,
						value: booking.get('product_id'),
						listeners:{
							expand: function(c){
								this.store.load();
							}
						}
					},

					{
						xtype: 'textfield',
						name : 'booking_price',
						value: booking.get('booking_price'),
						fieldLabel: 'Price'
					},
					{
						xtype: 'textfield',
						name : 'unit_price',
						value: booking.get('unit_price'),
						fieldLabel: 'Unit Price'
					},
					{
						xtype: 'textfield',
						name : 'booking_qty',
						value: booking.get('booking_qty'),
						fieldLabel: 'Qty'
					},
					{
						xtype: 'textfield',
						value: booking.get('discount'),
						name : 'discount',
						fieldLabel: 'Discount'
					}
					]			
			}]);
	},
	
	addEmptyBooking: function(){
		var form = this.down('form');
		form.add([{
			xtype:'fieldset',
			title:'Booking Info',
			collapsible: true,
			columnWidth:0.5,
			defaultType:'textfield',
			defaults:{anchor:'100%'},
			layout:'anchor',
			items:[
//			       {
//						xtype: 'combo',
//						name : 'product_id',
//						fieldLabel: 'Product Name',
//						editable: false,
//						emptyText: 'Please select',
//						store: Ext.create('Test.store.Products'),
//						valueField: 'product_id',
//						displayField: 'product_name',
//						allowBlank: false,
//						listeners:{
//							expand: function(c){
//								this.store.load();
//							}
//						}
//					},
			       {
						xtype: 'combo',
						name : 'product_id',
						fieldLabel: 'Product Name',
			    		emptyText: 'Please select',
						displayField: 'product_name',
						store: Ext.create('Test.store.Products').load(),
						valueField: 'product_id',
						queryMode: 'local',
						typeAhead: true,
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
					}
					]			
			}]);
	},
	
	removeBooking: function(){
		var form = this.down('form');
		var fieldset = form.down('fieldset').nextSibling('fieldset');
		 while(fieldset){
			 var toRemove = fieldset;
			 fieldset=fieldset.nextSibling('fieldset');
			 form.remove(toRemove);
		 }
	},
	
	loadRecord: function(rec,isNew){
		var form = this.down('form');
		form.loadRecord(rec);
		if(isNew){
			form.getForm().findField('order_number').enable();
		}
		else{
			form.getForm().findField('order_number').disable();
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

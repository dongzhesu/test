Ext.define('Test.view.edit.Order', {
	extend: 'Ext.window.Window',
	alias: 'widget.orderEdit',
	title: 'Edit Order',
	layout: 'fit',
	closeAction: 'hide',
	customertype: 'Retail',
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
							//editable: false,
							emptyText: 'Please select',
							store: Ext.create('Test.store.CustomerStore').load(),
							valueField: 'customer_id',
							displayField: 'customer_name',
							allowBlank: false,

							queryMode: 'local',
							typeAhead: true,
							
							listeners:{
								select: function(combo, records, eOpts){
								var ctype=	records[0].get('customer_type');
								if(ctype=='4'||ctype=='5'||ctype=='6'){
									combo.up('window').customertype = 'Trade';
								}else
									combo.up('window').customertype = 'Retail';
								},
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
							itemId : 'odprice',
							fieldLabel: 'Order Price'
						},
						{
        					xtype: 'datefield',
        					anchor: '100%',
					        fieldLabel: 'Delivery Date',
					        name: 'delivery_date',
        					format: 'm-d-Y'
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
						fieldLabel: 'Product Code',
						//editable: false,
						emptyText: 'Please select',
						displayField: 'product_code',
						store: Ext.create('Test.store.Products').load(),
						valueField: 'product_id',
						allowBlank: false,
						
						queryMode: 'local',
						typeAhead: true,

						value: booking.get('product_id'),
						listeners:{
							select: function(combo, records, eOpts){
								var uprice = combo.up('container').down('#uprice');
								var ori_uprice = combo.up('container').down('#ori_uprice');
								var type_price = records[0].get('product_price');
								if(combo.up('window').customertype=='Trade')
									type_price = records[0].get('product_price2');
								uprice.setValue(type_price);
								ori_uprice.setValue(type_price);
							},
							expand: function(c){
								this.store.load();
							}
						}
					},
					{
						xtype: 'textfield',
						name : 'booking_price',
						itemId: 'tprice',
						value: booking.get('booking_price'),
						fieldLabel: 'Amount',
						allowBlank: false,
						listeners:{
							change: function(field, newValue, oldValue, eOpts ){
								
								var form = field.up('container').up('container');
								var order = form.down('fieldset');
								var orderPrice=order.down('#odprice');
								
								var booking = order.nextSibling('fieldset');
								var newPrice = 0.0;
								
								//get unit quantity
								var Qty = field.up('container').down('#Qty');
								var quantity = Qty.getValue();

								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								if (field.value.length>2&&quantity!=null&&quantity!=''&&unitPrice!=null&&unitPrice!='') {
									var originalTotalPrice = parseFloat(unitPrice)*parseFloat(quantity);
									var newDiscount = ((originalTotalPrice-parseFloat(newValue)) / originalTotalPrice) * 100;
									newDiscount.toFixed(0);
									
									//set discount
									var discField = field.up('container').down('#disc');
									discField.setValue(newDiscount);
								}
								
								while(booking){
									var tprice = booking.down('#tprice').getValue();
									if(tprice==null||tprice=='')
										break;
									newPrice = newPrice + parseFloat(tprice);	
									booking = booking.nextSibling('fieldset');
								}
								
								if(booking==null){
									orderPrice.setValue(newPrice);
								}
								
							}
						}
					},
					{
						xtype : 'fieldcontainer',
						name : 'unit_price_container',
						border : true,
						layout : 'hbox',
						fieldLabel: 'Unit Price',
						defaultType : 'field',
						items : [
									{
										xtype: 'textfield',
										name : 'unit_price',
										itemId: 'uprice',
										value: booking.get('unit_price'),
										width: '85%',
										listeners: {
											change: function(field, newValue, oldValue, eOpts) {
												//get quantity
												var Qty = field.up('container').up('container').down('#Qty');
												var quantity = Qty.getValue();

												//get discount
												var disc = field.up('container').up('container').down('#disc');
												var discount = disc.getValue();

												if (quantity!=null&&quantity!=''&&discount!=null&&discount!='') {
													var newPrice = (parseFloat(quantity)*parseFloat(newValue))-(parseFloat(quantity)*parseFloat(newValue)*parseFloat(discount)/100);
													newPrice.toFixed(2);

													//set total price
													var tprice = field.up('container').up('container').down('#tprice');
													tprice.setValue(newPrice);
												}
											}
										}
									},
									{
										xtype : 'textfield',
										name : 'original_unit_price',
										itemId: 'ori_uprice',
										margin: '0 0 0 7',
										disabled: true,
										value: booking.get('unit_price'),
										width: '14%'
									}
						]
					},
					{
						xtype: 'numberfield',
						name : 'booking_qty',
						value: booking.get('booking_qty'),
						fieldLabel: 'Qty',
						allowNegative: false,
						allowDecimals: false,
		                minValue: 1,
						itemId: 'Qty',
						listeners:{
							change: function(field, newValue, oldValue, eOpts ){
								
								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								//get discount
								var disc = field.up('container').down('#disc');
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
						value: booking.get('discount'),
						name : 'discount',
						fieldLabel: 'Discount',
						itemId: 'disc',
						allowNegative: false,
						allowDecimals: false,
		                minValue: 0,
		                maxValue: 99,
						listeners:{
							change: function(field, newValue, oldValue, eOpts ){
								
								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								//get quantity
								var Qty = field.up('container').down('#Qty');
								var quantity = Qty.getValue();
								
								if(unitPrice!=null&&unitPrice!=''&&quantity!=null&&quantity!=''){
									var newPrice = (parseFloat(unitPrice)*parseFloat(quantity))-(parseFloat(unitPrice)*parseFloat(quantity)*parseFloat(newValue)/100);
									newPrice.toFixed(2);
									
									//set total price
									var tprice = field.up('container').down('#tprice');
									tprice.setValue(newPrice);
								}
							}
						}
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
			       {
						xtype: 'combo',
						name : 'product_id',
						fieldLabel: 'Product Code',
			    		emptyText: 'Please select',
						displayField: 'product_code',
						store: Ext.create('Test.store.Products').load(),
						valueField: 'product_id',
						queryMode: 'local',
						typeAhead: true,
						allowBlank: false,
						listeners:{
							select: function(combo, records, eOpts){
								var uprice = combo.up('container').down('#uprice');
								var ori_uprice = combo.up('container').down('#ori_uprice');
								var type_price = records[0].get('product_price');
								if(combo.up('window').customertype=='Trade')
									type_price = records[0].get('product_price2');
								uprice.setValue(type_price);
								ori_uprice.setValue(type_price);
							},
							expand: function(c){
								this.store.load();
							}
						}
					},
					{
						xtype: 'textfield',
						name : 'booking_price',
						itemId: 'tprice',
						fieldLabel: 'Amount',
						allowBlank: false,
						listeners:{
							change: function(field, newValue, oldValue, eOpts ) {
								var form = field.up('container').up('container');
								var order = form.down('fieldset');
								var orderPrice=order.down('#odprice');
								
								var booking = order.nextSibling('fieldset');
								var newPrice = 0.0;
								
								//get unit quantity
								var Qty = field.up('container').down('#Qty');
								var quantity = Qty.getValue();

								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								if (field.value.length>2&&quantity!=null&&quantity!=''&&unitPrice!=null&&unitPrice!='') {
									var originalTotalPrice = parseFloat(unitPrice)*parseFloat(quantity);
									var newDiscount = ((originalTotalPrice-parseFloat(newValue)) / originalTotalPrice) * 100;
									newDiscount.toFixed(0);
									
									//set discount
									var discField = field.up('container').down('#disc');
									discField.setValue(newDiscount);
								}
								while(booking){
									var tprice = booking.down('#tprice').getValue();
									if (tprice==null||tprice=='') break;
									newPrice = newPrice + parseFloat(tprice);
									booking = booking.nextSibling('fieldset');
								}
								
								if(booking==null){
									orderPrice.setValue(newPrice);
								}
							}
						}
					},
					{
						xtype : 'fieldcontainer',
						name : 'unit_price_container',
						border : true,
						layout : 'hbox',
						fieldLabel: 'Unit Price',
						defaultType : 'field',
						items : [
									{
										xtype: 'textfield',
										name : 'unit_price',
										itemId: 'uprice',
										width: '85%',
										listeners: {
											change: function(field, newValue, oldValue, eOpts) {
												//get quantity
												var Qty = field.up('container').up('container').down('#Qty');
												var quantity = Qty.getValue();

												//get discount
												var disc = field.up('container').up('container').down('#disc');
												var discount = disc.getValue();

												if (quantity!=null&&quantity!=''&&discount!=null&&discount!='') {
													var newPrice = (parseFloat(quantity)*parseFloat(newValue))-(parseFloat(quantity)*parseFloat(newValue)*parseFloat(discount)/100);
													newPrice.toFixed(2);

													//set total price
													var tprice = field.up('container').up('container').down('#tprice');
													tprice.setValue(newPrice);
												}
											}
										}
									},
									{
										xtype : 'textfield',
										name : 'original_unit_price',
										itemId: 'ori_uprice',
										margin: '0 0 0 7',
										disabled: true,
										width: '14%'
									}
						]
					},
					{
						xtype: 'numberfield',
						allowNegative: false,
						allowDecimals: false,
		                minValue: 1,
						name : 'booking_qty',
						fieldLabel: 'Qty',
						itemId: 'Qty',
						listeners:{
							change: function(field, newValue, oldValue, eOpts ){
								
								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								//get discount
								var disc = field.up('container').down('#disc');
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
						fieldLabel: 'Discount',
						itemId: 'disc',
						allowNegative: false,
						allowDecimals: false,
		                minValue: 0,
		                maxValue: 99,
						listeners:{
							change: function(field, newValue, oldValue, eOpts ){
								
								//get unit price
								var uprice = field.up('container').down('#uprice');
								var unitPrice = uprice.getValue();
								
								//get quantity
								var Qty = field.up('container').down('#Qty');
								var quantity = Qty.getValue();
								
								if(unitPrice!=null&&unitPrice!=''&&quantity!=null&&quantity!=''){
									var newPrice = (parseFloat(unitPrice)*parseFloat(quantity))-(parseFloat(unitPrice)*parseFloat(quantity)*parseFloat(newValue)/100);
									newPrice.toFixed(2);
									
									//set total price
									var tprice = field.up('container').down('#tprice');
									tprice.setValue(newPrice);
								}
							}
						}
					}
					]			
			}]);
	},
	
	getTotalPrice: function(){
		
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

Ext.define('Test.controller.sub.OrderController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'orderList',
        selector: 'orderList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addOrder': {
                click: this.onAddOrderClick
            },
            '#editOrder': {
                click: this.onEditOrderClick
            },
            '#deleteOrder': {
                click: this.onDeleteOrderClick
            },
            '#printPDF': {
                click: this.onPrintPDFClick
            },
            '#printExcel': {
                click: this.onPrintExcelClick
            },
    		'orderEdit button[action=save]': {
    			click: this.updateOrder
    		}
        });
		this.isReady=true;
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.OrderList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Order');
		    console.log('Test.view.edit.Order created...');
		}
		return this.editWin;
	},
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	
	onAddOrderClick: function (btn) {
	    var win = this.getEditWin();
        win.removeBooking();
		win.setTitle(Test.getTitle('Order','Add'));
		var rec = Ext.create('Test.model.Order');
		
		win.loadRecord(rec, true);
		win.show();
    },
    
    onPrintPDFClick: function (btn) {
    	var grid = this.getOrderList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if (selection && selection.length>0) {
			var serverPath = window.location.href;
			var serverPathIndex = serverPath.lastIndexOf("/");
			var order_id = selection[0].get('order_id');
			console.log(order_id);
			window.open(serverPath.substring(0, serverPathIndex)+'/OD/'+order_id+'/pdf.html');
//			Ext.Ajax.request({
//				url: 'http://localhost:8080/ERP_demo/OD/pdf.html',
//				method:'GET',
//				params: {order_id: order_id},
//				success: function (response){
//					var serverResponse = response.responseText;
//					console.log(serverResponse);
//				}
//			
//			});
			}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Order'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
        onPrintExcelClick: function (btn) {
    	var grid = this.getOrderList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var order_id = selection[0].get('order_id');
			console.log(order_id);
			window.open('/ERP_demo/OD/'+order_id+'/xls.html');
			}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Order'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onEditOrderClick: function (btn) {
    	var grid = this.getOrderList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
            win.removeBooking();
			win.setTitle(Test.getTitle('Order','Edit') + ' - ['+selection[0].get('order_number')+']');
			win.loadRecord(selection[0], true);
			var bookingStore = Ext.create('Test.store.BookingStore');
			var order_id = selection[0].get('order_id');
			bookingStore.load({
				scope: this,
				callback: function(records,operation,success){
					Ext.Array.each(records,function(v,i,a){
						var order_id_booking=v.get('order_id');
						if(order_id==order_id_booking){
							win.addBooking(v);
						}
					});
				}
			});
			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Order'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteOrderClick: function (btn) {
    	var grid = this.getOrderList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
        if(selection && selection.length>0){
			Ext.MessageBox.confirm(
					Test.getConfirmTitle('Delete'),
					Test.getConfirmContent('delete','order'),
					function(r){
						if(r=='yes'){
					        var store = grid.getStore();
					        store.remove(selection);
					        store.sync();							
						}
					}
			);
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('order'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    updateOrder: function(button) {
    	 var me = this;
    	 var win = button.up( 'window');
         var form= win.down( 'form');
         var record = form.getRecord();
         var values = form.getValues();

         record.set(values);
         if(form.getForm().isValid()){
             var record = win.getRecord();
             record.save(
                		{callback: function(rec,operation){
                			if(operation.error){
                				Ext.MessageBox.show({
                					title: 'Error',
                					msg: operation.error,
                					buttons: Ext.MessageBox.OK,
                					icon: Ext.MessageBox.ERROR
                				});
                			}
                			var grid = me.getOrderList().down('grid' );
                			grid.getStore().load();
                	        var orderId=Ext.JSON.decode(operation.response.responseText).orderId;
                            var fieldset = form.down('fieldset').nextSibling('fieldset');
                            while(fieldset){
                           	 var product_id = '';
                           	 var booking_price = '';
                           	 var unit_price = '';
                           	 var booking_qty = '';
                           	 var discount = '';
                           	 
                           	 var product_id_EL = fieldset.down('combo');
                           	 var nextEL=product_id_EL;
                           	 while(nextEL){
                           		 if(nextEL.name=='product_id')
                           			 product_id=nextEL.getValue();
                           		 if(nextEL.name=='booking_price')
                           			 booking_price=nextEL.getValue();
                           		 if(nextEL.name=='unit_price')
                           			 unit_price=nextEL.getValue();
                           		 if(nextEL.name=='booking_qty')
                           			 booking_qty=nextEL.getValue();
                           		 if(nextEL.name=='discount')
                           			 discount=nextEL.getValue();
                           		 nextEL=nextEL.nextSibling();
                           	 };
                           	 
                           	 var bookingRec = Ext.create('Test.model.Booking',
                           			 {
                           		 		order_id:orderId,
                           		 		product_id:product_id,
                           		 		booking_price:booking_price,
                           		 		unit_price:unit_price,
                           		 		booking_qty:booking_qty,
                           		 		discount:discount
                           		 		
                           		 });
                           	 bookingRec.save();
                           	 
                           	 fieldset=fieldset.nextSibling('fieldset');
                            }
                            
                            win.removeBooking();
                         	win.close();
                		}}
             	);

           }
    },
    
    actionItems: [
              	Ext.create('Ext.Action',{
                  	text: 'Add Order'.i18n(),
                  	iconCls: 'btn-add',
          			itemId: 'addOrder'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Edit'.i18n(),
                  	iconCls: 'btn-edit',
          			itemId: 'editOrder'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Delete'.i18n(),
                  	iconCls: 'btn-delete',
          			itemId: 'deleteOrder'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Print Invoice(PDF)'.i18n(),
                  	iconCls: 'btn-pdf',
          			itemId: 'printPDF'
              	})
//              	,
//              	Ext.create('Ext.Action',{
//                  	text: 'Print Invoice(Excel)'.i18n(),
//                  	iconCls: 'btn-excel',
//          			itemId: 'printExcel'
//              	})
              ]
	
});
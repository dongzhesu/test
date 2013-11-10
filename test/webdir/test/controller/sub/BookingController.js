Ext.define('Test.controller.sub.BookingController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'bookingList',
        selector: 'bookingList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addBooking': {
                click: this.onAddBookingClick
            },
            '#editBooking': {
                click: this.onEditBookingClick
            },
            '#deleteBooking': {
                click: this.onDeleteBookingClick
            },
    		'bookingEdit button[action=save]': {
    			click: this.updateBooking
    		}
        });
		this.isReady=true;
		this.callParent(arguments);
    },
    
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Booking');
		    console.log('Test.view.edit.Booking created...');
		}
		return this.editWin;
	},
	
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.BookingList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	
	onAddBookingClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('Booking','Add'));
		var rec = Ext.create('Test.model.Booking');
		
		win.loadRecord(rec, true);
		win.show();
    },
    
    onEditBookingClick: function (btn) {
    	var grid = this.getBookingList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('Booking','Edit') + ' - ['+selection[0].get('order_name')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Booking'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteBookingClick: function (btn) {
    	var grid = this.getBookingList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
        if(selection && selection.length>0){
			Ext.MessageBox.confirm(
					Test.getConfirmTitle('Delete'),
					Test.getConfirmContent('delete','booking'),
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
	            msg: Test.getSelectRemindText('booking'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}

    },
    
    updateBooking: function(button) {
    	 var me = this;
    	 var win = button.up( 'window');
         var form= win.down( 'form');
         var record = form.getRecord();
         var values = form.getValues();

         record.set(values);
         
         if(form.getForm().isValid()){
             var record = win.getRecord();
             record.save(
                		{callback: function(response,a){
                  if(a.error){
                      Ext.MessageBox.show({
                          title: 'Error',
                          msg: a.error,
                          buttons: Ext.MessageBox.OK,
                          icon: Ext.MessageBox.ERROR
                     });
                 }
                var grid = me.getBookingList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
    
    actionItems: [
              	Ext.create('Ext.Action',{
                  	text: 'Add Booking'.i18n(),
                  	iconCls: 'btn-add',
          			itemId: 'addBooking'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Edit'.i18n(),
                  	iconCls: 'btn-edit',
          			itemId: 'editBooking',
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Delete'.i18n(),
                  	iconCls: 'btn-delete',
          			itemId: 'deleteBooking',
              	})
              ]
	
});
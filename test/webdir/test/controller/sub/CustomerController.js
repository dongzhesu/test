Ext.define('Test.controller.sub.CustomerController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'customerList',
        selector: 'customerList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addCustomer': {
                click: this.onAddCustomerClick
            },
            '#editCustomer': {
                click: this.onEditCustomerClick
            },
            '#deleteCustomer': {
                click: this.onDeleteCustomerClick
            },
    		'customerEdit button[action=save]': {
    			click: this.updateCustomer
    		}
        });
		this.isReady=true;
		console.log('Test.controller.sub.CustomerController init...');
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.CustomerList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Customer');
		    console.log('Test.view.edit.Customer created...');
		}
		return this.editWin;
	},
	
	onViewRendered: function(view){
	    view.gridStore.load();
	},

	onAddCustomerClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('Customer','Add'));
		var rec = Ext.create('Test.model.Customer');
		
		win.loadRecord(rec, true);
		win.show();
    },
    
    onEditCustomerClick: function (btn) {
    	var grid = this.getCustomerList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('Customer','Edit') + ' - ['+selection[0].get('customer_name')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('customer'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteCustomerClick: function (btn) {
    	var grid = this.getCustomerList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
        if(selection && selection.length>0){
			Ext.MessageBox.confirm(
					Test.getConfirmTitle('Delete'),
					Test.getConfirmContent('delete','customer'),
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
	            msg: Test.getSelectRemindText('customer'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}

    },
    
    updateCustomer: function(button) {
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
                var grid = me.getCustomerList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
    
    actionItems: [
              	Ext.create('Ext.Action',{
                  	text: 'Add Customer'.i18n(),
                  	iconCls: 'btn-add',
          			itemId: 'addCustomer'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Edit'.i18n(),
                  	iconCls: 'btn-edit',
          			itemId: 'editCustomer',
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Delete'.i18n(),
                  	iconCls: 'btn-delete',
          			itemId: 'deleteCustomer',
              	})
              ]
});
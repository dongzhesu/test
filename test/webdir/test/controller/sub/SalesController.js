Ext.define('Test.controller.sub.SalesController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'salesList',
        selector: 'salesList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
        	'#addSales': {
                click: this.onAddClick
            },
            '#editSales': {
                click: this.onEditClick
            },
            '#deleteSales': {
                click: this.onDeleteClick
            },
    		'salesEdit button[action=save]': {
    			click: this.updateSales
    		}
        });
		this.isReady=true;
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.SalesList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Sales');
		    console.log('Test.view.edit.Sales created...');
		}
		return this.editWin;
	},
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	
	onAddClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('Sales','Add'));
		console.log('Test.controller.sub.TrafficStatController init...');
		var rec = Ext.create('Test.model.Sales');
		
		win.loadRecord(rec, true);
		win.show();
    },
    onEditClick: function (btn) {
    	var grid = this.getSalesList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('Sales','Edit') + ' - ['+selection[0].get('sales_name')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Sales'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteClick: function (btn) {
    	var grid = this.getSalesList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			Ext.MessageBox.confirm(
					Test.getConfirmTitle('Delete'),
					Test.getConfirmContent('delete','sales'),
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
	            msg: Test.getSelectRemindText('sales'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    updateSales: function(button) {
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
                var grid = me.getSalesList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
 
    actionItems: [
              	Ext.create('Ext.Action',{
                  	text: 'Add Sales'.i18n(),
                  	iconCls: 'btn-add',
          			itemId: 'addSales'
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Edit'.i18n(),
                  	iconCls: 'btn-edit',
          			itemId: 'editSales',
              	}),
              	Ext.create('Ext.Action',{
                  	text: 'Delete'.i18n(),
                  	iconCls: 'btn-delete',
          			itemId: 'deleteSales',
              	})
    ]
});
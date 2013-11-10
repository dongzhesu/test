Ext.define('Test.controller.sub.StorageController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'storageList',
        selector: 'storageList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addStorage': {
                click: this.onAddStorageClick
            },
            '#editStorage': {
                click: this.onEditStorageClick
            },
            '#deleteStorage': {
                click: this.onDeleteStorageClick
            },
    		'storageEdit button[action=save]': {
    			click: this.updateStorage
    		}
        });
		this.isReady=true;
		
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.StorageList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Storage');
		    console.log('Test.view.edit.Storage created...');
		}
		return this.editWin;
	},
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	
 
 
	onAddStorageClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('Storage','Add'));
		console.log('Test.controller.sub.TrafficStatController init...');
		var rec = Ext.create('Test.model.Storage');
		
		win.loadRecord(rec, true);
		win.show();
    },
    onEditStorageClick: function (btn) {
    	var grid = this.getStorageList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('Storage','Edit') + ' - ['+selection[0].get('storage_name')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('Storage'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteStorageClick: function (btn) {
    	var grid = this.getStorageList().down('grid');
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
    
    updateStorage: function(button) {
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
                var grid = me.getStorageList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
 
 
    actionItems: [
    	Ext.create('Ext.Action',{
        	text: 'Add Storage'.i18n(),
        	iconCls: 'btn-add',
			itemId: 'addStorage'
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Edit'.i18n(),
        	iconCls: 'btn-edit',
			itemId: 'editStorage',
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Delete'.i18n(),
        	iconCls: 'btn-delete',
			itemId: 'deleteStorage',
    	})
    ]
});
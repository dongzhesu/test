Ext.define('Test.controller.sub.UserController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'userList',
        selector: 'userList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addUser': {
                click: this.onAddUserClick
            },
            '#editUser': {
                click: this.onEditUserClick
            },
            '#deleteUser': {
                click: this.onDeleteUserClick
            },
    		'userEdit button[action=save]': {
    			click: this.updateUser
    		}
        });
		this.isReady=true;
		console.log('Test.controller.sub.UserController init...');
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.statList = Ext.create('Test.view.sub.UserList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.statList;
	},
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.User');
		    console.log('Test.view.edit.User created...');
		}
		return this.editWin;
	},
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	onAddUserClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('User','Add'));
		console.log('Test.controller.sub.TrafficStatController init...');
		var rec = Ext.create('Test.model.User');
		
		win.loadRecord(rec, true);
		win.show();
    },
    onEditUserClick: function (btn) {
    	var grid = this.getUserList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('User','Edit') + ' - ['+selection[0].get('user_login')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('User'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteUserClick: function (btn) {
    	var grid = this.getUserList().down('grid');
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
    
    updateUser: function(button) {
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
                var grid = me.getUserList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
 
 
    actionItems: [
    	Ext.create('Ext.Action',{
        	text: 'Add User'.i18n(),
        	iconCls: 'btn-add',
			itemId: 'addUser'
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Edit'.i18n(),
        	iconCls: 'btn-edit',
			itemId: 'editUser',
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Delete'.i18n(),
        	iconCls: 'btn-delete',
			itemId: 'deleteUser',
    	})
    ]
});
Ext.define('Test.controller.sub.ProductController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'productList',
        selector: 'productList'
    }],
    isReady: false,
    init: function (module) {
	    this.module= module;
        this.control({
            '#addProduct': {
                click: this.onAddProductClick
            },
            '#editProduct': {
                click: this.onEditProductClick
            },
            '#deleteProduct': {
                click: this.onDeleteProductClick
            },
    		'productEdit button[action=save]': {
    			click: this.updateProduct
    		}
        });
		this.isReady=true;
		this.callParent(arguments);
    },
	createView: function(vid, rec){
		var menu = Ext.create('Ext.menu.Menu',{
            items: this.actionItems
        });
		this.productList = Ext.create('Test.view.sub.ProductList', {id: vid,
																  menuItemRec: rec,
																  toolbarItems: this.actionItems, 
																  menuAlarm: menu
																  });
	    return this.productList;
	},
	
	getEditWin: function(){
		if(!this.editWin){
		    this.editWin = Ext.create('Test.view.edit.Product');
		    console.log('Test.view.edit.Product created...');
		}
		return this.editWin;
	},
	
	onViewRendered: function(view){
	    view.gridStore.load();
	},
	
	onAddProductClick: function (btn) {
	    var win = this.getEditWin();
		win.setTitle(Test.getTitle('Product','Add'));
		console.log('Test.controller.sub.TrafficStatController init...');
		var rec = Ext.create('Test.model.Product');
		
		win.loadRecord(rec, true);
		win.show();
    },
    onEditProductClick: function (btn) {
    	var grid = this.getProductList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
		if(selection && selection.length>0){
			var win = this.getEditWin();
			win.setTitle(Test.getTitle('Product','Edit') + ' - ['+selection[0].get('product_name')+']');
			win.loadRecord(selection[0], true);

			win.show();
		}
		else{
			Ext.MessageBox.show({
	            title: 'Info'.i18n(),
	            msg: Test.getSelectRemindText('product'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}
    },
    
    onDeleteProductClick: function (btn) {
    	var grid = this.getProductList().down('grid');
        var selection = grid.getSelectionModel().getSelection();
        
		if(selection && selection.length>0){
			Ext.MessageBox.confirm(
					Test.getConfirmTitle('Delete'),
					Test.getConfirmContent('delete','product'),
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
	            msg: Test.getSelectRemindText('product'),
	            buttons: Ext.MessageBox.OK,
	            icon: Ext.MessageBox.INFO
	        });
		}

    },
    
    updateProduct: function(button) {
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
                var grid = me.getProductList().down('grid' );
                     grid.getStore().load();
                }}
                		);
                win.close();
           }


    },
 
 
    actionItems: [
    	Ext.create('Ext.Action',{
        	text: 'Add Product'.i18n(),
        	iconCls: 'btn-add',
			itemId: 'addProduct'
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Edit'.i18n(),
        	iconCls: 'btn-edit',
			itemId: 'editProduct',
    	}),
    	Ext.create('Ext.Action',{
        	text: 'Delete'.i18n(),
        	iconCls: 'btn-delete',
			itemId: 'deleteProduct',
    	})
    ]
});
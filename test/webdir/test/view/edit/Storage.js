Ext.define('Test.view.edit.Storage', {
    extend: 'Ext.window.Window',
    alias: 'widget.storageEdit',
    title: 'Edit Storage',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'hiddenfield',
                name : 'storage_id'
            },
            {
                xtype: 'textfield',
                name : 'storage_name',
                fieldLabel: 'Storage Name',
                allowBlank: false
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
       if(isNew){
       	  form.getForm().findField('storage_name').enable();
       }
       else{
       	  form.getForm().findField('storage_name').disable();
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

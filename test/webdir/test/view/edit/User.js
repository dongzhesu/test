Ext.define('Test.view.edit.User', {
    extend: 'Ext.window.Window',
    alias: 'widget.userEdit',
    title: 'Edit User',
    layout: 'fit',
    closeAction: 'hide',
    initComponent: function () {
        this.items = [{
            xtype: 'form',
            items: [{
                xtype: 'hiddenfield',
                name : 'tuid'
            },
            {
                xtype: 'textfield',
                name : 'user_login',
                fieldLabel: 'Login Name',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name : 'user_password',
                fieldLabel: 'Password'
            },
            {
                xtype: 'combobox',
                name : 'user_language',
                fieldLabel: 'Language',
                valueField : 'value',
                displayField : 'name',
                store : user_languages
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
       	  form.getForm().findField('user_login').enable();
       }
       else{
       	  form.getForm().findField('user_login').disable();
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

//The data store containing the list of languages
var user_languages = Ext.create('Ext.data.Store', {
    fields: ['value', 'name'],
    data : [
        {"value":"en", "name":"English"},
        {"value":"zh_TW", "name":"Traditional Chinese"},
        {"value":"zh_CN", "name":"Simplified Chinese"}
    ]
});
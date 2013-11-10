Ext.define('AM.view.user.Edit', {
    extend: 'Ext.window.Window',
    alias: 'widget.useredit',
    title: 'Edit User',
    layout: 'fit',
    autoShow: true,
    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'hiddenfield',
                        name : 'user_id'
                    },
                    {
                        xtype: 'textfield',
                        name : 'user_login',
                        fieldLabel: 'User Login'
                    },
                    {
                        xtype: 'combobox',
                        name : 'user_language',
                        fieldLabel: 'User Language',
                        valueField : 'value',
                        displayField : 'name',
                        store : user_languages
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Save',
                action: 'save'
            },
            {
                text: 'Cancel',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
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
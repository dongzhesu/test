Ext.define('AM.model.User', {
    extend: 'Ext.data.Model',
    fields: ['user_id', 'user_login', 'user_language', 'createdDate', 'lastModifiedDate'],
    proxy: {
        type: 'ajax',
        api: {
            read: '004/userList.json',
            update: 'update.json'
        },
        reader: {
            type: 'json',
            root: 'users',
            successProperty: 'success'
        }
    }
});
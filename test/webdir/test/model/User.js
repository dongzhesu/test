Ext.define('Test.model.User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'tuid',  type: 'string'},     
        {name: 'user_login',  type: 'string'},
        {name: 'user_language',  type: 'string'},
        {name: 'user_password',  type: 'string'},
        {name: 'createdDate',  type: 'date'},
        {name: 'lastModifiedDate',  type: 'date'}
    ],
    
    proxy: {
        type: 'rest',
        api:{
        	read:'US/004/userList.json',
                update: 'US/update.json',
                create: 'US/create.json',
                destroy: 'US/delete.json'
        },
        
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'users',
            successProperty: 'success'
        }
    }
});
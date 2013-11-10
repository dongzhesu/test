Ext.define('Test.model.Storage', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'storage_id',  type: 'string'}
        ,{name: 'storage_name',  type: 'string'}
        ,{name: 'createdDate',  type: 'date'}
        ,{name: 'lastModifiedDate',  type: 'date'}
    ],
    proxy: {
        type: 'rest',
        api: {
            read: 'SR/004/storageList.json',
            update: 'SR/update.json',
            create: 'SR/create.json',
            destroy: 'SR/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success'
        }
    }
});
Ext.define('Test.model.Sales', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'sales_id',  type: 'string'},
        {name: 'sales_name',  type: 'string'},
        {name: 'sales_email',  type: 'string'},
        {name: 'sales_phone',  type: 'string'},
        {name: 'createdDate',  type: 'date'},
        {name: 'lastModifiedDate',  type: 'date'}
    ],
    

    proxy: {
        type: 'rest',
        api: {
            read: 'SL/004/salesList.json',
            update: 'SL/update.json',
            create: 'SL/create.json',
            destroy: 'SL/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success'
        }
    }
});
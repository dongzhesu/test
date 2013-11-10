Ext.define('Test.model.Customer', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'customer_id',  type: 'string'},
        
        {name: 'sales_id',  type: 'string'},
        
        {name: 'customer_name',  type: 'string'},
        {name: 'customer_type',  type: 'string'},
        {name: 'payment_type',  type: 'string'},
        {name: 'customer_contact',  type: 'string'},
        {name: 'customer_phone',  type: 'string'},
        {name: 'customer_fax',  type: 'string'},
        {name: 'customer_email',  type: 'string'},
        {name: 'customer_address',  type: 'string'},
        {name: 'createdDate',  type: 'date'},
        {name: 'lastModifiedDate',  type: 'date'}
    ],
    

    proxy: {
        type: 'rest',
        api: {
            read: 'CT/004/customerList.json',
            update: 'CT/update.json',
            create: 'CT/create.json',
            destroy: 'CT/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success'
        }
    }
});
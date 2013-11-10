Ext.define('Test.model.Order', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'order_id',  type: 'string'},
        {name: 'customer_name',  type: 'string'},
        {name: 'customer_id',  type: 'string'},
        {name: 'order_number',  type: 'string'},
        {name: 'order_price',  type: 'string'},
        {name: 'order_date',  type: 'date'},
        {name: 'createdDate',  type: 'date'},
        {name: 'lastModifiedDate',  type: 'date'}
    ],
    
    proxy: {
        type: 'rest',
        api: {
            read: 'OD/004/orderList.json',
            update: 'OD/update.json',
            create: 'OD/create.json',
            destroy: 'OD/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success',
            messageProperty: 'message'
        }
    },
    
    hasMany: {model: 'Test.model.Booking', name: 'bookings', primaryKey: 'order_id'}
});
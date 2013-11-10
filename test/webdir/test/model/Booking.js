Ext.define('Test.model.Booking', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'order_id',  type: 'string'}, 
        {name: 'order_number',  type: 'string'},  
        {name: 'product_id',  type: 'string'},
        {name: 'product_name',  type: 'string'},  
        {name: 'booking_price',  type: 'string'},
        {name: 'unit_price',  type: 'string'},
        {name: 'booking_qty',  type: 'int'},
        {name: 'discount',  type: 'int'},
        {name: 'createdDate',  type: 'date'},
        {name: 'lastModifiedDate',  type: 'date'}
    ],
    

    proxy: {
        type: 'rest',
        api: {
            read: 'BK/004/bookingList.json',
            update: 'BK/update.json',
            create: 'BK/create.json',
            destroy: 'BK/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success'
        }
    },
    
    belongsTo: {model: 'Test.model.Order', primaryKey: 'order_id'}
});
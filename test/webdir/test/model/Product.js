Ext.define('Test.model.Product', {
    extend: 'Ext.data.Model',
    
    fields: [
             {name: 'product_id',  type: 'string'},     
             {name: 'product_name',  type: 'string'},
             {name: 'product_year',  type: 'int'},
             {name: 'product_price',  type: 'number'},
             {name: 'product_price2',  type: 'number'},
             {name: 'product_qty',  type: 'int'},
             {name: 'createdDate',  type: 'date'},
             {name: 'lastModifiedDate',  type: 'date'}
         ],
         
         validations: [
                       {type: 'presence',  field: 'product_name'}
                   ],
    
    proxy: {
        type: 'rest',
        api: {
            read: 'PD/004/productList.json',
            update: 'PD/update.json',
            create: 'PD/create.json',
            destroy: 'PD/delete.json'
        },
        simpleSortMode:true,
        reader: {
            type: 'json',
            root: 'stats',
            successProperty: 'success'
        }
    }
});
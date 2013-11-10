Ext.define('Test.store.UserStore', {
    extend: 'Ext.data.Store',
    model: 'Test.model.User',
    sortOnLoad: true,
});
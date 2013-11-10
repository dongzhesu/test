Ext.define('AM.view.user.List' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.userlist',

    title: 'All Users',
    store: 'Users',
    initComponent: function() {

        this.columns = [
            {header: 'User Login',  dataIndex: 'user_login',  flex: 1},
            {header: 'User Language', dataIndex: 'user_language', flex: 1},
            {header: 'User Created Date', dataIndex: 'createdDate', flex: 1},
            {header: 'User Last Modified Date', dataIndex: 'lastModifiedDate', flex: 1}
        ];

        this.callParent(arguments);
    }
});
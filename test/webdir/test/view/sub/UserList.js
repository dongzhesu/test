Ext.define('Test.view.sub.UserList', {
    extend: 'Test.view.comm.BaseListView',
    alias: 'widget.userList',
    toolbarItems: [],
    gridColumns: [ {
        header: 'User Login Name'.i18n(),
        dataIndex: 'user_login',
        align: 'center',
        flex: 1,
        minWidth: 100
    },  {
        header: 'language'.i18n(),
        dataIndex: 'user_language',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true
    },{
        header: 'CreateTime'.i18n(),
        dataIndex: 'createdDate',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    }, {
        header: 'Last Modified Time'.i18n(),
        dataIndex: 'lastModifiedDate',
        align: 'center',
        flex: 1,
        minWidth: 100,
        sortable: true,
        renderer:Ext.util.Format.dateRenderer('m/d/Y')
    }],

    menuAlarm: [],

    initComponent: function () {
        console.log("init component");
        Ext.apply(this, {
            title: Test.getTitle('User', 'User list'), 
            gridStore: Ext.create('Test.store.UserStore'),
            gridSelModel: Ext.create('Ext.selection.CheckboxModel')
        });
        this.refreshInterval = '10';
        this.callParent(arguments);
    }
})

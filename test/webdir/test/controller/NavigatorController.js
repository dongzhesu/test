Ext.define('Test.controller.NavigatorController', {
    extend: 'Ext.app.Controller',
    refs: [{
        ref: 'navpanel',
        selector: 'navpanel'
    }],
    init: function () {
        console.log("nav init...");
        this.control({
            'navpanel': {
                boxready: this.onPanelRendered
            }
        });
    },
    onPanelRendered: function (navPanel) {
        console.log("start");
        this.loadMenus(navPanel, Test.modules);
    },
    loadMenus: function (navPanel, modules) {
        console.log("start to load Menus");
        var selModule = null;
        var selView = null;
        for (var i = 0; i < modules.length; i++) {
            var module = modules[i];
            var menuView = this.createMenuItems(module.id, module.subModules);
            console.log("create menuView of "+module.name.i18n());
            navPanel.add({
                title: module.name.i18n(),
                items: menuView
            });
            if (module.selectedId && module.selectedId.length > 0 && module.subModules.length > 0) {
                selModule = module;
                selView = menuView;
            }
        };
        if (selModule != null && selView != null) {
            for (var i = 0; i < selModule.subModules.length; i++) {
                if (selModule.subModules[i].id == selModule.selectedId) {
                    var selectedMenu = selView.getStore().findRecord('id', selModule.selectedId);
                    this.onMenuItemClick(null, selectedMenu);
                }
            }
        }
        console.log("after select menu");
    },
    createMenuItems: function (mid, subModules) {
        var view = Ext.create('widget.dataview', {
            store: Ext.create('Ext.data.Store', {
                fields: ['id', 'name', 'controller'],
                data: subModules
            }),
            listeners: {
                scope: this,
                afterrender: function (c) {
                    if (c.store.data.items[0] && c.store.data.items[0].get('id') == "pl/list") {
                        c.getSelectionModel().select(0);
                    }
                },
                itemclick: this.onMenuItemClick
            },
            trackOver: true,
            cls: 'main-menu-list',
            itemSelector: 'div.main-menu-item',
            overItemCls: 'main-menu-item-hover',
            tpl: '<tpl for="."><div class="main-menu-item">{[values.name.i18n()]}</div></tpl>'
        });
        return view;
    },
    onMenuItemClick: function (obj, rec) {
        var viewId = '_tab_' + rec.get('id');
        var ctrlClassName = rec.get('controller');
        //logging
        console.log('onMenuItemClick: id=' + viewId + ', controller=' + ctrlClassName);
        //find view if exists
        var panel = Ext.getCmp(viewId);
        var main = Ext.getCmp('contentPanel');
        if (!panel) {
            console.log('load controller if not exists');
            console.log(ctrlClassName);
            var controller = this.getController(ctrlClassName);
            console.log("got controller");
            if (!controller.isReady)
                controller.init(rec);
            console.log("start to create the view");
            panel = controller.createView(viewId);
            main.add(panel);
        }
        main.setActiveTab(panel);
    }
});

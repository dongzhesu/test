/**
 * Multi-Language 
 * @type 
 **/
String.Locale = (Ext.urlDecode((window.location.href.substr(window.location.href.indexOf("?")+1))))['lang'] || window.navigator.userLanguage || window.navigator.language;
String.Locale = String.Locale.replace('-', '_');
var index = String.Locale.indexOf('_');
String.Locale = String.Locale.toString().substring(0,index)+String.Locale.toString().substring(index).toUpperCase();

String.LangMap = {};
String.prototype.i18n = function(){
    var val = String.LangMap[this] || this;
    if(arguments){
        var args = Ext.Array.toArray(arguments, 0);
        args.splice(0, 0, val);
        val = Ext.String.format.apply(val, args);
    }
    return val;
};
String.prototype.startWith = function(v){
	return this.indexOf(v)==0;
};
String.prototype.format = function(){
	var val = this;
    if(arguments){
        var args = Ext.Array.toArray(arguments, 0);
        args.splice(0, 0, val);
        val = Ext.String.format.apply(val, args);
    }
    return val;
};

/**
 * override Loader to load multi-language files
 **/
Ext.apply(Ext.Loader, {
	loadLang: function(){
		var locale = String.Locale;
		if(!locale.startWith('en_')){
		   for(var i=0; i<arguments.length; i++){
		   	  var url = arguments[i] + locale + '.js';
		   	  Ext.Loader.loadScript(url);
		   }
        }
	}
});


Ext.override(Ext.grid.property.Grid, {
	viewConfig: {
    	enableTextSelection: true
	}
});

//solve grid sort when rows are selected
Ext.define('My.app.grid.Column', {
    override: 'Ext.grid.column.Column',

    doSort: function(state) {
        var tablePanel = this.up('tablepanel'),
            store = tablePanel.store;


        // If the owning Panel's store is a NodeStore, this means that we are the unlocked side
        // of a locked TreeGrid. We must use the TreeStore's sort method because we cannot
        // reorder the NodeStore - that would break the tree.
        if (tablePanel.ownerLockable && store.isNodeStore) {
            store = tablePanel.ownerLockable.lockedGrid.store;
        }
        //temporary solution, deselect rows before sort
        tablePanel.getSelectionModel().deselectAll();
        store.sort({
            property: this.getSortParam(),
            direction: state
        });
    }
});

/**
 * Tab scroller menu
 **/
Ext.define('Ext.ux.TabScrollerMenu', {

    alias: 'plugin.tabscrollermenu',

    requires: ['Ext.menu.Menu'],
    
    constructor: function(config) {
        Ext.apply(this, config);
    },
    
    //private
    init: function(tabPanel) {
        var me = this;

        me.tabPanel = tabPanel;

        var bar = tabPanel.tabBar;
	                bar.insert(2, [{
	                    xtype: 'component',
	                    flex: 1
	                }, {
	                    xtype: 'button',
	                    text: 'Log Out',
	                    iconCls: 'btn-logout',
	                    handler: function() {
	                    	window.location = Test.LOGOUT_URL;
	                    }
	                }]);
	                
        tabPanel.on({
            render: function() {
                me.tabBar = tabPanel.tabBar;
                me.layout = me.tabBar.layout;
                me.layout.overflowHandler.handleOverflow = Ext.Function.bind(me.showButton, me);
                me.layout.overflowHandler.clearOverflow = Ext.Function.createSequence(me.layout.overflowHandler.clearOverflow, me.hideButton, me);
            },
            single: true
        });
    },

    showButton: function() {
        var me = this,
            result = Ext.getClass(me.layout.overflowHandler).prototype.handleOverflow.apply(me.layout.overflowHandler, arguments);

        if (!me.menuButton) {
            me.menuButton = me.tabBar.body.createChild({
                cls: Ext.baseCSSPrefix + 'tab-tabmenu-right'
            }, me.tabBar.body.child('.' + Ext.baseCSSPrefix + 'box-scroller-right'));
            me.menuButton.addClsOnOver(Ext.baseCSSPrefix + 'tab-tabmenu-over');
            me.menuButton.on('click', me.showTabsMenu, me);
        }
        me.menuButton.show();
        result.reservedSpace += me.menuButton.getWidth();
        return result;
    },

    hideButton: function() {
        var me = this;
        if (me.menuButton) {
            me.menuButton.hide();
        }
    },

    showTabsMenu: function(e) {
        var me = this;
        if (me.tabsMenu) {
            me.tabsMenu.removeAll();
        } else {
            me.tabsMenu = new Ext.menu.Menu({cls: 'tabmenu'});
            me.tabPanel.on('destroy', me.tabsMenu.destroy, me.tabsMenu);
        }

        me.generateTabMenuItems();

        var target = Ext.get(e.getTarget()),
            xy = target.getXY();

        //Y param + 24 pixels
        xy[1] += 24;

        me.tabsMenu.showAt(xy);
    },

    // private
    generateTabMenuItems: function() {
        var me = this,
            tabPanel = me.tabPanel,
            curActive = tabPanel.getActiveTab(),
            allItems = tabPanel.items.getRange(),
            tabsMenu = me.tabsMenu,
            totalItems, i, item;
        tabsMenu.suspendLayouts();

        allItems = Ext.Array.filter(allItems, function(item){
            if (item.id == curActive.id) {
                return false;
            }
            return item.hidden ? !!item.hiddenByLayout : true;
        });
        totalItems = allItems.length;
        
        Ext.Array.each(allItems, function(item){
        	tabsMenu.add(me.autoGenMenuItem(item));
        });
		//add close other tab item
        tabsMenu.add('-',{
        	text: 'Close other tabs',
        	handler: this.closeOtherTab,
        	scope: this,
        	cls: 'closeothertab',
        	iconCls: 'closeothertabsicon'
        });
        tabsMenu.resumeLayouts(true);
    },

    // private
    autoGenMenuItem: function(item) {
        return {
            text: item.title,
            handler: this.showTabFromMenu,
            scope: this,
            disabled: item.disabled,
            tabToShow: item,
            iconCls: item.iconCls
        };
    },

    // private
    showTabFromMenu: function(menuItem) {
        this.tabPanel.setActiveTab(menuItem.tabToShow);
    },
    
    //close other tabs
    closeOtherTab: function(menuItem){
    	var me = this;
    	var totalItems = me.tabPanel.items.getRange();
    		curActive = me.tabPanel.getActiveTab(),
    		
    	totalItems = Ext.Array.filter(totalItems, function(item){
            if (item.id == curActive.id) {
                return false;
            }
            return item.hidden ? !!item.hiddenByLayout : true;
        });
        
        Ext.Array.each(totalItems, function(item){
        	me.tabPanel.remove(item);
        });

    }
});
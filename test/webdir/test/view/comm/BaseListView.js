Ext.define('Test.view.comm.BaseListView', {
    extend: 'Ext.container.Container',
    toolbarItems: [{ 
        xtype: 'button',
        text: 'Add',
        iconCls: 'btn-add',
		action: 'add'
    }, '-', {
        xtype: 'button',
        text: 'Edit',
        iconCls: 'btn-edit',
        action: 'edit'
    }, '-', {
        xtype: 'button',
        text: 'Delete',
        iconCls: 'btn-delete',
        action: 'delete'
    }],

    filterItems: [],
    filterAutoLayout: true,
    filterColumnNum: 2,
    gridColumns: [], 
    initComponent: function () {
        Ext.apply(this, {
			layout: 'fit',
            border: false,
            closable: true,
            items: [this.createGridPanel()],
            selectedRecords: [],
            unlockState: false
        });
        this.callParent(arguments);
        var me = this;     
		me.on('activate', function(){
			console.log('activate:'+this.title);
			this.onActive();
		});
		me.on('render', function(){
			console.log('render:'+this.title);
			this.onActive();
		});
		me.on('deactivate', function(){
			console.log('deactive:'+this.title);
			this.onDeactive();
		});
		me.on('close', function(){
			console.log('close:'+this.title);
			this.onDeactive();
		});
	},
    onActive: function(){
    	var me = this;
    	me.gridStore.load();
    	if(!me.refreshInterval || me.refreshInterval == '0')
			return;
    	if(this.task)
    		Ext.TaskManager.stop(this.task);
    	this.task = {
        	run: function(){
        		console.log('refreshing, interval:'+me.refreshInterval);
        		me.gridStore.load();
        	},
        	interval: parseInt(me.refreshInterval)*1000//10 secs
        };
        Ext.TaskManager.start(this.task);
    },
    onDeactive: function() {
    	if(this.task)
	    	Ext.TaskManager.stop(this.task);
    },
    createToolbar: function () {
        this.toolbar = Ext.create('Ext.toolbar.Toolbar', {
            items: this.toolbarItems
        });
		if (this.filterItems.length > 0) {
		    this.toolbar.add({
                text: 'Search'.i18n(),
                iconCls: 'btn-search',
                enableToggle: true,
                pressed: false,
                listeners: {
                    scope: this,
                    toggle: this.onSearchToggle
                }
            });
		}
        return this.toolbar;
    },
    createFilterPanel: function () {
        var items = this.filterItems;
        if (this.filterAutoLayout) {
            var colNum = this.filterColumnNum;
            items = new Array(colNum);
            for (var i = 0; i < colNum; i++) {
                items[i] = {
                    xtype: 'container',
                    columnWidth: 1 / colNum,
                    layout: 'anchor',
                    defaultType: 'textfield',
                    style: {margin: '0 20 0 20'},
                    defaults: {
                        anchor: '100%'
                    },
                    items: []
                };
            }
            for (var j = 0; j < this.filterItems.length; j++) {
                items[j % colNum].items.push(this.filterItems[j]);
            }
        }
        this.filterPanel = Ext.create('Ext.form.Panel', {
            border: false,
            padding: '10 8 5 8',
            bodyStyle: 'background-color: transparent;',
            buttonAlign: 'center',
            defaultType: 'textfield',
            hidden: true,
            fieldDefaults: {
                msgTarget: 'side'
            },
            anchor: '95%',
            layout: 'column',
            items: items,
            buttons: [{
                text: 'Query'.i18n(),
                listeners: {
                    scope: this,
                    click: this.onFilterQueryClick
                }
            }, {
                text: 'Reset'.i18n(),
                listeners: {
                    scope: this,
                    click: this.onFilterResetClick
                }
            }]
        });
        return this.filterPanel;
    },
    createGridPanel: function () {
    	var me = this;
        this.gridPanel = Ext.create('Ext.grid.Panel', {
            store: this.gridStore,
			selModel: this.gridSelModel,
            columns: this.gridColumns,
            emptyText: 'No records', 
            rowLines: true,
            region: 'center',
            autoScroll: true,
            layout: 'fit',
			loadMask: true,
            border: this.border,
            viewConfig: {
            	loadMask: false,
            	enableTextSelection: true,
            	//emptyText: 'No records', 
            	deferEmptyText: false,
		        //Return CSS class to apply to rows depending upon data values
		        getRowClass: function(record, rowIndex, rp, ds){
		        	var parent = this.up('container').up('container');
		        	return parent.changeRowColor(record);
		        }
		    },
		    listeners: {  //IMPORTANT NOTE: ensure add "idProperty: 'xxx'" to the model of listview!!!!
		    	selectionchange: function(selectionModel, selecteds){
		        	if (me.selectedRecords != null && me.unlockState) {
			            me.selectedRecords = [];
			            Ext.each(selecteds, function(selected) {
			                me.selectedRecords.push(selected.getId());
			            });
			        }
				}
		    },
            dockedItems: [{
                xtype: 'pagingtoolbar',
                store: this.gridStore,
                dock: 'bottom',
                displayInfo: true
//                displayMsg: 'Displaying {0} - {1} of {2}   (Last Updated: '+ Ext.Date.format(new Date(),"H:i:s")+')'
            },
            
            this.createToolbar()]
        });
		
        if (this.filterItems.length > 0) 
		    this.gridPanel.insertDocked(3, this.createFilterPanel());
        return this.gridPanel;
    },
    onSearchToggle: function (item, pressed) {
        this.filterPanel.setVisible(pressed);
    },
    onFilterResetClick: function (btn) {
    	console.log('onFilterResetClick...');
        this.filterPanel.getForm().reset();
        this.doFilter();
    },
    onFilterQueryClick: function (btn) {
    	console.log('onFilterQueryClick...');
    	this.doFilter();
    },
    changeRowColor: function(record){
    	//extended classes will implement this function
    },
    getFilterForm: function(){
    	return this.filterPanel.getForm();
    },
    doFilter: function(){
    	var store = this.gridStore,
    	    form = this.filterPanel.getForm();
    	if (store){
    		store.clearFilter(true);
    	    var filterArray = new Array();
        	Ext.Array.forEach(this.filterItems, function(v, i, a){
        	    var field = form.findField(v.name),
        	        value = field.getValue();
				if(value && (!Ext.isString(value) || value.length>0)){
					filterArray.push(Ext.create('Ext.util.Filter', {property: v.name, value: value}));
				}
			});
			store.filter(filterArray);
        }
    }
});

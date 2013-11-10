Ext.define('Test', {
	BASIC: '/test',
	singleton : true,
	//constants PUT HERE
	TITLE_TPL: '{0} -> {1}',
	SELECT_REMIND_TPL: 'Please select {0} first!',
	CONFIRMATION:'Are you sure to {0} selected {1}', 
	CONFIRMATION_T: 'Confirm {0}',
	//modules
	modules: {},
	//logon user
	user: {},
	getTitle: function(v1, v2){
		if(Ext.isString(v1)) v1 = v1.i18n();
		if(Ext.isString(v2)) v2 = v2.i18n();
	    return this.TITLE_TPL.format(v1, v2);
	},
	getSelectRemindText: function(name){
		name = name.i18n();
		return this.SELECT_REMIND_TPL.i18n(name);
	},
	getConfirmContent: function(action,name){
		return this.CONFIRMATION.i18n(action,name); 
	},
	getConfirmTitle: function(action){
		return this.CONFIRMATION_T.i18n(action); 
	}
});

Ext.apply(Test, {
	modules : [{
	    id : "pl",
        index: 0,
		name : "Product",
		selectedId : "pl/list",
		subModules : [{
		    id : "pl/list",
			name : "Product List",
			controller : "Test.controller.sub.ProductController"
		}]
	},
    {
	    id : "ol",
        index: 1,
		name : "Order",
		subModules : [{
		    id : "orl/list",
			name : "Order List",
			controller : "Test.controller.sub.OrderController"
		}]
	},
    {
	    id : "cl",
        index: 2,
		name : "Customer",
		subModules : [{
		    id : "cl/list",
			name : "Customer List",
			controller : "Test.controller.sub.CustomerController"
		}]
	},
    {
	    id : "bl",
        index: 3,
		name : "Booking",
		subModules : [{
		    id : "bl/list",
			name : "Booking List",
			controller : "Test.controller.sub.BookingController"
		}]
	},
    {
	    id : "sl",
        index: 4,
		name : "Sales",
		subModules : [{
		    id : "sl/list",
			name : "Sales List",
			controller : "Test.controller.sub.SalesController"
		}]
	},
    {
	    id : "wl",
        index: 5,
		name : "Storage",
		subModules : [{
		    id : "wl/list",
			name : "Storage List",
			controller : "Test.controller.sub.StorageController"
		}]
	},
    {
	    id : "ul",
        index: 6,
		name : "User",
		subModules : [{
		    id : "ul/list",
			name : "User List",
			controller : "Test.controller.sub.UserController"
		}]
	}]
});
package com.erp.china.demo.dao;
 
import com.erp.china.demo.model.System;

public interface SystemDAO {
	public System loadSystem(String systemId);
	public void updateSystem(System system);
}
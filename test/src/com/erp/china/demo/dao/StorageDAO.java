package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Storage;

public interface StorageDAO {
	public void createStorage(Storage storage);
	public List<Storage> storageList();
	public Storage loadStorage(String storageId);
	public void updateStorage(Storage storage);
	public void removeStorage(String storageId);
}
package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Lookup;

public interface LookupDAO {
	public void createLookup(Lookup lookup);
	public List<Lookup> lookupList();
	public List<Lookup> lookupList(String criteria);
	public Lookup loadLookup(String lookupId);
	public void updateLookup(Lookup lookup);
	public void removeLookup(String lookupId);
}
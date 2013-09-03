package com.k_int.euinside.statistics.datamodel

import java.text.SimpleDateFormat;
import java.util.Map;

class ModuleStatistic {
	static SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	Long duration = 0;
	Integer numberFailed = 0;
	Integer numberProcessed = 0;
	Integer numberSuccessful = 0;
	Date statisticDate;
	
	String toString(){
		
		"${moduleSet}" + statisticDate 
	}
	
	static belongsTo = [moduleSet : ModuleSet]
	
	static mapping = {
		version false 
	}
	
    static constraints = {
		duration			nullable : false
		numberFailed		nullable : false
		numberProcessed 	nullable : false
		numberSuccessful	nullable : false
    }
	
	private addFieldIfNotNull(Map map, String key, value) {
		if (value != null) {
			map[key] = value;
		}
	}
	
	private Map generateJSONFields() {
		def fieldsJSON = [ : ]
		addFieldIfNotNull(fieldsJSON, "duration", duration);
		addFieldIfNotNull(fieldsJSON, "numberFailed", numberFailed);
		addFieldIfNotNull(fieldsJSON, "numberProcessed", numberProcessed);
		addFieldIfNotNull(fieldsJSON, "numberSuccessful", numberSuccessful);
		if (statisticDate != null) {
			addFieldIfNotNull(fieldsJSON, "statisticDate", expectedDateFormat.format(statisticDate));
		}
		return(fieldsJSON)
	}
}
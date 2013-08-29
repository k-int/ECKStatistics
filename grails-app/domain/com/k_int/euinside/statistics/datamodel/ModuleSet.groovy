package com.k_int.euinside.statistics.datamodel

class ModuleSet {
	
	String code
	String description
	
	String toString(){
		
		"${description}"
	}
	
	static belongsTo = [module : Module]
	static hasMany = [statistics : ModuleStatistic]
		
	static mapping = {
		version false 
	}
	
    static constraints = {
		
		code 		nullable : false , maxSize : 20 , unique : true
		description	nullable : false , maxSize : 200
    }
}

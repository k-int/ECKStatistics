package com.k_int.euinside.statistics.datamodel

class Module {
	
	String code
	String description
	
	String toString(){
		
		"${description}"
	}
	
	static hasMany = [sets : ModuleSet]
		
	static mapping = {
		version false 
	}
	
    static constraints = {
		
		code 		nullable : false , maxSize : 20 , unique : true
		description	nullable : false , maxSize : 200
    }
}

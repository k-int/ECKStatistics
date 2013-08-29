package eckstatistics

class Module {
	
	String code
	String description
	
	String toString(){
		
		"${description}"
	}
	
	static hasMany = [statistics : ModuleStatistic]
		
	static mapping = {
		version false 
	}
	
    static constraints = {
		
		code 		nullable : false , maxSize : 20 , unique : true
		description	nullable : false , maxSize : 200
    }
}

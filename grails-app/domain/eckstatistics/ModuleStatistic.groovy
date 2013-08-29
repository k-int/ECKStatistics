package eckstatistics

class ModuleStatistic {

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
}
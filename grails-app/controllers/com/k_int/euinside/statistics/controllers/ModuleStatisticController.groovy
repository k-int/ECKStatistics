
package com.k_int.euinside.statistics.controllers

import grails.converters.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Query
import org.springframework.dao.DataIntegrityViolationException

import com.k_int.euinside.statistics.datamodel.Module;
import com.k_int.euinside.statistics.datamodel.ModuleSet;
import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

class ModuleStatisticController {
	// This is the date format that we expect to receive the date format
	static SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	def status() {
		def results = [];
		
		// Is there a minimum duration we are interested in
		Long duration = params.long('duration');
		if (duration == null) {
			duration = 0;
		}

		// Is there a limit in the number of records they would like to request		
		Integer limit = params.int('limit');
		if (limit == null) {
			limit = 100;
		}

		// Now down to the nitty gritty
		String moduleId = params.moduleId;
		if (moduleId != null) {
			String set = params.set;
	
			// Ensure we have a legitimate module and set
			Module module = findModule(moduleId, true);
			if (module != null) {
				ModuleSet moduleSet = findModuleSet(module, set, true);
				if (moduleSet != null) {
					// Now we can execute the query
					results = ModuleStatistic.findAll("FROM ModuleStatistic WHERE module_set_id = ? and duration >= ? order by statistic_date desc", [moduleSet.id, duration], [max : limit]);
				}
			}
		}
		render results as JSON;
	}
	
    def query(Integer max, String typeOfQuery ) {
        //params.max = Math.min(max ?: 10, 100)
		// params.moduleId 
		// params.typeOfQuery     Slowest   Status
		
		//		if(params.typeOfQuery == "Slowest")  Return the longest running calls (based on the average time taken to process 1 item)
				
		//		if(params.typeOfQuery == "Status") Returns the following 
		//	+ Total number of items processed
		//	+ Total time taken to process the items
		//	Fastest time to process an item
		//	Slowest time to process an item
		//	+ Average time taken to process the items
		
		//		def allStatistics = ModuleStatistic.findAllBy


		if(params.typeOfQuery == "Status")	{
			
//			def allStatistics = ModuleStatistic.list()
//			def totalTime = ModuleStatistic.list(params)
			
//			def totalNumberOfItemsProcessed = ModuleStatistic.executeQuery("SELECT SUM(numberProcessed) AS Total FROM ModuleStatistic MS WHERE MS.module = :moduleid ",[moduleid : 1])
		//	
// params.duration
// params.limit
			
			// if url parameters are missing use default values:
			if(!params.list('duration')) params.duration = 1 
			if(!params.list('limit')) params.limit = 1
//			render params.modx
			//moduleId
			
			def dateAndTimeWhenProcessingStarted = ModuleStatistic.executeQuery("SELECT SUM(numberProcessed) FROM ModuleStatistic MS WHERE MS.module = :duration AND MS.duration >= :duration",[ duration : params.duration.toInteger()] , [max : params.limit.toInteger()])
			// def dateAndTimeWhenProcessingStarted = ModuleStatistic.executeQuery("SELECT SUM(numberProcessed) FROM ModuleStatistic MS WHERE MS.module = :module AND duration >= :duration",[ module : params.modx.toInteger(), duration : params.duration.toInteger()] , [max : params.limit.toInteger()])
			
			def totalNumberOfItemsProcessed = ModuleStatistic.executeQuery("SELECT SUM(numberProcessed) AS Total FROM ModuleStatistic MS WHERE MS.module = 1 AND duration >= :duration",[duration : params.duration.toInteger()] , [max : params.limit.toInteger()])
			def totalTimeTakenToProcessTheItems = ModuleStatistic.executeQuery("SELECT SUM(duration) AS Total FROM ModuleStatistic MS WHERE MS.module = 1") 
			
			//  TODO :  two values below seem to get rounded and are integers instead of becoming float - divide operation does not make the output float type
			// TODO: add "WHERE module_id" clause to all queries , each query should run on specific module
			
			// MYSQL: SELECT min(duration / number_processed) FROM module_statistic
			def FastestTimeToProcessAnItem = ModuleStatistic.executeQuery("SELECT min(duration / numberProcessed) FROM ModuleStatistic MS WHERE MS.module = 1",[max:1])
			def SlowestTimeToProcessAnItem = ModuleStatistic.executeQuery("SELECT max(duration / numberProcessed) FROM ModuleStatistic MS WHERE MS.module = 1",[max:1])
			
			// Average time to process the items
			def AverageTimeTakenToProcessTheItems = ModuleStatistic.executeQuery("SELECT avg(duration) FROM ModuleStatistic MS WHERE MS.module = 1")
			
			return [totalNumberOfItemsProcessed : totalNumberOfItemsProcessed,
				totalTimeTakenToProcessTheItems : totalTimeTakenToProcessTheItems,
				FastestTimeToProcessAnItem : FastestTimeToProcessAnItem,
				SlowestTimeToProcessAnItem : SlowestTimeToProcessAnItem,
				AverageTimeTakenToProcessTheItems : AverageTimeTakenToProcessTheItems,
				moduleStatisticInstanceTotal : ModuleStatistic.count()]
			
			
			// TODO instead of returning values we need to return json
//			def json;
//			if (item == null) {
//				// They are interested in all the items or we have been passed a filter
//				json = [ ];
//				items.each() {key, value ->
//					// If we have a filter, then is this the item we are interested in
//					if ((filter == null) || (filter == key)) {
//						json.add(value.getJSONObject(language));
//					}
//				}
//			} else {
//				// Just dealing with a single item, with a potential parameter
//				if (parameter == null) {
//					json = item.getJSONObject(language);
//				} else {
//					json = item.getJSONObject(language, parameter);
//				}
//			}
			
//			render json as JSON;
		}
		
		
		if(params.typeOfQuery == "Slowest")	{
			params.sort = "duration"
			params.order = "desc"
			
			def allStatistics = ModuleStatistic.list(params)
		
			def logestRunningCalls = ModuleStatistic.executeQuery("SELECT * FROM ModuleStatistic ",[max:1])

			
			return [moduleStatisticInstanceList: allStatistics, moduleStatisticInstanceTotal: ModuleStatistic.count()]
			
			
			// Return the longest running calls (based on the average time taken to process 1 item)
			// Duration / number processed
			// return
		}
		
		
//        [moduleStatisticInstanceList: ModuleStatistic.list(params), moduleStatisticInstanceTotal: ModuleStatistic.count()]
    }
	
	def update() {
		
		Long duration = params.long('duration');
		Integer itemsProcessed = params.int('itemsProcessed');
		Integer numberSuccessful = params.int('numberSuccessful');
		Integer numberFailed = params.int('numberFailed');
		
		// If we have 2 off itemsProcessed, numberSelected or numberFailed then we will calculate the third
		if (itemsProcessed == null) {
			if ((numberSuccessful != null) && (numberFailed != null)) {
				itemsProcessed = numberSuccessful + numberFailed;
			}
		} else if (numberSuccessful == null) {
			if ((itemsProcessed != null) && (numberFailed != null)) {
				numberSuccessful = itemsProcessed - numberFailed;
			}
		} else if (numberFailed == null) {
			if ((itemsProcessed != null) && (numberSuccessful != null)) {
				numberFailed = itemsProcessed - numberSuccessful;
			}
		}

		Date dateTime = null
		try {
			dateTime = expectedDateFormat.parse(params.dateTime);
		} catch (ParseException e) {
			// Do not care if we had an error, it will be dealt with below
		}
		
		
		String moduleId = params.moduleId;
		String set = params.set;
		String error = null;

		if (moduleId == null) {
			error = "No module specified";
		} else if (duration == null) {
			error = "No duration specified";		
		} else if (itemsProcessed == null) {
			error = "Number of items processed (itemsProcessed) not specified";		
		} else if (numberSuccessful == null) {
			error = "Number of items successfully processed (numberSuccessful) not specified";		
		} else if (numberFailed == null) {
			error = "Number of items failed to process or in error (numberFailed) not specified";		
		} else if (dateTime == null) {
			error = "dateTime not specified or the date was in an incorrect format";
		}
		if (error == null) {
			Module module = findModule(moduleId, true);
			if (module == null) {
				error = "Internal error: Creating new module record";
			} else {
				ModuleStatistic statistic = new ModuleStatistic();
				statistic.duration = duration;
				statistic.numberFailed = numberFailed;
				statistic.numberProcessed = itemsProcessed;
				statistic.numberSuccessful = numberSuccessful;
				statistic.statisticDate = dateTime;
				statistic.moduleSet = findModuleSet(module, set, true);
				if (statistic.moduleSet == null) {
					error = "Internal error: Creating new module set record";
				} else {
					if (statistic.save(flush: true)) {
						response.sendError(202, "Statistic has been stored");
					} else {
						error = "Internal error: Saving statistics record";
					}
				}
			}
		}
		
		// If we had an error respond with a 400
		if (error != null) {
			response.sendError(400, error);
		}
	}
	
	private Module findModule(moduleId, boolean create) {
		Module module = Module.findByCode(moduleId);
		if ((module == null) && create) {
			module = new Module();
			module.code= moduleId;
			module.description = moduleId;
			if (!module.save(flush: true)) {
				module = null;
			}
		}
		return(module);
	}
	
	private ModuleSet findModuleSet(module, set, boolean create) {
		// If we have not been supplied a set or the set is *, then set it to default		
		if ((set == null) || (set == "*")) {
			set = "DEFAULT";
		}

		// Now try and find the set		
		ModuleSet moduleSet = ModuleSet.findByModuleAndCode(module, set);
		if ((moduleSet == null) && create) {
			moduleSet = new ModuleSet();
			moduleSet.code= set;
			moduleSet.description = set;
			moduleSet.module = module;
			if (!moduleSet.save(flush: true)) {
				moduleSet = null;
			}
		}
		return(moduleSet);
	}
}

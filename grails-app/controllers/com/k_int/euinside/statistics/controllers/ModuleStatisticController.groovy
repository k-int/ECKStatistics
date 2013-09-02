
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
	def ReportService;
	
	// This is the date format that we expect to receive the date format
	static SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	def test() {
		// Throws up a test page
	}

	def query( ) {
		def results = ReportService.execute(getParameterQueryType(), buildReportParameters());

		render results as JSON;
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
	
	private ModuleSet findModuleSet() {
		ModuleSet moduleSet = null;
		String moduleId = params.moduleId;
		if (moduleId != null) {
			String set = params.set;
	
			// Ensure we have a legitimate module and set
			Module module = findModule(moduleId, false);
			if (module != null) {
				 moduleSet = findModuleSet(module, set, false);
			}
		}
		return(moduleSet);
	}
	
	private Long getLongParameter(parameter, defaultValue) {
		Long value = params.long(parameter);
		if (value == null) {
			value = defaultValue;
		}

		// We do not allow negative values
		if (value < 0) {
			value = 0;
		}
		return(value);
	}

	private Integer getIntegerParameter(parameter, defaultValue) {
		Integer value = params.int(parameter);
		if (value == null) {
			value = defaultValue;
		}
		
		// We do not allow negative values
		if (value < 0) {
			value = 0;
		}
		return(value);
	}

	private def buildReportParameters() {
		return([moduleSet : findModuleSet(),
			    duration : getParameterDuration(),
				limit :  getParameterLimit(),
				offset : getParameterOffset(),
				days : getParameterDays()]);
	}
		
	private String getStringParameter(parameter, defaultValue) {
		String value = params.get(parameter);
		if (value == null) {
			value = defaultValue;
		}
		return(value);
	}
	
	private Long getParameterDuration() {
		return(getLongParameter("duration", 0));
	}
	
	private Integer getParameterLimit() {
		return(getIntegerParameter("limit", 100));
	}
	
	private Integer getParameterOffset() {
		return(getIntegerParameter("offset", 0));
	}
	
	private Integer getParameterDays() {
		return(getIntegerParameter("days", 30));
	}

	private String getParameterQueryType() {
		return(getStringParameter("queryType", "status"));
	}
}


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
	
	def test() {
		// Throws up a test page
	}

	def query( ) {
		def results;
		def reportParameters = buildReportParameters();
		def queryType = getParameterQueryType();
		if (params.moduleId == "*") {
			results = ReportService.executeAll(queryType, reportParameters);
		} else if (params.set == "*") {
			results = ReportService.executeAllSets(queryType, reportParameters);
		} else {
			results = ReportService.execute(queryType, reportParameters);
		}

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
			String chas = params.dateTime
			dateTime = ModuleStatistic.expectedDateFormat.parse(params.dateTime);
		} catch (ParseException e) {
			// Do not care if we had an error, it will be dealt with below
		}
		
		String moduleId = params.moduleId;
		String set = params.set;
		String responseText = null;
		def responseCode = 400; // Default to being an error, since only 1 route is successful

		if ((moduleId == null) || (moduleId == "*")) {
			responseText = "No module specified";
		} else if (duration == null) {
			responseText = "No duration specified";		
		} else if (itemsProcessed == null) {
			responseText = "Number of items processed (itemsProcessed) not specified";		
		} else if (numberSuccessful == null) {
			responseText = "Number of items successfully processed (numberSuccessful) not specified";		
		} else if (numberFailed == null) {
			responseText = "Number of items failed to process or in error (numberFailed) not specified";		
		} else if (dateTime == null) {
			responseText = "dateTime not specified or the date was in an incorrect format";
		}
		if (responseText == null) {
			Module module = findModule(moduleId, true);
			if (module == null) {
				responseText = "Internal error: Creating new module record";
			} else {
				ModuleStatistic statistic = new ModuleStatistic();
				statistic.duration = duration;
				statistic.numberFailed = numberFailed;
				statistic.numberProcessed = itemsProcessed;
				statistic.numberSuccessful = numberSuccessful;
				statistic.statisticDate = dateTime;
				statistic.moduleSet = findModuleSet(module, set, true);
				if (statistic.moduleSet == null) {
					responseText = "Internal error: Creating new module set record";
				} else {
					if (statistic.save(flush: true)) {
						responseCode = 202;
						responseText = "Statistic has been stored";
					} else {
						responseText = "Internal error: Saving statistics record";
					}
				}
			}
		}

		// Now give the response 		
		render(status: responseCode, text: responseText, contentType: "text/plain", encoding: "UTF-8");
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
		ModuleSet moduleSet = null;
		if (module != null) {
			// If we have not been supplied a set or the set is *, then set it to default		
			if ((set == null) || (set == "*")) {
				set = "DEFAULT";
			}
	
			// Now try and find the set		
			moduleSet = ModuleSet.findByModuleAndCode(module, set);
			if ((moduleSet == null) && create) {
				moduleSet = new ModuleSet();
				moduleSet.code= set;
				moduleSet.description = set;
				moduleSet.module = module;
				if (!moduleSet.save(flush: true)) {
					moduleSet = null;
				}
			}
		}
		return(moduleSet);
	}
	
	private Long getLongParameter(parameter, defaultValue) {
		Long value = params.long(parameter);
		value = checkParameter(value, defaultValue);

		// We do not allow negative values
		if (value < 0) {
			value = 0;
		}
		return(value);
	}

	private Integer getIntegerParameter(parameter, defaultValue) {
		Integer value = params.int(parameter);
		value = checkParameter(value, defaultValue);
		
		// We do not allow negative values
		if (value < 0) {
			value = 0;
		}
		return(value);
	}

	private String getStringParameter(parameter, defaultValue) {
		String value = params.get(parameter);
		value = checkParameter(value, defaultValue);
		
		return(value);
	}

	private def buildReportParameters() {
		def module = findModule(params.moduleId, false);
		return([module : module,
			    moduleSet : findModuleSet(module, params.set, false),
			    duration : getParameterDuration(),
				limit :  getParameterLimit(),
				offset : getParameterOffset(),
				days : getParameterDays()]);
	}
		
	private def checkParameter(value, defaultValue) {
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

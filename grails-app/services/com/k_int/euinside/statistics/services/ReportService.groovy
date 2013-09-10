package com.k_int.euinside.statistics.services

import java.util.Map;

import com.k_int.euinside.statistics.datamodel.Module;
import com.k_int.euinside.statistics.datamodel.ModuleSet;

class ReportService {
	def grailsApplication;

	// The reports package
	static String REPORTS_PACKAGE = "com.k_int.euinside.statistics.services.report.";

	static Map reportMap = [ : ];
	
    def initialise() {
		// Loop through our service classes, to find all the reports
		grailsApplication.serviceClasses.each {
			def serviceBean = grailsApplication.mainContext.getBean(it.propertyName);
			if (it.clazz.name.startsWith(REPORTS_PACKAGE) &&
				it.metaClass.respondsTo(serviceBean, 'doReport')) {
				// We have found a report, so add it to the map
				String name = it.clazz.name.substring(REPORTS_PACKAGE.length());
				name = name.replace("Service", "").toLowerCase();
				reportMap.put(name, serviceBean);
			}
		}
    }

	private def getReport(reportType) {
		return(reportMap.get(reportType.toLowerCase()));
	}
		
	def execute(reportType, reportParameters) {
		def results = [ ]
		// Do we have a report of this name
		def report = getReport(reportType);
		if (report != null) {
			// We do so execute it
			results = report.doReport(reportParameters);
		}
		return(results);
	}
	
	def executeAll(reportType, reportParameters) {
		def results = [ ];
		def report = getReport(reportType);
		if (report != null) {
			Module.list().each()  {
				def moduleResult = [ : ];
				moduleResult.'module' = it.code;
				moduleResult.'sets' = reportAllSets(report, it, reportParameters);
				results.add(moduleResult);
			}
		}
		return(results);
	}
	
	def executeAllSets(reportType, reportParameters) {
		def results = [ ];
		def report = getReport(reportType);
		if (report != null) {
			results = reportAllSets(report, reportParameters.module, reportParameters)
		}
		return(results);
	}
	
	private def reportAllSets(report, module, reportParameters) {
		def results = [ ];
		if (module != null) {
			module.sets.each() {
				reportParameters.'moduleSet' = it;
				results.add([set : it.code,
							 statistics : report.doReport(reportParameters)]);
			}
		}
		return(results)
	}
}

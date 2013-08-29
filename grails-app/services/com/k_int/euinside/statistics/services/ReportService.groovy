package com.k_int.euinside.statistics.services

import java.util.Map;

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
	
	def execute(reportType, reportParams) {
		def results = [ ]
		// Do we have a report of this name
		def report = reportMap.get(reportType.toLowerCase());
		if (report != null) {
			// We do so execute it
			results = report.doReport(reportParams);
		}
		return(results);
	}
}

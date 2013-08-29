package com.k_int.euinside.statistics.services.report

import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

class StatusService {

    def doReport(reportParameters) {
		def results = [];
		if (reportParameters.moduleSet != null) {
			def statistics = ModuleStatistic.executeQuery("select sum(numberProcessed), sum(numberSuccessful), sum(numberFailed), sum(duration), min(duration), max(duration) from ModuleStatistic where moduleSet = ? and statisticDate > subdate(curdate(), ?)", [reportParameters.moduleSet, reportParameters.days]);
			def resultRow = statistics[0];
			if (resultRow[0] != null) {
				Double averageTime = resultRow[3] / resultRow[0];
				
				results = [numberProcessed : resultRow[0],
					       successful : resultRow[1],
					       failed : resultRow[2],
					       duration : resultRow[3],
						   fastestTime : resultRow[4],
						   slowestTime : resultRow[5],
						   averageTime : averageTime.round(0)];
			}
		}
		return(results);
    }
}

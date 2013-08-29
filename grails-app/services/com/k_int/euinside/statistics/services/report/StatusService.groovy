package com.k_int.euinside.statistics.services.report

import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

class StatusService {

    def doReport(reportParameters) {
		def results = [];
		if (reportParameters.moduleSet != null) {
			def numberProcessed = ModuleStatistic.executeQuery("select sum(numberProcessed) from ModuleStatistic where moduleSet = ?", [reportParameters.moduleSet]);
			if (numberProcessed[0] != null) {
				def totalDuration = ModuleStatistic.executeQuery("select sum(duration) from ModuleStatistic where moduleSet = ?", [reportParameters.moduleSet]);
				def fastestTime = ModuleStatistic.executeQuery("select min(duration) as fastestTime from ModuleStatistic where moduleSet = ?", [reportParameters.moduleSet]);
				def slowestTime = ModuleStatistic.executeQuery("select max(duration) as slowestTime from ModuleStatistic where moduleSet = ?", [reportParameters.moduleSet]);
				Double averageTime = totalDuration[0] / numberProcessed[0];
				
				results = [numberProcessed : numberProcessed[0],
					       duration : totalDuration[0],
						   fastestTime : fastestTime[0],
						   slowestTime : slowestTime[0],
						   averageTime : averageTime.round(0)];
			}
		}
		return(results);
    }
}

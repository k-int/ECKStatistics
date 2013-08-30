package com.k_int.euinside.statistics.services.report

import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

class StatusByDayService {

    def doReport(reportParameters) {
		def results = [];
		if (reportParameters.moduleSet != null) {
			def statistics = ModuleStatistic.executeQuery("select date_format(statisticDate, '%Y%m%d'), " +
																 "sum(numberProcessed), " +
				                                                 "sum(numberSuccessful), " +
																 "sum(numberFailed), " +
																 "sum(duration), " +
																 "min(duration / numberProcessed), " + 
																 "max(duration / numberProcessed) " +
														  "from ModuleStatistic " +
														  "where moduleSet = ? and " +
																"statisticDate > subdate(curdate(), ?) " +
														  "group by 1 " +
														  "order by 1 desc",
														  [reportParameters.moduleSet, reportParameters.days]);
			if (statistics != null) {
				statistics.each() {statisticRow -> 
					if (statisticRow[1] != null) {
						Double averageTime = statisticRow[4] / statisticRow[1];
						Double fastestTime = statisticRow[5];
						Double slowestTime = statisticRow[6];
						
						results.add([date : statisticRow[0],
							         numberProcessed : statisticRow[1],
									 numberSuccessful : statisticRow[2],
									 numberFailed : statisticRow[3],
									 duration : statisticRow[4],
									 fastestTime : fastestTime.round(0),
									 slowestTime : slowestTime.round(0),
									 averageTime : averageTime.round(0)])
					}
				} 
			}
		}
		return(results);
    }
}

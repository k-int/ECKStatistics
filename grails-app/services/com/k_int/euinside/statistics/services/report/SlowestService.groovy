package com.k_int.euinside.statistics.services.report

import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

class SlowestService {

    def doReport(reportParameters) {
		def results = [];
		if (reportParameters.moduleSet != null) {
			def moduleSetId = reportParameters.moduleSet.id;
			Long duration = reportParameters.duration;
			Integer limit = reportParameters.limit;
			Integer offset = reportParameters.offset;
			 
			results = ModuleStatistic.findAll("from ModuleStatistic where module_set_id = ? and (duration / number_processed) >= ? order by (duration / number_processed) desc", [moduleSetId, duration], [max : limit, offset : offset]);
		}
		return(results);
    }
}

class UrlMappings {
	
	static mappings = {
		"/$moduleId/$set/$action" {
			controller = "ModuleStatistic"
		}
		
		// The old way, kept here to conform with what is in D2.6 until we update it
		"/Statistic/update/$moduleId" {
			controller = "ModuleStatistic"
			action = "update"
		}
		
		// The old way, kept here to conform with what is in D2.6 until we update it
		"/Statistic/query/$moduleId/$typeOfQuery" {
			controller = "ModuleStatistic"
			action = "query"
		}

		"/Help/$action" {
			controller = "help"
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}


import grails.converters.JSON;

class BootStrap {

	def grailsApplication;
	
    def init = { servletContext ->
		// Loop through all the services, to see if they need to be initialised
		grailsApplication.serviceClasses.each { 
			def serviceBean = grailsApplication.mainContext.getBean(it.propertyName) 
			if (it.metaClass.respondsTo(serviceBean, 'initialise')) { 
				// We have one that wants initialising and has an initialise method
				serviceBean.initialise() 
			}
		}

		// We do not want the id, class, moduleSet or moduleSetId fields appearing in the json, we also do not want fields being output if the value is null		
		JSON.registerObjectMarshaller(com.k_int.euinside.statistics.datamodel.ModuleStatistic) {
			return(it.properties.findAll{key, value ->
										 ((key != "class") && (key != "id") && (key != "moduleSet") && (key != "moduleSetId") && (value != null))
										});
		}
    }
	
    def destroy = {
    }
}

package eckstatistics



import org.junit.*
import grails.test.mixin.*

@TestFor(ModuleStatisticController)
@Mock(ModuleStatistic)
class ModuleStatisticControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

}

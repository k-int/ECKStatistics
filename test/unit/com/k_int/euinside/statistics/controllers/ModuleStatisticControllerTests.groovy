package com.k_int.euinside.statistics.controllers



import org.junit.*

import com.k_int.euinside.statistics.controllers.ModuleStatisticController;
import com.k_int.euinside.statistics.datamodel.ModuleStatistic;

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

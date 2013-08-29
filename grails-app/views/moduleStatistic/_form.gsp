<%@ page import="eckstatistics.ModuleStatistic" %>



<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'duration', 'error')} required">
	<label for="duration">
		<g:message code="moduleStatistic.duration.label" default="Duration" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="duration" type="number" value="${moduleStatisticInstance.duration}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'numberFailed', 'error')} required">
	<label for="numberFailed">
		<g:message code="moduleStatistic.numberFailed.label" default="Number Failed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberFailed" type="number" value="${moduleStatisticInstance.numberFailed}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'numberProcessed', 'error')} required">
	<label for="numberProcessed">
		<g:message code="moduleStatistic.numberProcessed.label" default="Number Processed" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberProcessed" type="number" value="${moduleStatisticInstance.numberProcessed}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'numberSuccessful', 'error')} required">
	<label for="numberSuccessful">
		<g:message code="moduleStatistic.numberSuccessful.label" default="Number Successful" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberSuccessful" type="number" value="${moduleStatisticInstance.numberSuccessful}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'module', 'error')} required">
	<label for="module">
		<g:message code="moduleStatistic.module.label" default="Module" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="module" name="module.id" from="${eckstatistics.Module.list()}" optionKey="id" required="" value="${moduleStatisticInstance?.module?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleStatisticInstance, field: 'statisticDate', 'error')} required">
	<label for="statisticDate">
		<g:message code="moduleStatistic.statisticDate.label" default="Statistic Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="statisticDate" precision="day"  value="${moduleStatisticInstance?.statisticDate}"  />
</div>


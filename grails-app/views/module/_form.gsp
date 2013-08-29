<%@ page import="eckstatistics.Module" %>



<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'code', 'error')} ">
	<label for="code">
		<g:message code="module.code.label" default="Code" />
		
	</label>
	<g:textField name="code" maxlength="20" value="${moduleInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="module.description.label" default="Description" />
		
	</label>
	<g:textField name="description" maxlength="200" value="${moduleInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: moduleInstance, field: 'statistics', 'error')} ">
	<label for="statistics">
		<g:message code="module.statistics.label" default="Statistics" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${moduleInstance?.statistics?}" var="s">
    <li><g:link controller="moduleStatistic" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="moduleStatistic" action="create" params="['module.id': moduleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'moduleStatistic.label', default: 'ModuleStatistic')])}</g:link>
</li>
</ul>

</div>



<%@ page import="eckstatistics.ModuleStatistic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleStatistic.label', default: 'ModuleStatistic')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-moduleStatistic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-moduleStatistic" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list moduleStatistic">
			
				<g:if test="${moduleStatisticInstance?.duration}">
				<li class="fieldcontain">
					<span id="duration-label" class="property-label"><g:message code="moduleStatistic.duration.label" default="Duration" /></span>
					
						<span class="property-value" aria-labelledby="duration-label"><g:fieldValue bean="${moduleStatisticInstance}" field="duration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleStatisticInstance?.numberFailed}">
				<li class="fieldcontain">
					<span id="numberFailed-label" class="property-label"><g:message code="moduleStatistic.numberFailed.label" default="Number Failed" /></span>
					
						<span class="property-value" aria-labelledby="numberFailed-label"><g:fieldValue bean="${moduleStatisticInstance}" field="numberFailed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleStatisticInstance?.numberProcessed}">
				<li class="fieldcontain">
					<span id="numberProcessed-label" class="property-label"><g:message code="moduleStatistic.numberProcessed.label" default="Number Processed" /></span>
					
						<span class="property-value" aria-labelledby="numberProcessed-label"><g:fieldValue bean="${moduleStatisticInstance}" field="numberProcessed"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleStatisticInstance?.numberSuccessful}">
				<li class="fieldcontain">
					<span id="numberSuccessful-label" class="property-label"><g:message code="moduleStatistic.numberSuccessful.label" default="Number Successful" /></span>
					
						<span class="property-value" aria-labelledby="numberSuccessful-label"><g:fieldValue bean="${moduleStatisticInstance}" field="numberSuccessful"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleStatisticInstance?.module}">
				<li class="fieldcontain">
					<span id="module-label" class="property-label"><g:message code="moduleStatistic.module.label" default="Module" /></span>
					
						<span class="property-value" aria-labelledby="module-label"><g:link controller="module" action="show" id="${moduleStatisticInstance?.module?.id}">${moduleStatisticInstance?.module?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${moduleStatisticInstance?.statisticDate}">
				<li class="fieldcontain">
					<span id="statisticDate-label" class="property-label"><g:message code="moduleStatistic.statisticDate.label" default="Statistic Date" /></span>
					
						<span class="property-value" aria-labelledby="statisticDate-label"><g:formatDate date="${moduleStatisticInstance?.statisticDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${moduleStatisticInstance?.id}" />
					<g:link class="edit" action="edit" id="${moduleStatisticInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

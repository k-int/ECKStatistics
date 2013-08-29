
<%@ page import="eckstatistics.ModuleStatistic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'moduleStatistic.label', default: 'ModuleStatistic')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-moduleStatistic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-moduleStatistic" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="duration" title="${message(code: 'moduleStatistic.duration.label', default: 'Duration')}" />
					
						<g:sortableColumn property="numberFailed" title="${message(code: 'moduleStatistic.numberFailed.label', default: 'Number Failed')}" />
					
						<g:sortableColumn property="numberProcessed" title="${message(code: 'moduleStatistic.numberProcessed.label', default: 'Number Processed')}" />
					
						<g:sortableColumn property="numberSuccessful" title="${message(code: 'moduleStatistic.numberSuccessful.label', default: 'Number Successful')}" />
					
						<th><g:message code="moduleStatistic.module.label" default="Module" /></th>
					
						<g:sortableColumn property="statisticDate" title="${message(code: 'moduleStatistic.statisticDate.label', default: 'Statistic Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${moduleStatisticInstanceList}" status="i" var="moduleStatisticInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${moduleStatisticInstance.id}">${fieldValue(bean: moduleStatisticInstance, field: "duration")}</g:link></td>
					
						<td>${fieldValue(bean: moduleStatisticInstance, field: "numberFailed")}</td>
					
						<td>${fieldValue(bean: moduleStatisticInstance, field: "numberProcessed")}</td>
					
						<td>${fieldValue(bean: moduleStatisticInstance, field: "numberSuccessful")}</td>
					
						<td>${fieldValue(bean: moduleStatisticInstance, field: "module")}</td>
					
						<td><g:formatDate date="${moduleStatisticInstance.statisticDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${moduleStatisticInstanceTotal}" />
			</div>
		</div>
	</body>
</html>

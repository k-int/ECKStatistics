<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    	<title>ECKStatistics</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Management of Module Statistics</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	            		<h4>Invocation</h4>
						<p>The url for the module takes the form <b>/Statistics/&lt;module&gt;/&lt;group&gt;/&lt;action&gt;/&lt;queryType&gt;?parameters</b> Where:</p>
						<table class="parameters">
							<tr>
								<th>URL Part</th>
								<th align="left">Description</th>
							</tr>
							<tr>
								<td>module </td>
								<td>Is the module the statistics are for</td>
							</tr>
							<tr>
								<td>group</td>
								<td>This is the group or action within the module that the statistics for</td>
							</tr>
							<tr>
								<td>action</td>
								<td>The statistics action to be performed</td>
							</tr>
							<tr>
								<td>queryType</td>
								<td>If this is a query, this is the query to be performed</td>
							</tr>
						</table>

						<p>The action pages lists the possible parameters that are applicable for that action</p>
						<p>The possible actions are:</p>
						<ul>
							<li><g:link controller="Help" action="query">Query</g:link></li>
							<li><g:link controller="Help" action="update">Update</g:link></li>
						</ul>
								
	            		<h4>Testing</h4>
	            		<p>In order to allow simple testing of the ECK Statistic interface. a test form is available <a href="/ECKStatistics/default/default/test">here</a> which performs actions against the default modules default group.</p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
	</body>
</html>

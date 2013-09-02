<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    	<title>ECKStatistics - Update</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Statistics - Update</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	          			<h4>Description</h4>
	          			<p> The update action allows you to add statistics for a module</p>
	          			
	            		<h4>Parameters</h4>
	            		
       					<table class="parameters">
       						<tr>
       							<th>Parameters</th>
       							<th>Description</th>
       						</tr>
       						<tr>
       							<td>dateTime</td>
       							<td>Date and time of when the processing started, format: ISO8601, RFC3339</td>
       						</tr>
       						<tr>
       							<td>duration</td>
       							<td>The length of time in milliseconds that it took to process the records</td>
       						</tr>
       						<tr>
       							<td>itemsProcessed</td>
       							<td>The number of items that were processed</td>
       						</tr>
       						<tr>
       							<td>numberFailed</td>
       							<td>The number of items that failed to be processed</td>
       						</tr>
       						<tr>
       							<td>numberSuccessful</td>
       							<td>The number of items that were successfully processed</td>
       						</tr>
       					</table>
       					<br/>
       					<br/>
	            		Note: If two off the three parameters itemsProcessed, numberSuccessful and numberFailed are supplied and the third is missing then the third one will be calculated, if we do not have at least two of these parameters then an error will be generated, the dateTime and duration parameters are mandatory and an error will be generated if either of these are missing.
       					<br/>
       					<br/>
	            		
	            		<h4>Responses</h4>
	            		<p>if successful the response will return a return a http status code 202, if there is an error then a http status code 400 will be returned</p>
	            
	            		<h4>Testing</h4>
	            		<p>In order to allow simple testing of the ECK Statistic interface. a test form is available <a href="/ECKStatistics/default/default/test">here</a> which performs actions against the default modules default group.</p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
	</body>
</html>

<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    	<title>ECKStatistics - Query</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Statistics - Query</h1>
	          		</div>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	          			<h4>Description</h4>
	          			<p> The query action runs a predefined report against the statistics data, all reports return JSON and the following query types are supported:</p>
	          			<ul>
	          				<li> items - Lists the statistics that have been reported to the statistics module for the specified module and group, ordered by date descending<br/>
	          					An array of JSON objects is returned with the following fields:
	          					<br/><br/>
			   					<table class="parameters">
	          						<tr>
	          							<th>JSON field name</th>
	          							<th align="left">Description</th>
	          						</tr>
	          						<tr>
	          							<td>duration</td>
	          							<td>The number of milliseconds it took to process the data</td>
	          						</tr>
	          						<tr>
	          							<td>numberFailed</td>
	          							<td>The number of items that failed processing</td>
	          						</tr>
	          						<tr>
	          							<td>numberProcessed</td>
	          							<td>The number of records that were processed</td>
	          						</tr>	
	          						<tr>
	          							<td>numberSuccessful</td>
	          							<td>The number of records that were successfully processed</td>
	          						</tr>
	          						<tr>
	          							<td>statisticsDate</td>
	          							<td>The date and time the run started (UTC)</td>
	          						</tr>
	          					</table>
	          				</li>
	          				<br/>
	          				<li> slowest - Lists the statistics where the duration for a single record is greater than that specified, ordered by duration for a single record, ordered by duration descending<br/>
	          					An array of JSON objects is returned with the following fields:
	          					<br/><br/>
			   					<table class="parameters">
	          						<tr>
	          							<th>JSON field name</th>
	          							<th align="left">Description</th>
	          						</tr>
	          						<tr>
	          							<td>duration</td>
	          							<td>The number of milliseconds it took to process the data</td>
	          						</tr>
	          						<tr>
	          							<td>numberFailed</td>
	          							<td>The number of items that failed processing</td>
	          						</tr>
	          						<tr>
	          							<td>numberProcessed</td>
	          							<td>The number of records that were processed</td>
	          						</tr>	
	          						<tr>
	          							<td>numberSuccessful</td>
	          							<td>The number of records that were successfully processed</td>
	          						</tr>
	          						<tr>
	          							<td>statisticsDate</td>
	          							<td>The date and time the run started (UTC)</td>
	          						</tr>
	          					</table>
	          				</li>
	          				<br/>
	          				<li> status - Returns a summary of the statistics for the number of days specified<br/>
	          					A JSON object is returned with the following fields:
	          					<br/><br/>
			   					<table class="parameters">
	          						<tr>
	          							<th>JSON field name</th>
	          							<th align="left">Description</th>
	          						</tr>
	          						<tr>
	          							<td>duration</td>
	          							<td>The total number of milliseconds processing took</td>
	          						</tr>
	          						<tr>
	          							<td>numberFailed</td>
	          							<td>The total number of items that failed</td>
	          						</tr>
	          						<tr>
	          							<td>numberProcessed</td>
	          							<td>The total number of records that were processed</td>
	          						</tr>	
	          						<tr>
	          							<td>numberSuccessful</td>
	          							<td>The total number of records that were successfully processed</td>
	          						</tr>
	          						<tr>
	          							<td>fastestTime</td>
	          							<td>The fastest time it took to process 1 record</td>
	          						</tr>
	          						<tr>
	          							<td>slowestestTime</td>
	          							<td>The slowest time it took to process 1 record</td>
	          						</tr>
	          						<tr>
	          							<td>averageTime</td>
	          							<td>The average time it took to process 1 record</td>
	          						</tr>
	          					</table>
	          				</li>
	          				<br/>
	          				<li> statusByDay - Returns a summary of statistics per day for the number of days specified<br/>
	          					An array of JSON objects is returned with the following fields:
	          					<br/><br/>
	          					<table class="parameters">
	          						<tr>
	          							<th>JSON field name</th>
	          							<th align="left">Description</th>
	          						</tr>
	          						<tr>
	          							<td>date</td>
	          							<td>The date the statistics are for (format: YYYYMMDD)</td>
	          						</tr>
	          						<tr>
	          							<td>duration</td>
	          							<td>The total number of milliseconds processing took</td>
	          						</tr>
	          						<tr>
	          							<td>numberFailed</td>
	          							<td>The total number of items that failed</td>
	          						</tr>
	          						<tr>
	          							<td>numberProcessed</td>
	          							<td>The total number of records that were processed</td>
	          						</tr>	
	          						<tr>
	          							<td>numberSuccessful</td>
	          							<td>The total number of records that were successfully processed</td>
	          						</tr>
	          						<tr>
	          							<td>fastestTime</td>
	          							<td>The fastest time it took to process 1 record</td>
	          						</tr>
	          						<tr>
	          							<td>slowestestTime</td>
	          							<td>The slowest time it took to process 1 record</td>
	          						</tr>
	          						<tr>
	          							<td>averageTime</td>
	          							<td>The average time it took to process 1 record</td>
	          						</tr>
	          					</table>
	          				</li>
	          			</ul>
	          			
	            		<h4>Parameters</h4>
	            		
       					<table class="parameters">
       						<tr>
       							<th>Parameters</th>
       							<th align="left">Description</th>
       							<th>Used by Query Type</th>
       						</tr>
       						<tr>
       							<td>days</td>
       							<td>The The number of days that the status should cover (default: 30)</td>
       							<td>statusByDay<br/>status</td>
       						</tr>
       						<tr>
       							<td>duration</td>
       							<td>The minimum number of milliseconds it took to process 1 record that you are interested in (default: 0)</td>
       							<td>items<br/>slowest</td>
       						</tr>
       						<tr>
       							<td>limit</td>
       							<td>The maximum number of records to be returned (default: 100)</td>
       							<td>items<br/>slowest</td>
       						</tr>
       						<tr>
       							<td>offset</td>
       							<td>Where in the result set do you want results to be returned from (default: 0)</td>
       							<td>items<br/>slowest</td>
       						</tr>
       					</table>
	            		
	            		<h4>Responses</h4>
	            		<p>The response will always be in JSON unless there is an error, in which case a http status 400 will be returned</p>
	            
	            		<h4>Testing</h4>
	            		<p>In order to allow simple testing of the ECK Statistic interface. a test form is available <a href="/ECKStatistics/default/default/test">here</a> which performs actions against the default modules default group.</p>
	          		</div>
	        	</div>
	      	</section>
    	</div>
	</body>
</html>

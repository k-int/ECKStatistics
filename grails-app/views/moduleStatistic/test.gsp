<!doctype html>
<html>
	<head>
		<!--  This meta line should get ignored, ripped this out from core, which has a master page ... -->
	  	<meta name="layout" content="bootstrap"/>
	  	<title>ECKStatistics - Test Page</title>
	</head>

	<body>
    	<div class="row-fluid">
			<section id="main">
		
		  		<div class="hero-unit row">
		    		<div class="page-header span12">
		      			<h1>Test Page</h1>
		    		</div>
		  		</div>
		  
			  	<div class="row">
			     	<div class="span12">
			       		<form id="testForm" name="testForm" action="update" method="POST" enctype="multipart/form-data">
			           		<table>
			               		<tr>
			                   		<th align="right">Date / Time: </th>
			                   		<td><g:textField name="dateTime" value="2013-09-02T09:30:12.654+0100"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">Duration (milliseconds): </th>
			                   		<td><g:textField name="duration"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">Items processed: </th>
			                   		<td><g:textField name="itemsProcessed"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">Number failed processing: </th>
			                   		<td><g:textField name="numberFailed"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">Number successfully processed: </th>
			                   		<td><g:textField name="numberSuccessful"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Number of days: </th>
			               			<td><g:textField name="days"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Maximum number of records in the result set: </th>
			               			<td><g:textField name="limit"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Where to start returning records from in the result set: </th>
			               			<td><g:textField name="offset"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Query type: </th>
									<td>
										<g:select name="queryType"
											  	  from="${['items', 'slowest', 'status', 'statusByDay']}"
									          	  valueMessagePrefix="query.type" />
									</td>
								</tr>
			               		<tr>
			                   		<td colspan="2">
			                   			<div class="btn btn-primary">
			                       			<g:field type="button"  name="testQuery"  value="Query"/>
			                       			<g:field type="button"  name="testUpdate" value="Update"/>
			                       		</div>
			                   		</td>
			               		</tr>
			           		</table>
			       		</form>
			     	</div>
		  		</div>
      		</section>

    	</div>
    
    	<script type="text/javascript">

    		function performAction(action) {
        		
	        	$("#testForm").attr("action", action);
	        	$("#testForm").submit();
	            return false;
        	}
        	
	    	$("#testQuery").click(function() {
	        	return(performAction("query"));
	    	});
	
	    	$("#testUpdate").click(function() {
	        	return(performAction("update"));
	    	});
	
    	</script>
    
  	</body>
</html>

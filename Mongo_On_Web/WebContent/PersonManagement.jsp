<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Person Manager</title>
<head>
<link rel="stylesheet" 	href="js/dojo-release-1.10.2/dijit/themes/claro/claro.css">
<script>
	dojoConfig = {
		parseOnLoad : true
	};
</script>
<script src="js/dojo-release-1.10.2/dojo/dojo.js"></script>

<script>
	dojo.require("dijit.layout.TabContainer");
	dojo.require("dijit.layout.ContentPane");
</script>

<script>
	require([ "dojo/parser", "dijit/ProgressBar" ], function() {
		var i = 0;
		personSaveWidget = function() {
			myProgressBar.set({
				value : ++i
			});
			if (i < 10) {
				setTimeout(personSaveWidget, 100 + Math.floor(Math.random() * 1000));
			}
		};
	});
</script>
</head>
<body class="claro">
	<div data-dojo-type="dijit.layout.TabContainer" region="center"
		style="width: 500px; height: 300px;" tabStrip="true">
		<div data-dojo-type="dijit.layout.ContentPane"
			data-dojo-props="region:'center'" title="Add" selected="true"
			style="width: 50px; height: 150px;">
			<form method="post" action="./PersonSave" id="myForm"
				enctype="multipart/form-data">

				<table style="border: 1px solid #9f9f9f;">
					<tr>
						<td><label for="fname">First Name:</label>
						</td>
						<td><input type="text" id="firstname" name="firstname"
							size=20 data-dojo-type="dijit/form/ValidationTextBox" />
						</td>
					</tr>
					<tr>
						<td><label for="lname">Last Name:</label>
						</td>
						<td><input type="text" id="lastname" name="lastname" size=20
							data-dojo-type="dijit/form/ValidationTextBox" />
						</td>
					<tr><td><label for="fileUpload">Choose File</label></td>
					<td> <input name="uploadedfile" multiple="false" type="file" data-dojo-type="dojox.form.Uploader" label="Select Some Files" id="uploader"></input></td>
					</tr>
				</table>
				<button data-dojo-type="dijit/form/Button" type="submit"
				id="saveButton" name="saveButton" value="Submit" >Submit</button>
				<!-- 
				<div data-dojo-type="dijit/ProgressBar" style="width: 300px"
				data-dojo-id="myProgressBar" id="downloadProgress"
				data-dojo-props="maximum:10"></div>
				 -->
			</form>


			
		</div>
		<div data-dojo-type="dijit.layout.ContentPane" title="Search">
			<form method="post" id="searchForm">
				<H3>Under Construction</H3>

				<table style="border: 1px solid #9f9f9f;">
					<tr>
						<td><label for="fname">First Name:</label>
						</td>
						<td><input type="text" id="firstname_search"
							name="firstname_search" size=20
							data-dojo-type="dijit/form/ValidationTextBox" />
						</td>
					</tr>
					<tr>
						<td><label for="lname">Last Name:</label>
						</td>
						<td><input type="text" id="lastname_search"
							name="lastname_search" size=20
							data-dojo-type="dijit/form/ValidationTextBox" />
						</td>
				</table>
				<button data-dojo-type="dijit/form/Button" type="submit"
					id="searchButton" name="searchButton" value="Search">Search</button>

			</form>
			<b>Search Result</b>
			<p id="SearchResponse"></p>
		</div>


		<div data-dojo-type="dijit.layout.ContentPane" title="Update">
			<H3>Under Construction</H3>
			This is where the functionality to update based on criteria would be
			implemented
		</div>
		<div data-dojo-type="dijit.layout.ContentPane" title="Delete">
			<H3>Under Construction</H3>
			This is where the functionality to delete based on criteria would be
			implemented
		</div>
	</div>
</body>
</html>
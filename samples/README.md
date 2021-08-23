
To use these samples:

* change the path to your project metadata in hop-server.xml 
* change system variable HOP_OPTIONS:

  export HOP_OPTIONS="-Xmx4g -DPROJECT_HOME=/path/to/project"

* start hop-server:

  sh hop-server.sh /path/to/project/samples/hop-server.xml

* Open a browser and run the data load workflow:

  http://localhost:8282/hop/asyncRun?service=dataload

* You'll get a JSON reply with the ID of the started workflow.
* Get the asynchronous status with the following call:

  http://localhost:8282/hop/asyncStatus?service=dataload&id=<id>

* Get generic status information in your browser:

  http://localhost:8282/hop/workflowStatus/?name=dataload&id=<id>

* Add &xml=y to see an XML variant of the workflow status


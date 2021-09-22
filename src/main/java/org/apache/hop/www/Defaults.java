package org.apache.hop.www;

public class Defaults {
  public static final String ASYNC_STATUS_GROUP = "ASYNC_STATUS_GROUP";
  public static final String ASYNC_ACTION_PIPELINE_SERVICE_NAME =
      "enable-asynchronous-pipeline-service-name";

  public static final String createWorkflowExtensionDataKey(String actionName) {
    return "Async-service - " + actionName;
  }

  public static final String createWorkflowPipelineStatusExtensionDataKey(
      String pipelineName, String serviceName) {
    return "Async-status - " + pipelineName + " - " + serviceName;
  }
}

package org.apache.hop.www.xp;

import org.apache.hop.core.exception.HopException;
import org.apache.hop.core.extension.ExtensionPoint;
import org.apache.hop.core.extension.IExtensionPoint;
import org.apache.hop.core.logging.ILogChannel;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.metadata.api.IHopMetadataSerializer;
import org.apache.hop.workflow.WorkflowExecutionExtension;
import org.apache.hop.workflow.WorkflowMeta;
import org.apache.hop.workflow.action.ActionMeta;
import org.apache.hop.workflow.engine.IWorkflowEngine;
import org.apache.hop.www.AsyncWebService;
import org.apache.hop.www.Defaults;

import java.util.Map;

@ExtensionPoint(
    id = "MarkAsyncWorkflowActionExtensionPoint",
    extensionPointId = "WorkflowBeforeActionExecution",
    description = "Before the execution of an action, pass service to workflow data map")
public class MarkAsyncWorkflowActionExtensionPoint
    implements IExtensionPoint<WorkflowExecutionExtension> {
  @Override
  public void callExtensionPoint(
      ILogChannel iLogChannel,
      IVariables iVariables,
      WorkflowExecutionExtension ext)
      throws HopException {

    ActionMeta actionMeta = ext.actionMeta;

    // Make sure it's a pipeline action
    //
    if (!actionMeta.isPipeline()) {
      return;
    }

    // Do we have a service name?
    //
    Map<String, String> attributesMap = actionMeta.getAttributesMap().get( Defaults.ASYNC_STATUS_GROUP );
    String serviceName = attributesMap.get( Defaults.ASYNC_ACTION_PIPELINE_SERVICE_NAME );
    if (serviceName==null) {
      return;
    }

    // Load the service...
    //
    IWorkflowEngine<WorkflowMeta> workflow = ext.workflow;

    IHopMetadataSerializer<AsyncWebService> serializer = workflow.getMetadataProvider().getSerializer( AsyncWebService.class );
    AsyncWebService service = serializer.load( serviceName );
    if (service==null) {
      throw new HopException("Unable to load asynchronous service "+serviceName);
    }

    // Store it in the workflow...
    //
    String key = Defaults.createWorkflowExtensionDataKey( actionMeta.getName() );
    workflow.getExtensionDataMap().put( key, service );
  }
}

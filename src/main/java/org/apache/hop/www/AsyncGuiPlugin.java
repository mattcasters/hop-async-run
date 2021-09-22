package org.apache.hop.www;

import org.apache.hop.core.action.GuiContextAction;
import org.apache.hop.core.gui.plugin.GuiPlugin;
import org.apache.hop.core.gui.plugin.action.GuiActionType;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.metadata.api.IHopMetadataSerializer;
import org.apache.hop.ui.core.dialog.EnterSelectionDialog;
import org.apache.hop.ui.core.dialog.ErrorDialog;
import org.apache.hop.ui.hopgui.HopGui;
import org.apache.hop.ui.hopgui.file.workflow.context.HopGuiWorkflowActionContext;
import org.apache.hop.workflow.action.ActionMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@GuiPlugin
public class AsyncGuiPlugin {

  @GuiContextAction(
      id = "workflow-graph-action-30000-enable-async-logging",
      parentId = HopGuiWorkflowActionContext.CONTEXT_ID,
      type = GuiActionType.Custom,
      name = "i18n::AsyncGuiPlugin.EnableAsyncLogging.Name",
      tooltip = "i18n::AsyncGuiPlugin.EnableAsyncLogging.ToolTip",
      image = "ui/images/log.svg",
      category = "i18n::AsyncGuiPlugin.EnableAsyncLogging.Category",
      categoryOrder = "9")
  public void enableAsyncLogging(HopGuiWorkflowActionContext context) {
    HopGui hopGui = context.getWorkflowGraph().getHopGui();

    try {
      ActionMeta actionMeta = context.getActionMeta();

      if (!actionMeta.isPipeline()) {
        // TODO: show message or disable in the menu if not applicable
        //
        return;
      }

      Map<String, String> asyncMap =
          actionMeta
              .getAttributesMap()
              .computeIfAbsent(Defaults.ASYNC_STATUS_GROUP, k -> new HashMap<>());

      // Ask for the name of the async service...
      //
      IHopMetadataProvider metadataProvider = hopGui.getMetadataProvider();
      IHopMetadataSerializer<AsyncWebService> serializer =
          metadataProvider.getSerializer(AsyncWebService.class);
      List<String> serviceNames = serializer.listObjectNames();
      EnterSelectionDialog dialog =
          new EnterSelectionDialog(
              hopGui.getShell(),
              serviceNames.toArray(new String[0]),
              "Select async service",
              "Select the asynchronous service to report to");
      String serviceName = dialog.open();
      if (serviceName==null) {
        return;
      }

      // Put the name of the async service in the map
      //
      asyncMap.put(Defaults.ASYNC_ACTION_PIPELINE_SERVICE_NAME, serviceName);

      // Flag the change
      actionMeta.setChanged();

    } catch (Exception e) {
      new ErrorDialog(
          hopGui.getShell(),
          "Error",
          "Error enabling asynchronous status reporting of a pipeline",
          e);
    }
    // Refresh the graph
    context.getWorkflowGraph().redraw();
  }

  @GuiContextAction(
      id = "workflow-graph-action-30100-disable-async-logging",
      parentId = HopGuiWorkflowActionContext.CONTEXT_ID,
      type = GuiActionType.Custom,
      name = "i18n::AsyncGuiPlugin.DisableAsyncLogging.Name",
      tooltip = "i18n::AsyncGuiPlugin.DisableAsyncLogging.ToolTip",
      image = "ui/images/log.svg",
      category = "i18n::AsyncGuiPlugin.DisableAsyncLogging.Category",
      categoryOrder = "9")
  public void disableAsyncLogging(HopGuiWorkflowActionContext context) {
    ActionMeta actionMeta = context.getActionMeta();
    if (!actionMeta.isPipeline()) {
      // TODO: show message or disable in the menu if not applicable
      //
      return;
    }

    Map<String, String> asyncMap = actionMeta.getAttributesMap().get(Defaults.ASYNC_STATUS_GROUP);
    if (asyncMap == null) {
      // Nothing to disable
      return;
    }
    asyncMap.remove(Defaults.ASYNC_ACTION_PIPELINE_SERVICE_NAME);

    // Flag the change
    actionMeta.setChanged();

    // Refresh the graph
    context.getWorkflowGraph().redraw();
  }
}

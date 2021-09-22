package org.apache.hop.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsyncStatus {
  private String service;
  private String id;
  private String startDate;
  private String endDate;
  private String statusDescription;
  private Map<String, String> statusVariables;
  private List<HopServerPipelineStatus> pipelineStatuses;

  public AsyncStatus() {
    statusVariables = new HashMap<>();
    pipelineStatuses = new ArrayList<>();
  }

  /**
   * Gets service
   *
   * @return value of service
   */
  public String getService() {
    return service;
  }

  /**
   * @param service The service to set
   */
  public void setService( String service ) {
    this.service = service;
  }

  /**
   * Gets id
   *
   * @return value of id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id The id to set
   */
  public void setId( String id ) {
    this.id = id;
  }

  /**
   * Gets startDate
   *
   * @return value of startDate
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * @param startDate The startDate to set
   */
  public void setStartDate( String startDate ) {
    this.startDate = startDate;
  }

  /**
   * Gets endDate
   *
   * @return value of endDate
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * @param endDate The endDate to set
   */
  public void setEndDate( String endDate ) {
    this.endDate = endDate;
  }

  /**
   * Gets statusDescription
   *
   * @return value of statusDescription
   */
  public String getStatusDescription() {
    return statusDescription;
  }

  /**
   * @param statusDescription The statusDescription to set
   */
  public void setStatusDescription( String statusDescription ) {
    this.statusDescription = statusDescription;
  }

  /**
   * Gets statusVariables
   *
   * @return value of statusVariables
   */
  public Map<String, String> getStatusVariables() {
    return statusVariables;
  }

  /**
   * @param statusVariables The statusVariables to set
   */
  public void setStatusVariables( Map<String, String> statusVariables ) {
    this.statusVariables = statusVariables;
  }

  /**
   * Gets pipelineStatuses
   *
   * @return value of pipelineStatuses
   */
  public List<HopServerPipelineStatus> getPipelineStatuses() {
    return pipelineStatuses;
  }

  /**
   * @param pipelineStatuses The pipelineStatuses to set
   */
  public void setPipelineStatuses( List<HopServerPipelineStatus> pipelineStatuses ) {
    this.pipelineStatuses = pipelineStatuses;
  }
}

package com.ebay.queens.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "queens.props")
public class ExternalConfig {

  private String developerName;

  private String globalId;

  private String certName;

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getGlobalId() {
    return globalId;
  }

  public void setGlobalId(String globalId) {
    this.globalId = globalId;
  }

  public String getCertName() {
    return certName;
  }

  public void setCertName(String certName) {
    this.certName = certName;
  }

}

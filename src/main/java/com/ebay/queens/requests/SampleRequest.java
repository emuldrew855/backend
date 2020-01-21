package com.ebay.queens.requests;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SampleRequest2")
public class SampleRequest {

  private int num;

  private String name;

  private SampleSubRequest subReq;

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SampleSubRequest getSubReq() {
    return subReq;
  }

  public void setSubReq(SampleSubRequest subReq) {
    this.subReq = subReq;
  }

}

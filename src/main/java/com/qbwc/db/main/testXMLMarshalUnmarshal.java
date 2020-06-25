package com.qbwc.db.main;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testXMLMarshalUnmarshal")
public class testXMLMarshalUnmarshal {
    private String testField;

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }
}

package com.pocemssanar.app.documents.models.entity;


import lombok.Data;


@Data
public class DocumentStructureRequest {
	

	 private String fieldName;
	 
	 private String documentName;
	 
	 private String dataType;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	 
		 
	 
	 

}

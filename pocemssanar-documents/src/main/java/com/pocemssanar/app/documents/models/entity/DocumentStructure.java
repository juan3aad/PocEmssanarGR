package com.pocemssanar.app.documents.models.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class DocumentStructure {
	
	  @Id
	    private String id; // Identificador único del documento

	 
	    private String documentName;

	    private Map<String, Object> dynamicFields = new HashMap<>();

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDocumentName() {
			return documentName;
		}

		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}

		public Map<String, Object> getDynamicFields() {
			return dynamicFields;
		}

		public void setDynamicFields(Map<String, Object> dynamicFields) {
			this.dynamicFields = dynamicFields;
		}
    

}

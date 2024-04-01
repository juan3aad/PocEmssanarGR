package com.pocemssanar.app.documents.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocemssanar.app.documents.models.entity.DocumentStructure;
import com.pocemssanar.app.documents.models.entity.DocumentStructureRequest;
import com.pocemssanar.app.documents.models.repository.DocumentRepository;



@Service
public class DocumentService {
	
	 @Autowired
	    private DocumentRepository documentRepository;

	    public void saveDocumentStructure(DocumentStructureRequest request) {
	        DocumentStructure documentStructure = new DocumentStructure();
	        documentStructure.setFieldName(request.getFieldName());
	        documentStructure.setDocumentName(request.getDocumentName());
	        documentStructure.setDataType(request.getDataType());
	        documentRepository.save(documentStructure);
	    }

	    public void generateJson() throws IOException {
	        List<DocumentStructure> documentStructures = documentRepository.findAll();
	        ObjectMapper mapper = new ObjectMapper();
	        String json = mapper.writeValueAsString(documentStructures);
	        // Log the JSON instead of writing to file
	        System.out.println("Generated JSON: " + json);
	    }
	    
	    public void hello() throws IOException {
	    	System.out.println("HELLO GELLLLL");
	    }

}

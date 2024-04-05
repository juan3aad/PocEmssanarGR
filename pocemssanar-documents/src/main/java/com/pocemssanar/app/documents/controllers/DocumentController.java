package com.pocemssanar.app.documents.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pocemssanar.app.documents.models.entity.DocumentStructure;
import com.pocemssanar.app.documents.services.DocumentService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    
  
/*
    @PostMapping("/addFields")
    public ResponseEntity<String> addFieldsToDocuments(@RequestBody DocumentStructure request) {
        String collectionName = request.getDocumentName();
        Map<String, Object> fieldsToAdd = request.getDynamicFields();
        documentService.addFieldsToDocuments(collectionName, fieldsToAdd);
        return ResponseEntity.status(HttpStatus.OK).body("Fields added to documents in collection: " + collectionName);
    }
  */  

    @PostMapping("/save")
    public ResponseEntity<String> saveDocumentStructure(@RequestParam("collectionName") String collectionName, @RequestBody Map<String, Object> fields) {
    	documentService.saveDocumentStructure(collectionName, fields);
        return ResponseEntity.ok("Document structure saved successfully");
    }

    @GetMapping("/generateJson")
    public ResponseEntity<String> generateJson() {
        try {
            documentService.generateJson();
            return ResponseEntity.ok("JSON file generated successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating JSON file");
        }
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
    	try {
			documentService.hello();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.ok("Json file hello");
    }
    
    
    @PostMapping("/validateDocument")
    public ResponseEntity<String> validateDocument(@RequestBody DocumentStructure request) {
        boolean exists = documentService.isDocumentExists(request.getDocumentName());
        if (exists) {
            return ResponseEntity.ok("El documento existe en MongoDB.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El documento no existe en MongoDB.");
        }
    }
    
    @PostMapping("/{collectionName}")
    public ResponseEntity<String> addFieldsToDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> fields) {
        try {
            documentService.addFieldsToDocument(collectionName, fields);
            return ResponseEntity.ok("Campos agregados al documento exitosamente");
        } catch (Exception e1) {
            return ((BodyBuilder) ResponseEntity.notFound()).body("El documento no existe en la colección");
        }
    }
    
}
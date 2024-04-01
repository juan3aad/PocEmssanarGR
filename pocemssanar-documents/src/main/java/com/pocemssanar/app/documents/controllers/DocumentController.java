package com.pocemssanar.app.documents.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pocemssanar.app.documents.models.entity.DocumentStructureRequest;
import com.pocemssanar.app.documents.services.DocumentService;



import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/save")
    public ResponseEntity<String> saveDocumentStructure(@RequestBody DocumentStructureRequest request) {
        documentService.saveDocumentStructure(request);
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
}
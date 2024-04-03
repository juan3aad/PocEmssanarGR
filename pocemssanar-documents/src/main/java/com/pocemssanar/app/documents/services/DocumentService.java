package com.pocemssanar.app.documents.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import com.pocemssanar.app.documents.models.entity.DocumentStructure;

import com.pocemssanar.app.documents.models.repository.DocumentRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class DocumentService {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DocumentRepository documentRepository;

	public void saveDocumentStructure(String collectionName, Map<String, Object> fields) {
		Document document = new Document();
		document.putAll(fields);
		mongoTemplate.save(document, collectionName);
	}

	public void generateJson() throws IOException {
		List<DocumentStructure> documentStructures = documentRepository.findAll();
		// Convertir los documentos a objetos Document de MongoDB
		List<Document> documents = documentStructures.stream().map(this::convertToDocument)
				.collect(Collectors.toList());

		// Convertir los objetos Document a JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(documents);

		// Log del JSON generado
		System.out.println("Generated JSON: " + json);
	}

	// Método para convertir un DocumentStructure a un Document de MongoDB
	private Document convertToDocument(DocumentStructure documentStructure) {
		Document document = new Document();
		document.putAll(documentStructure.getDynamicFields());
		return document;
	}

	public void hello() throws IOException {
		System.out.println("HELLO GELLLLL");
	}

	/*
	 * public void addFieldsToDocuments(String collectionName, Map<String, Object>
	 * fieldsToAdd) { validateInput(collectionName, fieldsToAdd);
	 * 
	 * List<Document> documents = mongoTemplate.findAll(Document.class,
	 * collectionName);
	 * 
	 * for (Document document : documents) { addFieldsToDocument(document,
	 * fieldsToAdd); }
	 * 
	 * mongoTemplate.save(documents, collectionName); }
	 * 
	 * private void validateInput(String collectionName, Map<String, Object>
	 * fieldsToAdd) { Assert.notNull(collectionName,
	 * "Ojo nombre de la collection no debe ser null"); Assert.notNull(fieldsToAdd,
	 * "Ojo los campos agregados no deben ser null"); }
	 * 
	 * private void addFieldsToDocument(Document document, Map<String, Object>
	 * fieldsToAdd) { for (Map.Entry<String, Object> entry : fieldsToAdd.entrySet())
	 * { String fieldName = entry.getKey(); Object fieldValue = entry.getValue();
	 * document.append(fieldName, fieldValue); } }
	 */

	public boolean isDocumentExists(String documentName) {
		if (StringUtils.isEmpty(documentName)) {
			return false;
		}
		return mongoTemplate.collectionExists(documentName);
	}

	public void addFieldsToDocument(String collectionName, Map<String, Object> fields) {
		Update update = new Update();

		// Agregar los campos adicionales al documento
		fields.forEach((key, value) -> update.set(key, value));

		// Ejecutar la actualización en la base de datos
		UpdateResult result = mongoTemplate.updateFirst(new Query(), update, collectionName);

		// Verificar si la actualización fue exitosa
		if (result.getModifiedCount() > 0) {
			System.out.println("Campos agregados al documento con éxito");
		} else {
			System.out.println("No se encontró el documento o no se realizaron cambios");
		}
	}

}

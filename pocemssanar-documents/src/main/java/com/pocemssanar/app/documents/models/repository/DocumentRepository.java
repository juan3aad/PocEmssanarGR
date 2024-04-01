package com.pocemssanar.app.documents.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pocemssanar.app.documents.models.entity.DocumentStructure;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentStructure, String> {
}

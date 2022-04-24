/**
 * 
 */
package com.tipikae.noteservice.migration;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

/**
 * Create collection changeUnit for Mongock.
 * @author tipikae
 * @version 1.0
 *
 */
@Profile("dev")
@ChangeUnit(id = "InsertDataDevProfile", order = "2", author = "tipikae")
public class InsertDataDevProfile {

	private final MongoTemplate mongoTemplate;
	
	@Value("${mongo.database.collection.notes.name:notes}")
	private String collName;
	
	public InsertDataDevProfile(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	//Note this method / annotation is Optional
	@BeforeExecution
	public void before() {
	}
	
	//Note this method / annotation is Optional
	@RollbackBeforeExecution
	public void rollbackBefore() {
	}
	
	@Execution
	public void migrationMethod() {
		getNotesDocuments()
	      	.stream()
	      	.forEach(notesDocument -> mongoTemplate.save(notesDocument, collName));
	}
	
	@RollbackExecution
	public void rollback() {
		mongoTemplate.deleteMany(new Document());
	}
	
	private List<Document> getNotesDocuments() {
		List<Document> documents = new ArrayList<>();
		return documents;
	}
}

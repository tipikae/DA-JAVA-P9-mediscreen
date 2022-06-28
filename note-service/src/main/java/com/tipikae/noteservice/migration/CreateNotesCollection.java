/**
 * 
 */
package com.tipikae.noteservice.migration;

import org.springframework.data.mongodb.core.MongoTemplate;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

/**
 * Create notes collection.
 * @author tipikae
 * @version 1.0
 *
 */
@ChangeUnit(id = "createNotesCollection", order = "1", author = "tipikae")
public class CreateNotesCollection {
	
	private final static String NOTES_COLL_NAME = "notes";
	
	private final MongoTemplate mongoTemplate;
	
	public CreateNotesCollection(MongoTemplate mongoTemplate) {
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
		mongoTemplate.createCollection(NOTES_COLL_NAME);
	}
	
	@RollbackExecution
	public void rollback() {
		if(mongoTemplate.collectionExists(NOTES_COLL_NAME)) {
			mongoTemplate.dropCollection(NOTES_COLL_NAME);
		}
	}
}

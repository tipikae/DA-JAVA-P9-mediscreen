/**
 * 
 */
package com.tipikae.noteservice.migration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

/**
 * Create collection changeUnit for Mongock
 * @author tipikae
 * @version 1.0
 *
 */
@ChangeUnit(id = "createCollection", order = "1", author = "tipikae")
public class CreateCollection {

	private final MongoTemplate mongoTemplate;
	
	@Value("${mongo.database.collection.notes.name:notes}")
	private String collName;
	
	public CreateCollection(MongoTemplate mongoTemplate) {
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
		mongoTemplate.createCollection(collName);
	}
	
	@RollbackExecution
	public void rollback() {
		mongoTemplate.dropCollection(collName);
	}
}

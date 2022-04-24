/**
 * 
 */
package com.tipikae.noteservice.migration;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.tipikae.noteservice.model.Note;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;

/**
 * Insert notes in dev profile.
 * @author tipikae
 * @version 1.0
 *
 */
@Profile("dev")
@ChangeUnit(id = "InsertDataDevProfile", order = "2", author = "tipikae")
public class InsertNotesDevProfile {
	
	private final static String NOTES_COLL_NAME = "notes";

	private final MongoTemplate mongoTemplate;
	
	public InsertNotesDevProfile(MongoTemplate mongoTemplate) {
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
	      	.forEach(notesDocument -> mongoTemplate.save(notesDocument, NOTES_COLL_NAME));
	}
	
	@RollbackExecution
	public void rollback() {
		mongoTemplate.remove(new Document(), NOTES_COLL_NAME);
	}
	
	private static List<Note> getNotesDocuments() {
		Note note1 = new Note(null, 1, LocalDate.of(2022, 01, 01), 
				"Patient: TestNone Practitioner's notes/recommendations: Patient states that they are 'feeling terrific' Weight at or below recommended level");
		Note note2 = new Note(null, 2, LocalDate.of(2022, 01, 01), 
				"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late");
		Note note3 = new Note(null, 2, LocalDate.of(2022, 02, 01), 
				"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic");
		Note note4 = new Note(null, 3, LocalDate.of(2022, 01, 01), 
				"Patient: TestInDanger Practitioner's notes/recommendations: Patient states that they are short term Smoker");
		Note note5 = new Note(null, 3, LocalDate.of(2022, 04, 01), 
						"Patient: TestInDanger Practitioner's notes/recommendations: Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high");
		Note note6 = new Note(null, 4, LocalDate.of(2022, 01, 01), 
				"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication");
		Note note7 = new Note(null, 4, LocalDate.of(2022, 02, 01), 
				"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that they are experiencing back pain when seated for a long time");
		Note note8 = new Note(null, 4, LocalDate.of(2022, 03, 01), 
				"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that they are a short term Smoker Hemoglobin A1C above recommended level");
		Note note9 = new Note(null, 4, LocalDate.of(2022, 02, 01), 
				"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
		
		List<Note> notes = List.of(note1, note2, note3, note4, note5, note6, note7, note8, note9);
		
		return notes;
	}
}

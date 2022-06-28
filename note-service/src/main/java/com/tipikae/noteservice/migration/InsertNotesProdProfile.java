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
 * Insert notes in prod profile.
 * @author tipikae
 * @version 1.0
 *
 */
@Profile("prod")
@ChangeUnit(id = "InsertNotesProdProfile", order = "2", author = "tipikae")
public class InsertNotesProdProfile {
	
	private final static String NOTES_COLL_NAME = "notes";

	private final MongoTemplate mongoTemplate;
	
	public InsertNotesProdProfile(MongoTemplate mongoTemplate) {
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
				"Le patient déclare qu'il « se sent très bien »\n"
				+ "Poids égal ou inférieur au poids recommandé");
		Note note2 = new Note(null, 1, LocalDate.of(2022, 02, 01), 
				"Le patient déclare qu'il se sent fatigué pendant la journée\n"
				+ "Il se plaint également de douleurs musculaires\n"
				+ "Tests de laboratoire indiquant une microalbumine élevée");
		Note note3 = new Note(null, 1, LocalDate.of(2022, 03, 01), 
				"Le patient déclare qu'il ne se sent pas si fatigué que ça\n"
				+ "Fumeur, il a arrêté dans les 12 mois précédents\n"
				+ "Tests de laboratoire indiquant que les anticorps sont élevés");
		Note note4 = new Note(null, 2, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il ressent beaucoup de stress au travail\n"
				+ "Il se plaint également que son audition est anormale dernièrement");
		Note note5 = new Note(null, 2, LocalDate.of(2022, 02, 01), 
				"Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\n"
				+ "Il remarque également que son audition continue d'être anormale");
		Note note6 = new Note(null, 2, LocalDate.of(2022, 03, 01), 
				"Tests de laboratoire indiquant une microalbumine élevée");
		Note note7 = new Note(null, 2, LocalDate.of(2022, 04, 01), 
				"Le patient déclare que tout semble aller bien\n"
				+ "Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé\n"
				+ "Le patient déclare qu’il fume depuis longtemps");
		Note note8 = new Note(null, 3, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il fume depuis peu");
		Note note9 = new Note(null, 3, LocalDate.of(2022, 02, 01), 
				"Tests de laboratoire indiquant une microalbumine élevée");
		Note note10 = new Note(null, 3, LocalDate.of(2022, 03, 01), 
				"Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière\n"
				+ "Il se plaint également de crises d’apnée respiratoire anormales\n"
				+ "Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
		Note note11 = new Note(null, 3, LocalDate.of(2022, 04, 01), 
				"Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
		Note note12 = new Note(null, 4, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il lui est devenu difficile de monter les escaliers\n"
				+ "Il se plaint également d’être essoufflé\n"
				+ "Tests de laboratoire indiquant que les anticorps sont élevés\n"
				+ "Réaction aux médicaments");
		Note note13 = new Note(null, 4, LocalDate.of(2022, 02, 01), 
				"Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps");
		Note note14 = new Note(null, 4, LocalDate.of(2022, 03, 01), 
				"Le patient déclare avoir commencé à fumer depuis peu\n"
				+ "Hémoglobine A1C supérieure au niveau recommandé");
		Note note15 = new Note(null, 5, LocalDate.of(2022, 01, 01), 
				"Le patient déclare avoir des douleurs au cou occasionnellement\n"
				+ "Le patient remarque également que certains aliments ont un goût différent\n"
				+ "Réaction apparente aux médicaments\n"
				+ "Poids corporel supérieur au poids recommandé");
		Note note16 = new Note(null, 5, LocalDate.of(2022, 02, 01), 
				"Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n"
				+ "Taille incluse dans la fourchette concernée");
		Note note17 = new Note(null, 5, LocalDate.of(2022, 03, 01), 
				"Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles\n"
				+ "Tests de laboratoire indiquant une microalbumine élevée\n"
				+ "Fumeur, il a arrêté dans les 12 mois précédents");
		Note note18 = new Note(null, 5, LocalDate.of(2022, 04, 01), 
				"Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite.\n"
				+ "Tests de laboratoire indiquant que les anticorps sont élevés");
		Note note19 = new Note(null, 6, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il se sent bien\n"
				+ "Poids corporel supérieur au poids recommandé");
		Note note20 = new Note(null, 6, LocalDate.of(2022, 02, 01), 
				"Le patient déclare qu'il se sent bien");
		Note note21 = new Note(null, 7, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il se réveille souvent avec une raideur articulaire\n"
				+ "Il se plaint également de difficultés pour s’endormir\n"
				+ "Poids corporel supérieur au poids recommandé\n"
				+ "Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
		Note note22 = new Note(null, 8, LocalDate.of(2022, 01, 01), 
				"Les tests de laboratoire indiquent que les anticorps sont élevés\n"
				+ "Hémoglobine A1C supérieure au niveau recommandé");
		Note note23 = new Note(null, 9, LocalDate.of(2022, 01, 01), 
				"Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires\n"
				+ "Hémoglobine A1C supérieure au niveau recommandé");
		Note note24 = new Note(null, 9, LocalDate.of(2022, 02, 01), 
				"Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée\n"
				+ "Il signale également que les produits du distributeur automatique ne sont pas bons\n"
				+ "Tests de laboratoire signalant des taux anormaux de cellules sanguines");
		Note note25 = new Note(null, 9, LocalDate.of(2022, 03, 01), 
				"Le patient signale qu'il est facilement irrité par des broutilles\n"
				+ "Il déclare également que l'aspirateur des voisins fait trop de bruit\n"
				+ "Tests de laboratoire indiquant que les anticorps sont élevés");
		Note note26 = new Note(null, 10, LocalDate.of(2022, 01, 01), 
				"Le patient déclare qu'il n'a aucun problème");
		Note note27 = new Note(null, 10, LocalDate.of(2022, 02, 01), 
				"Le patient déclare qu'il n'a aucun problème\n"
				+ "Taille incluse dans la fourchette concernée\n"
				+ "Hémoglobine A1C supérieure au niveau recommandé");
		Note note28 = new Note(null, 10, LocalDate.of(2022, 03, 01), 
				"Le patient déclare qu'il n'a aucun problème\n"
				+ "Poids corporel supérieur au poids recommandé\n"
				+ "Le patient a signalé plusieurs épisodes de vertige depuis sa dernière visite");
		Note note29 = new Note(null, 10, LocalDate.of(2022, 04, 01), 
				"Le patient déclare qu'il n'a aucun problème\n"
				+ "Tests de laboratoire indiquant une microalbumine élevée");
		
		List<Note> notes = List.of(note1, note2, note3, note4, note5, note6, note7, note8, note9, note10, 
				note11, note12, note13, note14, note15, note16, note17, note18, note19, note20, note21, 
				note22, note23, note24, note25, note26, note27, note28, note29);
		
		return notes;
	}
}

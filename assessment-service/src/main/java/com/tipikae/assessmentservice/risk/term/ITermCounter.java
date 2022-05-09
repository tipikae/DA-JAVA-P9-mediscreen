/**
 * 
 */
package com.tipikae.assessmentservice.risk.term;

import java.util.List;

import com.tipikae.assessmentservice.model.Note;

/**
 * Term counter.
 * @author tipikae
 * @version 1.0
 *
 */
public interface ITermCounter {

	/**
	 * Count terms in notes list.
	 * @param notes List
	 * @return int
	 */
	int countTerms(List<Note> notes);
}

/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

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
	 * @throws Exception
	 */
	int countTerms(List<Note> notes) throws Exception;
}

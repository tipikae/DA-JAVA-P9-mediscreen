/**
 * 
 */
package com.tipikae.noteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tipikae.noteservice.model.Note;

/**
 * Note service repository.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface INoteServiceRepository extends MongoRepository<Note, String> {

}

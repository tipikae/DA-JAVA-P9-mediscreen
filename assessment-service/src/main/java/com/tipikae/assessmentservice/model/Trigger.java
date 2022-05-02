/**
 * 
 */
package com.tipikae.assessmentservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trigger entity.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Table(name = "trigger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trigger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String term;
}

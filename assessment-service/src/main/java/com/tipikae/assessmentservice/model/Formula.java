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
 * Formula model.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Table(name = "formula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long risk_id;
	private String form;
}

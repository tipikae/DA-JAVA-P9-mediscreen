/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;

/**
 * Note controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private INoteServiceClient noteClient;
	
	@Autowired
	private IPatientServiceClient patientClient;

	/**
	 * Get a note.
	 * @param patId long
	 * @param id String
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/{patId}/{id}")
	public String getNote(
			@PathVariable("patId") @NotNull @Positive long patId, 
			@PathVariable("id") @NotBlank String id, 
			Model model) {
		return null;
	}

	/**
	 * Show note add form.
	 * @param patId long
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/add/{patId}")
	public String showAddFormNote(
			@PathVariable("patId") @NotNull @Positive long patId, 
			Model model) {
		return null;
	}
	
	/**
	 * Add a note.
	 * @param patId long
	 * @param newNoteDTO NewNoteDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/add/{patId}")
	public String addNote(
			@PathVariable("patId") @NotNull @Positive long patId, 
			@ModelAttribute("note") @Valid NewNoteDTO newNoteDTO,
			BindingResult result, 
    		Model model) {
		return null;
	}
	
	/**
	 * Show note update form.
	 * @param patId long
	 * @param id String
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/update/{patId}/{id}")
	public String showUpdateFormNote(
			@PathVariable("patId") @NotNull @Positive long patId, 
			@PathVariable("id") @NotBlank String id, 
			Model model) {
		return null;
	}
	
	/**
	 * Update a note.
	 * @param patId long
	 * @param id String
	 * @param updateNoteDTO UpdateNoteDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/update/{patId}/{id}")
	public String updateNote(
			@PathVariable("patId") @NotNull @Positive long patId, 
			@PathVariable("id") @NotBlank String id, 
    		@ModelAttribute("note") @Valid UpdateNoteDTO updateNoteDTO,
            BindingResult result, 
    		Model model) {
		return null;
	}
	
	/**
	 * Delete a note.
	 * @param patId long
	 * @param id String
	 * @return String
	 */
	@GetMapping("/delete/{patId}/{id}")
	public String deleteNote(
			@PathVariable("patId") @NotNull @Positive long patId,
			@PathVariable("id") @NotBlank String id) {
		return null;
	}
	
	private void log(String method, Exception e) {
		LOGGER.debug(method + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
	}
}

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
	 * @param id String
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/{id}")
	public String getNote(@PathVariable("id") @NotBlank String id, Model model) {
		return null;
	}

	/**
	 * Show note add form.
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/add")
	public String showAddFormNote(Model model) {
		return null;
	}
	
	/**
	 * Add a note.
	 * @param newNoteDTO NewNoteDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/add")
	public String addNote(
			@ModelAttribute("note") @Valid NewNoteDTO newNoteDTO,
			BindingResult result, 
    		Model model) {
		return null;
	}
	
	/**
	 * Show note update form.
	 * @param id String
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/update/{id}")
	public String showUpdateFormNote(@PathVariable("id") @NotBlank String id, Model model) {
		return null;
	}
	
	/**
	 * Update a note.
	 * @param id String
	 * @param updateNoteDTO UpdateNoteDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/update/{id}")
	public String updateNote(
			@PathVariable("id") @NotBlank String id, 
    		@ModelAttribute("note") @Valid UpdateNoteDTO updateNoteDTO,
            BindingResult result, 
    		Model model) {
		return null;
	}
	
	/**
	 * Delete a note.
	 * @param id String
	 * @return String
	 */
	@GetMapping("/delete/{id}")
	public String deleteNote(@PathVariable("id") @NotBlank String id) {
		return null;
	}
	
	private void log(String method, Exception e) {
		LOGGER.debug(method + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
	}
}

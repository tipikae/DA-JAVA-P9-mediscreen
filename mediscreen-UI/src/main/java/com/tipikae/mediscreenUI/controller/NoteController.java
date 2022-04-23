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
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.NoteNotFoundException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Note;

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
		LOGGER.info("Getting a note with patientId=" + patId + " and id=" + id);
		try {
			model.addAttribute("patient", patientClient.getPatient(patId));
			model.addAttribute("note", noteClient.getNote(id));
			return "note/get";
		} catch (NoteNotFoundException | PatientNotFoundException e) {
			log("getNote", e);
			return "error/404";
		} catch (Exception e) {
			log("getNote", e);
			return "error/400";
		}
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
		LOGGER.info("Getting add note form with patientId=" + patId);
		if(!model.containsAttribute("note")) {
    		model.addAttribute("note", new Note());
    	}
		
		try {
			model.addAttribute("patient", patientClient.getPatient(patId));
			return "note/add";
		} catch (PatientNotFoundException e) {
			log("showAddFormNote", e);
			return "error/404";
		} catch (Exception e) {
			log("showAddFormNote", e);
			return "error/400";
		}
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
		LOGGER.info("Adding a note for patientId=" + patId);
		if(result.hasErrors()) {
    		StringBuilder sb = new StringBuilder();
    		result.getAllErrors().stream().forEach(e -> sb.append(e.getDefaultMessage() + " "));
			LOGGER.debug("addNote: has errors:" + sb);
			try {
				model.addAttribute("patient", patientClient.getPatient(patId));
				return "note/add";
			} catch (PatientNotFoundException e1) {
				log("addNote", e1);
				return "error/404";
			} catch (Exception e1) {
				log("addNote", e1);
				return "error/400";
			}
    	}
		
		try {
			noteClient.addNote(newNoteDTO);
			return "redirect:/patient/" + patId + "?success=New note added.";
		} catch (BadRequestException e) {
			log("addNote", e);
			return "redirect:/patient/" + patId + "?error=Request error.";
		} catch (Exception e) {
			log("addNote", e);
			return "redirect:/patient/" + patId + "?error=An error occured.";
		}
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
		LOGGER.info("Getting update form with patientId=" + patId + " and id=" + id);
		try {
			model.addAttribute("patient", patientClient.getPatient(patId));
			model.addAttribute("note", noteClient.getNote(id));
			return "note/update";
		} catch (PatientNotFoundException | NoteNotFoundException e) {
			log("showUpdateFormNote", e);
			return "error/404";
		} catch (Exception e) {
			log("showUpdateFormNote", e);
			return "error/400";
		}
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
		LOGGER.info("Updating a note with patientId=" + patId + " and id=" + id);
		if(result.hasErrors()) {
    		StringBuilder sb = new StringBuilder();
    		result.getAllErrors().stream().forEach(e -> sb.append(e.getDefaultMessage() + " "));
			LOGGER.debug("updateNote: has errors:" + sb);
			try {
				model.addAttribute("patient", patientClient.getPatient(patId));
				return "redirect:/note/update/" + patId + "/" + id + "?error=" + sb;
			} catch (PatientNotFoundException e1) {
				log("updateNote", e1);
				return "redirect:/note/" + patId + "/" + id + "?error=Patient not found.";
			} catch (Exception e1) {
				log("updateNote", e1);
				return "redirect:/note/" + patId + "/" + id + "?error=An error occured.";
			}
    	}
		
		try {
			noteClient.updateNote(id, updateNoteDTO);
			return "redirect:/patient/" + patId + "?success=Note updated.";
		} catch (NoteNotFoundException e) {
			log("updateNote", e);
			return "redirect:/patient/" + patId + "?error=Note not found.";
		} catch (BadRequestException e) {
			log("updateNote", e);
			return "redirect:/patient/" + patId + "?error=Request error.";
		} catch (Exception e) {
			log("updateNote", e);
			return "redirect:/patient/" + patId + "?error=An error occured.";
		}
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
		LOGGER.info("Deleting note with id=" + id);
		try {
			noteClient.deleteNote(id);
			return "redirect:/patient/" + patId + "?success=Note deleted.";
		} catch (NoteNotFoundException e) {
			log("deleteNote", e);
			return "redirect:/patient/" + patId + "?error=Note not found.";
		} catch (BadRequestException e) {
			log("deleteNote", e);
			return "redirect:/patient/" + patId + "?error=Request error.";
		} catch (Exception e) {
			log("deleteNote", e);
			return "redirect:/patient/" + patId + "?error=An error occured.";
		}
	}
	
	private void log(String method, Exception e) {
		LOGGER.debug(method + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
	}
}

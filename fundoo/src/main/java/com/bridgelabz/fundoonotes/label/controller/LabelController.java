package com.bridgelabz.fundoonotes.label.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.service.LabelServiceImpl;
import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.notes.controller.NoteController;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.response.Response;
@RequestMapping(value="/user/label")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
@RestController
public class LabelController {
	
	
	
	@Autowired
	LabelServiceImpl labelserviceImpl;
	
	
	
	Logger logger=LoggerFactory.getLogger(NoteController.class);
	
	@PostMapping("/create")
	public Response createLabel(@RequestHeader String token,@RequestBody LabelDto labelDto)
	{
		logger.info("create label");
		return labelserviceImpl.createlabel(labelDto, token);
		
	}
	
	@PutMapping("/update")
	public Response updateLabel(@RequestHeader String token,@RequestBody Labels label)
	{
		logger.info("update label");
		return labelserviceImpl.updateLabel(token, label);
	}
	
	@DeleteMapping("/delete")
	public Response deleteUserLabel(@RequestHeader String token,@RequestParam Long labelId)
	{
		logger.info("delete user label");
		return labelserviceImpl.deleteUserlabel(token, labelId);
    }
	
	@PostMapping("/add/label/note")
	public Response addLabelToNote(@RequestParam Long noteId,@RequestHeader String token,@RequestParam String labelTitle)
	{
		logger.info("add Label To Note");
		return labelserviceImpl.addLabelToNote(noteId, token, labelTitle);
		
	}
	
	@GetMapping("/getall/user/label")
	public List<Labels> getAllUserLabel(@RequestHeader String token)
	
	{
		logger.info("get all user label");
		return labelserviceImpl.getAllUserLabel(token);
	}
	
	@DeleteMapping("/delete/note/label")
	public Response deleteNoteLabel(@RequestHeader String token, @RequestParam Long noteId,@RequestParam Long labelId)
	{
		logger.info("delete note label");
		return labelserviceImpl.deleteNoteLabel(token, noteId, labelId);
	}
	
	@GetMapping("/get/label/notes")
	public List<Notes> getLabelNotes(@RequestHeader String token,@RequestParam Long labelId)
	{
		logger.info("get label note");
		return labelserviceImpl.getLabelNote(token, labelId);
	}
	
}

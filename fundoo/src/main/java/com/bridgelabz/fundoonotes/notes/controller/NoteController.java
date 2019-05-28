package com.bridgelabz.fundoonotes.notes.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.service.NoteServiceImpl;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.ResponseToken;
@RestController
@RequestMapping(value="/user/note")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
public class NoteController {
	@Autowired
	NoteServiceImpl noteServiceImpl;
	Logger logger=org.slf4j.LoggerFactory.getLogger(NoteController.class);
	
	@PostMapping(value="/createNote")
	public ResponseEntity<Response> createNote(HttpServletRequest request,@RequestBody NoteDto noteDto,@RequestHeader String token)
	{
		logger.info("token---"+token);
	    logger.info((String)request.getAttribute("jwt_token"));
		System.out.println("title is"+noteDto.getTitle().length());
		Response response= noteServiceImpl.createNote(noteDto, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		
	}
	@PutMapping(value="/update")
	public ResponseEntity<Response> updateNote(@RequestParam Long noteId,@RequestBody NoteDto noteDto,@RequestHeader String token)
	{
		System.out.println("inside update");
		logger.info("note details"+noteDto.toString());
		logger.info("note id"+noteId.toString());
		logger.info("update");
		Response response=noteServiceImpl.updateNote(noteId, noteDto, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
			}
	
	@PutMapping(value="/trash")
	public ResponseEntity<Response>trashNote(@RequestParam Long noteId,@RequestHeader String token)

	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.trashNote(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
			}
	@PutMapping(value="/isPin")
	public ResponseEntity<Response>isPin(@RequestParam Long noteId,@RequestHeader String token)
	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.isPing(noteId,token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping(value="/isArchive")
	public ResponseEntity<Response>isArchive(@RequestParam Long noteId,@RequestHeader String token)
	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.archive(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@GetMapping(value="/getnote")
	public List<Notes> getNotes(@RequestHeader String token,@RequestParam boolean trash,@RequestParam boolean archive)
	{
		System.out.println("User get note");
		logger.info("get all notes");
		List<Notes> note=noteServiceImpl.getAllUserNotes(token,trash,archive);
		return note;
	}
	@GetMapping(value="/getTrash")
	public List<Notes> getTrash(@RequestHeader String token)
	
	{	logger.info("get all trash notes");
		List<Notes> note=noteServiceImpl.getTrash(token);
		return note;
	}
	
@GetMapping(value="/getUntrash")
public List<Notes> getUnTrash(@RequestHeader String token)
	
	{
		logger.info("get all untrash notes");
		List<Notes> note=noteServiceImpl.getUnTrash(token);
		return note;
	}
	
	
	@GetMapping(value="/getPin")
	public List<Notes> getisPinNotes(@RequestHeader String token)
	{
		logger.info("get all pin notes");
		List<Notes> note=noteServiceImpl.getPingNotes(token);
		return note;
	}
	
	@GetMapping(value="/getUnPin")
	public List<Notes> getisUnPinNotes(@RequestHeader String token)
	{
		logger.info("get all unpin notes");
		List<Notes> note=noteServiceImpl.getunPinNotes(token);
		return note;
	}
	
	@GetMapping(value="/getarchive")
	public List<Notes> getArchiveNotes(@RequestHeader String token)
	{
		logger.info("get all archive notes");
		List<Notes> note=noteServiceImpl.getArchive(token);
		return note;
	}
	
	@GetMapping(value="/getUnarchive")
	public List<Notes> getUnArchiveNotes(@RequestHeader String token)
	{
		logger.info("get all Unarchive notes");
		List<Notes> note=noteServiceImpl.getUnArchive(token);
		return note;
	}
	
	@DeleteMapping(value="/deletenote")
	public ResponseEntity<Response> deleteNote(@RequestParam Long noteId,@RequestHeader String token)
	{
		logger.info("note delete");
		Response response=noteServiceImpl.deleteNotes(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
		}




package com.joh.bhms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.bhms.exception.AttachmentNotFoundException;
import com.joh.bhms.model.AttachedFile;
import com.joh.bhms.service.AttachedFileService;

@Controller
@RequestMapping(path = "/attachedFiles")
public class AttachedFileControlller {

	private static final Logger logger = Logger.getLogger(AttachedFileControlller.class);

	@Autowired
	private AttachedFileService attachedFileService;

	@GetMapping(path = "/{size}/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable int size, @PathVariable int id) {
		logger.info("getImage->fired");
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		try {
			byte[] media = null;
			if (size == 0) {
				media = attachedFileService.getAttachentFile(id);
			} else {
				media = attachedFileService.getAttachedFileSmall(id);
			}

			AttachedFile attachedFile = attachedFileService.findOne(id);

			switch (attachedFile.getExtension()) {
			case "JPG":
			case "JPEG":
				headers.setContentType(MediaType.IMAGE_JPEG);
				break;
			case "PNG":
				headers.setContentType(MediaType.IMAGE_PNG);
				break;

			case "GIF":
				headers.setContentType(MediaType.IMAGE_GIF);
				break;
			}

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
			return responseEntity;

		} catch (AttachmentNotFoundException e) {
			logger.info("exceptin occured->AttachmentNotFoundException");

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
			return responseEntity;
		}

	}
}

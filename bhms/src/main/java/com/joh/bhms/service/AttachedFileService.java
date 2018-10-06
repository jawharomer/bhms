package com.joh.bhms.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.joh.bhms.exception.AttachmentNotFoundException;
import com.joh.bhms.model.AttachedFile;

public interface AttachedFileService {

	AttachedFile save(MultipartFile multipartFile) throws IOException;

	byte[] getAttachentFile(int id) throws AttachmentNotFoundException;

	AttachedFile findOne(int id);

	byte[] getAttachedFileSmall(int id) throws AttachmentNotFoundException;

	void delete(int id);

}

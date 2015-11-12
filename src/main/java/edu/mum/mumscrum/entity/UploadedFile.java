package edu.mum.mumscrum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class UploadedFile {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Transient
	private MultipartFile file;
	private String absolutePath;
	private String relativePathFromContext;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String imageAbsolutePath) {
		this.absolutePath = imageAbsolutePath;
	}
	public String getRelativePathFromContext() {
		return relativePathFromContext;
	}
	public void setRelativePathFromContext(String relativePathFromContext) {
		this.relativePathFromContext = relativePathFromContext;
	}	
}

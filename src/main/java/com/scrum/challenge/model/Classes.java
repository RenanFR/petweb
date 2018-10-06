package com.scrum.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tbl_classes")
public class Classes implements GrantedAuthority	{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ObjectId objectId;	
	
	private String description;

	public Classes(String description) {
		this.description = description;
	}

	public Classes(ObjectId objectId, String description) {
		this.objectId = objectId;
		this.description = description;
	}
	
	public Classes() {
	}

	@Override
	public String getAuthority() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Classes generateId() {
		setObjectId(new ObjectId());
		return this;
	}	
	
	@Override
	public String toString() {
		return description;
	}

}

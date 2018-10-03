package com.scrum.challenge.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bson.types.ObjectId;

@Entity
@Table(name = "tbl_quest")
public class Quest implements Serializable	{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ObjectId objectId;
	
	private String title;
	
	private String description;
	
	private LocalDateTime beginDate;
	
	private LocalDateTime expectedEndDate;
	
	private LocalDateTime endDate;
	
	@ManyToOne
	private Sprint sprint;
	
	@ManyToOne
	private Hero assignedHero;

	public Quest() {
	}

	public Quest(ObjectId objectId, String title, String description, LocalDateTime beginDate,
			LocalDateTime expectedEndDate, LocalDateTime endDate, Sprint sprint, Hero assignedHero) {
		this.objectId = objectId;
		this.title = title;
		this.description = description;
		this.beginDate = beginDate;
		this.expectedEndDate = expectedEndDate;
		this.endDate = endDate;
		this.sprint = sprint;
		this.assignedHero = assignedHero;
	}
	
	public Quest(ObjectId objectId, String title, String description, LocalDateTime beginDate,
			LocalDateTime expectedEndDate, LocalDateTime endDate) {
		this.objectId = objectId;
		this.title = title;
		this.description = description;
		this.beginDate = beginDate;
		this.expectedEndDate = expectedEndDate;
		this.endDate = endDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDateTime beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDateTime getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(LocalDateTime expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Hero getAssignedHero() {
		return assignedHero;
	}

	public void setAssignedHero(Hero assignedHero) {
		this.assignedHero = assignedHero;
	}
	
	public Quest generateId() {
		setObjectId(new ObjectId());
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quest [title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quest other = (Quest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

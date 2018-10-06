package com.scrum.challenge.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbl_hero")
public class Hero implements UserDetails	{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private ObjectId objectId;
	
	private String name;
	
	private BigDecimal xp;
	
	private String password;
	
	@ElementCollection(targetClass = Skills.class)
	@Enumerated(EnumType.STRING)
	private List<Skills> skills;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Classes> classes;
	
	public Hero() {
	}

	public Hero(String name) {
		this.name = name;
	}

	public Hero(ObjectId objectId) {
		this.objectId = objectId;
	}

	public Hero(ObjectId objectId, String name) {
		this.objectId = objectId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getXp() {
		return xp;
	}

	public void setXp(BigDecimal xp) {
		this.xp = xp;
	}
	
	public List<Skills> getSkills() {
		return skills;
//		if (skills == null) {
//			return null;
//		}
//		return skillsToMap();
	}

	public Map<String, String> skillsToMap() {
		return skills.stream().collect(Collectors.toMap(s -> s.name(), s -> s.getDescription()));
	}
	
	public List<Skills> skillsToList() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Classes> getClasses() {
		return classes;
	}
//		if (classes == null) {
//			return null;
//		}
//		return classesToMap();
//	}

	public Map<String, String> classesToMap() {
		Map<String, String> collect = classes.stream().collect(Collectors.toMap(c -> c.getObjectId().toHexString(), c -> c.getDescription()));
		return collect;
	}
	
	public List<Classes> classesToList() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public Hero generateId() {
		setObjectId(new ObjectId());
		return this;
	}	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return classes;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
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
		Hero other = (Hero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}

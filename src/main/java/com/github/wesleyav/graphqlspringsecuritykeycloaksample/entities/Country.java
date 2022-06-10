package com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long country_id;

	@Column(name = "country")
	private String country;

	@Column(name = "last_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_update;

	@OneToMany(mappedBy = "country_id")
	@JsonIgnore
	private List<City> listCity = new ArrayList<>();

}

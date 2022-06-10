package com.github.wesleyav.graphqlspringsecuritykeycloaksample.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long city_id;

	@Column(name = "city")
	private String city;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country_id;

	@Column(name = "last_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_update;

}


package com.timothypolke.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="table_Theme")
public class Theme implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="themeID",length=16)
	private UUID themeID;
	
	@Column(name="themeAlias",length=255)
	private String themeAlias;

	@Column(name="themeDate",length=255)
	private String themeDate;
	
	@Column(name="foreground_red",length=3)
	private String foreground_red;
	@Column(name="foreground_green",length=3)
	private String foreground_green;
	@Column(name="foreground_blue",length=3)
	private String foreground_blue;

	@Column(name="background_red",length=3)
	private String background_red;
	@Column(name="background_green",length=3)
	private String background_green;
	@Column(name="background_blue",length=3)
	private String background_blue;
	
	@Column(name="highlight_red",length=3)
	private String highlight_red;
	@Column(name="highlight_green",length=3)
	private String highlight_green;
	@Column(name="highlight_blue",length=3)
	private String highlight_blue;
}
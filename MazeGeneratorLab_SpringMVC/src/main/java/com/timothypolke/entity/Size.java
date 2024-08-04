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
@Table(name="table_Size")
public class Size implements Serializable {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="sizeID",length=16)
	private UUID sizeID;
	
	@Column(name="sizeAlias",length=255)
	private String sizeAlias;
	
	@Column(name="sizeDate",length=255)
	private String sizeDate;
	
	@Column(name="columnCount",length=3)
	private String columnCount;
	@Column(name="rowCount",length=3)
	private String rowCount;
	
	@Column(name="wallSize",length=3)
	private String wallSize;
	@Column(name="cellSize",length=3)
	private String cellSize;
}
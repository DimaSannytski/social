package com.ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ua.entity.enums.Country;
import com.ua.entity.enums.Male;
import com.ua.entity.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "user", indexes =  @Index(columnList = "email,firstName,lastName"))
@Getter @Setter
@NoArgsConstructor

public class Person extends BaseEntity{
	
	
	private String firstName;
	
	
	private String lastName;

	private String token;
	private boolean isActivated;
	private String email;

	private boolean isBanned;
	
	private String password;
	
	private int age;
	
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private Male male;
	@Enumerated(EnumType.STRING)
	private Country country;
	
	private String about;

	private String photo;
	
	@OneToMany(mappedBy="afriend",cascade = CascadeType.ALL)
	List<Friend> friendsa= new ArrayList<Friend>();
	@OneToMany(mappedBy="bfriend",cascade = CascadeType.ALL)
	List<Friend> friendsb = new ArrayList<Friend>();
	@OneToMany(mappedBy = "fromPerson",cascade = CascadeType.ALL)
	List<Wall> wallMassage = new ArrayList<Wall>();
	@OneToMany(mappedBy="person",cascade = CascadeType.ALL)
	List<Wall> wall = new ArrayList<Wall>();
	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
	List<WallComents> wallComents = new ArrayList<>();
	@OneToMany(mappedBy="person",cascade = CascadeType.ALL)
	List<Photo> photos = new ArrayList<Photo>();
	@OneToMany(mappedBy="person",cascade = CascadeType.ALL)
	List<PhotoAlbum> photoAlbum = new ArrayList<PhotoAlbum>();
	@OneToMany(mappedBy="person",cascade = CascadeType.ALL)
	List<PhotoComent> photoComent = new ArrayList<PhotoComent>();
	@OneToMany(mappedBy="aPerson",cascade = CascadeType.ALL)
	List<MyMessage> aMessage = new ArrayList<MyMessage>();

	@OneToMany(mappedBy="aPerson",cascade = CascadeType.ALL)
	List<Dialog> aDialog = new ArrayList<Dialog>();
	@OneToMany(mappedBy="bPerson",cascade = CascadeType.ALL)
	List<Dialog> bDialog = new ArrayList<Dialog>();
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(
	        name = "Person_Spilnoty", 
	        joinColumns = { @JoinColumn(name = "person_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "spilnoty_id") }
	    )
	private List<Spilnoty> spilnotys = new ArrayList<Spilnoty>();
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(
	        name = "Person_Like", 
	        joinColumns = { @JoinColumn(name = "person_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "photo_id") }
	    )
	private List<Photo> photoLikes = new ArrayList<Photo>();


	 @OneToMany(mappedBy="admin",cascade = CascadeType.ALL)
	 List<Spilnoty> spilnotyAdmin = new ArrayList<Spilnoty>();

	
	

}

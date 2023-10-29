package Models;

import java.sql.Date;

public class Compte {
	
	private Integer id;
	private Date date_creation;
	private Date date_modification;
	private String username;
	private String password;
	private boolean is_online;
	private Integer id_employee;
	private Integer id_Administrateur; 
	private Integer id_typecompte;
	private String StringTypecompte;
	private String StringAdministrateur;
	private String StringNomEmp;


	
	

	public Compte(Integer id, Date date_creation, Date date_modification, String username, String password,
			boolean is_online, Integer id_employee, Integer id_Administrateur, Integer id_typecompte,
			String stringTypecompte, String stringAdministrateur, String stringNomEmp) {
		super();
		this.id = id;
		this.date_creation = date_creation;
		this.date_modification = date_modification;
		this.username = username;
		this.password = password;
		this.is_online = is_online;
		this.id_employee = id_employee;
		this.id_Administrateur = id_Administrateur;
		this.id_typecompte = id_typecompte;
		StringTypecompte = stringTypecompte;
		StringAdministrateur = stringAdministrateur;
		StringNomEmp = stringNomEmp;
	}


	public String getStringNomEmp() {
		return StringNomEmp;
	}


	public void setStringNomEmp(String stringNomEmp) {
		StringNomEmp = stringNomEmp;
	}


	public Compte(Integer id, Date date_creation, Date date_modification, String username, String password,
			boolean is_online, Integer id_employee, Integer id_Administrateur, Integer id_typecompte) {
		super();
		this.id = id;
		this.date_creation = date_creation;
		this.date_modification = date_modification;
		this.username = username;
		this.password = password;
		this.is_online = is_online;
		this.id_employee = id_employee;
		this.id_Administrateur = id_Administrateur;
		this.id_typecompte = id_typecompte;
	}
	
	
	public Compte(Date date_creation, Date date_modification, String username, String password,
			boolean is_online, Integer id_employee, Integer id_Administrateur, Integer id_typecompte) {
		super();
		this.date_creation = date_creation;
		this.date_modification = date_modification;
		this.username = username;
		this.password = password;
		this.is_online = is_online;
		this.id_employee = id_employee;
		this.id_Administrateur = id_Administrateur;
		this.id_typecompte = id_typecompte;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Date getDate_creation() {
		return date_creation;
	}




	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}






	public boolean getIs_online() {
		return is_online;
	}




	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}




	public Integer getId_employee() {
		return id_employee;
	}




	public void setId_employee(Integer id_employee) {
		this.id_employee = id_employee;
	}




	public Integer getId_Administrateur() {
		return id_Administrateur;
	}




	public void setId_Administrateur(Integer id_Administrateur) {
		this.id_Administrateur = id_Administrateur;
	}




	public Integer getId_typecompte() {
		return id_typecompte;
	}




	public void setId_typecompte(Integer id_typecompte) {
		this.id_typecompte = id_typecompte;
	}




	public Integer getIdTypecompte() {
		return id_typecompte;
	}




	public void setTypecompte(Integer id_typecompte) {
		this.id_typecompte = id_typecompte;
	}




	public Date getDate_modification() {
		return date_modification;
	}




	public void setDate_modification(Date date_modification) {
		this.date_modification = date_modification;
	}


	

	public String getStringTypecompte() {
		return StringTypecompte;
	}


	public void setStringTypecompte(String stringTypecompte) {
		StringTypecompte = stringTypecompte;
	}


	public String getStringAdministrateur() {
		return StringAdministrateur;
	}


	public void setStringAdministrateur(String stringAdministrateur) {
		StringAdministrateur = stringAdministrateur;
	}
	



}

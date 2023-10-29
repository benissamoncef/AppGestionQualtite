package Models;

import java.sql.Date;

public class Auth {
	
	private Integer id;
	private Date date_login;
	private Date date_logout;
	private int id_compte;
	
	
	public Auth(Integer id, Date date_login, Date date_logout, int id_compte) {
		super();
		this.id = id;
		this.date_login = date_login;
		this.date_logout = date_logout;
		this.id_compte = id_compte;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate_login() {
		return date_login;
	}


	public void setDate_login(Date date_login) {
		this.date_login = date_login;
	}


	public Date getDate_logout() {
		return date_logout;
	}


	public void setDate_logout(Date date_logout) {
		this.date_logout = date_logout;
	}


	public int getIdCompte() {
		return id_compte;
	}


	public void setIdCompte(int id_compte) {
		this.id_compte = id_compte;
	}

}

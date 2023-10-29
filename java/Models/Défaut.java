package Models;

public class Défaut {
	
	private Integer code;
	private String Description;


	public Défaut(Integer code, String description) {
		super();
		this.code = code;
		Description = description;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	
	

}

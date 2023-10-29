package Models;

public class TypeCompte {
	
	private Integer id;
	private String labelType;
	private String description;
	
	
	public TypeCompte(Integer id, String labelType, String description) {
		super();
		this.id = id;
		this.labelType = labelType;
		this.description = description;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLabelType() {
		return labelType;
	}


	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}

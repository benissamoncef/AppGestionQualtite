package Models;

import java.sql.Time;


public class Shift {
	
	private Integer id;
	private String LabelShift;
	private Time startShift;
	private Time endShift;
	private String commentaire;
	
	
	
	public Shift(Integer id, String labelShift, Time startShift, Time endShift, String commentaire) {
		super();
		this.id = id;
		LabelShift = labelShift;
		this.startShift = startShift;
		this.endShift = endShift;
		this.commentaire = commentaire;
	}
	
	

	public Shift(String labelShift, Time startShift, Time endShift, String commentaire) {
		super();
		LabelShift = labelShift;
		this.startShift = startShift;
		this.endShift = endShift;
		this.commentaire = commentaire;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLabelShift() {
		return LabelShift;
	}



	public void setLabelShift(String labelShift) {
		LabelShift = labelShift;
	}



	public Time getStartShift() {
		return startShift;
	}



	public void setStartShift(Time startShift) {
		this.startShift = startShift;
	}



	public Time getEndShift() {
		return endShift;
	}



	public void setEndShift(Time endShift) {
		this.endShift = endShift;
	}



	public String getCommentaire() {
		return commentaire;
	}



	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	

}

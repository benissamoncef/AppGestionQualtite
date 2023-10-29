package Models;

public class ReponseControles {
	
	private Integer id_reponse_formulaire;

	// Questions Contrôles dimentionnel et visuel:  
	
	private Integer id;
	private Integer Q1;
	private Integer Q2;
	private Integer Q3;
	private Integer Q4;
	private Integer Q5;
	private Integer Q6;
	private Integer Q7;
	private Integer Q8;
	private Integer Q9;
	private Integer Q10;
	
	// Matricule des postes de contrôl : 

	private String Q11;
	private String Q12 ;
	private String Q13;
	private String Q14;
	private String Q15;
	private String Q16;
	
	// Questions du status:
	
	private Boolean Q17;
	private Boolean Q18;
	private Boolean Q19;
	
	// Serial number des pièces audités durant l'audit produit (4 digits) :
	
	private String SerialNumbers;

	

	public ReponseControles(Integer id_reponse_formulaire, Integer id, Integer q1, Integer q2, Integer q3,
			Integer q4, Integer q5, Integer q6, Integer q7, Integer q8, Integer q9, Integer q10, String q11, String q12,
			String q13, String q14, String q15, String q16, Boolean q17, Boolean q18, Boolean q19,
			String serialNumbers) {
		super();
		this.id_reponse_formulaire = id_reponse_formulaire;
		this.id = id;
		Q1 = q1;
		Q2 = q2;
		Q3 = q3;
		Q4 = q4;
		Q5 = q5;
		Q6 = q6;
		Q7 = q7;
		Q8 = q8;
		Q9 = q9;
		Q10 = q10;
		Q11 = q11;
		Q12 = q12;
		Q13 = q13;
		Q14 = q14;
		Q15 = q15;
		Q16 = q16;
		Q17 = q17;
		Q18 = q18;
		Q19 = q19;
		SerialNumbers = serialNumbers;
	}
	
	
	

	public ReponseControles(Integer id_reponse_formulaire, Integer q1, Integer q2, Integer q3, Integer q4,
			Integer q5, Integer q6, Integer q7, Integer q8, Integer q9, Integer q10, String q11, String q12, String q13,
			String q14, String q15, String q16, Boolean q17, Boolean q18, Boolean q19, String serialNumbers) {
		super();
		this.id_reponse_formulaire = id_reponse_formulaire;
		Q1 = q1;
		Q2 = q2;
		Q3 = q3;
		Q4 = q4;
		Q5 = q5;
		Q6 = q6;
		Q7 = q7;
		Q8 = q8;
		Q9 = q9;
		Q10 = q10;
		Q11 = q11;
		Q12 = q12;
		Q13 = q13;
		Q14 = q14;
		Q15 = q15;
		Q16 = q16;
		Q17 = q17;
		Q18 = q18;
		Q19 = q19;
		SerialNumbers = serialNumbers;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQ1() {
		return Q1;
	}

	public void setQ1(Integer q1) {
		Q1 = q1;
	}

	public Integer getQ2() {
		return Q2;
	}

	public void setQ2(Integer q2) {
		Q2 = q2;
	}

	public Integer getQ3() {
		return Q3;
	}

	public void setQ3(Integer q3) {
		Q3 = q3;
	}

	public Integer getQ4() {
		return Q4;
	}

	public void setQ4(Integer q4) {
		Q4 = q4;
	}

	public Integer getQ5() {
		return Q5;
	}

	public void setQ5(Integer q5) {
		Q5 = q5;
	}

	public Integer getQ6() {
		return Q6;
	}

	public void setQ6(Integer q6) {
		Q6 = q6;
	}

	public Integer getQ7() {
		return Q7;
	}

	public void setQ7(Integer q7) {
		Q7 = q7;
	}

	public Integer getQ8() {
		return Q8;
	}

	public void setQ8(Integer q8) {
		Q8 = q8;
	}

	public Integer getQ9() {
		return Q9;
	}

	public void setQ9(Integer q9) {
		Q9 = q9;
	}

	public Integer getQ10() {
		return Q10;
	}

	public void setQ10(Integer q10) {
		Q10 = q10;
	}

	public String getQ11() {
		return Q11;
	}

	public void setQ11(String q11) {
		Q11 = q11;
	}

	public String getQ12() {
		return Q12;
	}

	public void setQ12(String q12) {
		Q12 = q12;
	}

	public String getQ13() {
		return Q13;
	}

	public void setQ13(String q13) {
		Q13 = q13;
	}

	public String getQ14() {
		return Q14;
	}

	public void setQ14(String q14) {
		Q14 = q14;
	}

	public String getQ15() {
		return Q15;
	}

	public void setQ15(String q15) {
		Q15 = q15;
	}

	public String getQ16() {
		return Q16;
	}

	public void setQ16(String q16) {
		Q16 = q16;
	}

	public Boolean getQ17() {
		return Q17;
	}

	public void setQ17(Boolean q17) {
		Q17 = q17;
	}

	public Boolean getQ18() {
		return Q18;
	}

	public void setQ18(Boolean q18) {
		Q18 = q18;
	}

	public Boolean getQ19() {
		return Q19;
	}

	public void setQ19(Boolean q19) {
		Q19 = q19;
	}

	public String getSerialNumbers() {
		return SerialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		SerialNumbers = serialNumbers;
	}




	public Integer getId_reponse_formulaire() {
		return id_reponse_formulaire;
	}




	public void setId_reponse_formulaire(Integer id_reponse_formulaire) {
		this.id_reponse_formulaire = id_reponse_formulaire;
	}

	@Override
	public String toString() {
		return "ReponseControles{" +
				"id_reponse_formulaire=" + id_reponse_formulaire +
				", id=" + id +
				", Q1=" + Q1 +
				", Q2=" + Q2 +
				", Q3=" + Q3 +
				", Q4=" + Q4 +
				", Q5=" + Q5 +
				", Q6=" + Q6 +
				", Q7=" + Q7 +
				", Q8=" + Q8 +
				", Q9=" + Q9 +
				", Q10=" + Q10 +
				", Q11='" + Q11 + '\'' +
				", Q12='" + Q12 + '\'' +
				", Q13='" + Q13 + '\'' +
				", Q14='" + Q14 + '\'' +
				", Q15='" + Q15 + '\'' +
				", Q16='" + Q16 + '\'' +
				", Q17=" + Q17 +
				", Q18=" + Q18 +
				", Q19=" + Q19 +
				", SerialNumbers='" + SerialNumbers + '\'' +
				'}';
	}
}

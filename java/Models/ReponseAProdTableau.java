package Models;

public class ReponseAProdTableau {

    /*------------------------ATRIBUTS----------------------*/

    // l'identifiant de la reponse:

    private int idReponse;

    // les references:

    private int idRef1;
    private int idRef2;
    private int idRef3;
    private int idRef4;

    // les descriptions des defaults:

    private Integer idDD1;
    private Integer idDD2;
    private Integer idDD3;
    private Integer idDD4;

    // les serials numbers:

    private String sn1;
    private String sn2;
    private String sn3;
    private String sn4;

    // les numeros des tableaux:

    private String nt1;
    private String nt2;
    private String nt3;
    private String nt4;

    // les statuts:
    private String statut1;
    private String statut2;
    private String statut3;
    private String statut4;


    //id question et l'id de la reponse formulaire:

    private int idQuestion;
    private int idRF;
    private boolean isTAP;

    /*------------------------CONSTRUCTEURS----------------------*/


    public ReponseAProdTableau(int idReponse, int idRef1, int idRef2, int idRef3, int idRef4, Integer idDD1, Integer idDD2, Integer idDD3, Integer idDD4, String statut1, String statut2, String statut3, String statut4, String sn1, String sn2, String sn3, String sn4, String nt1, String nt2, String nt3, String nt4, int idQuestion, int idRF, boolean isTAP) {
        this.idReponse = idReponse;
        this.idRef1 = idRef1;
        this.idRef2 = idRef2;
        this.idRef3 = idRef3;
        this.idRef4 = idRef4;
        this.idDD1 = idDD1;
        this.idDD2 = idDD2;
        this.idDD3 = idDD3;
        this.idDD4 = idDD4;
        this.sn1 = sn1;
        this.sn2 = sn2;
        this.sn3 = sn3;
        this.sn4 = sn4;
        this.nt1 = nt1;
        this.nt2 = nt2;
        this.nt3 = nt3;
        this.nt4 = nt4;
        this.statut1 = statut1;
        this.statut2 = statut2;
        this.statut3 = statut3;
        this.statut4 = statut4;
        this.idQuestion = idQuestion;
        this.idRF = idRF;
        this.isTAP = isTAP;
    }

    public ReponseAProdTableau(int idRef1, int idRef2, int idRef3, int idRef4, Integer idDD1, Integer idDD2, Integer idDD3, Integer idDD4,String statut1, String statut2, String statut3, String statut4, String sn1, String sn2, String sn3, String sn4, String nt1, String nt2, String nt3, String nt4, int idQuestion, int idRF, boolean isTAP) {
        this.idRef1 = idRef1;
        this.idRef2 = idRef2;
        this.idRef3 = idRef3;
        this.idRef4 = idRef4;
        this.idDD1 = idDD1;
        this.idDD2 = idDD2;
        this.idDD3 = idDD3;
        this.idDD4 = idDD4;
        this.sn1 = sn1;
        this.sn2 = sn2;
        this.sn3 = sn3;
        this.sn4 = sn4;
        this.nt1 = nt1;
        this.nt2 = nt2;
        this.nt3 = nt3;
        this.nt4 = nt4;
        this.statut1 = statut1;
        this.statut2 = statut2;
        this.statut3 = statut3;
        this.statut4 = statut4;
        this.idQuestion = idQuestion;
        this.idRF = idRF;
        this.isTAP = isTAP;
    }

    /*------------------------GETTERS AND SETTERS----------------------*/

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public int getIdRef1() {
        return idRef1;
    }

    public void setIdRef1(int idRef1) {
        this.idRef1 = idRef1;
    }

    public int getIdRef2() {
        return idRef2;
    }

    public void setIdRef2(int idRef2) {
        this.idRef2 = idRef2;
    }

    public int getIdRef3() {
        return idRef3;
    }

    public void setIdRef3(int idRef3) {
        this.idRef3 = idRef3;
    }

    public int getIdRef4() {
        return idRef4;
    }

    public void setIdRef4(int idRef4) {
        this.idRef4 = idRef4;
    }

    public int getIdDD1() {
        return idDD1;
    }

    public void setIdDD1(int idDD1) {
        this.idDD1 = idDD1;
    }

    public int getIdDD2() {
        return idDD2;
    }

    public void setIdDD2(int idDD2) {
        this.idDD2 = idDD2;
    }

    public int getIdDD3() {
        return idDD3;
    }

    public void setIdDD3(int idDD3) {
        this.idDD3 = idDD3;
    }

    public int getIdDD4() {
        return idDD4;
    }

    public void setIdDD4(int idDD4) {
        this.idDD4 = idDD4;
    }

    public String getSn1() {
        return sn1;
    }

    public void setSn1(String sn1) {
        this.sn1 = sn1;
    }

    public String getSn2() {
        return sn2;
    }

    public void setSn2(String sn2) {
        this.sn2 = sn2;
    }

    public String getSn3() {
        return sn3;
    }

    public void setSn3(String sn3) {
        this.sn3 = sn3;
    }

    public String getSn4() {
        return sn4;
    }

    public void setSn4(String sn4) {
        this.sn4 = sn4;
    }

    public String getNt1() {
        return nt1;
    }

    public void setNt1(String nt1) {
        this.nt1 = nt1;
    }

    public String getNt2() {
        return nt2;
    }

    public void setNt2(String nt2) {
        this.nt2 = nt2;
    }

    public String getNt3() {
        return nt3;
    }

    public void setNt3(String nt3) {
        this.nt3 = nt3;
    }

    public String getNt4() {
        return nt4;
    }

    public void setNt4(String nt4) {
        this.nt4 = nt4;
    }

    public String getStatut1() {
        return statut1;
    }

    public void setStatut1(String statut1) {
        this.statut1 = statut1;
    }

    public String getStatut2() {
        return statut2;
    }

    public void setStatut2(String statut2) {
        this.statut2 = statut2;
    }

    public String getStatut3() {
        return statut3;
    }

    public void setStatut3(String statut3) {
        this.statut3 = statut3;
    }

    public String getStatut4() {
        return statut4;
    }

    public void setStatut4(String statut4) {
        this.statut4 = statut4;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdRF() {
        return idRF;
    }

    public void setIdRF(int idRF) {
        this.idRF = idRF;
    }

    public boolean isTAP() {
        return isTAP;
    }

    public void setTAP(boolean TAP) {
        isTAP = TAP;
    }

    /*-----------------------------------------TO STRING----------------------------------*/

    @Override
    public String toString() {
        return "ReponseTableaux{" +
                "idReponse=" + idReponse +
                ", idRef1=" + idRef1 +
                ", idRef2=" + idRef2 +
                ", idRef3=" + idRef3 +
                ", idRef4=" + idRef4 +
                ", idDD1=" + idDD1 +
                ", idDD2=" + idDD2 +
                ", idDD3=" + idDD3 +
                ", idDD4=" + idDD4 +
                ", sn1='" + sn1 + '\'' +
                ", sn2='" + sn2 + '\'' +
                ", sn3='" + sn3 + '\'' +
                ", sn4='" + sn4 + '\'' +
                ", nt1='" + nt1 + '\'' +
                ", nt2='" + nt2 + '\'' +
                ", nt3='" + nt3 + '\'' +
                ", nt4='" + nt4 + '\'' +
                ", statut1='" + statut1 + '\'' +
                ", statut2='" + statut2 + '\'' +
                ", statut3='" + statut3 + '\'' +
                ", statut4='" + statut4 + '\'' +
                ", idQuestion=" + idQuestion +
                ", idRF=" + idRF +
                ", isTAP=" + isTAP +
                '}';
    }

}

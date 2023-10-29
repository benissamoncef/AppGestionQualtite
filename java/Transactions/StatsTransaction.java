package Transactions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsTransaction extends BaseTransaction{

	public StatsTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//--------------------------------------------------------------------------------------------------- IPPM TRANSACTIONS :
	
	public int getZoneIppm(int id_zone, int year, int month , int day) throws SQLException {
				
		
		String request = "select idLigne from ligne where idZone = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_zone);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		int totalIppm = 0;
		int ctr = 0;

		
		while(resultSet.next()) {
			totalIppm += getLigneIppm(resultSet.getInt(1), year, month, day);
			 ctr++;
		}

		
		if(ctr != 0)
			return (int) (totalIppm/ctr);
		else 
			return 0;
	
		
	}
	
	
	public int getLigneIppm(int id_ligne, int year, int month, int day) throws SQLException {
		
		int totalIppm = 0;
		int ctr = 0;
		
		if(year == 0 && month == 0 && day == 0) {
		
			String request= "select id from reponse_formulaire where id_ligne = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);

			this.resultSet = this.preparedStatement.executeQuery();
				
		

	
		}
		
		else if(year != 0 && month == 0 && day == 0) {
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);

			this.resultSet = this.preparedStatement.executeQuery();
				
			

				
		}
		
		else if (month != 0 && year != 0 && day == 0){
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);

			this.resultSet = this.preparedStatement.executeQuery();
				
			

			
			
			
		}
		else if(month != 0 && year != 0 && day != 0) {
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ? and DAY(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);
			this.preparedStatement.setInt(4, day);

			this.resultSet = this.preparedStatement.executeQuery();
	
		}
		
		while(resultSet.next()) {
			
			totalIppm += getIppm(resultSet.getInt(1));
			ctr++;
		 
		}	
			
		
	if(ctr != 0)	
		return (int) (totalIppm/ctr);
	else 
		return 0;
		
	}
	
	
	
	
	public int getClientIppm(int id_client, int year, int month , int day) throws SQLException {
		
		String request = "select id from projet where id_client = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_client);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		int totalIppm = 0;
		int ctr = 0;

		
		while(resultSet.next()) {
			totalIppm += getProjetIppm(resultSet.getInt(1), year, month, day);
			 ctr++;
		}
		
	
		
		if(ctr != 0)
			return (int) (totalIppm/ctr);
		else 
			return 0;
	}
	

	public int getProjetIppm(int id_projet, int year, int month, int day) throws SQLException {
		
		String request= "select idFamille from famille where idProjet = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_projet);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		int totalIppm = 0;
		int ctr = 0;

		while(resultSet.next()) {
			totalIppm += getFamilleIppm(resultSet.getInt(1), year, month, day);
			 ctr++;
		}
		
		if(ctr != 0)
		return (int) (totalIppm/ctr);
		else 
			return 0;
		
	}
	
	
	
	
	// b : true => dimentionnel, false => visual.
	
	public int getFamilleIppm(int id_famille, int year, int month, int day) throws SQLException { 
		
		int totalIppm = 0;
		int ctr = 0;
		
		if(year == 0 && month == 0 && day == 0) {
		
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);

			this.resultSet = this.preparedStatement.executeQuery();
				
		
		
			while(resultSet.next()) {
			
				totalIppm += getIppm(resultSet.getInt(1));
				ctr++;
			 
			}
		}
		else if(year != 0 && month == 0 && day == 0) {
			
			String request= "select id from reponse_formulaire  where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);

			this.resultSet = this.preparedStatement.executeQuery();
				
			
		
			while(resultSet.next()) {
			
				totalIppm += getIppm(resultSet.getInt(1));
				ctr++;
			 
			}
				
		}
		
		else if (month != 0 && year != 0 && day == 0){
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);

			this.resultSet = this.preparedStatement.executeQuery();
				
			
		
			while(resultSet.next()) {
			
				totalIppm += getIppm(resultSet.getInt(1));
				ctr++;
			 
			}
			
			
			
		}
		else if(month != 0 && year != 0 && day != 0) {
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ? and DAYOFMONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);
			this.preparedStatement.setInt(4, day);

			this.resultSet = this.preparedStatement.executeQuery();
				
			
		
			while(resultSet.next()) {
			
				totalIppm += getIppm(resultSet.getInt(1));
				ctr++;
			 
			}
		}
			
		
	if(ctr != 0)	
		return (int) (totalIppm/ctr);
	else 
		return 0;
	}
	
	
	public int getIppm(int id_réponse) throws SQLException {

			
			String request= "select 1000000*(q3/q1)+1000000*(q8/q6) from reponse_controles where id_reponse_formulaire = ?";	
				
			this.preparedStatement = this.cnx.prepareStatement(request);
		
			this.preparedStatement.setInt(1, id_réponse);
			
			this.resultSet = this.preparedStatement.executeQuery();
			
			
			
			if(resultSet.next()) 
				return (int)(resultSet.getInt(1) / 2);
		
			else 
				return 0;
		
		
	}
	
	
	//--------------------------------------------------------------------------------------------------- DEFAUTS TRANSACTIONS :	
	
	
	
	
	
	
	public List<Integer> getZoneDéfauts(int id_zone, int year, int month , int day) throws SQLException {
				
		
		String request = "select idLigne from ligne where idZone = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_zone);
		
		this.resultSet = this.preparedStatement.executeQuery();

		List<Integer> Défauts = new ArrayList<>();
		
		while(resultSet.next()) {
			Défauts.addAll(getLigneDéfauts(resultSet.getInt(1), year, month, day));
		}

		return Défauts;
	
	}
	
	
	public List<Integer> getLigneDéfauts(int id_ligne, int year, int month, int day) throws SQLException {
		
		List<Integer> Défauts = new ArrayList<>();
		
		if(year == 0 && month == 0 && day == 0) {
		
			String request= "select id from reponse_formulaire where id_ligne = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);

			this.resultSet = this.preparedStatement.executeQuery();
				
		
		
			while(resultSet.next()) {
			
				Défauts.addAll(getRéponseTableau(resultSet.getInt(1)));
			 
			}
		}
		
		else if(year != 0 && month == 0 && day == 0) {
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);

			this.resultSet = this.preparedStatement.executeQuery();

				
		}
		
		else if (month != 0 && year != 0 && day == 0){
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);

			this.resultSet = this.preparedStatement.executeQuery();

		}
		else if(month != 0 && year != 0 && day != 0) {
			
			String request= "select id from reponse_formulaire where id_ligne = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ? and DAY(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_ligne);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);
			this.preparedStatement.setInt(4, day);

			this.resultSet = this.preparedStatement.executeQuery();

		}
			
		
		while(resultSet.next()) {
			
			Défauts.addAll(getRéponseTableau(resultSet.getInt(1)));
		 
		}
		
		return Défauts;
	}
	
	
	public List<Integer> getClientDéfauts(int id_client, Boolean isFirstPièce, int year, int month , int day) throws SQLException {
		
		List<Integer> Défauts = new ArrayList<>();
		
		String request = "select id from projet where id_client = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_client);
		
		this.resultSet = this.preparedStatement.executeQuery();
	
		while(resultSet.next()) {
			Défauts.addAll(getProjetDéfauts(resultSet.getInt(1), year, month, day));
		}
		

		return Défauts;
	}
	

	public List<Integer> getProjetDéfauts(int id_projet, int year, int month, int day) throws SQLException {
		
		List<Integer> Défauts = new ArrayList<>();
		
		String request= "select idFamille from famille where idProjet = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setInt(1, id_projet);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			Défauts.addAll(getFamilleDéfauts(resultSet.getInt(1), year, month, day));		
		}
		
		return Défauts;
		
	}
	
		
	public List<Integer> getFamilleDéfauts(int id_famille, int year, int month, int day) throws SQLException { 
		
		List<Integer> Défauts = new ArrayList<>();
		
		if(year == 0 && month == 0 && day == 0) {
		
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);

			this.resultSet = this.preparedStatement.executeQuery();
				
		
			
		}
		else if(year != 0 && month == 0 && day == 0) {
			
			String request= "select id from reponse_formulaire  where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);

			this.resultSet = this.preparedStatement.executeQuery();
				

				
		}
		
		else if (month != 0 && year != 0 && day == 0){
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);

			this.resultSet = this.preparedStatement.executeQuery();
				

			
			
		}
		else if(month != 0 && year != 0 && day != 0) {
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and id_famille = ? and YEAR(CreatedAt) = ? and MONTH(CreatedAt) = ? and DAYOFMONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
				
			this.preparedStatement.setInt(1, id_famille);
			this.preparedStatement.setInt(2, year);
			this.preparedStatement.setInt(3, month);
			this.preparedStatement.setInt(4, day);

			this.resultSet = this.preparedStatement.executeQuery();
				
		}
		
		
		while(resultSet.next()) {
			
			Défauts.addAll(getRéponseTableau(resultSet.getInt(1)));
		 
		}
		
		return Défauts;
	}
	
	
	private List<Integer> getRéponseTableau(int id_réponseFormulaire) throws SQLException {
		
			List<Integer> Défauts = new ArrayList<>();
		
		
		
			String request = "select idDD1, idDD2, idDD3, idDD4 from reponse_tableaux where idRF = ? "; 
			
			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, id_réponseFormulaire);
			
			this.resultSet = this.preparedStatement.executeQuery();
			
			int Défaut = 0;
			
			while(resultSet.next()) {	
				
				for(int i=1; i < 5; i++) {			
					Défaut = this.resultSet.getInt(i);
					if(Défaut != 0) 
						Défauts.add(Défaut);
					
				
				}		
			 
			}
		
		
		return Défauts;

	}
	
	
	
	public int getQteBy_Code(int code) throws SQLException {

			
			String request= "select  idDD1, idDD2, idDD3, idDD4 from reponse_tableaux ";

			this.preparedStatement = this.cnx.prepareStatement(request);

			this.resultSet = this.preparedStatement.executeQuery();
			
			int cte = 0;
		
			while(resultSet.next()) {
			
				for(int i = 1; i < 5; i++) {
					if(this.resultSet.getInt(i) == code) cte++;
				}
			 
			}
			
			return cte;
	}
	
	
	
	public int getQteBy_Code_Time(int code, int year, int month, int day) throws SQLException {

		int cte = 0;
			
		if(year != 0 && month == 0 && day == 0) {
		
			String request= "select id from reponse_formulaire where id_formulaire = 2 and Year(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, year);

			this.resultSet = this.preparedStatement.executeQuery();

			while(resultSet.next()) {
			
				cte += getQteFromRéponseTableau(code, resultSet.getInt(1));
			 
			}
			
		}
		
		else if(year != 0 && month != 0 && day == 0) {
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and Year(CreatedAt) = ? and MONTH(CreatedAt) = ?";

			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, year);
			this.preparedStatement.setInt(2, month);

			this.resultSet = this.preparedStatement.executeQuery();

			while(resultSet.next()) {
			
				cte += getQteFromRéponseTableau(code, resultSet.getInt(1));
			 
			}
			
		}
		
		else if(year != 0 && month != 0 && day != 0) {
			
			String request= "select id from reponse_formulaire where id_formulaire = 2 and Year(CreatedAt) = ? and MONTH(CreatedAt) = ? and DAY(CreatedAt) = ? ";

			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, year);
			this.preparedStatement.setInt(2, month);
			this.preparedStatement.setInt(3, day);

					
			this.resultSet = this.preparedStatement.executeQuery();

			while(resultSet.next()) {
			
				cte += getQteFromRéponseTableau(code, resultSet.getInt(1));
			 
			}
			
		}

			
	return cte;
	}
	
	
	
	public int getQteFromRéponseTableau(int code , int idRep) throws SQLException {
		
		
		String request= "select  idDD1, idDD2, idDD3, idDD4 from reponse_tableaux where idReponse = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, idRep);

		this.resultSet = this.preparedStatement.executeQuery();
		
		int cte = 0;
	
		while(resultSet.next()) {
		
			for(int i = 1; i < 5; i++) {
				if(this.resultSet.getInt(i) == code) cte++;
			}
		 
		}
		
		return cte;

	}
	
	
	
	
	
}

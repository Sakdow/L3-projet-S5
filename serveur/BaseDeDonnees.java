package serveur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class BaseDeDonnees {

	private Connection bdd;

	public BaseDeDonnees(String url, String usr, String pswrd) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.bdd = DriverManager.getConnection(url, usr, pswrd);
	}

	public ResultSet requete(String requete) throws SQLException {
		Statement state = bdd.createStatement();
		return state.executeQuery(requete);
	}
	
	public ResultSet requeteInsertReturnKey(String requete) throws SQLException {
		Statement state = bdd.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		state.execute(requete);
		return state.getGeneratedKeys();
	}
	
	public void requeteInsertWithoutReturn(String requete) throws SQLException {
		Statement state = bdd.prepareStatement(requete);
		state.execute(requete);
	}

}

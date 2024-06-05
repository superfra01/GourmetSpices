package model;

import java.sql.ResultSet;

public class UserDAO implements BeanDAO<UserBean, String>{
	private static String TABLE_NAME = "utente";
	private DataSource dataSource = null;

	public UtenteDAO(DataSource dataSource) {
		this.dataSource = dataSource;	
	}
	
	@Override
	public synchronized void doSave(UserBean data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserDAO.TABLE_NAME
				+ " (email, username, password, nome, cognome, Tipo_utente) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, data.getEmail());
			preparedStatement.setString(2, data.getUsername());
			preparedStatement.setString(3, data.getPassword());
			preparedStatement.setString(4, data.getNome());
			preparedStatement.setString(5, data.getCognome());
			preparedStatement.setString(6, data.getTipoUtente());

			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	@Override
	public synchronized boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		3 preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UserDAO.TABLE_NAME + " WHERE email = ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized UserBean doRetrieveByKey(String code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE email = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setAdmin(rs.getString("Tipo_utente"));
				

			}

		}finally {
		
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		} 
		return bean;
		
	}
	
	@Override
	/*
	 * String order - da fare WHITE LIST nel caso di ordinamento degli utenti
	 */
	public synchronized Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		Collection<UserBean> utenti = new ArrayList<UserBean>();
		String selectSQL = "SELECT * FROM " + UserDAO.TABLE_NAME;
		if(order != null && !order.equals("")){
			selectSQL += " ORDER BY "+order;
		}
		UserBean bean = null;
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = new UserBean();
				
				bean.setEmail(rs.getString("email"));
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPassword(rs.getString("password"));
				bean.setTipo_utente(rs.getString("Tipo_utente"));
				
				utenti.add(bean);
			}
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					connection.close();
			}
		}
		
		return utenti;
	}
	
	
}

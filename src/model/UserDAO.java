package model;
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

		String insertSQL = "INSERT INTO " + UtenteDAO.NOME_TABELLA
				+ " (email, username, password, nome, cognome, Tipo_utente) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, data.getEmail());
			preparedStatement.setString(2, data.getUsername());
			preparedStatement.setString(3, data.getPassword());
			preparedStatement.setString(4, data.getNome());
			preparedStatement.setString(5, data.getCognome());
			preparedStatement.setBoolean(6, data.getTipoUtente());

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
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteDAO.NOME_TABELLA + " WHERE email = ?";

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

		String selectSQL = "SELECT * FROM " + UtenteDAO.NOME_TABELLA + " WHERE email = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setNazionalità(rs.getString("nazionalità"));
				bean.setDataNascita(rs.getDate("dataDiNascita"));
				bean.setPassword(rs.getString("password"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
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
		String selectSQL = "SELECT * FROM " + UtenteDAO.NOME_TABELLA;
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
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setNazionalità(rs.getString("nazionalità"));
				bean.setDataNascita(rs.getDate("dataDiNascita"));
				bean.setPassword(rs.getString("password"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
				
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

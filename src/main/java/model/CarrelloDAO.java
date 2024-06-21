package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class CarrelloDAO implements BeanDAO<CarrelloBean, Integer>{
	private static String TABLE_NAME = "carrello";
	private DataSource dataSource = null;

	public CarrelloDAO(DataSource dataSource) {
		this.dataSource = dataSource;	
	}
	@Override
	public void doSave(CarrelloBean data) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + CarrelloDAO.TABLE_NAME
				+ " (ID_carrello, email) VALUES (?, ?)";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, data.getIdCarrello());
			preparedStatement.setString(2, data.getEmail());

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
	public boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CarrelloDAO.TABLE_NAME + " WHERE ID_carrello = ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	public CarrelloBean doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CarrelloBean bean = new CarrelloBean();

		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE ID_carrello = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdCarrello(rs.getInt("ID_carrello"));
				bean.setEmail(rs.getString("email"));
				

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
	public CarrelloBean doRetrieveByUserKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CarrelloBean bean = new CarrelloBean();

		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE email = ?";

		try {
		
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdCarrello(rs.getInt("ID_carrello"));
				bean.setEmail(rs.getString("email"));
				

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
	public Collection<CarrelloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		Collection<CarrelloBean> carrelli = new ArrayList<CarrelloBean>();
		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME;
		if(order != null && !order.equals("")){
			selectSQL += " ORDER BY "+order;
		}
		CarrelloBean bean = null;
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = new CarrelloBean();
				
				bean.setEmail(rs.getString("email"));
				bean.setIdCarrello(rs.getInt("ID_carrello"));
				
				carrelli.add(bean);
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
		
		return carrelli;
	}

}

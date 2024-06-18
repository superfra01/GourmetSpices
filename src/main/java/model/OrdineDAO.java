package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class OrdineDAO implements BeanDAO<OrdineBean, Integer> {
    private static String TABLE_NAME = "ordine";
    private DataSource dataSource = null;

    public OrdineDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(OrdineBean data) throws SQLException {
    	if(data.getIdOrdine()!=-1) {
    		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
                    + " (nCartaIban, email, data, ID_utente, spesa, indirizzo) VALUES (?, ?, ?, ?, ?)";
    		try (Connection connection = dataSource.getConnection();
    	            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
    	            preparedStatement.setString(1, data.getnCartaIban());
    	            preparedStatement.setString(2, data.getEmail());
    	            preparedStatement.setDate(3, new Date(data.getData().getTime()));
    	            preparedStatement.setInt(4, data.getIdUtente());
    	            preparedStatement.setFloat(5, data.getSpesa());
    	            preparedStatement.setString(6, data.getIndirizzo());

    	            preparedStatement.executeUpdate();
    	        }
    	}
    		
    	else {
    		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
                	+ " (ID_ordine, nCartaIban, email, data, ID_utente, spesa, indirizzo) VALUES (?, ?, ?, ?, ?)";
    		try (Connection connection = dataSource.getConnection();
    	            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
    				preparedStatement.setInt(1, data.getIdOrdine());
    	            preparedStatement.setString(2, data.getnCartaIban());
    	            preparedStatement.setString(3, data.getEmail());
    	            preparedStatement.setDate(4, new Date(data.getData().getTime()));
    	            preparedStatement.setInt(5, data.getIdUtente());
    	            preparedStatement.setFloat(6, data.getSpesa());
    	            preparedStatement.setString(7, data.getIndirizzo());

    	            preparedStatement.executeUpdate();
    		}
    	}

        
    }

    @Override
	public synchronized boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE ID_ordine = ?";

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

   

	
	public synchronized Collection<OrdineBean> doRetrieveByUserKey(Integer code) throws SQLException {
        String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE ID_utente = ?";
        
        Collection<OrdineBean> ordini = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, code);
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
            	 while (rs.next()) {
                     OrdineBean bean = new OrdineBean();
                     bean.setIdOrdine(rs.getInt("idOrdine"));
                     bean.setnCartaIban(rs.getString("nCartaIban"));
                     bean.setEmail(rs.getString("email"));
                     bean.setData(rs.getDate("data"));
                     bean.setIdUtente(rs.getInt("ID_utente"));
                     bean.setSpesa(rs.getFloat("spesa"));
                     bean.setIndirizzo(rs.getString("indirizzo"));
                     ordini.add(bean);
                 }
            }
        }
        return null;
    }
	
	@Override
	public synchronized OrdineBean doRetrieveByKey(Integer code) throws SQLException {
	
        String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE idOrdine = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, code);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    OrdineBean bean = new OrdineBean();
                    bean.setIdOrdine(rs.getInt("idOrdine"));
                    bean.setnCartaIban(rs.getString("nCartaIban"));
                    bean.setEmail(rs.getString("email"));
                    bean.setData(rs.getDate("data"));
                    bean.setIdUtente(rs.getInt("ID_utente"));
                    bean.setSpesa(rs.getFloat("spesa"));
                    bean.setIndirizzo(rs.getString("indirizzo"));
                    return bean;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized Collection<OrdineBean> doRetrieveAll(String order) throws SQLException {
        String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;
        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        Collection<OrdineBean> ordini = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                OrdineBean bean = new OrdineBean();
                bean.setIdOrdine(rs.getInt("idOrdine"));
                bean.setnCartaIban(rs.getString("nCartaIban"));
                bean.setEmail(rs.getString("email"));
                bean.setData(rs.getDate("data"));
                bean.setIdUtente(rs.getInt("ID_utente"));
                bean.setSpesa(rs.getFloat("spesa"));
                bean.setIndirizzo(rs.getString("indirizzo"));
                ordini.add(bean);
            }
        }
		
        return ordini;
    }
	
}

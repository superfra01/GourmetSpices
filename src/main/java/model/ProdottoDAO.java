package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class ProdottoDAO implements BeanDAO<ProdottoBean, Integer> {
    private static final String TABLE_NAME = "prodotto";
    private DataSource dataSource;

    public ProdottoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(ProdottoBean data) throws SQLException {
    	if(data.getIdProdotto()!=-1) {
    		String insertSQL = "INSERT INTO " + TABLE_NAME
                    + " (ID_prodotto, In_Evidenza, prezzo, valido, nome, descrizione) VALUES (?, ?, ?, ?, ?, ?)"
                    + " ON DUPLICATE KEY UPDATE "
                    + "In_Evidenza = VALUES(In_Evidenza),"
                    + "prezzo = VALUES(prezzo), "
                    + "valido = VALUES(valido), "
                    + "nome = VALUES(nome), "
                    + "descrizione = VALUES(descrizione)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            	 preparedStatement.setInt(1, data.getIdProdotto());
            	 preparedStatement.setInt(2, data.getInEvidenza());
            	 preparedStatement.setFloat(3, data.getPrezzo());
            	 preparedStatement.setInt(4, data.getValidoProdotto());
            	 preparedStatement.setString(5, data.getNome());
            	 preparedStatement.setString(6, data.getDescrizione());

            	 preparedStatement.executeUpdate();
            }
    	}
    	else {
    		String insertOrUpdateSQL = "INSERT INTO " + TABLE_NAME
                    + " (prezzo, In_Evidenza, valido, nome, descrizione) VALUES (?, ?, ?, ?, ?)";

			try (Connection connection = dataSource.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(insertOrUpdateSQL)) {
			    preparedStatement.setFloat(1, data.getPrezzo());
			    preparedStatement.setInt(2, data.getInEvidenza());
			    preparedStatement.setInt(3, data.getValidoProdotto());
			    preparedStatement.setString(4, data.getNome());
			    preparedStatement.setString(5, data.getDescrizione());
			
			    preparedStatement.executeUpdate();
			}

             
    		
    	}
        
    }

    @Override
    public synchronized boolean doDelete(Integer code) throws SQLException {
        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE ID_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, code);
            int result = preparedStatement.executeUpdate();
            return (result != 0);
        }
    }

    @Override
    public synchronized ProdottoBean doRetrieveByKey(Integer code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_prodotto = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, code);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ProdottoBean bean = new ProdottoBean();
                    bean.setIdProdotto(rs.getInt("ID_prodotto"));
                    bean.setValidoProdotto(rs.getInt("In_Evidenza"));
                    bean.setPrezzo(rs.getFloat("prezzo"));
                    bean.setValidoProdotto(rs.getInt("valido"));
                    bean.setNome(rs.getString("nome"));
                    bean.setDescrizione(rs.getString("descrizione"));
                    return bean;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }

        Collection<ProdottoBean> prodotti = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                ProdottoBean bean = new ProdottoBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setValidoProdotto(rs.getInt("In_Evidenza"));
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setValidoProdotto(rs.getInt("valido"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                prodotti.add(bean);
            }
        }
        return prodotti;
    }
    public synchronized int nextId() throws SQLException{
    	String selectSQL = "SELECT COUNT(*) AS numero_prodotti FROM prodotto"
    			+ "";

    	int nextAutoIncrementValue = 0;

    	try{
    		Connection connection = dataSource.getConnection();
	   	    PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	   	    ResultSet rs = preparedStatement.executeQuery();

    	    if (rs.next()) {
    	        nextAutoIncrementValue = rs.getInt("numero_prodotti")+1;
    	    }
    	    preparedStatement.close();
    	    connection.close();
    	}catch(SQLException e) {
    		
    	    e.printStackTrace(); // Gestione dell'eccezione SQL, puoi gestirla in modo più appropriato nel tuo caso
    	}
    	

    	return nextAutoIncrementValue;
    }
}

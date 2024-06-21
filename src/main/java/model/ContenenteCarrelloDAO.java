package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class ContenenteCarrelloDAO implements BeanDAO<ContenenteCarrelloBean,ContenenteCarrelloCombinedKey>{
	private static final String TABLE_NAME = "contenente_carrello";
    private DataSource dataSource;
    
    public ContenenteCarrelloDAO(DataSource dataSource){
    	this.dataSource = dataSource;
    }
    
    
    
	@Override
	public void doSave(ContenenteCarrelloBean data) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (ID_carrello, ID_ordine, quantita) VALUES (?, ?, ?)";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            String idProdotto = data.getIdProdotto() == 0 ? "*" : String.valueOf(data.getIdProdotto());
            String idOrdine = data.getIdCarrello() == 0 ? "*" : String.valueOf(data.getIdCarrello());

            preparedStatement.setString(1, idProdotto);
            preparedStatement.setString(2, idOrdine);
            preparedStatement.setInt(3, data.getQuantita());

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
	public boolean doDelete(ContenenteCarrelloCombinedKey key) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE ID_prodotto = ? AND ID_carrello = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            String idProdotto = key.getIdProdotto() == 0 ? "*" : String.valueOf(key.getIdProdotto());
            String idCarrello = key.getIdCarrello() == 0 ? "*" : String.valueOf(key.getIdCarrello());

            preparedStatement.setString(1, idProdotto);
            preparedStatement.setString(2, idCarrello);

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
	public ContenenteCarrelloBean doRetrieveByKey(ContenenteCarrelloCombinedKey key) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        ContenenteCarrelloBean bean = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_prodotto = ? AND ID_carrello = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setInt(1, key.getIdProdotto());
            preparedStatement.setInt(2, key.getIdCarrello());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bean = new ContenenteCarrelloBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdCarrello(rs.getInt("ID_Carrello"));
                bean.setQuantita(rs.getInt("quantita"));
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return bean;
		
	}
	public Collection<ContenenteCarrelloBean> doRetrieveByCarrelloKey(int key) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<ContenenteCarrelloBean> contenenti = new ArrayList<>();
        

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_prodotto = * AND ID_carrello = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setInt(1, key);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	ContenenteCarrelloBean bean = null;
                bean = new ContenenteCarrelloBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdCarrello(rs.getInt("ID_Carrello"));
                bean.setQuantita(rs.getInt("quantita"));
                contenenti.add(bean);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return contenenti;
		
	}
	@Override
	public Collection<ContenenteCarrelloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<ContenenteCarrelloBean> contenenti = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ContenenteCarrelloBean bean = new ContenenteCarrelloBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdCarrello(rs.getInt("ID_Carrello"));
                bean.setQuantita(rs.getInt("quantita"));

                contenenti.add(bean);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return contenenti;
	}
    
    
    
}

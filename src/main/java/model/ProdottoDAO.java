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
                    + " (prezzo, valido, nome, descrizione) VALUES (?, ?, ?, ?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setFloat(1, data.getPrezzo());
                preparedStatement.setInt(2, data.getValidoProdotto());
                preparedStatement.setString(3, data.getNome());
                preparedStatement.setString(4, data.getDescrizione());

                preparedStatement.executeUpdate();
            }
    	}
    	else {
    		String insertSQL = "INSERT INTO " + TABLE_NAME
                    + " (ID_prodotto, prezzo, valido, nome, descrizione) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setInt(1, data.getIdProdotto());
                preparedStatement.setFloat(2, data.getPrezzo());
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
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setValidoProdotto(rs.getInt("valido"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                prodotti.add(bean);
            }
        }
        return prodotti;
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

public class ContenenteDAODAFINIRE implements BeanDAO<ContenenteBean, ContenenteCombinedKey> {
    private static final String TABLE_NAME = "contenente";
    private DataSource dataSource;

    public ContenenteDAODAFINIRE(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(ContenenteBean data) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (idProdotto, idOrdine, prezzoAllAcquisto, quantita) VALUES (?, ?, ?, ?)";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            String idProdotto = data.getIdProdotto() == 0 ? "*" : String.valueOf(data.getIdProdotto());
            String idOrdine = data.getIdOrdine() == 0 ? "*" : String.valueOf(data.getIdOrdine());

            preparedStatement.setString(1, idProdotto);
            preparedStatement.setString(2, idOrdine);
            preparedStatement.setFloat(3, data.getPrezzoAllAcquisto());
            preparedStatement.setInt(4, data.getQuantita());

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
    public synchronized boolean doDelete(ContenenteCombinedKey key) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE idProdotto = ? AND idOrdine = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            String idProdotto = key.getIdProdotto() == 0 ? "*" : String.valueOf(key.getIdProdotto());
            String idOrdine = key.getIdOrdine() == 0 ? "*" : String.valueOf(key.getIdOrdine());

            preparedStatement.setString(1, idProdotto);
            preparedStatement.setString(2, idOrdine);

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
    public synchronized ContenenteBean doRetrieveByKey(ContenenteCombinedKey key) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ContenenteBean bean = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idProdotto = ? AND idOrdine = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            String idProdotto = key.getIdProdotto() == 0 ? "*" : String.valueOf(key.getIdProdotto());
            String idOrdine = key.getIdOrdine() == 0 ? "*" : String.valueOf(key.getIdOrdine());

            preparedStatement.setString(1, idProdotto);
            preparedStatement.setString(2, idOrdine);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bean = new ContenenteBean();
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setIdOrdine(rs.getInt("idOrdine"));
                bean.setPrezzoAllAcquisto(rs.getFloat("prezzoAllAcquisto"));
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

    @Override
    public synchronized Collection<ContenenteBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<ContenenteBean> contenenti = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ContenenteBean bean = new ContenenteBean();
                bean.setIdProdotto(rs.getInt("idProdotto"));
                bean.setIdOrdine(rs.getInt("idOrdine"));
                bean.setPrezzoAllAcquisto(rs.getFloat("prezzoAllAcquisto"));
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

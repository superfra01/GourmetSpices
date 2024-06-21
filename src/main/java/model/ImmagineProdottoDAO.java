package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class ImmagineProdottoDAO implements BeanDAO<ImmagineProdottoBean, Integer> {
    private static String TABLE_NAME = "Immagine_Prodotto";
    private DataSource dataSource = null;

    public ImmagineProdottoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(ImmagineProdottoBean data) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + ImmagineProdottoDAO.TABLE_NAME
                + " (ID_prodotto, Immagine) VALUES (?, ?)";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, data.getIdProdotto());
            preparedStatement.setString(2, data.getImmagine());

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
    public synchronized boolean doDelete(Integer idImmagine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + ImmagineProdottoDAO.TABLE_NAME + " WHERE ID_immagine = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idImmagine);

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
    public synchronized ImmagineProdottoBean doRetrieveByKey(Integer idImmagine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ImmagineProdottoBean bean = new ImmagineProdottoBean();

        String selectSQL = "SELECT * FROM " + ImmagineProdottoDAO.TABLE_NAME + " WHERE ID_immagine = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idImmagine);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdImmagine(rs.getInt("ID_immagine"));
                bean.setImmagine(rs.getString("Immagine"));
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

    public synchronized Collection<ImmagineProdottoBean> doRetrieveByProductKey(Integer idProdotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        Collection<ImmagineProdottoBean> immagini = new ArrayList<ImmagineProdottoBean>();
        

        String selectSQL = "SELECT * FROM " + ImmagineProdottoDAO.TABLE_NAME + " WHERE ID_prodotto = ?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idProdotto);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	ImmagineProdottoBean bean = new ImmagineProdottoBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdImmagine(rs.getInt("ID_immagine"));
                bean.setImmagine(rs.getString("Immagine"));
                immagini.add(bean);
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
        return immagini;
    }
    @Override
    public synchronized Collection<ImmagineProdottoBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<ImmagineProdottoBean> immagini = new ArrayList<ImmagineProdottoBean>();

        String selectSQL = "SELECT * FROM " + ImmagineProdottoDAO.TABLE_NAME;
        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        ImmagineProdottoBean bean = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean = new ImmagineProdottoBean();
                bean.setIdProdotto(rs.getInt("ID_prodotto"));
                bean.setIdImmagine(rs.getInt("ID_immagine"));
                bean.setImmagine(rs.getString("Immagine"));

                immagini.add(bean);
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

        return immagini;
    }
}

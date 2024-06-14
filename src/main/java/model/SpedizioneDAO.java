package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class SpedizioneDAO implements BeanDAO<SpedizioneBean, String> {
    private static final String TABLE_NAME = "spedizione";
    private DataSource dataSource;

    public SpedizioneDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(SpedizioneBean data) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (ID_ordine, N_spedizione, G_di_arrivo, corriere) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, data.getIdOrdine());
            preparedStatement.setString(2, data.getnSpedizione());
            preparedStatement.setDate(3, new Date(data.getgDiArrivo().getTime()));
            preparedStatement.setString(4, data.getCorriere());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public synchronized boolean doDelete(String code) throws SQLException {
        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE N_spedizione = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, code);
            int result = preparedStatement.executeUpdate();
            return (result != 0);
        }
    }

    @Override
    public synchronized SpedizioneBean doRetrieveByKey(String code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE N_spedizione = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, code);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    SpedizioneBean bean = new SpedizioneBean();
                    bean.setIdOrdine(rs.getInt("ID_ordine"));
                    bean.setnSpedizione(rs.getString("N_spedizione"));
                    bean.setgDiArrivo(rs.getDate("G_di_arrivo"));
                    bean.setCorriere(rs.getString("corriere"));
                    return bean;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized Collection<SpedizioneBean> doRetrieveAll(String order) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }

        Collection<SpedizioneBean> spedizioni = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                SpedizioneBean bean = new SpedizioneBean();
                bean.setIdOrdine(rs.getInt("ID_ordine"));
                bean.setnSpedizione(rs.getString("N_spedizione"));
                bean.setgDiArrivo(rs.getDate("G_di_arrivo"));
                bean.setCorriere(rs.getString("corriere"));
                spedizioni.add(bean);
            }
        }
        return spedizioni;
    }
}

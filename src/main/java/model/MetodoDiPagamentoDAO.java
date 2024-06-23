package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

public class MetodoDiPagamentoDAO implements BeanDAO<MetodoDiPagamentoBean, String> {
    private static final String TABLE_NAME = "metodo_di_pagamento";
    private DataSource dataSource;

    public MetodoDiPagamentoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized void doSave(MetodoDiPagamentoBean data) {
        String insertSQL = "INSERT INTO " + TABLE_NAME 
                          + " (email, NCarta, CVV, data) VALUES (?, ?, ?, ?)"
                          + " ON DUPLICATE KEY UPDATE email = VALUES(email), CVV = VALUES(CVV), data = VALUES(data)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, data.getEmail());
            preparedStatement.setString(2, data.getNCarta());
            preparedStatement.setString(3, data.getCVV());
            preparedStatement.setDate(4, data.getData());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gestisci l'eccezione o logga un messaggio
            e.printStackTrace(); // Esempio di log dell'eccezione
        }
    }


    @Override
    public synchronized boolean doDelete(String code) throws SQLException {
        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE NCarta = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, code);
            int result = preparedStatement.executeUpdate();
            return (result != 0);
        }
    }

    @Override
    public synchronized MetodoDiPagamentoBean doRetrieveByKey(String code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE NCarta = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, code);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    MetodoDiPagamentoBean bean = new MetodoDiPagamentoBean();
                    bean.setEmail(rs.getString("email"));
                    bean.setNCarta(rs.getString("NCarta"));
                    bean.setCVV(rs.getString("CVV"));
                    bean.setData(rs.getDate("data"));
                    return bean;
                }
            }
        }
        return null;
    }
    
    public synchronized Collection<MetodoDiPagamentoBean> doRetrieveByUserKey(String code) throws SQLException {
    	String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";

    	Collection<MetodoDiPagamentoBean> metodiDiPagamento = new ArrayList<>();
    	
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, code);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    MetodoDiPagamentoBean bean = new MetodoDiPagamentoBean();
                    bean.setEmail(rs.getString("email"));
                    bean.setNCarta(rs.getString("NCarta"));
                    bean.setCVV(rs.getString("CVV"));
                    bean.setData(rs.getDate("data"));
                    metodiDiPagamento.add(bean);
                }
            }
        }
        return metodiDiPagamento;
    }
    

    @Override
    public synchronized Collection<MetodoDiPagamentoBean> doRetrieveAll(String order) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }

        Collection<MetodoDiPagamentoBean> metodiDiPagamento = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MetodoDiPagamentoBean bean = new MetodoDiPagamentoBean();
                bean.setEmail(rs.getString("email"));
                bean.setNCarta(rs.getString("NCarta"));
                bean.setCVV(rs.getString("CVV"));
                bean.setData(rs.getDate("data"));
                metodiDiPagamento.add(bean);
            }
        }
        return metodiDiPagamento;
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;

public class UserModel {

    public synchronized Collection<String> retrieveAllEmail() {
        Collection<String> collection = new LinkedList<String>();
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "SELECT email FROM Utente";
        
        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                collection.add(rs.getString(1));
            }
            return collection;
        }
        catch (Exception e) {
            e.printStackTrace();
            return collection;
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }
    
    public synchronized UserBean update(UserBean bean, String email) {
        String email = bean.getEmail();
        String username = bean.getUsername();
        String password = bean.getPassword();
        String nome = bean.getNome();
        String cognome = bean.getCognome();
        String tipoUtente = bean.getTipoUtente();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "UPDATE Utente SET email = ?, username = ?, password = ?, nome = ?, cognome = ?, Tipo_utente = ? WHERE email = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, nome);
            ps.setString(5, cognome);
            ps.setString(6, tipoUtente);
            ps.setString(7, email);
            
            ps.executeUpdate();
            con.commit();
            DriverManagerConnectionPool.releaseConnection(con);
            return bean;
        }
        catch (Exception e) {
            e.printStackTrace();
            return bean;
        }
    }
}

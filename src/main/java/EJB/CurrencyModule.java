/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author Admin
 */
@Stateless
public class CurrencyModule implements CurrencyModuleLocal {
    
    Connection conn;
    Statement stmt;
    String query;
    ResultSet rs;

   @PostConstruct
   void connect()
    {
        try {
            // Load Driver in Memeory
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish Connection to Database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_ass3?useSSL=false","root","neelgajjar");
            if(!conn.isClosed())
            {
                System.out.println("Connection Established...!");
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Driver class not found");
        }
        catch (SQLException se)
        {
            System.out.println("Connection Could not be Established");
        }
    }
    

    @Override
    public ResultSet AllCurrency() 
    {
        try{
            connect();
            query = "Select * from currencytb";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch(SQLException sq)
        {
            sq.printStackTrace();
        }
        return rs;
    }

    
       
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public double CalculateConvertAMT(String fcode, String tcode, double amt){
        double ConvertAMT=0.0;
        try {
            ResultSet rs = null;
            connect();
            String query = "SELECT `exchangeRate` FROM `currency_exchange` WHERE from_currency=(SELECT cid FROM currencytb WHERE ccode='"+fcode+"') AND to_currency=(SELECT cid FROM currencytb WHERE ccode='"+tcode+"')";
            System.out.println("Query :- " + query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                double rate = rs.getDouble("exchangeRate");
                ConvertAMT = rate * amt;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CurrencyModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ConvertAMT;
    }
}

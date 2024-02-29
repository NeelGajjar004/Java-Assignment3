/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package EJB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Admin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/A3SenderTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/A3SenderTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/A3SenderTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class StockPriceBean1 implements MessageListener {
    
    Double Reliance,TATA,Wipro;
    Connection conn;
    Statement stmt;
    String query;
    ResultSet rs;

   @PostConstruct
   void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_ass3?useSSL=false","root","neelgajjar");
            if(!conn.isClosed())
                System.out.println("Connection Established...!");
        }catch (ClassNotFoundException cnfe){
            System.out.println("Driver class not found");
        }catch (SQLException se){
            System.out.println("Connection Could not be Established");
        }
    }    
    
    public StockPriceBean1() {
    }
    
    public void updatePrice(double reliance,double tata,double wipro)
    {
        try {
            connect();
//            String query = "INSERT INTO `client1` (`Reliance`, `TATA`, `Wipro`) VALUES ("+reliance+","+tata+","+wipro+");";
            String query = "UPDATE `client1` SET `Reliance` = '"+reliance+"', `TATA` = '"+tata+"', `Wipro` = '"+wipro+"' WHERE `client1`.`c1id` = 1;";
            System.out.println("Query :- " + query);
            stmt = conn.createStatement();
            if(stmt.executeUpdate(query) == 1){
                System.out.println("********** Client 1 Stock Price Updated **********");
            }else{
                System.out.println("********** Someting Was Wrong **********");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onMessage(Message message) {
        try{
            connect();
            String msg = null;
            if(message instanceof TextMessage)
            {
                msg = ((TextMessage)message).getText();
                System.out.println("Client 1 has got Stock Price :- " + msg );
                
                String[] pairs = msg.split(",");
                double[] prices = new double[pairs.length];
                int index = 0;
                
                for (String pair : pairs) 
                {
                    String[] keyValue = pair.split(":");
                    double price = Double.parseDouble(keyValue[1]);
                    prices[index] = price;
                    index++;
                }
                Reliance = prices[0];
                TATA = prices[1];
                Wipro = prices[2];
                updatePrice(Reliance, TATA, Wipro);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

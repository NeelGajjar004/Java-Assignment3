/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import java.sql.ResultSet;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CurrencyModuleLocal {
    
    public ResultSet AllCurrency();
    public double CalculateConvertAMT(String fcode,String tcode,double amt);
    
}

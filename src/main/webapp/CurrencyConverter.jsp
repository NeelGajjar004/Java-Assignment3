<%-- 
    Document   : CurrencyConverter.jsp
    Created on : Feb 15, 2024, 1:40:57 PM
    Author     : Admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.*,javax.ejb.*,java.util.Properties,EJB.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    InitialContext ic = new InitialContext();
    CurrencyModuleLocal cml = (CurrencyModuleLocal) ic.lookup("java:global/Q1_CurrencyConverter/CurrencyModule");
            
    ResultSet rs = cml.AllCurrency();
    ResultSet rs1 = cml.AllCurrency();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Neel Gajjar</title>
    </head>
    <body>
        
        
        
        <h1><u>15 Neel Gajjar Assignment 3 :- Q1 - Currency Converter</u></h1>
        
        <h3>
        <form method="post" action="">
        Form Currency : 
        <select name="fromCurrency">
            <%
                while(rs.next()){
            %>
            <option value=<%=rs.getString("ccode")%>><%=rs.getString("cname")%></option>
            <%
                }
            %>
        </select>
        <br><br>
        <!--&nbsp;-->
        
        To Currency : 
        <select name="toCurrency">
            <%
                while(rs1.next()){
            %>
            <option value=<%=rs1.getString("ccode")%>><%=rs1.getString("cname")%></option>
            <%
                }
            %>
        </select>
        <br><br>
        Enter Amount : <input type="number" name="amt"/>&nbsp;
        <input type="submit" value="Convert"/>
        </form>
        <br><br>
        
        Previous Conversion : <br><br>
        <%
        
            if("POST".equalsIgnoreCase(request.getMethod()))
            {
                String fromCur = request.getParameter("fromCurrency");
                String toCur = request.getParameter("toCurrency");
                double amt = Double.parseDouble(request.getParameter("amt"));
        %>
        
        <span>Amount : <%= amt %></span> <span>[</span><%= fromCur %><span>]</span>
        <!--<span><%= fromCur %> : <%= amt %></span> <span>[</span><%= fromCur %><span>]</span>-->
        <br><br>
        <span>Converted Amount : <%= cml.CalculateConvertAMT(fromCur, toCur, amt)%></span> <span>[</span><%= toCur %><span>]</span>
        <!--<span><%= toCur %> : <%= cml.CalculateConvertAMT(fromCur, toCur, amt)%></span> <span>[</span><%= toCur %><span>]</span>-->
        
        <%
            }
        %> 
        
        
        </h3>
        
    </body>
</html>

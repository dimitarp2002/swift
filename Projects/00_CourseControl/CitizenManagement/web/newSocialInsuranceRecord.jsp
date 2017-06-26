<%-- 
    Document   : newSocialInsuranceRecord
    Created on : Jun 26, 2017, 1:07:54 PM
    Author     : dimitar
--%>

<%@page import="insurance.SocialInsuranceRecord"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="address.Address"%>
<%@page import="personaldetails.Citizen"%>
<%@page import="java.sql.SQLException"%>
<%@page import="storages.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="#E6E6FA">
        <h1>WELCOME TO CITIZEN STORAGE MANAGER!</h1>
        <h1>Adding new Social Insurance Record for</h1>

        <%
            String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
            String username = "dimitar";
            String password = "123456";
            String citizenId = request.getParameter("citizenId");
            String yearStr = request.getParameter("year");
            String monthStr = request.getParameter("month");
            String amountStr = request.getParameter("amount");

            CitizenStorage storageCitizen;
            AddressStorage storageAddress;
            SocialInsuranceStorage storageSocialInsurance;

            try {
                storageCitizen = new MySqlCitizenStorage(url, username, password);
                storageAddress = new MySqlAddressStorage(url, username, password);
                storageSocialInsurance = new MySqlSocialInsuranceStorage(url, username, password);

            } catch (SQLException ex) {
                throw new DALException("Unable to open storage", ex);
            }

            Citizen citizen = null;
            Address address = null;
            List<SocialInsuranceRecord> records = new ArrayList<>();
            SocialInsuranceRecord newRecord =null;

            int receivedId = 0;

            int year = 0;
            int month = 0;
            Double amount =0.0;
            
            if (yearStr != null) {
                try {
                    year = Integer.parseInt(yearStr);
                    if(1900>year || year >2040){
                    throw new NumberFormatException("Year should be between 1900 and 2040");
                    }
                } catch (NumberFormatException e) {
                    throw new DALException("Not correct format for Integer year", e);
                }
            }
            
            if (monthStr != null) {
                try {
                    month = Integer.parseInt(monthStr);
                    if (1>month || month>12){
                        throw new NumberFormatException("Month should be between 1 and 12");
                    }
                } catch (NumberFormatException e) {
                    throw new DALException("Not correct format for Integer month", e);
                }
            }

            if (amountStr != null) {
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    throw new DALException("Not correct format for Double amount", e);
                }
            }
            
            if(year!=0 && month !=0 && amount != 0.0){
            newRecord = new SocialInsuranceRecord(year, month, amount);
            }
            
            if (citizenId != null) {

                receivedId = Integer.parseInt(citizenId);
                citizen = storageCitizen.getCitizenById(receivedId);
                address = storageAddress.getAddressById(receivedId);
                if(newRecord !=null){
                storageSocialInsurance.insert(newRecord, receivedId);
                }
                records = storageSocialInsurance.getSocialInsuranceById(receivedId);
            } else {

            }

        %>
        <%  if (citizen != null) {%>
        <h3> 
            <%= citizen.toString()%>
        </h3>
        <br>
        <div>Адрес:</div>
        <div><%= address.toString()%> </div>
        <br>
        <br>

        <form name="toHome" action="userInfo.jsp" method="POST">
            <input type="submit" value="Home" name="toHomeButton" />
        </form>

        <br>
        <br>

        <table border="0" cellspacing="1" width="25%" bgcolor="#000000">
            <tbody>
                <tr bgcolor="#F5E8FF">
                    <td style="width:35%">
                        Year
                    </td>
                    <td style="width:35%">
                        Month
                    </td>
                    <td style="width:30%">
                        Amount
                    </td>
                </tr>
            </tbody>
        </table>


        <form name="newEntrySIR" action="newSocialInsuranceRecord.jsp" method="POST">
            <input type="text" name="year" value="" maxlength="4" size="13"/>
            <input type="text" name="month" value="" maxlength="2" size="13"/>
            <input type="text" name="amount" value=""  maxlength="10" size="10"/>
            <input type="hidden" name="citizenId"
                   value="<%=request.getParameter("citizenId")%>" />
            <input type="submit" value="Add" name="AddSIR" />
        </form>

        <br>
        <br>


        <table border="0" cellspacing="1" width="25%" bgcolor="#000000">
            <th bgcolor="#F5E8FF" colspan="3">
                Социални осигуровки:
            </th>
            <tbody>
                <tr bgcolor="#F5E8FF">
                    <td style="width:35%">
                        Година
                    </td>
                    <td style="width:35%">
                        Месец
                    </td>
                    <td style="width:30%">
                        Сума
                    </td>
                </tr>

                <% for (SocialInsuranceRecord record : records) {%>

                <tr bgcolor="#F5E8FF">
                    <td style="width:35%">
                        <%= String.format("%d", record.getYear())%>
                    </td>
                    <td style="width:35%">
                        <%= String.format("%d", record.getMonth())%>
                    </td>
                    <td style="width:30%">
                        <%= String.format("%.2f", record.getAmount())%>
                    </td>

                </tr>
                <% } %>

            <tbody>

        </table>



        <% }%>



    </body>
</html>

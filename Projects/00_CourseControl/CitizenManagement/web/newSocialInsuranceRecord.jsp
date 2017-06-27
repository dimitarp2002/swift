<%-- 
    Document   : newSocialInsuranceRecord
    Created on : Jun 26, 2017, 1:07:54 PM
    Author     : dimitar
--%>

<%@page import="education.Education"%>
<%@page import="businessmanagers.*"%>
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
        <h1>WELCOME TO CITIZEN MANAGER!</h1>
        <h1>Adding new Social Insurance Record for</h1>

        <%
            BusinessManagerCitizen BMC = new MyBusinessManagerCitizen();
            BusinessManagerAddress BMA = new MyBusinessManagerAddress();
//            BusinessManagerEducation BME = new MyBusinessManagerEducation();
            BusinessManagerSIRecords BMS = new MyBusinessManagerSIRecords();
            
            String citizenId = request.getParameter("citizenId");
            String yearStr = request.getParameter("year");
            String monthStr = request.getParameter("month");
            String amountStr = request.getParameter("amount");
            
            
            if (yearStr != null && monthStr != null && amountStr != null) {
                BMS.insertSiRecord(yearStr, monthStr, amountStr, citizenId);
            }
            Citizen citizen = BMC.getCitizen(citizenId);
            Address address = BMA.getAddress(citizenId);
            List<SocialInsuranceRecord> records = new ArrayList<>();
            records = BMS.getSiRecords(citizenId);
            

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

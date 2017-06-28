<%-- 
    Document   : userInfo
    Created on : Jun 26, 2017, 1:06:43 PM
    Author     : dimitar
--%>

<%@page import="businessmanagers.BusinessManagerCitizen"%>
<%@page import="businessmanagers.*"%>
<%@page import="education.GradedEducation"%>
<%@page import="education.Education"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="address.Address"%>
<%@page import="personaldetails.Citizen"%>
<%@page import="java.sql.SQLException"%>
<%@page import="storages.*"%>
<%@page import="storages.MySqlCitizenStorage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body bgcolor="#E6E6FA">
        <h1>WELCOME TO CITIZEN MANAGER!</h1>
        
        <%
            Class.forName("com.mysql.jdbc.Driver");
        %>

        <%
            BusinessManagerCitizen BMC = new MyBusinessManagerCitizen();
            BusinessManagerAddress BMA = new MyBusinessManagerAddress();
            BusinessManagerEducation BME = new MyBusinessManagerEducation();
//            BusinessManagerSIRecords BMS = new MyBusinessManagerSIRecords();
            
            String citizenId = request.getParameter("citizenId");
            String isEligible = request.getParameter("isEligiblebutton");
            
            Citizen citizen = BMC.getCitizen(citizenId);
            Address address = BMA.getAddress(citizenId);
            List<Education> educations = BME.getEducations(citizenId);
            

        %>
        <%  if (citizen != null) {%>
        <h1>Info for Citizen!</h1>
        <h3> 
            <%= citizen.toString()%>
        </h3>
        <br>
        <div>Адрес:</div>
        <div><%= address.toString()%> </div>
        <br>
        
        <% if (isEligible !=null) {%>
       
        <form name="toHome" action="userInfo.jsp" method="POST">
            <input type="submit" value="Home" name="toHomeButton" />
        </form>
        <br>
        
        <% } %> 
        
        
        <table border="0" cellspacing="1" width="50%" bgcolor="#000000">  
            <th bgcolor="#F5E8FF" colspan="6">
                Образования:
            </th>
            <tbody>
                <tr bgcolor="#F5E8FF">
                    <td style="width:35%">
                        Институция
                    </td>
                    <td style="width:15%">
                        От дата
                    </td>
                    <td style="width:15%">
                        До дата
                    </td>
                    <td style="width:15%">
                        Степен
                    </td>
                    <td style="width:10%">
                        Завършено
                    </td>
                    <td style="width:10%">
                        Оценка
                    </td>
                </tr>
                <% for (Education e : educations) {%>

                <tr bgcolor="#F5E8FF">
                    <td style="width:35%">
                        <%= String.format("%s", e.getInstitutionName())%>
                    </td>
                    <td style="width:15%">
                        <%= String.format("%s", e.getEnrollmentDate())%>
                    </td>
                    <td style="width:15%">
                        <%= String.format("%s", e.getGraduationDate())%>
                    </td>
                    <td style="width:15%">
                        <%= String.format("%s", e.getDegree())%>
                    </td>
                    <td style="width:10%">
                        <%= String.format("%s", e.isGraduated())%>
                    </td>
                    <% if (e instanceof GradedEducation && e.isGraduated()) {%>
                    <td style="width:10%">
                        <%= String.format("%s", ((GradedEducation) e).getFinalGrade())%>
                    </td>
                    <% } else {%>
                    <td style="width:10%">
                        <%= String.format("  ")%>
                    </td>
                    <% } %>
                </tr>

                <% }%>
            </tbody>   
        </table>
        <br>
        <br>
        <br>
        <table border="0" cellspacing="1" width="65%">
            <tbody>
                <tr>
                    <td style="width:33%">
                        <form name="newSRI" action="newSocialInsuranceRecord.jsp">
                            <input type="submit" value="newSocialInsuranceRecord" name="newSRIbutton" />
                            <input type="hidden" name="citizenId"
                                   value="<%=request.getParameter("citizenId")%>" />
                        </form>
                    </td>
                    <td style="width:33%">
                        <form name="newEdu" action="newEducation.jsp">
                            <input type="submit" value="newEducation" name="newEDUbutton" />
                            <input type="hidden" name="citizenId"
                                   value="<%=request.getParameter("citizenId")%>" />
                        </form>
                    </td>
                    <% if (isEligible == null) { %>
                    <td style="width:34%">
                        <form name="newEdu" action="userInfo.jsp">
                            <input type="submit" value="isEligible" name="isEligiblebutton" />
                            <input type="hidden" name="citizenId"
                                   value="<%=request.getParameter("citizenId")%>" />
                        </form>
                    </td>
                    <% }else { %>
                    <td>
                        <%=  String.format("%s", BMC.isEligible(citizenId))   %>
                    </td>
                    
                    <% }%>
                </tr>
            </tbody>
        </table>
        <% } else { %>

        <h1>Search People in the DB </h1>
        <form name="search_form" action="userInfo.jsp">
            <input type="text" placeholder="insert citizen id" name="citizenId" value=""/>
            <input type="submit" value="OK" name="submit" />
        </form>
        <% } %>
    </body>
</html>

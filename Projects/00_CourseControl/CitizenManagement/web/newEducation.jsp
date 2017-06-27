<%-- 
    Document   : newEducation
    Created on : Jun 26, 2017, 1:07:28 PM
    Author     : dimitar
--%>

<%@page import="education.GradedEducation"%>
<%@page import="education.Education"%>
<%@page import="java.util.List"%>
<%@page import="address.Address"%>
<%@page import="personaldetails.Citizen"%>
<%@page import="businessmanagers.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="#E6E6FA">
        <h1>WELCOME TO CITIZEN MANAGER!</h1>
        <h1>Adding new Education for </h1>
        <%
            request.setCharacterEncoding("UTF-8");
            BusinessManagerCitizen BMC = new MyBusinessManagerCitizen();
            BusinessManagerAddress BMA = new MyBusinessManagerAddress();
            BusinessManagerEducation BME = new MyBusinessManagerEducation();
//            BusinessManagerSIRecords BMS = new MyBusinessManagerSIRecords();

            String citizenId = request.getParameter("citizenId");
            String institutionName = request.getParameter("institution");
            String enrollmentDate = request.getParameter("enrollmentDate");
            String graduationDate = request.getParameter("graduationDate");
            String degree = request.getParameter("degree");
            String isGraduated = request.getParameter("isGraduated");
            String grade = request.getParameter("grade");
            
            if ( institutionName != null && enrollmentDate != null && graduationDate != null 
                    && degree != null && isGraduated!= null){
                    BME.insertEducation(institutionName, enrollmentDate, graduationDate, degree, isGraduated, grade, citizenId );
            }
            
            Citizen citizen = BMC.getCitizen(citizenId);
            Address address = BMA.getAddress(citizenId);
            List<Education> educations = BME.getEducations(citizenId);

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
                    <% if (e instanceof GradedEducation) {%>
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
        <form name="insertNewEducation" action="newEducation.jsp" method="POST"  >
            <table border="0" cellspacing="1" width="50%" >
                <tbody>
                    <tr>
                        <td align="center">
                            Институция
                        </td>
                        <td align="center">
                            От дата
                        </td>
                        <td align="center">
                            До дата
                        </td>
                        <td align="center">
                            Степен
                        </td>
                        <td align="center">
                            Завършено
                        </td>
                        <td align="center">
                            Оценка
                        </td>
                        </tr>
                    <tr>
                        <td align="center">
                            <input type="text" name="institution" value="" size="30"  />
                        </td>
                        <td align="center">
                            <input type="text" name="enrollmentDate" value="" maxlength="10" size="10"/>
                        </td>
                        <td align="center">
                            <input type="text" name="graduationDate" value=""  maxlength="10" size="10"/>
                        </td>
                        <td align="center">
                            <input type="text" name="degree" value=""  maxlength="10" size="10"/>
                        </td>
                        <td align="center">
                            <input type="text" name="isGraduated" value=""  maxlength="5" size="6"/>
                        </td>
                        <td align="center">
                            <input type="text" name="grade" value=""  maxlength="5" size="5"/>
                        </td>
                <input type="hidden" name="citizenId"
                       value="<%=request.getParameter("citizenId")%>" />
                </tr>
                <tr>
                    <td>
                <input type="submit" value="Add New Education" name="newEduButton" />
                </td>
                </tr>
                </tbody>
            </table>
        </form>

        <% }%>

    </body>
</html>

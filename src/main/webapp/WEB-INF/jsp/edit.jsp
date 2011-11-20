<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editing contact</title>
    <style type="text/css">
        body {
            font-family: sans-serif;
        }
        .data, .data td {
            border-collapse: collapse;
            width: 100%;
            border: 1px solid #aaa;
            margin: 2px;
            padding: 2px;
        }
        .data th {
            font-weight: bold;
            background-color: #5C82FF;
            color: white;
        }
    </style>
</head>

<body>
<h2>Editing contact</h2>

<form:form method="post" action="/contacts/add.html" commandName="contact">
  <table>
    <tr>
      <td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
      <td><form:input path="firstname" value="${contact.firstname}"/></td> 
    </tr>
    <tr>
      <td><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
      <td><form:input path="lastname" value="${contact.lastname}"/></td>
    </tr>
    <tr>
      <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
      <td><form:input path="email" value="${contact.email}"/></td>
    </tr>
    <tr>
      <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
      <td><form:input path="telephone"  value="${contact.telephone}"/></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="<spring:message code="label.editcontact"/>"/>
      </td>
    </tr>
  </table>
  <input name="id" type="hidden" value="${contact.id}"/>
</form:form>

<p>
  <!-- TODO: 'Show', (@contact) | contacts_path  -->
  <a href="/contacts/show/${contact.id}">Show</a> | <a href="/contacts/index">Back</a>
</p>


</body>
</html>
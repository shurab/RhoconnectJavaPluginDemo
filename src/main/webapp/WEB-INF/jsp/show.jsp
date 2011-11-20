<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person Info</title>
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

  <table>
    <tr>
        <td>First Name: </td>
        <td>${contact.firstname}</td> 
    </tr>
    <tr>
        <td>Last Name: </td>
        <td>${contact.lastname}</td> 
    </tr>
    <tr>
        <td>Email: </td>
        <td>${contact.email}</td>
    </tr>
    <tr>
        <td>Telephone: </td>
        <td>${contact.telephone}</td>
    </tr>
  </table>
 
<p>
  <!-- TODO: edit_contact_path(@contact) | contacts_path  -->
  <a href="/contacts/edit/${contact.id}">Edit</a> | 
  <a href="/contacts/index">Back</a>
</p>

</body>
</body>
</html>
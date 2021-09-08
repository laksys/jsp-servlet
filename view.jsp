<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
	<title>view</title>
</head>
<body>

<h2>List of candidates</h2>

<sql:query dataSource="jdbc/crud" var="rs">
	SELECT
		cid,
		cname,
		fname,
		gender,
		mobile,
		address
	 FROM
		candidate
</sql:query>

<table border="1">
	<tr>
		<th>Id</th>
		<th>Candidate Name</th>
		<th>Father Name</th>
		<th>Gender</th>
		<th>Mobile</th>
		<th>Addresss</th>
	</tr>
	<c:forEach var="row" items="${rs.rows}">
	<tr>
		<td>${row.cid}</td>
		<td>${row.cname}</td>
		<td>${row.fname}</td>
		<td>${row.gender}</td>
		<td>${row.mobile}</td>
		<td>${row.address}</td>
	</tr>
	</c:forEach>
</table>
<p>&nbsp;</p>
<a href="index.html">Home</a>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Localized Dates</title></head>
<body bgcolor="white">
<h3>File Upload</h3>
select a file to upload:<br />
<form action="index.jsp" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="50" />
    <input type="submit" value="Upload File" />
</form>
</body>
</html>
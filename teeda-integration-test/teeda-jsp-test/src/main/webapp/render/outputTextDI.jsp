<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head>
<title>this is outputTextDI.jsp</title>
</head>
<body>
<f:view>
	<h:outputText id="helloDI" value="#{helloBeanDI.hello}"/>
</f:view>
</body>
</html>

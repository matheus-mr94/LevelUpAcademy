<%@tag language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title" required="true" %>

<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="container">
            <jsp:doBody/>
        </div>
    </body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <meta charset="utf8">
    </head>
    <body>
        <h1><%= "Hello World!" %></h1><br/>
        <form method="post" action="search?action=submit" accept-charset="utf-8">
            <dl>
                <dt>слова: </dt>
                <dd><input type="text" name="id" value="${word}" placeholder="${word}" /></dd>
            </dl>
            <button type="submit">Find</button>
        </form>

    </body>
</html>
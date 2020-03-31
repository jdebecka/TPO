<%--
  Created by IntelliJ IDEA.
  User: juliadebecka
  Date: 31/03/2020
  Time: 6:41 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <h1 style="align-content: center">Let me help you add 2 integers</h1>
    <br>
    <form action="${pageContext.request.contextPath}/calculator" >
      <label>First int:</label>
      <label>
        <input name="first" type="text" required style="align-content: center">
      </label>
      <br>
      <label>Second int:</label>
      <label>
        <input name="second" type="text" required style="align-content: center" >
      </label>
      <br>
      <input type="submit" value="GET" formmethod="get">
      <input type="submit" value="POST"  formmethod="post">
    </form>
  <h3>Response</h3>
  <p>Your response is  <span style="font-weight: bold"> ${response}</p>

  </body>
</html>

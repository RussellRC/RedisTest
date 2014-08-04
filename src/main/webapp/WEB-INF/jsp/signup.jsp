<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="signUp" method="post">
    <table>
        <tr>
            <td><fmt:message key="email" /></td>
            <td><input name="email" /></td>
        </tr>
        <tr>
            <td><fmt:message key="username" /></td>
            <td><input name="username" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password" /></td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password.again" /></td>
            <td><input type="password" name="password2" /></td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="signup"/>">
</form>
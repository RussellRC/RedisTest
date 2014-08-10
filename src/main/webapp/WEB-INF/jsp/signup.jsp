<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="register" method="post">
    <table>
        <tr>
            <td><fmt:message key="email" /></td>
            <td><form:input id="email" name="email" path="userSignUp.email" /></td>
            <td><form:errors path="userSignUp.email" cssClass="error" /></td>
        </tr>
        <tr>
            <td><fmt:message key="username" /></td>
            <td><form:input id="username" name="username" path="userSignUp.username" /></td>
            <td><form:errors path="userSignUp.username" cssClass="error" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password" /></td>
            <td><form:input type="password" id="password" name="password" path="userSignUp.password" /></td>
            <td><form:errors path="userSignUp.password" cssClass="error" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password.again" /></td>
            <td><form:input type="password" id="password2" name="password2" path="userSignUp.password2" /></td>
            <td><form:errors path="userSignUp.password2" cssClass="error" /></td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="signup"/>">
</form>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form name="f" action="<c:url value='/j_spring_security_check'/>" method="POST">
    <ol>
        <li>
            <label style="display:none;"><spring:message code="username"/></label>
            <input name="j_username" class="text-input" type="text" autocomplete="off" autocorrect="off" autocapitalize="off" placeholder="<spring:message code="username"/>">
        </li>
        <li>
            <label style="display:none;"><spring:message code="password"/></label> 
            <input name="j_password" type="password" class="text-input" autocomplete="off" placeholder="<spring:message code="password"/>">
        </li>
    </ol>
    <input type="submit" value="<fmt:message key="signin"/>">
</form>
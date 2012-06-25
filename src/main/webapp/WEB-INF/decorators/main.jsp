<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<%@include file="header.jsp" %>

<body bgcolor="#FFFFFF">
    <table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
            <td colspan="7">
                <img src='<c:url value="/images/Banner2.jpg"/>' alt="Header image" width="100%" height="180" border="0"/>
            </td>
        </tr>
        <tr>
            <td colspan="7" bgcolor="#C0DFFD">
                <img src='<c:url value="/images/mm_spacer.gif"/>' alt="Spacer Image" width="1" height="2" border="0"/>
            </td>
        </tr>
        <tr bgcolor="#C0DFFD">
            <td colspan="7" id="dateformat" height="20">&nbsp;&nbsp;
                <fmt:formatDate var="TODAY" value="<%=new java.util.Date()%>" type="DATE"/>
                ${TODAY}
            </td>
        </tr>
        <tr>
            <td colspan="7" bgcolor="#C0DFFD">
                <img src='<c:url value="/images/mm_spacer.gif"/>' alt="Spacer Image" width="1" height="2" border="0"/>
            </td>
        </tr>
        <tr>
            <td bgcolor="#3366CC" colspan="7">
                <table border="0" cellspacing="0" cellpadding="0" width="100%" id="navigation">
                    <td width="60"><a href="loginform.html" class="navText">Home</a></td>
                    <td width="90"><a href="edituser.html" class="navText">My Profile</a></td>
                    <td width="90"><a href="myphotos.html" class="navText">My Photos</a></td>
                    <td width="100"><a href="javascript:;" class="navText">My Contacts</a></td>
                    <td width="110"><a href="javascript:;" class="navText">Create Album</a></td>
                    <td width="70"><a href="upload.html" class="navText">Upload</a></td>
                    <c:if test="${not empty User}">
                        <td width="70"><a href="logout.html" class="navText">Logout</a></td>
                    </c:if>
                    <td width="360">&nbsp;</td>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="7">
                <decorator:body/>
            </td>
        </tr>
        <tr>
            <td colspan="7">
                <%@ include file="footer.jsp" %>
            </td>
        </tr>
    </table>
</body>

</html>


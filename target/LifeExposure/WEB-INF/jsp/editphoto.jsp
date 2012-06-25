<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script language="javascript">
        function returnHome() {
            document.location.href = "photodetails.html?photoId=${photo.photoId}"
        }

        function addTag() {
            document.location.href = "editphoto.html"
        }

        function delPhoto() {
            var isOK = confirm("Are you sure to delete this photo?");
            if (isOK) {
                document.location.href = "deletephoto.html?photoId=${photo.photoId}"
            }
        }
    </script>
</head>

<body>
<form:form method="POST" commandName="photoCmd" enctype="multipart/form-data" onsubmit="return Validate();">
    <table>
        <tr>
            <td width="70" height="70">
                <img src="image/userimage.html?userId=${userId}" alt="Header image" width="60" height="60" border="0"/>
            </td>
            <td>
                <b><big>Hi! ${loginName}</big></b>
            </td>
        </tr>
    </table>
    <table>
        <tr height="50px">
            <td>
               &nbsp;
            </td>
        </tr>
        <tr>
            <td>
                <img src="image/photoimage.html?photoId=${photo.photoId}" alt="photo" border="0"/>
            </td>
            <td width="=50px">
                &nbsp;
            </td>
            <td>
                <fieldset>
                    <legend>Photo Details</legend>
                    <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                        <tr>
                            <td>Add a caption:</td>
                            <td><form:input path="caption" maxlength="255"/></td>
                            <td><form:errors path="caption" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td>Location: (optional)</td>
                            <td><form:input path="location" maxlength="255"/></td>
                        </tr>
                        <tr>
                            <td>Description: (optional)</td>
                            <td><form:input path="description" maxlength="255"/></td>
                        </tr>
                        <tr>
                            <td>Tag: (optional)</td>
                            <td>
                                <form:select path="tag">
                                    <c:forEach var="photoTag" items="${photoTagList}">
                                        <form:option value="${photoTag.tag}" label="${photoTag.tag}"/>
                                    </c:forEach>
                                </form:select>
                                <%--<input type="button" align="right" value="Add Tag" onClick="addTags()">--%>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br>
                <input type="submit" align="right" value="Ok">
                <input type="button" align="right" value="Cancel" onClick="returnHome()">
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" align="right" value="Delete" onClick="delPhoto()">
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
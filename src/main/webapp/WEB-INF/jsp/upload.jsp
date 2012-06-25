<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script language="javascript">
        function returnHome() {
            document.location.href = "myphotos.html"
        }
    </script>
    <script language="JavaScript">
        function Validate() {
            var image = document.getElementById("image").value;
            if (image == '') {
                alert("Please select a photo.");
                document.getElementById("image").focus();
                return false;
            }
            var checking = image.toLowerCase();
            if (!checking.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
                alert("Please enter Image File Extensions .jpg, .png, .jpeg");
                document.getElementById("image").focus();
                return false;
            }
            return true;
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
        <fieldset>
            <legend>Photo Details</legend>
            <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td>Upload Photo:</td>
                    <td><input path="photo" type="file" name="file" id="image"/></td>
                    <td><form:errors path="photo" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label class="required"> * </label>Add a caption:</td>
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
                    </td>
                </tr>
            </table>
        </fieldset>
        <br>
        <input type="submit" align="right" value="Ok">
        <input type="button" align="right" value="Cancel" onClick="returnHome()">
    </form:form>
</body>

</html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet"
          type="text/css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

    <script>
        $(function() {
            $("#datepicker").datepicker();
        });
    </script>

    <script language="JavaScript">
        function Validate() {
            var image = document.getElementById("image").value;
            if (image == '') {
                alert("Please select a profile picture.");
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
    <form:form method="post" commandName="editUser" enctype="multipart/form-data" onsubmit="return Validate();">
        <table width="100%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td><form:hidden path="loginName"/></td>
            </tr>
            <tr>
                <td>Login Name:</td>
                <td>${editUser.loginName}</td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password"  maxlength="255"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><form:input path="firstName"  maxlength="255"/></td>
                <td><form:errors path="firstName" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input path="lastName"  maxlength="255"/></td>
                <td><form:errors path="lastName" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Date of Birth:</td>
                <td><form:input path="birthDate" id="datepicker"/></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                    <form:select path="country">
                        <form:option value="Bangladesh" label="Bangladesh"/>
                        <form:option value="USA" label="USA"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                    <form:radiobutton path="gender" value="M" label="M"/>
                    <form:radiobutton path="gender" value="F" label="F"/>
                </td>
            </tr>
            <tr>
                <td>Profile Picture:</td>
                <td><input path="profilePicThumbnail" type="file" name="file" id="image"/></td>
                <td><form:errors path="profilePicThumbnail" cssClass="error"/></td>
            </tr>
        </table>
        <br>
        <input type="submit" align="center" value="Save">
    </form:form>
</body>

</html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <link href='<c:url value="/rating/rating_simple.css"/>' rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src='<c:url value="/rating/jquery.js"/>'></script>
    <script type="text/javascript" src='<c:url value="/rating/rating_simple.js"/>'></script>
    <script language="javascript" type="text/javascript">
        function test(value) {
            alert("This rating's value is " + value);
        }
        $(function () {
            $("#rating").webwidget_rating_simple({
                rating_star_length:'5',
                rating_initial_value:'0',
                rating_function_name:'', //this is function name for click
                directory:'rating/'
            });
        });
    </script>
</head>

<body>
    <table>
        <tr>
            <td height="50px">
               &nbsp;
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <tr>
                        <td align="right">
                            <a href="prevphotopublic.html?photoId=${photo.photoId}">Prev</a> | <a href="nextphotopublic.html?photoId=${photo.photoId}">Next</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <img src="image/photoimage.html?photoId=${photo.photoId}" alt="photo" border="0"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>${photo.caption}</b>
                        </td>
                    </tr>
                    <tr>
                        <td class="tableText">
                            ${photo.description}
                        </td>
                    </tr>
                    <tr>
                        <td class="tableText">
                            Location: ${photo.location}
                        </td>
                    </tr>
                </table>
            </td>
            <td width="50px">
                &nbsp;
            </td>
            <td>
                <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="70" height="70">
                            <img src="image/userimage.html?userId=${photo.user.userId}" alt="Header image" width="60" height="60"
                                 border="0"/>
                        </td>
                        <td>
                            <b><big>Uploaded by ${photo.user.loginName} </big></b>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td height="20px">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Views: ${photo.views}
                        </td>
                    </tr>
                    <tr>
                        <td>Rating: ${rating}</td>
                    </tr>
                </table>
                <br>
                <br>
                <c:if test="${not empty commentList}">
                    <fieldset>
                        <legend>Photo Comments</legend>
                        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                            <c:forEach var="comment" items="${commentList}">
                                <tr>
                                    <td class="tableText">${comment.commentedBy.loginName}</td>
                                    <td>${comment.photoComment}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </fieldset>
                </c:if>
            </td>
        </tr>
    </table>
</body>

</html>
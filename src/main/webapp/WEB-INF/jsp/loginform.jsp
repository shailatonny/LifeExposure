<%--
  Created by IntelliJ IDEA.
  User: saima
  Date: 6/7/12
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <!-- the CSS for Smooth Div Scroll -->
    <link rel="Stylesheet" type="text/css" href='<c:url value="/css/smoothDivScroll.css"/>'/>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <script src='<c:url value="/js/jquery-ui-1.8.18.custom.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery.mousewheel.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery.smoothdivscroll-1.2-min.js"/>' type="text/javascript"></script>

    <!-- Plugin initialization -->
    <script type="text/javascript">
        $(document).ready(function () {
            $("div#makeMeScrollable").smoothDivScroll({
                mousewheelScrolling: true,
                manualContinuousScrolling: true,
                visibleHotSpotBackgrounds: "always",
                autoScrollingMode: ""
            });
        });
    </script>

    <!-- Styles for my specific scrolling content -->
    <style type="text/css">
        #makeMeScrollable {
            width: 500px;
            height: 330px;
            position: relative;
        }

        #makeMeScrollable div.scrollableArea img {
            position: relative;
            display: block;
            float: left;
            margin: 0;
            padding: 0;
            /* If you don't want the images in the scroller to be selectable, try the following
                  block of code. It's just a nice feature that prevent the images from
                  accidentally becoming selected/inverted when the user interacts with the scroller. */
            -webkit-user-select: none;
            -khtml-user-select: none;
            -moz-user-select: none;
            -o-user-select: none;
            user-select: none;
        }
    </style>
</head>

<body>
    <table>
        <tr>
            <td height="50px">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td width="50px">
                &nbsp;
            </td>
            <td>
                <table>
                    <tr>
                        <td>
                            <b><big>All public photos</big></b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="makeMeScrollable" align="center">
                            <c:forEach var="entry" items="${photoList}">
                                <c:if test="${empty User}">
                                    <a href="photodetailspublic.html?photoId=${entry.photoId}">
                                        <img src="image/photoimage.html?photoId=${entry.photoId}" alt="Demo image"/>
                                    </a>
                                </c:if>
                                <c:if test="${not empty User}">
                                    <a href="photodetails.html?photoId=${entry.photoId}">
                                        <img src="image/photoimage.html?photoId=${entry.photoId}" alt="Demo image"/>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Click any photo to see its details.
                        </td>
                    </tr>
                </table>
            </td>
            <td width="50px">
                &nbsp;
            </td>
            <td>
                <c:if test="${empty User}">
                    <table>
                        <tr>
                            <td class="msgText">
                                ${msg}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:form method="POST" commandName="loginForm">
                                    <fieldset>
                                        <legend>LogIn</legend>
                                        <table width="100%" align="right">
                                            <tr>
                                                <td>User Name:</td>
                                                <td><form:input path="userName"/></td>
                                                <td><form:errors path="userName" cssClass="error"/></td>
                                            </tr>
                                            <tr>
                                                <td>Password:</td>
                                                <td><form:password path="password"/></td>
                                                <td><form:errors path="password" cssClass="error"/></td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                    <br>
                                    <input type="submit" align="right" value="Submit">
                                </form:form>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="newuser.html">Sign Up</a>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </td>
        </tr>
    </table>

    <div id="reference" style="width: 1px; height: 1px;"></div>
</body>

</html>
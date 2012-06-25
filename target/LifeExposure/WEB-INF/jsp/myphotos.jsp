<%--
  Created by IntelliJ IDEA.
  User: Saima
  Date: 6/11/12
  Time: 1:02 AM
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
    <table bottom:100 right:10>
        <tr>
            <td width="70" height="70">
                <img src="image/userimage.html?userId=${userId}" alt="Header image" width="60" height="60" border="0"/>
            </td>
            <td>
                <b><big>Hi! ${loginName} </big></b>
            </td>
        </tr>
    </table>

    <table>
        <tr>
            <td height="50px">
                &nbsp;
            </td>
        </tr>
        <c:if test="${empty photoList}">
            <tr>
                <td>
                    Start uploading your photo in LifeExposure!
                </td>
            </tr>
        </c:if>
        <c:if test="${not empty photoList}">
            <tr>
                <td width="250px">
                    &nbsp;
                </td>
                <td>
                    <div id="makeMeScrollable" align="center">
                        <c:forEach var="entry" items="${photoList}">
                            <a href="photodetails.html?photoId=${entry.photoId}">
                                <img src="image/photoimage.html?photoId=${entry.photoId}" alt="Demo image"/>
                            </a>
                        </c:forEach>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="250px">
                    &nbsp;
                </td>
                <td>
                    Click any photo to see its details.
                </td>
            </tr>
        </c:if>
    </table>

    <div id="reference" style="width: 1px; height: 1px;"></div>
</body>

</html>
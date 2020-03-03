<%@ page import="org.csu.mypetstore.domain.Account" %><%--
  Created by IntelliJ IDEA.
  User: 蒯支睿
  Date: 2019/10/2
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="../../../css/jpetstore.css" type="text/css"
          media="screen" />

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <script type="text/javascript" src="../../../js/jquery.3.4.1.js"></script>
    <style>
        .card {
            margin:0;
            padding:0;
            display:none;
        }
        .tab {
            position: relative;
            left: 445px;
            margin:0;
            padding:0;
            list-style:none;
            overflow:hidden;
        }
        .tab li {
            width: 145px;
            float:left;
            color:black;
            border:1px solid black;
            text-align:center;
            line-height:30px;
            cursor:pointer;
        }
        .on {
            display:block;
        }
        .tab li.cur {
            background-color:pink;
        }

    </style>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="../../../images/logo-topbar.gif" /></a>
        </div>
    </div>

<%--    <div id="Menu">--%>
<%--        <div id="MenuContent">--%>
<%--            <a href="viewCart"><img align="middle" name="img_cart" src="../../../images/cart.gif" /></a>--%>
<%--            <img align="middle" src="../../../images/separator.gif" />--%>
<%--            <a href="Signon">Sign In</a>--%>
<%--            <a href="main">Sign Out</a>--%>
<%--            <img align="middle" src="../../../images/separator.gif" />--%>
<%--            <a href="confirmEdit">My Account</a>--%>
<%--            <img align="middle" src="../../../images/separator.gif" />--%>
<%--            <a href="../../../help.html">?</a>--%>
<%--        </div>--%>
    <div id="Menu">
    <div id="MenuContent">
        <%! Account account; %>
        <% account = (Account)session.getAttribute("loginUser"); %>

<%--        <a href="viewCart"><img align="middle" name="img_cart" src="../images/cart.gif" /></a>--%>

        <c:if
                test="${account == null}">

            <a href="Signon"><img align="middle" name="img_cart" src="../images/cart.gif" /></a>

        </c:if> <c:if test="${account != null}">
        <c:if test="${!sessionScope.accountBean.authenticated}">

            <a href="viewCart"><img align="middle" name="img_cart" src="../images/cart.gif" /></a>

        </c:if></c:if>


        <img align="middle" src="../images/separator.gif" /> <c:if
            test="${account == null}">

                     <a href="Signon">Sign In</a>

    </c:if> <c:if test="${account != null}">
        <c:if test="${!sessionScope.accountBean.authenticated}">

                     <a href="Signout">Sign Out</a>

        </c:if></c:if>
        <c:if test="${account != null}">
        <c:if test="${!sessionScope.accountBean.authenticated}">

            <a href="confirmEdit">My Account</a>

        </c:if></c:if>
<%--     <c:if test="${account != null}">--%>
<%--        <c:if test="${sessionScope.accountBean.authenticated}">--%>
<%--            <img align="middle" src="../images/separator.gif" />--%>
<%--            <a href="confirmEdit">My Account</a>--%>
<%--        </c:if>--%>
<%--    </c:if>--%>
        <img align="middle" src="../images/separator.gif" /> <a
            href="../help.html">?</a></div>
</div>

<%--    </div>--%>

    <div id="Search">
        <div id="SearchContent">
            <form action="search" method="post">
                <input id="text" type="text" name="keyword" autocomplete="off" size="14" />
                <input type="submit" name="searchProducts" value="Search" />
                <div id="tips"></div>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="ViewCategory?categoryId=FISH">
            <img src="../../../images/sm_fish.gif" /></a>
        <img src="../../../images/separator.gif" />
        <a href="ViewCategory?categoryId=DOGS">
            <img src="../../../images/sm_dogs.gif" /></a>
        <img src="../../../images/separator.gif" />
        <a href="ViewCategory?categoryId=REPTILES">
            <img src="../../../images/sm_reptiles.gif" /></a>
        <img src="../../../images/separator.gif" />
        <a href="ViewCategory?categoryId=CATS">
            <img src="../../../images/sm_cats.gif" /></a>
        <img src="../../../images/separator.gif" />
        <a href="ViewCategory?categoryId=BIRDS">
            <img src="../../../images/sm_birds.gif" /></a>
    </div>

</div>

<div id="Content">

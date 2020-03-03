<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<div align="middle">
    <h1>My Orders</h1>

    <table>
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Total Price</th>
            </tr>
        <c:forEach var="order" items="${sessionScope.orderList}">
            <tr>
                <td>
                    <a href="checkOrder?orderId=${order.orderId}">${order.orderId}</a>
                </td>
                <td>
                    <fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
                </td>
                <td>
                    <fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
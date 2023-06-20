<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="col-8 offset-2">
        <form action="/coupons/search" method="get">
            <div class="row mb-3">
                <div class="col-6">
                    <label>Form:</label>
                    <input type="date" name="startDay" class="form-control">
                </div>
                <div class="col-6">
                    <label>To:</label>
                    <input type="date" name="endDay" class="form-control">
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-8">
                    <label>Customer:</label>
                    <select name="customer" class="form-select">
                        <c:forEach items="${listCustomers}" var="customer">
                            <option value="${customer.id}">${customer.id}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mb-5">
                <div class="col-6">
                    <button type="submit" class="btn btn-info">Search</button>
                </div>
            </div>
        </form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Start day</th>
            <th scope="col">End day</th>
            <th scope="col">Reduce value</th>
            <th scope="col">Status</th>
            <th scope="col">Customer</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCoupons}" var="coupons" varStatus="stt">
            <tr>
                <td>${stt.index +1}</td>
                <td>${coupons.code}</td>
                <td>${coupons.name}</td>
                <td>${coupons.startDay}</td>
                <td>${coupons.endDay}</td>
                <td>${coupons.reduceValue}</td>
                <td>${coupons.status ==0?"Ket thuc":"Dang chay"}</td>
                <td>${coupons.customer.name}</td>
                <td>
                    <a onclick="return confirm('Are you sure?')" class="btn btn-info"
                       href="/coupons/delete/${coupons.code}">Delete</a>
                    <a onclick="return confirm('Are you sure?')" class="btn btn-danger"
                       href="/coupons/edit/${coupons.code}">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination float-end">
            <li class="page-item">
                <a class="page-link" href="/coupons/show?page=${currentPage - 1 <= 0 ? 0 : currentPage - 1}">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:if test="${totalPages - 1 >= 0}">
                <c:forEach var="i" begin="0" end="${totalPages - 1}">
                    <li class="page-item"><a class="page-link" href="/coupons/show?page=${i}">${i + 1}</a></li>
                </c:forEach>
            </c:if>
            <li class="page-item">
                <a class="page-link"
                   href="/coupons/show?page=${currentPage + 1 >= totalPages - 1 ? totalPages - 1 : currentPage + 1}">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
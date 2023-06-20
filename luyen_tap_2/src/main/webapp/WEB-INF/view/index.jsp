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
    <div class="col-10 offset-1">
        <form method="get" action="/customer/search">
            <div class="row mt-5">
                <div class="col-6">
                    <input type="text" name="keyword" class="form-control">
                </div>
                <div class="col-4">
                    <select name="name" class="form-select">
                        <c:forEach items="${listCustomerClass}" var="customerClass">
                            <option value="${customerClass.code}">${customerClass.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-2">
                    <button type="submit" class="btn btn-danger">Search</button>
                </div>
            </div>
        </form>
    </div>
    <div class="col-6 offset-3">
        <form:form modelAttribute="customer" action="/customer/save" method="post">
            <div class="row mb-3">
                <label>Code</label>
                <form:input path="code" cssClass="form-control" disabled="true"/>
            </div>
            <div class="row mb-3">
                <label>Name</label>
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssStyle="color: red"/>
            </div>
            <div class="row mb-3">
                <label>Phone number</label>
                <form:input path="phoneNumber" cssClass="form-control"/>
                <form:errors path="phoneNumber" cssStyle="color: red"/>
                <span style="color: red">${mess}</span>
            </div>
            <div class="row mb-3">
                <label>Gender</label>
                <div class="form-check">
                    <form:radiobutton path="gender" cssClass="form-check-input" value="true" label="Male"/>
                </div>
                <div class="form-check">
                    <form:radiobutton path="gender" cssClass="form-check-input" value="false" label="Female"/>
                </div>
                <form:errors path="gender" cssStyle="color: red"/>

            </div>
            <div class="row mb-3">
                <label>Customer class</label>
                <form:select path="customerClass" cssClass="form-select">
                    <c:forEach items="${listCustomerClass}" var="customerClass">
                        <form:option value="${customerClass.code}">${customerClass.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="row mb-3">
                <div class="col-6">
                    <button onclick="return confirm('Are you sure?')" type="submit" class="btn btn-info">Add</button>
                </div>
            </div>
        </form:form>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Phone number</th>
            <th scope="col">Gender</th>
            <th scope="col">CustomerClass</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCustomers}" var="customer" varStatus="stt">
            <tr>
                <td>${stt.index +1}</td>
                <td>${customer.name}</td>
                <td>${customer.phoneNumber}</td>
                <td>${customer.gender == true ? "Male" : "Female"}</td>
                <td>${customer.customerClass.name}</td>
                <td>
                    <a onclick="return confirm('Are you sure?')" class="btn btn-info" href="/customer/delete/${customer.code}">Delete</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination float-end">
            <li class="page-item">
                <a class="page-link"
                   href="/customer/show?page=${currentPage - 1 <= 0 ? 0 : currentPage - 1}">Previous</a>
            </li>
            <c:if test="${totalPages -1 >=0}">

                <c:forEach var="i" begin="0" end="${totalPages -1}">
                    <li class="page-item"><a class="page-link" href="/customer/show?page=${i}">${ i + 1 }</a></li>
                </c:forEach>
            </c:if>
            <li class="page-item">
                <a class="page-link"
                   href="/customer/show?page=${currentPage + 1 >= totalPages - 1 ? totalPages - 1 : currentPage + 1}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>
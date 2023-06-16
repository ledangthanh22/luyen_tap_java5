<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<body>
<div class="container">
   <div class="col-6 offset-3">
       <form:form action="/coupons/update" method="post" modelAttribute="coupons">
           <h3 class="text-center text-danger">Update Coupons</h3>
           <form:hidden path="code"/>
           <div class="row mb-3">
               <label>Name</label>
               <form:input path="name" cssClass="form-control"/>
               <form:errors path="name" cssStyle="color: red"/>
           </div>
           <div class="row mb-3">
               <label>Reduce value</label>
               <form:input path="reduceValue" cssClass="form-control"/>
               <form:errors path="reduceValue" cssStyle="color: red"/>
           </div>
           <div class="row mb-3">
               <label>Maximum reduce value</label>
               <form:input path="maximumReduceValue" cssClass="form-control"/>
               <form:errors path="maximumReduceValue" cssStyle="color: red"/>
           </div>
           <div class="row mb-3">
               <label>Start day</label>
               <form:input path="startDay" cssClass="form-control" type="date"/>
               <form:errors path="startDay" cssStyle="color: red"/>
           </div>
           <div class="row mb-3">
               <label>End date</label>
               <form:input path="endDay" cssClass="form-control" type="date"/>
               <form:errors path="endDay" cssStyle="color: red"/>
           </div>
           <div class="row mb-3">
               <label>Customer</label>
               <form:select path="customer" cssClass="form-select">
                   <c:forEach items="${listCustomers}" var="customer">
                       <option value="${customer.id}">${customer.id}</option>
                   </c:forEach>
               </form:select>
               <span>${coupons.customer.name}</span>
           </div>
           <div class="row mb-3">
               <div class="col-6">
                   <button onclick="return confirm('Are you sure?')" class="btn btn-primary">Update</button>
               </div>
           </div>
       </form:form>
   </div>
</div>
</body>
</html>
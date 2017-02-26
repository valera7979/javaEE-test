<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="utf-8">
    <!-- fill modal form by  users data -->
    <script>
        $(document).on("click", ".open-EditUserDialog", function () {
        var userId = $(this).data('id');
        var userName = $(this).data('name');
        var userAge = $(this).data('age');
        var isAdmin = $(this).data('admin');
        //$(".modal-body #bookId").val( myBookId );
        $(".modal-body #name").val( userName );
        $(".modal-body #id").val( userId );
        $(".modal-body #age").val( userAge );
       // $(".modal-body #isAdmin").val( isAdmin );
        $('#addBookDialog').modal('show');
        });

    </script>
</head>
<body>

<a href = "/users" title="Home" style = "text-decoration: none">
<div class="jumbotron text-center" >
    <h1>Users List</h1>
    <p></p>
</div>
</a>

<div class="container">
<!-- Search form-->
    <div class="col-sm-9" >
        <form action="/doSearch" method="post" class="form-inline">
           <input type="text" class="form-control" name="searchString" placeholder="search by name"/>
            <button type="submit" class="btn btn-default">Search</button>
            <input type="reset" class="btn btn-default" value="Cancel"/>
        </form>
    </div>

    <div class="col-sm-2" >
        <!-- Trigger the modal with a button -->
        <button type="button" class="btn btn-success " data-toggle="modal" data-target="#myModal">Add User</button>
    </div>
<!-- Table of users -->
    <c:if test="${!empty getAllUsers}">
        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>AGE</th>
                <th>ROLE</th>
                <th>DATE OF CREATING</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var = "user" items="${getAllUsers}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                            <c:if test="${!user.isAdmin}">User</c:if>
                            <c:if test="${user.isAdmin}">Admin</c:if>
                    </td>
                    <td>${user.createdDate}</td>
                    <td><a class = "open-EditUserDialog btn btn-primary"  data-toggle="modal"
                           data-id = "${user.id}" data-name="${user.name}" data-age = "${user.age}" data-admin = "${user.isAdmin}"
                       href="#editModal">Edit</a></td>

                    <td><a class = "btn btn-danger" href="<c:url value="/remove/${user.id}/${searchString}/${currentPage}"/>">Delete</a></td>

                </tr>
            </c:forEach>
        </table>
    </c:if>

<!-- Pager -->
    <div class="col-sm-10">
        <div style="width: 50%; margin: 0 auto;">
            <c:if test="${numberOfPages>1}">

                <c:if test="${!empty searchString}">
                    <c:set var="path" value="doSearch/"/>
                </c:if>
                <c:if test="${empty searchString}">
                <c:set var="path" value="users"/>
            </c:if>

            <ul class="pagination" style = "margin: 0">

                <li><a <c:if test="${currentPage>1}">
                        href="/${path}${searchString}/${currentPage-1}"
                    </c:if> title="Previous page">◄</a></li>
            <c:forEach var="i" begin="1" end="${numberOfPages}">

                    <li <c:if test="${i == currentPage}">class="active"</c:if>
                        ><a href="<c:url value="/${path}${searchString}/${i}"/>">${i}</a></li>

            </c:forEach>
                <li><a <c:if test="${currentPage<numberOfPages}">
                    href="/${path}${searchString}/${currentPage+1}"
                </c:if> title="Next page">►</a></li>
            </ul>
        </c:if>
        </div>
    </div>


<!-- Add Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">X</button>
                          <h1 class="modal-title">Add new User</h1>

            </div>
            <div class="modal-body">
            <form:form action="/users/add/${numberOfPages}" commandName="user" class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-2" >Name:</label>
                        <div class="col-sm-10">

                            <form:input path="name" type="text" class="form-control" id="name" placeholder="Enter your name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Age:</label>
                        <div class="col-sm-10">

                            <form:input path="age" type="number" class="form-control" id="age" min="0" max="200" value="0" placeholder="Enter you age"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">

                                <form:radiobutton id="isAdmin" path="isAdmin" value="0"/> <b>User</b>
                                <form:radiobutton id="isAdmin" path="isAdmin" value="1"/> <b>Admin</b>

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </div>
            </form:form>
            </div>

        </div>

    </div>
</div>

<!-- Edit Modal -->
<div class="modal fade" id="editModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">X</button>

                    <h1 class="modal-title" id="id">Edit user</h1>

            </div>
            <div class="modal-body">
                <form:form action="/users/add/${searchString}/${currentPage}" commandName="user" class="form-horizontal">

                    <div class="form-group">
                        <label class="control-label col-sm-2" >id:</label>
                        <div class="col-sm-10">

                            <form:input path="id" readonly="true"  type="text" class="form-control" id="id" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Name:</label>
                        <div class="col-sm-10">

                            <form:input path="name" type="text" class="form-control" id="name" placeholder="Enter your name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Age:</label>
                        <div class="col-sm-10">

                            <form:input path="age" type="number" class="form-control" id="age" min="1" max="200" value="0" placeholder="Enter you age"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">

                                <form:radiobutton id="isAdmin" path="isAdmin" value="0"/> <b>User</b>
                                <form:radiobutton id="isAdmin" path="isAdmin" value="1"/> <b>Admin</b>

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </div>
                </form:form>
            </div>

        </div>

    </div>
</div>

</div>

</body>
</html>

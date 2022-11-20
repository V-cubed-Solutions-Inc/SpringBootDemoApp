<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Notes</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td>
                                <fmt:formatDate value="${todo.startDate}" pattern="MM/dd/yyyy" />
                            </td>
                            <td>
                                <fmt:formatDate value="${todo.endDate}" pattern="MM/dd/yyyy" />
                            </td>
                            <td>${todo.status}</td>
                            <td>${todo.notes}</td>
                            <td><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a></td>
                            <td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <a class="button" href="/add-todo">Add a Todo</a>
            </div>
        </div>
        <%@ include file="common/footer.jspf" %>
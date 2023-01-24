<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>

        <div class="container">
            <table id="todo-table" class="table table-striped">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Notes</th>
                        <th>Hours</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td>
                                <fmt:formatDate value="${todo.date}" pattern="EEE, MMM d yyyy" />
                            </td>
                            <td>${todo.status}</td>
                            <td>${todo.notes}</td>
                            <td>${todo.hoursRequired}</td>
                            <td><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a></td>
                            <td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <a id="add-todo-btn" class="button" href="/add-todo">Add a Todo</a>
            </div>
        </div>
        <%@ include file="common/footer.jspf" %>
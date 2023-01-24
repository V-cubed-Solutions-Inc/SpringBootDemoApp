<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
        <div class="container">
            <form:form method="post" commandName="todo">
                <form:hidden path="id" />
                <fieldset class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" type="text" class="form-control" required="required" />
                    <form:errors path="description" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="notes">Notes</form:label>
                    <form:input path="notes" type="text" class="form-control" required="required" />
                    <form:errors path="notes" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="status">Status</form:label>
                    <form:input path="status" type="text" class="form-control" required="required" />
                    <form:errors path="status" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="hoursRequired">Hours Required</form:label>
                    <form:input path="hoursRequired" type="text" class="form-control" required="required" />
                    <form:errors path="hoursRequired" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="date">Date</form:label>
                    <form:input path="date" type="text" class="form-control" required="required" />
                    <form:errors path="date" cssClass="text-warning" />
                </fieldset>

                <button id="add-todo-btn-submit" type="submit" class="btn btn-success">Add</button>
            </form:form>
        </div>
        <%@ include file="common/footer.jspf" %>
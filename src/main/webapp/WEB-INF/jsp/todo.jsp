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
                    <form:label path="startDate">Start Date</form:label>
                    <form:input path="startDate" type="text" class="form-control" required="required" />
                    <form:errors path="startDate" cssClass="text-warning" />
                </fieldset>

                <fieldset class="form-group">
                    <form:label path="endDate">End Date</form:label>
                    <form:input path="endDate" type="text" class="form-control" required="required" />
                    <form:errors path="endDate" cssClass="text-warning" />
                </fieldset>

                <button type="submit" class="btn btn-success">Add</button>
            </form:form>
        </div>
        <%@ include file="common/footer.jspf" %>
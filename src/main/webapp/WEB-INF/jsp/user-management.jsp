<%@ include file="common/header.jspf"%>
    <%@ include file="common/navigation.jspf"%>
        <div class="container" style="margin-bottom: 10px ;">
            Welcome DemoAdmin!! <a href="/list-todos">Click here</a> to manage your todo's.
        </div>
        <div class="container">
            Admin Options:

            <form action="enable-overtime" method="get">
                <button id="overtime-on" onchange="submit()">Allow Overtime for ALL Users</button>
            </form>
            <form action="disable-overtime" method="get">
                <button id="overtime-off" onchange="submit()">DO NOT allow Overtime for ANY Users</button>
            </form>
        </div>
        <%@ include file="common/footer.jspf"%>
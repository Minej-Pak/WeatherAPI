<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html; charset=UTF-16BE" pageEncoding="UTF-16BE" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Weather REST Client</title>
</head>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(() => {
        $('#frmInput').on('submit', async (e) => {
            e.preventDefault();
            let loc = $('#txtLoc').val();
            let dtm = $('#txtDate').val();
            let url = "api/weather/" + encodeURI(dtm) + "?location=" + encodeURI(loc) + "&width=800&height=600";
            $('#imgGraph').attr("src", "https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif");
            let data = await $.get(url);
            $('#imgGraph').attr("src", "data:image/png;base64," + data.image);
            return false;
        });
    });
</script>
<body>
<h1><%= "Weather REST Client" %>
</h1>
<br>
<form id="frmInput">
    <label for="txtLoc">Location:</label><br>
    <input type="text" id="txtLoc" name="txtLoc" value="Maribor"><br>
    <label for="txtDate">Date:</label><br>
    <input type="text" id="txtDate" name="txtDate"
           value='<%= new SimpleDateFormat("dd.MM.yyyy").format(Date.from(Instant.now().minus(Duration.ofDays(3))))%>'>
    <input type="submit" id="butSubmit" value="Temperature Graph">
</form>
<div id="graph" style="">
    <img id="imgGraph" src="">
</div>
</body>
</html>
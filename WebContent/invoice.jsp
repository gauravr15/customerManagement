<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "sm";
String userid = "root";
String password = "root";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<body>

<p>Invoice</p>
<table style="width:100%">
<tr>
<td>Service</td>
<td>Amount</td>
</tr>
<%
try{
String number=(String)session.getAttribute("customer_phone"); 
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="SELECT * FROM customers where mobile_no = '"+number+"' ORDER BY last_visit_date DESC LIMIT 1";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
	String item = resultSet.getString("service");
	String[] serviceList = item.split(",");
	int count =0;
	while(count < serviceList.length){
		String productBill = serviceList[count].trim();
		String[] billBreakdown = productBill.split(" ");
		count++;
		%>
		<tr>
<td><%=billBreakdown[0] %></td>
<td><%=billBreakdown[1] %></td>
</tr>
<%	}
	}
%>
<%
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
<style>
            body {
                text-align:center;
            }
            @media print {
               .noprint {
                  visibility: hidden;
               }
            }
        </style>
<button class = "noprint" onclick="window.print()">Print Bill</button>
<button class = "noprint" onclick="homePage()">Home</button>

<script>
function homePage() {
  location.replace("http://localhost:8080/Subscription/home.html")
}
</script>
</body>
</html>
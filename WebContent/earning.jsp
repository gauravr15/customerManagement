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

<p>Profit Calculator</p>
<table style="width:100%">
<tr>
<td><b>Service</b></td>
<td><b>Amount</b></td>
</tr>
<%
try{
	int amount = 0;
	int point = 0;
	int amountPayable = 0;
String fromDate=(String)session.getAttribute("fromDate");
String toDate = (String)session.getAttribute("toDate");
System.out.println(fromDate);
System.out.println(toDate);
String sql = "SELECT * FROM customers WHERE last_visit_date >= '"+fromDate+"' AND last_visit_date <= '"+toDate+"';";
System.out.println(sql);
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
	point = Integer.parseInt(resultSet.getString("points"));
	String item = resultSet.getString("service");
	String[] serviceList = item.split(",");
	int count =0;
	while(count < serviceList.length){
		String productBill = serviceList[count].trim();
		String[] billBreakdown = productBill.split(" ");
		amount = amount + Integer.parseInt(billBreakdown[1]);
		count++;
		%>
<%	}
}
if(point < 0){
amountPayable = amount+point;
}
else{
	amountPayable = amount;
	point = 0;
}
%>
<tr>
<td><b>Earning</b></td>
<td><%=amountPayable%></td>
</tr>
<%
sql = "SELECT * FROM expense WHERE date >= '"+fromDate+"' AND date <= '"+toDate+"';";
System.out.println(sql);
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
resultSet = statement.executeQuery(sql);
int expense = 0;
while(resultSet.next()){
	expense = Integer.parseInt(resultSet.getString("total_amount"));
}
%>
<tr>
<td><b>Expenses</b></td>
<td><%=expense%></td>
</tr>
<tr>
<td><b>Profit</b></td>
<td><%=amountPayable-expense%></td>
</tr>
<%
connection.close();
}
catch (Exception e) {
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

<script>
function homePage() {
  location.replace("http://localhost:8080/Subscription/home.html")
}
</script>
</body>
</html>
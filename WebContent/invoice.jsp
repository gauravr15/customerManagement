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
<td><b>Service</b></td>
<td><b>Amount</b></td>
</tr>
<%
try{
	int amount = 0;
	int point = 0;
	int amountPayable = 0;
String number=(String)session.getAttribute("customer_phone");
String date = (String)session.getAttribute("date");
String sql = "SELECT * FROM customers where mobile_no ='0';";
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
if(date == null){
	sql ="SELECT * FROM customers where mobile_no = '"+number+"' ORDER BY last_visit_date DESC LIMIT 1;";
	
}
else{
	sql = "SELECT * FROM customers where mobile_no = '"+number+"' AND last_visit_date like '%"+date+"%';";
	
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
		<tr>
<td><%=billBreakdown[0] %></td>
<td><%=billBreakdown[1] %></td>
</tr>
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
<td><b>Discount</b></td>
<td><%=point%></td>
</tr>
<tr>
<td><b>Total</b></td>
<td><%=amountPayable%></td>
</tr>
<%
connection.close();
}
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
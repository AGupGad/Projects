<jsp:useBean id="person"  class="person.Person" scope="session"/>
<jsp:setProperty name="person" property="*"/> 
<HTML>
<BODY>
First Name: <%= person.getFirstName() %>
Last Name: <%= person.getLastName() %><BR>
</BODY>
</HTML>
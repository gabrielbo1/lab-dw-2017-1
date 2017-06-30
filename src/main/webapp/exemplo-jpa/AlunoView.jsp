<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="lab.academico.Aluno"%>
<html>

  <head>
    <title>Aluno</title>
  </head>

  <body>
  	<h1><a href="professor">Professor</a></h1>&nbsp;
    <h1><a href="aluno">Aluno</a></h1>
  
  <form>
      <table>
        <tr>
          <td>Matrícula</td>
          <td><input name="matricula"></td>
        </tr>
        <tr>
          <td>Nome:</td>
          <td><input name="nome"></td>
        </tr>
        <tr>
        	<td>Curso:</td>
        	<td><input name="curso"></td>
        </tr>
      </table>
      <button name="operacao" value="incluir">Incluir</button>
      <button name="operacao" value="excluir">Excluir</button>
      <button name="operacao" value="alterar">Alterar</button>
    </form>
    <b>${msg}</b>
    <hr>
    <table border="1">
      <tr>
        <th>Matrícula</th>
        <th>Nome</th>
        <th>Curso</th>
        <th>Ações</th>
      </tr>
      <%
      ArrayList<Aluno> alunos =
        (ArrayList<Aluno>) request.getAttribute("alunos");
      for (Aluno aluno : alunos) {
      %>
      <tr>
        <td><%=aluno.getMatricula()%></td>
        <td><%=aluno.getNome()%></td>
        <td><%=aluno.getCurso()%></td>
        <td><a href="professor?operacao=excluir&matricula=<%=aluno.getMatricula()%>">Excluir</a></td>
      </tr>
      <%}%>
    </table>
    
  
  </body>
	
	
 </html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:out value="${produto != null ? 'Editar Produto' : 'Adicionar Produto'}"/></title>
</head>
<body>
    <h1><c:out value="${produto != null ? 'Editar Produto' : 'Adicionar Produto'}"/></h1>
    <form action="${produto != null ? 'update' : 'insert'}" method="post">
        <input type="hidden" name="id" value="${produto != null ? produto.id : ''}">
        Nome: <input type="text" name="nome" value="${produto != null ? produto.nome : ''}" required><br/>
        Unidade Compra: <input type="number" name="unidadeCompra" value="${produto != null ? produto.unidadeCompra : ''}" required><br/>
        Descrição: <input type="text" name="descricao" value="${produto != null ? produto.descricao : ''}" required><br/>
        Quantidade Previsto/Mês: <input type="number" step="0.01" name="qtdPrevistoMes" value="${produto != null ? produto.qtdPrevistoMes : ''}" required><br/>
        Preço Máximo Comprado: <input type="number" step="0.01" name="precoMaxComprado" value="${produto != null ? produto.precoMaxComprado : ''}" required><br/>
        <input type="submit" value="Salvar">
    </form>
    <br/>
    <a href="list">Retornar a Lista de Produtos</a>
    <a href="${pageContext.request.contextPath}/index.jsp">Voltar ao Menu</a>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listar Produtos</title>
</head>
<body>
    <h1>Lista de Produtos</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Unidade Compra</th>
            <th>Descrição</th>
            <th>Quantidade Previsto/Mês</th>
            <th>Preço Máximo Comprado</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="produto" items="${listProdutos}">
            <tr>
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.unidadeCompra}</td>
                <td>${produto.descricao}</td>
                <td>${produto.qtdPrevistoMes}</td>
                <td>${produto.precoMaxComprado}</td>
                <td>
                    <a href="edit?id=${produto.id}">Editar</a> |
                    <a href="delete?id=${produto.id}">Remover</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="new">Adicionar Produto</a>
    <br/>
    <a href="${pageContext.request.contextPath}/index.jsp">Voltar ao Menu</a>
</body>
</html>

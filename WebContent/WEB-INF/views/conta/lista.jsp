<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="resources/js/jquery.js" />
<script type="text/javascript">
	function	deuCerto(dadosResposta) {
		alert("Conta Paga com Sucesso!")
	}

	function pagaAgora(id) {
		$.get("pagarConta?id="+ id, deuCerto);

	}
</script>

</head>
<body>


	<table>
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Paga?</th>
			<th>Data de Pagamento</th>
		</tr>

		<c:forEach items="${contas}" var="conta">
			<tr>
				<td>${conta.id}</td>
				<td>${conta.descricao}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td><c:if test="${conta.paga eq false }">
				Não Paga!
				</c:if> <c:if test="${conta.paga eq true }">
				Paga!
				</c:if></td>
				<td><fmt:formatDate value="${conta.dataPagamento.time}"
						pattern="dd/MM/yyyy" /></td>
				<td><a href="removerConta?id=${conta.id}">Deletar</a></td>|

				<c:if test="${conta.paga eq false}">
					<td><a href="#" onclick="pagaAgora(${conta.id});">Paga</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
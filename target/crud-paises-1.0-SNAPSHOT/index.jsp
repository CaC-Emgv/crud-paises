<jsp:include page="WEB-INF/pages/comunes/inicioHTML.jsp" />
<jsp:include page="WEB-INF/pages/comunes/inicioHead.jsp" />
<title>Home</title>
<jsp:include page="WEB-INF/pages/comunes/finHead.jsp" />
<jsp:include page="WEB-INF/pages/comunes/nav.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5 col-8">
        <h1 class="display-5 fw-bold">WebApp CRUD Países</h1>
        <p class="fs-4"><p>Proyecto para Codo a Codo, primer cuatrimestre 2021.</p>
        <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/app">Ir a la app</a>
    </div>
</div>

<jsp:include page="WEB-INF/pages/comunes/footer.jsp" />
<jsp:include page="WEB-INF/pages/comunes/finHTML.jsp" />
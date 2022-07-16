<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../comunes/inicioHTML.jsp"/>

<jsp:include page="../comunes/inicioHead.jsp"/>
<title>Editando a ${paisAEditar.nombre}</title>
<jsp:include page="../comunes/finHead.jsp"/>

<jsp:include page="../comunes/nav.jsp"/>

<section class="py-3">
    <div class="container">
        <div class="row">
            <h1 class="h3">Editando país</h1>
            <p class="lead">Estás a punto de editar a ${paisAEditar.nombre}. Asegurate de hacer click en "Confirmar cambios".</p>
        </div>
    </div>
    <div class="container px-4 mt-3">
        <div class="row align-items-center">
            <div class="col-md-5 col-lg-4" >
                <div class="card border p-4 rounded-3 bg-light">
                    <img class="card-img-top" src="${paisAEditar.bandera}" alt="Bandera de ${paisAEditar.nombre}" />
                    <div class="card-body pb-0">
                        <div class="text-center">
                            <h5 class="fw-bolder">${paisAEditar.nombre}</h5>
                            <p class="mb-1">Fecha de Independencia ${paisAEditar.fechaIndependencia} Independizado hace ${paisAEditar.edad} años</p>
                            <p>${paisAEditar.poblacion} habitantes</p>
                            <p>${paisAEditar.superficie} KM cuadrados</p>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-7 col-lg-8" >
                <form id="formAgregarPais" action="${pageContext.request.contextPath}/app?accion=update&id=${paisAEditar.id}"
                      method="post" class="was-validated border p-4 rounded-3 bg-light">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="${paisAEditar.nombre}" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="poblacion" class="form-label">Poblacion</label>
                            <input type="text" class="form-control" id="poblacion" name="poblacion" value="${paisAEditar.poblacion}" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="superficie" class="form-label">Superficie</label>
                            <input type="text" class="form-control" id="superficie" name="superficie" value="${paisAEditar.superficie}" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="fechaInd" class="form-label">Fecha de independencia</label>
                            <input type="date" class="form-control" id="fechaInd" name="fechaInd" value="${paisAEditar.fechaIndependencia}" required>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="foto" class="form-label">
                                <c:choose>
                                    <c:when test="${yaTieneBandera}">Modificar</c:when>
                                    <c:otherwise>Agregar</c:otherwise>
                                </c:choose>
                                foto
                            </label>
                            <input type="file" class="form-control" id="inputFoto" name="foto">
                            <input type="hidden" id="fotoBase64" name="fotoBase64" value="${paisAEditar.bandera}}">
                        </div>
                    </div>
                    <div class="row justify-content-end mt-2">
                        <div class="col-12">
                            <div class="float-end">
                                <a href="${pageContext.request.contextPath}/app" class="btn btn-secondary">Volver atrás</a>
                                <button type="submit" class="btn btn-warning">Confirmar cambios</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script src="scripts/fotobase64.js"></script>
<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>



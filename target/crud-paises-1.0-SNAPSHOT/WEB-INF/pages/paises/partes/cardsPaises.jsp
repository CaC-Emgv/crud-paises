<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listaPaises}" var="pais">  
    <div class="col">
        <div class="card h-100">
            <!-- Foto -->
            <img class="card-img-top" src="${pais.bandera}" alt="Foto de la bandera de ${pais.nombre}" />
            <!-- Detalles -->
            <div class="card-body p-4">
                <div class="text-center">
                    <h5 class="fw-bolder">${pais.nombre}</h5>
                    <p class="mb-1">Independencia: ${pais.fechaIndependencia} Independizado hace ${pais.edad} a�os ${pais.poblacion} habitantes</p>
                    <p>${pais.superficie} KM�</p> 
                </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer border-top-0 bg-transparent">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <a href="${pageContext.request.contextPath}/app?accion=edit&id=${pais.id}" class="btn btn-warning btn-block w-100">Editar</a>
                    </div>
                    <div class="col-6">
                        <a href="${pageContext.request.contextPath}/app?accion=remove&id=${pais.id}" class="btn btn-danger btn-block w-100">Borrar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>


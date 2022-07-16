<div class="modal fade" id="modalAgregarPais" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar país</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="formAgregarPais" action="${pageContext.request.contextPath}/app?accion=add"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="Argentina" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="poblacion" class="form-label">Poblacion</label>
                            <input type="text" class="form-control" id="poblacion" name="poblacion" value="0" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="superficie" class="form-label">Superficie</label>
                            <input type="text" class="form-control" id="superficie" name="superficie" value="0" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="fechaInd" class="form-label">Fecha de independencia</label>
                            <input type="date" class="form-control" id="fechaInd" name="fechaInd" value="1990-05-12" required>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="bandera" class="form-label">Cargar bandera</label>
                            <input type="file" class="form-control" id="inputFoto" name="bandera">
                            <input type="hidden" id="fotoBandera" name="fotoBandera">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-success">Agregar</button>
                </div>
            </form>
        </div>
    </div>
</div>
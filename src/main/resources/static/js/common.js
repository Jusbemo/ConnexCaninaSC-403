function initializeDataTable(tableId) {
    $(document).ready(function() {
        $(`#${tableId}`).DataTable({
            "paging": true,
            "lengthMenu": [10, 15, 30, 50],
            "searching": true,
            "ordering": true,
            "language": {
                "lengthMenu": "Mostrar _MENU_ registros por página",
                "zeroRecords": "No se encontraron registros",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay registros disponibles",
                "infoFiltered": "(filtrado de _MAX_ registros en total)",
                "search": "Buscar:",
                "paginate": {
                    "first": "Primero",
                    "last": "Último",
                    "next": "Siguiente",
                    "previous": "Anterior"
                }
            }
        });
    });
}

// Retorna mensajes específicos según el tipo de entidad.
function getAlertMessages(entityType) {
    switch (entityType) {
        case 'usuario':
            return {
                alertText: 'El usuario, sus citas y mascotas asociadas serán permanentemente eliminadas.',
                successMessage: 'El usuario y sus datos asociados fueron eliminados correctamente.'
            };
        case 'cita':
            return {
                alertText: 'La cita será eliminada permanentemente.',
                successMessage: 'La cita fue eliminada correctamente.'
            };
        case 'mascota':
            return {
                alertText: 'La mascota será eliminada permanentemente.',
                successMessage: 'La mascota fue eliminada correctamente.'
            };
        case 'consulta':
            return {
                alertText: 'La consulta será eliminada permanentemente.',
                successMessage: 'La consulta fue eliminada correctamente.'
            };
        default:
            return {
                alertText: `El ${entityType} será eliminado permanentemente.`,
                successMessage: `El ${entityType} fue eliminado correctamente.`
            };
    }
}

// Muestra la confirmación de eliminación.
function showDeleteConfirmation(alertText) {
    return Swal.fire({
        title: '¿Estás seguro?',
        text: alertText,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    });
}

// Realiza la petición para eliminar la entidad.
function performDeleteRequest(deleteUrl, entityId) {
    return fetch(`${deleteUrl}/${entityId}`, { method: 'GET' });
}

// Maneja la respuesta de la petición fetch.
function handleDeleteResponse(response, successMessage, entityType) {
    if (response.ok) {
        Swal.fire('¡Eliminado!', successMessage, 'success')
            .then(() => window.location.reload());
    } else {
        Swal.fire('Error', `Hubo un problema al eliminar el ${entityType}.`, 'error');
    }
}

// Inicializa la lógica del botón eliminar.
function initializeDeleteButton(tableId, entityType, deleteUrl) {
    $(`#${tableId}`).on('click', '.btn-delete', function () {
        const entityId = $(this).attr('data-id');

        const { alertText, successMessage } = getAlertMessages(entityType);

        showDeleteConfirmation(alertText).then(result => {
            if (result.isConfirmed) {
                performDeleteRequest(deleteUrl, entityId)
                    .then(response => handleDeleteResponse(response, successMessage, entityType))
                    .catch(() => {
                        Swal.fire('Error', 'Hubo un error en la solicitud.', 'error');
                    });
            }
        });
    });
}

function initializeSelectColor(selectClass, statusClasses) {
    $(selectClass).each(function () {
        actualizarColor($(this), statusClasses);
    });
}


// Inicializa la lógica para cambiar el estado de una entidad.
function handleStateChange(selectClass, url, dataIdAttribute, idKey, statusClasses) {
    $(document).on("change", `.${selectClass}`, function () {
        const csrfToken = $("meta[name='_csrf']").attr("content");
        const csrfHeader = $("meta[name='_csrf_header']").attr("content");

        const $select = $(this);
        const id = $select.data(dataIdAttribute);
        const nuevoEstado = $select.val();

        const bodyData = {};
        bodyData[idKey] = id;
        bodyData['estado'] = nuevoEstado;

        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify(bodyData)
        })
            .then(response => {
                if (!response.ok) throw new Error("Error en la actualización del estado");
                return response.json();
            })
            .then(data => {
                Swal.fire({ title: "Estado actualizado correctamente", icon: "success" });
                actualizarColor($select, statusClasses);
            })
            .catch(error => console.error("Error:", error));
    });
}

// Función para actualizar colores según el estado
function actualizarColor($select, statusClasses) {
    $select.removeClass(statusClasses.join(" "));
    $select.addClass($select.val());
}
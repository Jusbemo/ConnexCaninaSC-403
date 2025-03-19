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
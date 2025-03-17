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

function initializeDeleteButton(tableId, entityType, deleteUrl) {
    $(`#${tableId}`).on('click', '.btn-delete', function() {
        const entityId = $(this).attr('data-id');

        let alertText;
        let successMessage;

        switch (entityType) {
            case 'usuario':
                alertText = 'El usuario, sus citas y mascotas asociadas serán permanentemente eliminadas.';
                successMessage = 'El usuario y sus datos asociados fueron eliminados correctamente.';
                break;
            case 'cita':
                alertText = 'La cita será eliminada permanentemente.';
                successMessage = 'La cita fue eliminada correctamente.';
                break;
            case 'mascota':
                alertText = 'La mascota será eliminada permanentemente.';
                successMessage = 'La mascota fue eliminada correctamente.';
                break;
            default:
                alertText = `El ${entityType} será eliminado permanentemente.`;
                successMessage = `El ${entityType} fue eliminado correctamente.`;
                break;
        }

        Swal.fire({
            title: '¿Estás seguro?',
            text: alertText,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: 'Sí, eliminar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch(`${deleteUrl}/${entityId}`, {
                    method: 'GET'
                })
                    .then(response => {
                        if (response.ok) {
                            Swal.fire('¡Eliminado!', successMessage, 'success')
                                .then(() => window.location.reload());
                        } else {
                            Swal.fire('Error', `Hubo un problema al eliminar el ${entityType}.`, 'error');
                        }
                    })
                    .catch(() => {
                        Swal.fire('Error', 'Hubo un error en la solicitud.', 'error');
                    });
            }
        });
    });
}

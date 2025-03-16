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
        if (entityType === 'usuario') {
            alertText = 'El usuario, citas y mascotas asociadas a este serán permanentemente eliminadas.';
        } else {
            alertText = `La ${entityType} será eliminada permanentemente.`;
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
                            Swal.fire('¡Eliminado!', `El ${entityType} fue eliminado correctamente.`, 'success')
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
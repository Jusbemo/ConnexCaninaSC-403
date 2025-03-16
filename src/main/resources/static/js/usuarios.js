$(document).ready(function() {
    $('#usuariosTable').DataTable({
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

// Lógica SweetAlert para eliminación del usuario
$('#usuariosTable').on('click', '.btn-delete', function() {
    const idUsuario = $(this).attr('data-id');

    Swal.fire({
        title: '¿Estás seguro?',
        text: 'El usuario, sus citas y mascotas asociadas serán eliminadas permanentemente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/usuarios/eliminar/${idUsuario}`, {
                method: 'GET'
            })
                .then(response => {
                    if (response.ok) {
                        Swal.fire('¡Eliminado!', 'El usuario fue eliminado correctamente.', 'success')
                            .then(() => window.location.reload());
                    } else {
                        Swal.fire('Error', 'Hubo un problema al eliminar el usuario.', 'error');
                    }
                })
                .catch(() => {
                    Swal.fire('Error', 'Hubo un error en la solicitud.', 'error');
                });
        }
    });
});

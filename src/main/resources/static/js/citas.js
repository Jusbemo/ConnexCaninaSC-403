// Inicializa DataTable
initializeDataTable('citasTable');

initializeSelectColor('.estado-select', ['pendiente', 'completada', 'cancelada']);

handleStateChange(
    'estado-select',
    '/citas/actualizarEstado',
    'idcita',
    'idCita',
    ['pendiente', 'completada', 'cancelada']
);

initializeDeleteButton('citasTable', 'cita', '/citas/eliminar');

initializeDataTable('citasSpaceTable');

initializeDeleteButton('citasSpaceTable', 'espacioCita', '/citas/eliminarEspacioCita');


function refreshEspaciosCitasSection() {
    fetch('/citas/fragmento/espacios')
        .then(response => response.text())
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const newSection = doc.querySelector('section');

            // Reemplaza la sección completa
            const currentSection = document.getElementById('table-space-citas-section');
            if (newSection && currentSection) {
                currentSection.replaceWith(newSection);

                // Re-inicializar funcionalidades
                initializeDataTable('citasSpaceTable');
                initializeDeleteButton('citasSpaceTable', 'espacioCita', '/citas/eliminarEspacioCita');
                initializeSelectColor('.estado-select', ['disponible', 'reservado', 'cancelada']);
            } else {
                console.error("No se encontró la sección actual o la nueva para reemplazar.");
            }
        });
}



$(document).ready(function () {
    $('#crearEspacioCitaForm').on('submit', function (e) {
        e.preventDefault();

        const fecha = $('#fecha').val();
        const hora = $('#hora').val();

        if (!fecha || !hora) {
            Swal.fire({
                title: "Campos incompletos",
                text: "Por favor completa la fecha y la hora.",
                icon: "warning"
            });
            return;
        }

        const espacioCitaData = {
            fecha: fecha,
            hora: hora
        };

        const csrfToken = $("meta[name='_csrf']").attr("content");
        const csrfHeader = $("meta[name='_csrf_header']").attr("content");

        fetch('/citas/crearEspacioCita', {
            method: 'POST',
            headers: {
                [csrfHeader]: csrfToken,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(espacioCitaData)
        })
            .then(response => response.json().then(data => ({status: response.status, body: data})))
            .then(({status, body}) => {
                if (status === 200) {
                    Swal.fire({
                        title: "Espacio creado correctamente",
                        icon: "success"
                    });

                    // Cerrar el modal
                    const modal = bootstrap.Modal.getInstance(document.getElementById('crearEspacioCitaModal'));
                    modal.hide();

                    // Resetear el formulario
                    $('#crearEspacioCitaForm')[0].reset();

                    // Recargar tabla Espacios Citas
                    refreshEspaciosCitasSection();
                } else {
                    Swal.fire({
                        title: "Error",
                        text: body.error || "No se pudo crear el espacio.",
                        icon: "error"
                    });
                }
            })
            .catch(error => {
                console.error(error);
                Swal.fire({
                    title: "Error en la solicitud",
                    text: "Ocurrió un error inesperado.",
                    icon: "error"
                });
            });
    });
});

handleStateChange(
    'estado-espacio-select',
    '/citas/actualizarEstadoEspacioCita',
    'idespacio',
    'idEspacio',
    ['disponible', 'reservado', 'cancelada']
);

// Inicializa DataTable
initializeDataTable('citasTable');

// Aplica color inicial a los selects
$(".estado-select").each(function () {
    actualizarColor($(this));
});

// Manejo de cambio de estado con fetch
$(".estado-select").on("change", function () {
    const $select = $(this);
    const idCita = $select.data("idcita"); // Obtener el atributo data-idcita
    const nuevoEstado = $select.val(); // Obtener el valor seleccionado

    fetch("/citas/actualizarEstado", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ idCita: idCita, estado: nuevoEstado })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error en la actualización del estado");
            }
            return response.json();
        })
        .then(data => {
            Swal.fire({
                title: "Estado actualizado correctamente",
                icon: "success"
            });
            actualizarColor($select);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

// Función para actualizar colores
function actualizarColor($select) {
    // Remover clases previas de estado
    $select.removeClass("pendiente completada cancelada");

    // Agregar la clase correspondiente según el estado actual
    $select.addClass($select.val());
}

initializeDeleteButton('citasTable', 'cita', '/citas/eliminar');
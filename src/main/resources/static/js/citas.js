initializeDataTable('citasTable');


$(document).ready(function () {
    $(".estado-select").each(function () {
        actualizarColor($(this)); // Aplica el color inicial
    });

    $(".estado-select").on("change", function () {
        actualizarColor($(this));
    });

    function actualizarColor($select) {
        // Remover clases previas
        $select.removeClass("pendiente completada cancelada reprogramada");

        // Agregar la clase según el valor seleccionado
        $select.addClass($select.val());
    }
});







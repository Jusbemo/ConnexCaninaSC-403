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
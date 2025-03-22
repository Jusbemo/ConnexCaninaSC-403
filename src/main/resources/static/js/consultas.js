initializeDataTable('consultasTable');

initializeSelectColor('.estado-select', ['pendiente', 'respondida']);

handleStateChange(
    'estado-select',
    '/consultas/actualizarEstado',
    'idconsulta',
    'idConsulta',
    ['pendiente', 'respondida']
);

initializeDeleteButton('consultasTable', 'consulta', '/consultas/eliminar');
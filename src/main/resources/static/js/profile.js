$(document).ready(function () {
    $('#formPerfilUsuario').on('submit', function (e) {
        e.preventDefault();

        const $form = $(this);
        const $submitBtn = $form.find('button[type="submit"]');
        const originalText = $submitBtn.html();

        $submitBtn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm me-1"></span> Guardando...');

        const formData = $form.serialize(); 
        const formMap = {};
        $form.serializeArray().forEach(field => {
            formMap[field.name] = field.value;
        });

        // CSRF token desde metatags
        const csrfToken = $("meta[name='_csrf']").attr("content");
        const csrfHeader = $("meta[name='_csrf_header']").attr("content");

        fetch('/profile/actualizar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken
            },
            body: formData
        })
            .then(response => {
                if (!response.ok) throw new Error("Error al guardar");
                return response.json();
            })
            .then(() => {
                Swal.fire({
                    icon: 'success',
                    title: 'Datos actualizados',
                    text: 'Tu información se guardó correctamente',
                    confirmButtonColor: '#3085d6'
                });

            })
            .catch(() => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'No se pudieron guardar los cambios.',
                    confirmButtonColor: '#d33'
                });
            })
            .finally(() => {
                $submitBtn.prop('disabled', false).html(originalText);
            });
    });
});



initializeDataTable('tablaMascotas');

initializeDataTable('tablaCitas');

initializeDeleteButton('tablaMascotas', 'mascota', '/perfil/eliminarMascota');

$(document).ready(function () {
    cargarRazas();

    function cargarRazas() {
        $.ajax({
            url: "https://dog.ceo/api/breeds/list/all",
            method: "GET",
            success: function (response) {
                if (response.status === "success") {
                    const razas = response.message;
                    const $select = $("#raza");
                    $select.empty(); // limpiamos el select
                    $select.append('<option value="">Selecciona una raza</option>');

                    // Recorremos las razas
                    for (const raza in razas) {
                        const subrazas = razas[raza];

                        if (subrazas.length === 0) {
                            // Si no hay subrazas, agregamos directamente
                            $select.append(`<option value="${raza}">${capitalizar(raza)}</option>`);
                        } else {
                            // Si hay subrazas, las agregamos como "subraza raza"
                            subrazas.forEach(sub => {
                                const nombreCompuesto = `${sub} ${raza}`;
                                $select.append(`<option value="${nombreCompuesto}">${capitalizar(nombreCompuesto)}</option>`);
                            });
                        }
                    }
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error al cargar razas",
                        text: "No se pudieron obtener las razas desde la API."
                    });
                }
            },
            error: function () {
                Swal.fire({
                    icon: "error",
                    title: "Error de conexión",
                    text: "No se pudo conectar con la API de razas."
                });
            }
        });
    }

    function capitalizar(texto) {
        return texto
            .split(" ")
            .map(word => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    }
});



$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const registroExitoso = urlParams.get('registro');

    if (registroExitoso === 'exito') {
        Swal.fire({
            icon: 'success',
            title: 'Mascota registrada',
            text: 'La mascota se registró correctamente',
            confirmButtonColor: '#3085d6'
        });

        // Limpiar los parámetros de la URL sin recargar
        const url = new URL(window.location);
        url.searchParams.delete('registro');
        window.history.replaceState({}, document.title, url);
    }
});
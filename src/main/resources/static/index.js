 // Obtener el formulario y la tabla
 const form = document.getElementById("dniForm");
 const resultTable = document.getElementById("resultTable").getElementsByTagName('tbody')[0];
 resultTable.parentElement.style.display = "table";
 form.addEventListener("submit", function(event) {
     event.preventDefault();  // Evitar que el formulario se envíe

     // Obtener el valor del DNI
     const dni = document.getElementById("dni").value;

     // Realizar la solicitud GET
     fetch(`http://localhost:8080/BD2024/usuario/${dni}`)
         .then(response => response.json()) // Convertir la respuesta a JSON
         .then(data => {
             // Limpiar la tabla antes de agregar nuevos datos
             resultTable.innerHTML = "";

             // Comprobar si se encontró el usuario
             if (data && data.nombre) {
                 // Crear una nueva fila de tabla con los datos
                 const newRow = resultTable.insertRow();

                 newRow.innerHTML = `
                     <td>${data.nombre}</td>
                     <td>${data.apellido}</td>
                     <td>${data.email}</td>
                     <td>${data.user_name}</td>
                     <td>${data.dni}</td>
                 `;

                 // Mostrar la tabla
                 resultTable.parentElement.style.display = "table";
             } else {
                 // Si no se encuentra el usuario
                 alert("No se encontraron resultados para este DNI.");
             }
         })
         .catch(error => {
             resultTable.innerHTML = "";
             console.error("Error al obtener los datos:", error);
             alert("No se encontró el recurso solicitado");
         });
 });
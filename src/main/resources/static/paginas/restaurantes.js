 // Obtener el formulario y la tabla
 const form = document.getElementById("dniForm");
 const resultTable = document.getElementById("resultTable").getElementsByTagName('tbody')[0];
 resultTable.parentElement.style.display = "table";
 form.addEventListener("submit", function(event) {
     event.preventDefault();  // Evitar que el formulario se envíe

     // Obtener el valor del DNI
     const dni = document.getElementById("dni").value;
     // Realizar la solicitud GET
     fetch(`http://localhost:8080/BD2024/Restaurante?tipo=${dni}`)
         .then(response => response.json()) // Convertir la respuesta a JSON
         .then((data) => {
             
             // Limpiar la tabla antes de agregar nuevos datos
             resultTable.innerHTML = "";

             if (Array.isArray(data) && data.length >= 0) {
                 data.forEach(usuario => {
                 const newRow = resultTable.insertRow(); 
                    newRow.innerHTML = `
                    <td>${usuario.nombre}</td>
                    <td>${usuario.direccion}</td>
                    <td>${usuario.hora_apertura}</td>
                    <td>${usuario.hora_cierre}</td>
                `;
                 });
                 
             }else if(data){
                const newRow = resultTable.insertRow();
                 newRow.innerHTML = `
                     <td>${data.nombre}</td>
                     <td>${data.direccion}</td>
                     <td>${data.hora_apertura}</td>
                     <td>${data.hora_cierre}</td>
                 `;
             } else {
                 // Si no se encuentra el usuario
                 alert("No se encontraron resultados para este DNI.");
             }
             resultTable.parentElement.style.display = "table";
         })
         .catch(error => {
             resultTable.innerHTML = "";
             console.error("Error al obtener los datos:", error);
             alert("No se encontró el recurso solicitado");
         });
 });
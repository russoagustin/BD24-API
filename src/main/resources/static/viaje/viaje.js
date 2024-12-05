const form = document.getElementById("eventoForm");
 const resultTable = document.getElementById("resultTable").getElementsByTagName('tbody')[0];
 resultTable.parentElement.style.display = "table";
 form.addEventListener("submit", function(event) {
     event.preventDefault();  
     // Obtener el valor del DNI
     const tipoEvento = document.getElementById("evento").value;

     // Realizar la solicitud GET
     fetch(`http://localhost:8080/BD2024/Viaje?tipoEvento=${tipoEvento}`)
         .then(response => response.json()) // Convertir la respuesta a JSON
         .then((data) => {
             
             // Limpiar la tabla antes de agregar nuevos datos
             resultTable.innerHTML = "";

             if (Array.isArray(data) && data.length >= 0) {
                 data.forEach(usuario => {
                 const newRow = resultTable.insertRow(); 
                    newRow.innerHTML = `
                    <td>${usuario.id}</td>
                    <td>${usuario.transporte}</td>
                    <td>${usuario.inicio}</td>
                    <td>${usuario.fin}</td>
                    <td>${usuario.destino}</td>
                    <td>${usuario.nombre_evento}</td>
                `;
                 });
                 
             }else if(data){
                const newRow = resultTable.insertRow();
                 newRow.innerHTML = `
                    <td>${usuario.id}</td>
                    <td>${usuario.transporte}</td>
                    <td>${usuario.inicio}</td>
                    <td>${usuario.fin}</td>
                    <td>${usuario.id_destino}</td>}
                    <td>${usuario.nombre_evento}</td>
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
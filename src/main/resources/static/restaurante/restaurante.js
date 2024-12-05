document.addEventListener("DOMContentLoaded", () => {
    const apiURL = "http://localhost:8080/BD2024/Restaurante"; // Sustituye con la URL real de tu API
    const cardContainer = document.querySelector(".card-container");
    const form = document.getElementById("comidaForm");

    // Función para crear una tarjeta
    function createCard(restaurant) {
        const card = document.createElement("div");
        card.className = "card";

        card.innerHTML = `
            <img src="${restaurant.url}" alt="${restaurant.descripción}">
            <div class="card-content">
                <h3>${restaurant.nombre}</h3>
                <p>${restaurant.direccion}</p>
                <div class="hours">Horario: ${restaurant.hora_apertura} - ${restaurant.hora_cierre}</div>
            </div>
        `;
        return card;
    }

    // Función para cargar los datos de la API
    async function loadRestaurants(url) {
        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            const restaurants = await response.json();

            cardContainer.innerHTML = "";

            restaurants.forEach((restaurant) => {
                const card = createCard(restaurant);
                cardContainer.appendChild(card);
            });
        } catch (error) {
            console.error("Error al cargar los restaurantes:", error);
            cardContainer.innerHTML = `<p>Error al cargar los datos.</p>`;
        }
    }

    loadRestaurants(apiURL);

    form.addEventListener("submit", (event) => {
        event.preventDefault(); // Evitar el envío por defecto del formulario

        const tipoComida = document.getElementById("comida").value.trim();

        const urlConParametro = tipoComida
            ? `${apiURL}?tipo=${encodeURIComponent(tipoComida)}`
            : apiURL;

        // Llamar a la función para cargar los restaurantes con el nuevo parámetro
        loadRestaurants(urlConParametro);
    });
});

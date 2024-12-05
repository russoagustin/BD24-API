document.addEventListener("DOMContentLoaded", () => {
    const apiURL = "http://localhost:8080/BD2024/actividad"; // Sustituye con la URL real de tu API
    const cardContainer = document.querySelector(".card-container");
    const form = document.getElementById("lugarForm");

    // Función para crear una tarjeta
    function createCard(restaurant) {
        const card = document.createElement("div");
        card.className = "card";

        card.innerHTML = `
            <img src="${restaurant.url}" alt="${restaurant.descripcion}">
            <div class="card-content">
                <h3>${restaurant.actividad}</h3>
                <p>${restaurant.descripcion}</p>
                <p>Lugar: ${restaurant.lugar}</p>
                <div class="hours">Horario: ${restaurant.hora}</div>
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

            // Limpiar el contenedor antes de agregar nuevas tarjetas
            cardContainer.innerHTML = "";

            // Generar las tarjetas
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
        event.preventDefault(); 

        const lugar = document.getElementById("lugar").value.trim();

        const urlConParametro = lugar
            ? `${apiURL}?lugar=${encodeURIComponent(lugar)}`
            : apiURL;

        loadRestaurants(urlConParametro);
    });
});

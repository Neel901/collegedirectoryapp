async function fetchAndDisplayData() {
    try {
        const response = await fetch('http://localhost:8080/');
        const data = await response.json();
        // console.log(data);
        const tableBody = document.querySelector('#data-table tbody');


        tableBody.innerHTML = '';

        data.forEach(item => {
            const row = document.createElement('tr');

            
            const idCell = document.createElement('td');
            idCell.textContent = item.id;

            const nameCell = document.createElement('td');
            nameCell.textContent = item.username;

            const roleCell = document.createElement('td');
            roleCell.textContent = item.role;

            row.appendChild(idCell);
            row.appendChild(nameCell);
            row.appendChild(roleCell);

            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

fetchAndDisplayData();

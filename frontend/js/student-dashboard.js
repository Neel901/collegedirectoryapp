document.addEventListener("DOMContentLoaded", async function() {
    const studentId = localStorage.getItem("userId");
    // Fetch student profile details
    const profileResponse = await fetch(`http://localhost:8080/${studentId}`);
    const profile = await profileResponse.json();

    document.getElementById("studentName").textContent = profile.username
    document.getElementById("studentPhoto").src = "https://cdn.pixabay.com/photo/2014/04/03/10/32/user-310807_1280.png"
    document.getElementById("email").textContent = profile.email
    document.getElementById("phone").textContent = profile.phone


    document.getElementById('searchForm').addEventListener('submit', function(event) {
        event.preventDefault();
    
        const searchType = document.getElementById('searchType').value;
        const searchQuery = document.getElementById('searchQuery').value;
        const resultsDiv = document.getElementById('searchResults');
            resultsDiv.innerHTML = '';
    
        if (!searchQuery) {
            resultsDiv.innerHTML = '<p>Please enter a search term.</p>';
            return;
        }
            let apiUrl = '';
    
        switch (searchType) {
            case 'name':
                apiUrl = `http://localhost:8080/student/search?name=${encodeURIComponent(searchQuery)}`;
                break;
            case 'dept':
                apiUrl = `http://localhost:8080/student/search?department=${encodeURIComponent(searchQuery)}`;
                break;
            case 'year':
                apiUrl = `http://localhost:8080/student/search?year=${encodeURIComponent(searchQuery)}`;
                break;
            default:
                resultsDiv.innerHTML = '<p>Invalid search type selected.</p>';
                return;
        }
    
        // Make the API call
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                if (data.length === 0) {
                    resultsDiv.innerHTML = '<p>No results found.</p>';
                } else {
                    resultsDiv.innerHTML = '<ul>' + data.map(item => `<li>${JSON.stringify(item)}</li>`).join('') + '</ul>';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                resultsDiv.innerHTML = '<p>An error occurred while fetching results.</p>';
            });
    });
    
});

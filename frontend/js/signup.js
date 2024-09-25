function signup() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;
    const errorMessage = document.getElementById("error-message");
    const successMessage = document.getElementById("success-message");
    errorMessage.textContent = '';
    successMessage.textContent = '';
    if (!name || !email || !phone || !username || !password || !role) {
        errorMessage.textContent = "All fields are required.";
        return;
    }

    const signupRequest = {
        name: name,
        email: email,
        phone: phone,
        username: username,
        password: password,
        role: role
    };
    fetch('http://localhost:8080/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(signupRequest)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Signup failed');
        }
        return response.json();
    })
    .then(data => {
        successMessage.textContent = "Signup successful! Please log in.";
        document.getElementById("name").value = '';
        document.getElementById("email").value = '';
        document.getElementById("username").value = '';
        document.getElementById("password").value = '';
        document.getElementById("role").value = 'STUDENT';
    })
    .catch(error => {
        errorMessage.textContent = error.message || 'Signup failed. Please try again.';
    });
}
function redirectToIndex() {
    window.location.href = "index.html";
}

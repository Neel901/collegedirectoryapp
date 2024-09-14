document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;
    const successMessage = document.getElementById("success-message");
    const errorMessage = document.getElementById("error-message");

    const loginData = { username, password, role };

    try {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json();
        if (response.ok) {
            console.log("Login successful ")
            console.log(data.userId)
            localStorage.setItem('userId', data.userId);
            successMessage.textContent = "Login Successful.";
            // Redirect based on role
            if (role === "STUDENT") {
                console.log("Login successful Student")
                // window.location.replace("/student-dashboard.html")
                // window.location.assign("student-dashboard.html")
                window.location.href = "student-dashboard.html";
            } else if (role === "FACULTY_MEMBER") {
                console.log("Login successful")

                window.location.href = "faculty-dashboard.html";
            } else {
                console.log("Login successful")

                window.location.href = "admin-dashboard.html";
            }
        } else {
            console.log("Login successful")
            errorMessage.textContent = error.message || 'Login failed. Please try again.';
                }
    } catch (error) {
        console.error("Error during login:", error);
        errorMessage.textContent = error.message || 'Login failed. Please try again.';
    }
});

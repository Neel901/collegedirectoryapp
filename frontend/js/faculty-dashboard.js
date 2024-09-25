document.addEventListener("DOMContentLoaded", async function () {
    const facultyId = localStorage.getItem("userId");

    const profileResponse = await fetch(`http://localhost:8080/faculty/${facultyId}`);
    const profile = await profileResponse.json();

    document.getElementById("facultyName").textContent = profile.user.name;
    document.getElementById("facultyPhoto").src = profile.photo;
    document.getElementById("facultyDepartment").textContent = profile.department.name;
    document.getElementById("facultyOfficeHours").textContent = profile.officeHours;

    const studentsRes = await fetch(`http://localhost:8080/faculty/students/${facultyId}`);
    const students = await studentsRes.json();
    const studentList = document.getElementById("classList");

    students.forEach(item => {
        const listItem = document.createElement("li");
        listItem.textContent = `Name: ${item.name} Email: ${item.email} Phone: ${item.phone}`;
        studentList.appendChild(listItem);
    });
});

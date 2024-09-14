document.addEventListener('DOMContentLoaded', () => {
    const addUpdateForm = document.getElementById('recordForm');
    const errorMessage = document.getElementById("error-message");
    const successMessage = document.getElementById("success-message");
    errorMessage.textContent = '';
    successMessage.textContent = '';

    // Form submit event to add or update record
    addUpdateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const recordType = document.getElementById('type').value;
        const userid = document.getElementById('userid').value;
        const departmentId = document.getElementById('departmentId').value;
        const photo = document.getElementById('photo').value;
        const year = document.getElementById('year').value;
        const officeHours = document.getElementById('officeHours').value;
        console.log(recordType)
        const updateStudentRecord = {
            studentId: userid,
            photoUrl: photo,
            year: year,
            departmentId: departmentId
        };

        const updateFacultyRecord = {
            userId: userid,
            photoUrl: photo,
            officeHours: officeHours,
            departmentId: departmentId
        };

        switch (recordType) {

            case 'student':
                
                fetch('http://localhost:8080/student', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updateStudentRecord)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Could not save student profile.');
                    }
                    return response.json();
                })
                .then(data => {
                    successMessage.textContent = "Student profile saved successfully.";
                })
                .catch(error => {
                    errorMessage.textContent = error.message || 'Could not save student profile.';
                });
                break;

            case 'faculty':
                fetch('http://localhost:8080/faculty', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updateFacultyRecord)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Could not save faculty profile.');
                    }
                    return response.json();
                })
                .then(data => {
                    successMessage.textContent = "Faculty profile saved successfully.";
                })
                .catch(error => {
                    errorMessage.textContent = error.message || 'Could not save faculty profile.';
                });
            break;
            default:
                console.error('Invalid record type selected');

        }


        


    
        // Assuming API is a POST request for creating or updating a record
        // const record = { name, email, phone, dept, recordType };

        // fetch(`/students`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify(record),
        // })
        //     .then(response => response.json())
        //     .then(data => {
        //         loadRecords(); // Reload records after the addition
        //         form.reset();
        //     })
            // .catch(error => console.error('Error:', error));
    });

    // Load records from API
    // function loadRecords() {
    //     fetch('/api/records')
    //         .then(response => response.json())
    //         .then(data => {
    //             recordsTable.innerHTML = ''; // Clear existing rows
    //             data.forEach(record => {
    //                 const row = document.createElement('tr');
    //                 row.innerHTML = `
    //                     <td>${record.id}</td>
    //                     <td>${record.name}</td>
    //                     <td>${record.email}</td>
    //                     <td>${record.phone}</td>
    //                     <td>${record.dept}</td>
    //                     <td>
    //                         <button onclick="editRecord(${record.id})">Edit</button>
    //                         <button onclick="deleteRecord(${record.id})">Delete</button>
    //                     </td>
    //                 `;
    //                 recordsTable.appendChild(row);
    //             });
    //         });
    // }

    // Delete record
    // window.deleteRecord = function (id) {
    //     fetch(`/${id}`, {
    //         method: 'DELETE',
    //     })
    //         .then(response => response.json())
    //         .then(data => loadRecords())
    //         .catch(error => console.error('Error:', error));
    // };

    // // Edit record (populate form with existing data)
    // window.editRecord = function (id) {
    //     fetch(`/api/record/${id}`)
    //         .then(response => response.json())
    //         .then(data => {
    //             document.getElementById('name').value = data.name;
    //             document.getElementById('email').value = data.email;
    //             document.getElementById('phone').value = data.phone;
    //             document.getElementById('dept').value = data.dept;
    //         })
    //         .catch(error => console.error('Error:', error));
    // };
});

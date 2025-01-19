/**
 * Example: User ChangePassword
 * Endpoint: http://localhost:8080/api/fitnesstracker/changepassword
 * */
async function user_changepassword(email, newpassword, oldpassword) {
    let url = `http://localhost:8080/api/fitnesstracker/changepassword`;

    let headers = {
        'Content-Type': 'application/json',
    };

    let body = {
        email: email,
        new_password: newpassword,
        old_password: oldpassword,
    };

    let response = await fetch(url, {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(body),
    });
    console.log(await response.json())
};

user_changepassword('usuario1@example.com', 'passwo1s+', 'password');
/**
 * Example: User verify
 * Endpoint: http://localhost:8080/api/fitnesstracker/verify
 * */
async function user_verify(email, password) {
    let url = `http://localhost:8080/api/fitnesstracker/verify`;

    let headers = {
        'Content-Type': 'application/json',
    };

    let body = {
        email,
        password,
    };

    let response = await fetch(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(body),
    });
    console.log(await response.json())
};

user_verify('usuario1@example.com', 'password');
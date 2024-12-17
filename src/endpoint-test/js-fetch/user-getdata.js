/**
 * Example: User GetUserInfo
 * Endpoint: http://localhost:8080/api/fitnesstracker/getuserinfo
 * */
async function user_getdata(email) {
    let url = `http://localhost:8080/api/fitnesstracker/getuserinfo`;

    let headers = {
        'Content-Type': 'application/json',
    };

    let body = {
        email,
    };

    let response = await fetch(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(body),
    });
    console.log(await response.json())
};

user_getdata('usuario1@example.com');
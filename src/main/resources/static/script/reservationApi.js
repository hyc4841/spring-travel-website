
const xhr = new XMLHttpRequest();

xhr.onload = function() {
    if (xhr.readyState === 4) { // 요청이 완료되면
        if (xhr.status === 200) {
            
            const response = JSON.parse(xhr.responseText); // 응답 처리
            console.log('응답:', response);
        } else {
            console.error('오류 발생:', xhr.statusText);
        }
    }
};

xhr.onerror = function() {
    console.log("에러 발생함");
};

document.body.addEventListener("click", function(event) {
    if (event.target.tagName === "BUTTON") {
        console.log("버튼 누름")
        const roomId = event.target.id;
        xhr.open("POST", `http://localhost:8080/reservation/check/${roomId}`, true); // 전송 방식, URL
        xhr.setRequestHeader('Content-Type', 'application/json'); // 컨텐츠 타입 정하기

        const reservation = {
            // 멤버 정보는 진짜 넘기기 직전에
            // 예약 날짜도 예약 확정될 때 그때 정해주기
            // 숙소 판매자도 예약 확정될 때
            accommodation: [[${room.accommodation}]],
            room: [[${room.roomId}]],
            personnel: [[${searchCond.personnel}]],
            checkIn: [[${searchCond.startDate}]],
            checkOut: [[${searchCond.endDate}]]
        };
        xhr.send(JSON.stringify(reservation));
    }
});


// XMLHttpRequest로 리다이렉트

/*
xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/1", true);

xhr.onload = function() {
    if (xhr.status === 200) {
        console.log("Success:", xhr.responseText);
        // 성공 시 리다이렉트
        window.location.href = "/success-page";
    } else if (xhr.status === 401) {
        // 인증 오류 시 로그인 페이지로 리다이렉트
        window.location.href = "/login";
    } else {
        console.log("Error:", xhr.status);
    }
};

xhr.onerror = function() {
    console.log("Request failed");
};

xhr.send();


// XMLHttpRequest get요청
const xhr = new XMLHttpRequest();
xhr.open("GET", "https://jsonplaceholder.typicode.com/posts/1", true);

xhr.onload = function() {
    if (xhr.status === 200) {
        console.log("Response: ", xhr.responseText);
    } else {
        console.log("Error: ", xhr.status);
    }
};

xhr.onerror = function() {
    console.log("Request failed");
};

xhr.send();

// XMLHttpRequest Post 요청
const xhr = new XMLHttpRequest();
xhr.open("POST", "https://jsonplaceholder.typicode.com/posts", true);
xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

xhr.onload = function() {
    if (xhr.status === 201) {
        console.log("Response: ", xhr.responseText);
    } else {
        console.log("Error: ", xhr.status);
    }
};

const data = JSON.stringify({
    title: "foo",
    body: "bar",
    userId: 1
});

xhr.send(data);
*/

/*
//Fetch API로 리다이렉트
fetch("https://jsonplaceholder.typicode.com/posts/1")
    .then(response => {
        if (response.ok) {
            return response.json();  // 성공 시 JSON 데이터 반환
        } else if (response.status === 401) {
            // 인증 오류 시 로그인 페이지로 리다이렉트
            window.location.href = "/login";
            throw new Error("Unauthorized");
        } else {
            throw new Error("Server error");
        }
    })
    .then(data => {
        console.log("Success:", data);
        // 데이터 처리 후 성공 페이지로 리다이렉트
        window.location.href = "/success-page";
    })
    .catch(error => console.error("Fetch error:", error));



    // Axios로 리다이렉트
    axios.get("https://jsonplaceholder.typicode.com/posts/1")
    .then(response => {
        console.log("Success:", response.data);
        // 성공 시 리다이렉트
        window.location.href = "/success-page";
    })
    .catch(error => {
        if (error.response && error.response.status === 401) {
            // 인증 오류 시 로그인 페이지로 리다이렉트
            window.location.href = "/login";
        } else {
            console.error("Error:", error);
        }
    });
    */



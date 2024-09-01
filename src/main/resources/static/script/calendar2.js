document.addEventListener("DOMContentLoaded", function() {
    buildCalendar();

    document.getElementById("btnPrevCalendar").addEventListener("click", function(event) {
        prevCalendar();
    });

    document.getElementById("nextNextCalendar").addEventListener("click", function(event) {
        nextCalendar();
    });
});


// 여기서 이제 앞 뒤 버튼 누르면 달력이 두개 보이고 당월과 다음달이 보여야함.
// 달력 1, 달력 2 이렇게 컨트롤 해야 한다.
// 먼저 달력이 어떻게 형성되는지 이해해야함.


var toDay = new Date(); // @param 전역 변수, 오늘 날짜 / 내 컴퓨터 로컬을 기준으로 toDay에 Date 객체를 넣어줌
var nowDate = new Date();  // @param 전역 변수, 실제 오늘날짜 고정값

// 이전달 버튼 클릭시
function prevCalendar() { // new Date(년, 월, 일)로 달력 컨트롤함. 이때 월 앞 뒤 버튼 누르면 자연스럽게 년도 바뀌는건 월 값에 13 또는 0값 들어가면 년도가 알아서 바뀌는 시스템인듯
    this.toDay = new Date(toDay.getFullYear(), toDay.getMonth() - 1, toDay.getDate());
    buildCalendar();    // @param 전월 캘린더 출력 요청
    relocation();
}

// 다음달 버튼 클릭시
function nextCalendar() {
    this.toDay = new Date(toDay.getFullYear(), toDay.getMonth() + 1, toDay.getDate());
    buildCalendar();    // @param 명월 캘린더 출력 요청
    relocation();
}


//----------------------------------------------------------------------------------------------------
/**
 * @brief   캘린더 오픈
 * @details 날짜 값을 받아 캘린더 폼을 생성하고, 날짜값을 채워넣는다.
 */
function buildCalendar() {

    let doMonth = new Date(toDay.getFullYear(), toDay.getMonth(), 1); // 이번 달 1일을 담고 있음.
    let lastDate = new Date(toDay.getFullYear(), toDay.getMonth() + 1, 0); // date 파라미터에 0을 넣으면 이번달의 0일 즉 저번달의 마지막 날이 됨. 그래서 month에 +1해줘야 이번달 마지막날이 됨.

    // 달력 1의 다음달을 보여줘야하니까.
    let doMonth2 = new Date(toDay.getFullYear(), toDay.getMonth() + 1, 1); // 다음달의 첫번째 날
    let lastDate2 = new Date(toDay.getFullYear(), toDay.getMonth() + 2, 0); // 다음달의 마지막 날


    let tbCalendar = document.querySelector(".calendar1 > tbody"); // 1번 캘린더
    let tbCalendar2 = document.querySelector(".calendar2 > tbody");// 2번 캘린더

    // 1번 캘린더의 year, month
    document.getElementById("calYear").innerText = toDay.getFullYear();                       // @param YYYY월
    // document.getElementById("calMonth").innerText = autoLeftPad((toDay.getMonth() + 1), 2);   // @param MM월
    document.getElementById("calMonth").innerText = toDay.getMonth() + 1


    // 달력 2 부분
    // 2번 캘린더의 year, month. toDay.getMonth()의 값은 0 ~ 11까지의 값이다. 그래서 마지막 11 + 돼버려서 13이 되어버리는 거임.
    if (toDay.getMonth() == 11) {
        document.getElementById("calYear2").innerText = toDay.getFullYear() + 1;
    }
    else {
        document.getElementById("calYear2").innerText = toDay.getFullYear();
    }
    // document.getElementById("calMonth2").innerText = autoLeftPad((toDay.getMonth() + 2), 2);
    document.getElementById("calMonth2").innerText = toDay.getMonth() + 2;

    // 이전 캘린더의 출력결과가 남아있다면, 이전 캘린더를 삭제한다. 1번 2번 캘린더 모두 삭제함
    while(tbCalendar.rows.length > 0) {
        tbCalendar.deleteRow(tbCalendar.rows.length - 1);
    }
    // 2번 캘린더
    while(tbCalendar2.rows.length > 0) {
        tbCalendar2.deleteRow(tbCalendar2.rows.length - 1);
    }


    // @param 첫번째 개행
    let row = tbCalendar.insertRow(); // <tr삽입>
    // 2번 캘린더 개행
    let row2 = tbCalendar2.insertRow();

    // @param 날짜가 표기될 열의 증가값
    let dom = 1;
    let dom2 = 1;

    // @details 시작일의 요일값( doMonth.getDay() ) + 해당월의 전체일( lastDate.getDate())을  더해준 값에서
    //               7로 나눈값을 올림( Math.ceil() )하고 다시 시작일의 요일값( doMonth.getDay() )을 빼준다.

    // getDay()는 요일을 반환한다 0(일) ~ 6(토), getDate()는 날짜를 반환함.
    let daysLength = (Math.ceil((doMonth.getDay() + lastDate.getDate()) / 7) * 7) - doMonth.getDay(); // {(이번달 1일의 요일) + (이번달 마지막 날짜)} / 7

    // 2번꺼
    let daysLength2 = (Math.ceil((doMonth2.getDay() + lastDate2.getDate()) / 7) * 7) - doMonth2.getDay();


//-------------------------달력1----------------------//
    // @param 달력 출력
    // @details 시작값은 1일을 직접 지정하고 요일값( doMonth.getDay() )를 빼서 마이너스( - )로 for문을 시작한다.

    for(let day = 1 - doMonth.getDay(); daysLength >= day; day++) { // 1-4 = -3부터 시작

        let column = row.insertCell();

        // 평일, 일, 토 날짝 집어넣는 부분. 새로운 열 삽입은 토요일에서 진행됨. 일 - 토 루틴
        // @param 평일( 전월일과 익월일의 데이터 제외 )
        if(Math.sign(day) == 1 && lastDate.getDate() >= day) { // day가 양수이고, 이번달의 마지막날이 day의 값보다 크거나 같으면. 즉 이번달의 날짜들은 여기서 처리됨.
            // @param 평일 날짜 데이터 삽입
            column.innerText = autoLeftPad(day, 2);

            // @param 일요일인 경우
            if(dom % 7 == 1) {
                // column.style.color = "#FF4D4D"; // 숫자 빨간색으로
                column.classList.add('sunday');
            }

            // @param 토요일인 경우
            if(dom % 7 == 0) {
                // column.style.color = "#4D4DFF"; // 숫자 파란색으로
                column.classList.add('saturday');
                row = tbCalendar.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
            }

            if (dom % 7 != 1 && dom % 7 != 0) {
                column.classList.add('weekday');

            }
        }

        // @param 평일 전월일과 익월일의 데이터 날짜변경
        // 저번달과 다음달 날짜 넣는 부분임

        else { // day의 부호가 -1 즉, 저번달 날짜면. 
            // 저번달 날짜는 비워둔다.
            column.innerText = '';
        }

        // @brief   전월, 명월 음영처리
        // @details 이번 년도와 선택년도가 같은경우
        if(toDay.getFullYear() == nowDate.getFullYear()) {

            // @details 이번달과 탐색달이 같은경우.
            if(toDay.getMonth() == nowDate.getMonth()) {

                // @details 오늘 날짜보다 이전 날짜인 경우이면서 이번달에 포함되는 날짜인 경우
                if(nowDate.getDate() > day && Math.sign(day) == 1) {
                    column.style.backgroundColor = "#E5E5E5"; // 배경 음영 처리
                }

                // @details 오늘날짜보다 이후 날짜 이면서 이번달에 포함되는 날짜인 경우
                else if(nowDate.getDate() < day && lastDate.getDate() >= day) {
                    
                    column.style.cursor = "pointer"; // 클릭가능하게 만듬
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day"); // 선택가능한 날에 day 클래스 추가하기
                    column.classList.add("day-number");
                }

                // @details 오늘 날짜인 경우
                else if(nowDate.getDate() == day) {
                    column.style.backgroundColor = "#FFFFE6"; // 약간 노랗게
                    column.style.cursor = "pointer"; // 클릭가능하게 만듬
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day");
                    column.classList.add("day-number");
                }

            // @details 현재월보다 이전인경우
            } else if(toDay.getMonth() < nowDate.getMonth()) {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    column.style.backgroundColor = "#E5E5E5";
                
                }
            }

            // @details 현재월보다 이후인경우
            else {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    // column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day");
                    column.classList.add("day-number");
                }
            }
        }

        // @details 선택한년도가 이번 년도보다 작은경우
        else if(toDay.getFullYear() < nowDate.getFullYear()) {
            if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                column.style.backgroundColor = "#E5E5E5";
            }
        }

        // @details 선택한년도가 현재년도보다 큰경우
        else {
            if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                // column.style.backgroundColor = "#FFFFFF";
                column.style.cursor = "pointer";
                column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
            }
        }
        dom++;
    }

//-----------------달력 2---------------------------------//

    for(let day = 1 - doMonth2.getDay(); daysLength2 >= day; day++) {

        let column = row2.insertCell();

        // @param 평일( 전월일과 익월일의 데이터 제외 )
        if(Math.sign(day) == 1 && lastDate2.getDate() >= day) { // Math.sign(day)는 day값의 부호를 반환한다고 함. 양수 1, 음수 -1, 0, -0, 숫자가 아니면 NaN
            // @param 평일 날짜 데이터 삽입
            column.innerText = autoLeftPad(day, 2);

            // @param 일요일인 경우
            if(dom2 % 7 == 1) {
                column.classList.add('sunday');
            }

            // @param 토요일인 경우
            if(dom2 % 7 == 0) {
                column.classList.add('saturday');
                row2 = tbCalendar2.insertRow(); // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
            }

            if (dom % 7 != 1 && dom % 7 != 0) {
                column.classList.add('weekday');
            }
        }

        // @param 평일 전월일과 익월일의 데이터 날짜변경
        else {
            column.innerText = '';
        }

        // @brief   전월, 명월 음영처리
        // @details 현재년과 선택 년도가 같은경우
        if(toDay.getFullYear() == nowDate.getFullYear()) {

            // @details 현재월과 선택월이 같은경우. 음영 처리 과정
            if(toDay.getMonth() + 1 == nowDate.getMonth()) {

                // @details 현재일보다 이전인 경우이면서 현재월에 포함되는 일인경우
                if(nowDate.getDate() > day && Math.sign(day) == 1) {
                    column.style.backgroundColor = "#E5E5E5";
                }

                // @details 현재일보다 이후이면서 현재월에 포함되는 일인경우
                else if(nowDate.getDate() < day && lastDate2.getDate() >= day) {
                    // column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day2");
                    column.classList.add("day-number");
                }

                // @details 현재일인 경우
                else if(nowDate.getDate() == day) {
                    column.style.backgroundColor = "#FFFFE6";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day2");
                    column.classList.add("day-number");
                }

            // @details 현재월보다 이전인경우
            } else if(toDay.getMonth() < nowDate.getMonth()) {
                if(Math.sign(day) == 1 && day <= lastDate2.getDate()) {
                    column.style.backgroundColor = "#E5E5E5";
                }
            }

            // @details 현재월보다 이후인경우
            else {
                if(Math.sign(day) == 1 && day <= lastDate2.getDate()) {
                    // column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                    column.classList.add("day2");
                    column.classList.add("day-number");
                }
            }
        }

        // @details 선택한년도가 현재년도보다 작은경우
        else if(toDay.getFullYear() < nowDate.getFullYear()) {
            if(Math.sign(day) == 1 && day <= lastDate2.getDate()) {
                column.style.backgroundColor = "#E5E5E5";
            }
        }

        // @details 선택한년도가 현재년도보다 큰경우
        else {
            if(Math.sign(day) == 1 && day <= lastDate2.getDate()) {
                // column.style.backgroundColor = "#FFFFFF";
                column.style.cursor = "pointer";
                column.onclick = function(){ selectDate(this, toDay.getFullYear(), toDay.getMonth(), toDay.getDate); }
                column.classList.add("day2");
                column.classList.add("day-number");
            }
        }
        dom2++;
    }

}

//----------------------------------------------------------------------------------------------------

/**
 * @brief   숫자 두자릿수( 00 ) 변경
 * @details 자릿수가 한자리인 ( 1, 2, 3등 )의 값을 10, 11, 12등과 같은 두자리수 형식으로 맞추기위해 0을 붙인다.
 * @param   num     앞에 0을 붙일 숫자 값
 * @param   digit   글자의 자릿수를 지정 ( 2자릿수인 경우 00, 3자릿수인 경우 000 … )
 */
function autoLeftPad(num, digit) { // digit은 자릿수 num = 12, digit = 3 이면 3자리수 012로 만드는 거임
    if (num == 13) {
        num = 1;
    }

    if(String(num).length < digit) {
        num = new Array(digit - String(num).length + 1).join("0") + num;
    }
    return num;
}

// 달력 숨겼다 보이기
const toggleButton = document.getElementById('dateSelect');
const calendar = document.querySelector('.calendar-container');

toggleButton.addEventListener('click', () => {
    if (calendar.style.display === 'none' || calendar.style.display === '') {
        calendar.style.display = 'table'; // 표시
    } else {
        calendar.style.display = 'none'; // 숨김
    }
});

var startDate = null, startMonth = null, startYear = null;
var endDate = null, endMonth = null, endYear = null;
var starttdItem = null, endtdItem = null;

function selectDate(tdItem, year, month, date) {
    console.log("selectDate 함수 실행");

    // clear
    if (startDate && endDate) {
        clearActiveDays();
        starttdItem = null;
        startDate = null;
        startMonth = null;
        startYear = null;

        endtdItem = null;
        endDate = null;
        endMonth = null;
        endYear = null;
    }
    if (startDate == null && endDate == null) { // 아직 시작 날짜를 선택하지 않았으면
        console.log("시작 날짜 아직 선택 안되어 있음, 시작 날짜 선택");
        starttdItem = tdItem;
        startDate = date;
        startMonth = month;
        startYear = year;
        tdItem.classList.add('active-a');
    }
    else if (startDate != null && endDate == null) { // 시작 날짜를 선택했고, 아직 끝 날짜를 선택하지 않았으면,
        console.log("시작 날짜 선택되어 있고 끝 날짜 선택 안되어 있음, 끝 날짜 선택");
        endtdItem = tdItem;
        endDate = date;
        endMonth = month;
        endYear = year;
        tdItem.classList.add('active-b');
    }
    if (startDate != null && endDate != null) { // 시작과 끝이 모두 선택되어 있으면
        console.log("시작과 끝이 모두 선택되어 있으므로 두 날짜 사이에 range 설정");
        calculateRange();
    }
}

function relocation() { // 달력 조작 버튼을 누르면 시작 날짜, 끝 날짜 재배치 함수를 실행한다.
    // 현재 달력은 두 개로 나뉘어져 있다. 앞으로 가기, 뒤로 가기 상관할 필요 없이 둘 다 눌렀을 때 실행되도록 만들것임.
    // 재배치 위치는 starttdItem의 행과 열 인덱스 값을 토대로 진행. 달력 1과, 달력 2 둘다 검사를 진행한 후 알맞게 배치
    
    // 버튼을 누르면 달력에 변화가 생긴다. 앞으로 가기 혹은 뒤로 가기 하면서 달력이 밀린다.
    // 달력 1과 달력 2의 년도, 몇월 달인지를 확인한다. 만약 일치한다면 저장해 두었던 위치에 재배치 한다.
    console.log("재배치 함수 실행");

    if (starttdItem == null) return;
    
    // 시작 일자 인덱스
    var srow = starttdItem.parentNode.rowIndex;
    var scol = starttdItem.cellIndex;

    // 끝 날짜 인덱스
    var erow = endtdItem.parentNode.rowIndex;
    var ecol = endtdItem.cellIndex;

    // 달력 1의 년도, 개월을 확인
    var calendar1Year = document.getElementById("calYear"); // 달력 1의 년도
    var calendar1Month = document.getElementById("calMonth"); // 달력 1의 월

    var calendar2Year = document.getElementById("calYear2"); // 달력 2의 년도
    var calendar2Month = document.getElementById("calMonth2"); // 달력 2의 월
    
    // 달력 1
    if (startYear.toString == calendar1Year.innerText && startMonth.toString == calendar1Month.innerText) { // 시작 날짜의 년도가 같고 달이 같으면 2
        document.querySelector(".calendar1 > tbody").rows[srow].cells[scol].classList.add('active-a');
    }
    // 달력 2
    else if (startYear.toString == calendar2Year.innerText && startMonth.toString == calendar2Month.innerText) { // 시작 날짜의 년도가 같고 달이 같으면 2 
        document.querySelector(".calendar2 > tbody").rows[srow].cells[scol].classList.add('active-a');
    }

    if (endtdItem == null) return

    if (endYear.toString == calendar1Year.innerText && endMonth.toString == calendar1Month.innerText) { // 시작 날짜의 년도가 같고 달이 같으면 1
        document.querySelector(".calendar1 > tbody").rows[erow].cells[ecol].classList.add('active-b');
    }
    else if (endYear.toString == calendar2Year.innerText && endMonth.toString == calendar2Month.innerText) { // 시작 날짜의 년도가 같고 달이 같으면 2
        document.querySelector(".calendar2 > tbody").rows[erow].cells[ecol].classList.add('active-b');
    }

    calculateRange();   
}

function calculateRange() {
    console.log("calculateRange 함수 실행");
    // a와 b가 정해져 있는데 a만 달력에 보이고, b는 안보이는 상태
    // a와 b가 정해져 있는데 b만 달력에 보이고, a는 안보이는 상태
    // a와 b가 정해져 있는데 a와 b가 달력에 안보이는 상태

    // 그럼 현재 달력에 a와 b가 어떻게 잡고 있는지 확인해야함.
    // calculateRange는 relocation이 모두 실행된 후 실행된다. 또한 relocation에서 a와 b가 결정되지 않으면 실행되지 않는다.
    // calculateRange는 selectDate에서 a와 b가 모두 결정된 후 실행된다.

    // 결국 a와 b가 모두 결정되면 calculateRange는 실행된다.

    // a와 b에 모두 값이 들어있다고 생각하고 진행.

    // a와 b가 정해져 있고, a와 b모두 달력에 있는 상태
    
    // 달력 1에 a가 있고, 달력 2에 b가 있는 경우
    if (document.querySelector(".calendar1 > tbody > tr > td.active-a") && document.querySelector(".calendar2 > tbody > tr > td.active-b")) { // 달력 1에 a가 있고, 달력 2에 b가 있는 경우

        console.log("달력 2에 a가 있고 달력 1, 2 어느 쪽에도 b가 없는 경우");

        var activeA = document.querySelector(".calendar1 > tbody > tr > td.active-a");
        var activeB = document.querySelector(".calendar2 > tbody > tr > td.active-b");

        var arow = document.querySelector(".calendar1 > tbody").getElementsByTagName('tr').length;

        console.log("달력 1의 년도 가져오기 : " + document.getElementById('calYear').innerText);
        var year = Number(document.getElementById('calYear').innerText)
        console.log("year : " + year);
        var lastDate = new Date(Number(document.getElementById('calYear').innerText), Number(document.getElementById('calMonth')) + 1, 0); // 마지막 날임.

        console.log(activeA);
        var count = Number(activeA.innerText);
        console.log("count 값 : " + count)

        var index = activeA.cellIndex;
        for (var i = activeA.parentNode.rowIndex - 2; i <= arow; i++) {
            document.querySelector(".calendar1 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 6) {
                index++;
                document.querySelector(".calendar1 > tbody").rows[i].cells[index].classList.add('range');
                if (count == lastDate.getDate()) {
                    return;
                }
                count++;
            }
            index = 0;
        }

        index = activeB.cellIndex;
        count = Number(activeB.innerText);
        for (var i = activeB.parentNode.rowIndex - 2; i >= 0; i--) {
            document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 0) {
                index--;
                document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
                if (count == 1) return;
                count--;
            }
            index = 6;
        }
    }

    // 달력 2에 a가 있고 달력 1, 2 어느 쪽에도 b가 없는 경우
    else if (document.querySelector(".calendar2 > tbody > tr > td.active-a") && 
    !document.querySelector(".calendar1 > tbody > tr > td.active-b") &&
    !document.querySelector(".calendar2 > tbody > tr > td.active-b")
    ) {
        console.log("달력 2에 a가 있고 달력 1, 2 어느 쪽에도 b가 없는 경우");

        var arow = document.querySelector(".calendar2 > tbody").getElementsByTagName('tr').length;
        var lastDate = new Date(Number(document.getElementById('calYear2').innerText), Number(document.getElementById('calMonth2')) + 1, 0); // 마지막 날임.

        var count = Number(activeA.innerText);
        var index = activeA.cellIndex;

        for (var i = activeA.parentNode.rowIndex - 2; i <= arow; i++) {
            document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 6) {
                index++;
                document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
                if (count == lastDate.getDate()) {
                    return;
                }
                count++;
            }
            index = 0;
        }
    }

    // 달력 1에 b가 있고 a는 달력 1, 2 어느 쪽에도 없는 경우
    else if (document.querySelector(".calendar1 > tbody > tr > td.active-b") && 
    !document.querySelector(".calendar1 > tbody > tr > td.active-a") &&
    !document.querySelector(".calendar2 > tbody > tr > td.active-a")) { 
        console.log("달력 1에 b가 있고 a는 달력 1, 2 어느 쪽에도 없는 경우");


        var count = Number(activeB.innerText);
        var index = activeB.cellIndex;

        for (var i = activeB.parentNode.rowIndex - 2; i >= 0; i--) {
            document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 0) {
                index++;
                document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
                if (count == 1) return;
                count--;
            }
            index = 0;
        }
    }

    // 달력 2에 a, b 모두 있는 경우
    else if (document.querySelector(".calendar1 > tbody > tr > td.active-a") && document.querySelector(".calendar1 > tbody > tr > td.active-b")) { // 달력 1에 a, b 모두 있는 경우
        // 사이에 있는 애들 모두 range로 만든다.
        console.log("달력 1에 a, b 모두 있는 경우");

        var activeA = document.querySelector(".calendar1 > tbody > tr > td.active-a");
        var activeB = document.querySelector(".calendar1 > tbody > tr > td.active-b");
        var index = activeA.cellIndex;

        // range 설정해주는 로직 잘못됐음
        for (var i = activeA.parentNode.rowIndex - 2; i <= activeB.parentNode.rowIndex - 2; i++) {
            document.querySelector(".calendar1 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 6) {
                index++;
                document.querySelector(".calendar1 > tbody").rows[i].cells[index].classList.add('range');
                if (i == (activeB.parentNode.rowIndex - 2) && index == activeB.cellIndex) {
                    break;
                }
            }
            index = 0;
        }
    }

    // 달력 2에 a, b 모두 있는 경우
    else if (document.querySelector(".calendar2 > tbody > tr > td.active-a") && document.querySelector(".calendar2 > tbody > tr > td.active-b")) { // 달력 2에 a, b 모두 있는 경우
        console.log("달력 2에 a, b 모두 있는 경우");

        var activeA = document.querySelector(".calendar2 > tbody > tr > td.active-a");
        var activeB = document.querySelector(".calendar2 > tbody > tr > td.active-b");
        var index = activeA.cellIndex;

        // range 설정해주는 로직 잘못됨 고쳐야함.
        for (var i = activeA.parentNode.rowIndex - 2; i <= activeB.parentNode.rowIndex - 2; i++) {
            document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');
            while (index % 7 != 6) {
                index++;
                document.querySelector(".calendar2 > tbody").rows[i].cells[index].classList.add('range');

                if (i == (activeB.parentNode.rowIndex) - 2 && index == activeB.cellIndex) {
                    break;
                }
            }
            index = 0;
        }
    }
}

function activateDay() { // 숫자 선택 활성화 하는 함수
    var activeElement = document.activeElement; // 내가 현재 상호작용 하고 있는 요소를 나타낸다고 함. 즉, 여기선 내가 달력에서 누른 숫자를 말함.
    var activeAItem = document.querySelector('.active-a');
    var activeBItem = document.querySelector('.active-b');
  
    if (activeAItem && activeBItem) { // activeAItem와 activeBItem가 dom 객체를 가지고 있으면 true로 판정. 즉, a와 b가 모두 선택되어 있으면 clearActiveDays하고 clearRange실행시키고 선택한 숫자에 새로 active-a 부여함
      clearActiveDays();
      clearRange();
      activeElement.classList.add('active-a');
      return;
    }
  
    if (activeAItem) activeElement.classList.add('active-b'); // activeAItem에 dom객체가 있으면 즉, 선택된 active-a가 있는 경우. 현재 내가 누른 숫자를 active-b로 만듬.
    else activeElement.classList.add('active-a'); // 아직 선택한 active-a가 없으면 현재 누른 숫자 active-a로 만듬.
  }


  function clearActiveDays() { // 선택한 숫자들 푸는 함수
    console.log("clearActiveDays함수 실행")
    var activeAItem = document.querySelector('.active-a');
    var activeBItem = document.querySelector('.active-b');
  
    if (activeAItem) activeAItem.classList.remove('active-a');
    if (activeBItem) activeBItem.classList.remove('active-b');

    // range도 같이 지워주기. querySelectorAll로 여러 요소 가져오면 forEach 써야함.
    var ran = document.querySelectorAll('.range');
    ran.forEach((item) => {
        item.classList.remove('range');
    });
  }

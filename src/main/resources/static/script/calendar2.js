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
}

// 다음달 버튼 클릭시
function nextCalendar() {
    this.toDay = new Date(toDay.getFullYear(), toDay.getMonth() + 1, toDay.getDate());
    buildCalendar();    // @param 명월 캘린더 출력 요청
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
    document.getElementById("calMonth").innerText = autoLeftPad((toDay.getMonth() + 1), 2);   // @param MM월

    // 2번 캘린더의 year, month. toDay.getMonth()의 값은 0 ~ 11까지의 값이다. 그래서 마지막 11 + 돼버려서 13이 되어버리는 거임.
    if (toDay.getMonth() == 11) {
        document.getElementById("calYear2").innerText = toDay.getFullYear() + 1;
    }
    else {
        document.getElementById("calYear2").innerText = toDay.getFullYear();
    }
    document.getElementById("calMonth2").innerText = autoLeftPad((toDay.getMonth() + 2), 2)

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
                column.style.color = "#FF4D4D"; // 숫자 빨간색으로
            }

            // @param 토요일인 경우
            if(dom % 7 == 0) {
                column.style.color = "#4D4DFF"; // 숫자 파란색으로
                row = tbCalendar.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
            }
        }

        // @param 평일 전월일과 익월일의 데이터 날짜변경
        // 저번달과 다음달 날짜 넣는 부분임
        else { // day의 부호가 -1 즉, 저번달 날짜면. 
            let exceptDay = new Date(doMonth.getFullYear(), doMonth.getMonth(), day); // 이번달의 마이너스 값의 date값 즉 저번달 마지막날에서 마이너스 시킨 date의 값을 가져온다. 반대로 이번달의 date값을 초과하는 값이 들어오면 다음달의 1일부터 플러스된 날짜가 나온다.
            // column.innerText = autoLeftPad(exceptDay.getDate(), 2);
            column.innerText = '';
            column.style.color = "#A9A9A9"; // 숫자 색깔 회색 처리
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
                    column.style.backgroundColor = "#FFFFFF"; // 배경 흰색 처리
                    column.style.cursor = "pointer"; // 클릭가능하게 만듬
                    column.onclick = function(){ startMove(this); }
                    column.classList.add("day"); // 선택가능한 날에 day 클래스 추가하기
                    column.classList.add("day-number");
                }

                // @details 오늘 날짜인 경우
                else if(nowDate.getDate() == day) {
                    column.style.backgroundColor = "#FFFFE6"; // 약간 노랗게
                    column.style.cursor = "pointer"; // 클릭가능하게 만듬
                    column.onclick = function(){ startMove(this); }
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
                    column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ startMove(this); }
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
                column.style.backgroundColor = "#FFFFFF";
                column.style.cursor = "pointer";
                column.onclick = function(){ startMove(this); }
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
                column.style.color = "#FF4D4D"; // 숫자 빨간색으로
            }

            // @param 토요일인 경우
            if(dom2 % 7 == 0) {
                column.style.color = "#4D4DFF"; // 숫자 파란색으로
                row2 = tbCalendar2.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
            }
        }

        // @param 평일 전월일과 익월일의 데이터 날짜변경
        else {
            column.innerText = '';
            column.style.color = "#A9A9A9";
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
                    column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay2(this); }
                    column.classList.add("day2");
                    column.classList.add("day-number");
                }

                // @details 현재일인 경우
                else if(nowDate.getDate() == day) {
                    column.style.backgroundColor = "#FFFFE6";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay2(this); }
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
                    column.style.backgroundColor = "#FFFFFF";
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay2(this); }
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
                column.style.backgroundColor = "#FFFFFF";
                column.style.cursor = "pointer";
                column.onclick = function(){ calendarChoiceDay2(this); }
                column.classList.add("day2");
                column.classList.add("day-number");
            }
        }
        dom2++;
    }

}

//----------------------------------------------------------------------------------------------------

/**
 * @brief   날짜 선택
 * @details 사용자가 선택한 날짜에 체크표시를 남긴다.
 */
function calendarChoiceDay(column) {
    // @param 기존 선택일이 존재하는 경우 기존 선택일의 표시형식을 초기화 한다.
    if(document.getElementsByClassName("choiceDay")[0]) {
        // @see 금일인 경우
        if(document.getElementById("calMonth").innerText == autoLeftPad((nowDate.getMonth() + 1), 2) && document.getElementsByClassName("choiceDay")[0].innerText == autoLeftPad(toDay.getDate(), 2)) {
            document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFE6";
        }
        // @see 금일이 아닌 경우
        else {
            document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFFF";
        }
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");
    }
    // @param 선택일 체크 표시
    column.style.backgroundColor = "#FF9999";
    // @param 선택일 클래스명 변경
    column.classList.add("choiceDay");
}

function calendarChoiceDay2(column) {

    // @param 기존 선택일이 존재하는 경우 기존 선택일의 표시형식을 초기화 한다.
    if(document.getElementsByClassName("choiceDay")[0]) {

        // @see 금일인 경우
        if(document.getElementById("calMonth2").innerText == autoLeftPad((nowDate.getMonth() + 1), 2) && document.getElementsByClassName("choiceDay")[0].innerText == autoLeftPad(toDay.getDate(), 2)) {
            document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFE6";
        }

        // @see 금일이 아닌 경우
        else {
            document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFFF";
        }
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");
    }

    // @param 선택일 체크 표시
    column.style.backgroundColor = "#FF9999";

    // @param 선택일 클래스명 변경
    column.classList.add("choiceDay");
}

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


var daySelect;
var calendar1;
document.addEventListener('DOMContentLoaded', function() { // html이 완전히 완성된 후에 실행되도록 설정.
    daySelect = document.querySelectorAll('.day');
    calendar1 = document.querySelector(".calendar1 > tbody"); // 1번 캘린더 가져오기
});

// var daySelect = document.querySelectorAll('.day'); // day는 달력 1, day2는 달력 2

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

  function calculateRange() { // range 계산 함수
    // 먼저 active-a가 달력1에 있는지 달력2에 있는지 확인한다. 추가로 날짜가 며칠인지 확인?
    var activeAItem;
    var activeBItem;
    console.log("여긴????");

    daySelect.forEach((item, index) => {
        console.log("여긴 실행되니?");
        if (item.classList.contains('active-a')) { // 아이템 a 가져오기
            console.log(item);
            activeAItem = item;
        } 
        
        if (item.classList.contains('active-b')) { // 아이템 b 가져오기
            console.log(item);
            activeBItem = item;
        }
    });

    var col;
    // 1. a, b 둘 다 달력 1에 있는 경우
    if (activeAItem.classList.contains('day') && activeBItem.classList.contains('day')) {
        // a와 b 행열 인덱스 가져와서 for문으로 하면 될듯
        var rowIndexA = activeAItem.parentNode.rowIndex;
        var rowIndexB = activeBItem.parentNode.rowIndex;
        var cellIndexA = activeAItem.cellIndex;
        var cellIndexB = activeBItem.cellIndex;
        col = cellIndexA;

        console.log(rowIndexA);
        console.log(rowIndexB);
        console.log(cellIndexA);
        console.log(cellIndexB);
        // 현재 col은 맞음 근데 row가 이상함.

        console.log(calendar1);

        for (var row = rowIndexA; row <= rowIndexB; row++) {
            while(col % 7 != 6) {
                calendar1.rows[row].cells[col].classList.add('range');

                if (row == rowIndexB && col % 7 == cellIndexB) break;
            
            }
        }
    }

    // 2. a는 달력 1에, b는 달력 2에 있는 경우
    else if (activeAItem.classList.contains('day')  && activeBItem.classList.contains('day2')) {

    }

    // 3. a와 b 둘 다 달력 2에 있는 경우
    else if (activeAItem.classList.contains('day2') && activeBItem.classList.contains('day2')) {

    }

}

  function clearActiveDays() { // 선택한 숫자들 푸는 함수
    var activeAItem = document.querySelector('.active-a');
    var activeBItem = document.querySelector('.active-b');
  
    if (activeAItem) activeAItem.classList.remove('active-a');
    if (activeBItem) activeBItem.classList.remove('active-b');
  }


  function clearRange() { // range 제거하기
    daySelect.forEach(item => {
      item.classList.remove('range');
    });
  }

  function startMove(item) {
    dragging = true;
  
    var activeAItem = document.querySelector('.active-a');
    var activeBItem = document.querySelector('.active-b');
  
    if (!activeBItem && activeAItem) { // b는 선택 안되어 있고, a만 선택되어 있으면
      item.classList.add('active-b');
      calculateRange();
    } else {
      clearActiveDays();
      clearRange();
      item.classList.add('active-a');
    }
  }

  daySelect.forEach((item, index) => {
    item.addEventListener('click', e =>{
        select(item);
    })
  });

  function select(item) {
    item.classList.add('active-a');
  }
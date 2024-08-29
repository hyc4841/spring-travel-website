var dragging = false;
var daySelect = document.querySelectorAll('.day');
var offset = 0;

//---------------------------------------------------------------//

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

//---------------------------------------------------------------//

function clearActiveDays() { // 선택한 숫자들 푸는 함수
  var activeAItem = document.querySelector('.active-a');
  var activeBItem = document.querySelector('.active-b');

  if (activeAItem) activeAItem.classList.remove('active-a');
  if (activeBItem) activeBItem.classList.remove('active-b');
}

//---------------------------------------------------------------//


function clearRange() { // range 제거하기
  daySelect.forEach(item => {
    item.classList.remove('range');
  });
}

//---------------------------------------------------------------//

function calculateRange() { // range 계산 함수
  var activeAIndex, activeBIndex;

  daySelect.forEach((item, index) => { // 이 구문은 어떤 리스트인 days를 foreach 즉, 순회하는 구문임
    if (item.classList.contains('active-a')) activeAIndex = index; // 인덱스 딸라고 하는 작업
    if (item.classList.contains('active-b')) activeBIndex = index; // 인덱스 따서 선택된 a와 b사이에 range 만들어줄라고
  });

  if (activeAIndex < activeBIndex) {
    for (var i = activeAIndex; i <= activeBIndex; i++) {
      daySelect[i].classList.add('range');
    }
  }

  if (activeAIndex > activeBIndex) {
    for (var i = activeAIndex; i >= activeBIndex; i--) {
      daySelect[i].classList.add('range');
    }
  }
}

//--------------------------------------------------------------//

// 드래그
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

function move(item) {
  if (dragging) {
    var activeA = document.querySelector('.active-a');
    var prevActiveB = document.querySelector('.active-b');

    clearRange();

    if (prevActiveB) prevActiveB.classList.remove('active-b');
    if (!item.classList.contains('active-a')) item.classList.add('active-b');

    var activeB = document.querySelector('.active-b');

    calculateRange();
  }
}

function endMove(item) {
  dragging = false;
}

window.addEventListener('mouseup', e => {
  dragging = false;
});

// 드래그
// 날짜 드래그 선택까지 지원함 ㅋㅋㅋ
daySelect.forEach((item, index) => {
  var dayNumber = item.querySelector('.day-number').innerHTML;

  if (dayNumber === '1' && !item.classList.contains('next-mon')) {
    offset = index;
  }

  item.addEventListener('mousedown', e => { // 마우스로 누른 애를 startMove 함수로 보냄
    startMove(item);
  });

  item.addEventListener('mousemove', e => {
    move(item);
  });

  item.addEventListener('mouseup', e => {
    endMove(item);
  });
});

// 키보드
window.addEventListener('keyup', e => {
  var key = e.keyCode;

  switch (key) { // key값이 13이 되면 여기 실행됨. 13은 엔터를 말함. 즉 키보드로도 조작 가능하도록 만들어 놨음
    case 13:
      activateDay();
      calculateRange();
      break;
  }
});

document.querySelector('.reset').addEventListener('click', e => {
  clearActiveDays();
  clearRange();
});
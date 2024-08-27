var dragging = false;
var days = document.querySelectorAll('.day');
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


function clearRange() { // 선택한 두 숫자 사이에 숫자들을 range 처리해주는 함수
  days.forEach(item => {
    item.classList.remove('range');
  });
}

//---------------------------------------------------------------//


function calculateRange() { // range 계산 함수
  var activeAIndex, activeBIndex;

  days.forEach((item, index) => { // 이 구문은 어떤 리스트인 days를 foreach 즉, 순회하는 구문임
    if (item.classList.contains('active-a')) activeAIndex = index; // 인덱스 딸라고 하는 작업
    if (item.classList.contains('active-b')) activeBIndex = index; // 인덱스 따서 선택된 a와 b사이에 range 만들어줄라고
  });

  if (activeAIndex < activeBIndex) {
    for (var i = activeAIndex; i <= activeBIndex; i++) {
      days[i].classList.add('range');
    }
  }

  if (activeAIndex > activeBIndex) {
    for (var i = activeAIndex; i >= activeBIndex; i--) {
      days[i].classList.add('range');
    }
  }
}

//---------------------------------------------------------------//

function startMove(item) {
  dragging = true;

  var activeAItem = document.querySelector('.active-a');
  var activeBItem = document.querySelector('.active-b');

  if (!activeBItem && activeAItem) {
    item.classList.add('active-b');
    calculateRange();
  } else {
    clearActiveDays();
    clearRange();
    item.classList.add('active-a');
  }
}

//---------------------------------------------------------------//


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

//---------------------------------------------------------------//

function endMove(item) {
  dragging = false;
}

//---------------------------------------------------------------//

window.addEventListener('mouseup', e => {
  dragging = false;
});


days.forEach((item, index) => {
  var dayNumber = item.querySelector('.day-number').innerHTML;

  if (dayNumber === '1' && !item.classList.contains('next-mon')) {
    offset = index;
  }

  item.addEventListener('mousedown', e => {
    startMove(item);
  });

  item.addEventListener('mousemove', e => {
    move(item);
  });

  item.addEventListener('mouseup', e => {
    endMove(item);
  });
});

window.addEventListener('keyup', e => {
  var key = e.keyCode;

  switch (key) {
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
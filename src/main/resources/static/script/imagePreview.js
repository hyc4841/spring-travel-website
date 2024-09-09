const fileInput = document.getElementById('images');
const previewContainer = document.getElementById('previewContainer');

fileInput.addEventListener('change', function() {
    previewContainer.innerHTML = ''; // 이전 미리보기 제거
    const files = fileInput.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        if (file.type.startsWith('image/')) { // 이미지 파일인지 확인
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.classList.add('preview'); // 미리보기 스타일 적용
                previewContainer.appendChild(img); // 미리보기 이미지 추가
            }
            reader.readAsDataURL(file);
        } else {
            alert('이미지 파일만 업로드할 수 있습니다.');
            fileInput.value = ''; // 입력 초기화
            break; // 반복 종료
        }
    }
});
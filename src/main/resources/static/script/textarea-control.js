 document.addEventListener("DOMContentLoaded", function() {
    const textarea = document.getElementById('content');

    function adjustHeight(element) {
        element.style.height = '500px'; // 초기 높이 정하기
        element.style.height = element.scrollHeight + 'px'; // Adjust to the content height
    }

    textarea.addEventListener('input', function() {
        adjustHeight(textarea);
    });

    // Adjust height on page load in case there's pre-filled content
    adjustHeight(textarea);
});

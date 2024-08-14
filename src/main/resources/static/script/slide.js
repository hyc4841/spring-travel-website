let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelector('.slides');
    const totalSlides = document.querySelectorAll('.slide').length; // 5

    if (index >= totalSlides - 1) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = totalSlides - 1;
    } else {
        currentSlide = index;
    }

    slides.style.transform = `translateX(-${currentSlide * 50}%)`;
}

function moveSlide(step) { // 1
    showSlide(currentSlide + step);
}

slideshowSlide(currentSlide);
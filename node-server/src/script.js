document.getElementById("btn1").addEventListener("click", () => {
  fetch("/api/button1")
    .then((response) => response.text())
    .then((data) => console.log(data));
});

document.getElementById("btn2").addEventListener("click", () => {
  fetch("/api/button2")
    .then((response) => response.text())
    .then((data) => console.log(data));
});

document.getElementById("btn3").addEventListener("click", () => {
  fetch("/api/button3")
    .then((response) => response.text())
    .then((data) => console.log(data));
});

document.getElementById("btn4").addEventListener("click", () => {
  fetch("/api/button4")
    .then((response) => response.text())
    .then((data) => console.log(data));
});

document.getElementById("btn5").addEventListener("click", () => {
  fetch("/api/button5")
    .then((response) => response.text())
    .then((data) => console.log(data));
});

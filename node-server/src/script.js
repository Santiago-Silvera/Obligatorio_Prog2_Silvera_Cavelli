function handleButton(buttonNumber) {
  fetch(`/api/button${buttonNumber}`)
    .then((response) => response.text())
    .then((data) => console.log(data))
    .catch((error) => console.log(error));
}

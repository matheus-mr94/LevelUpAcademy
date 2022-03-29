function changeStatus(id, element) {
    const active = element.closest('ul').querySelector('#active-' +id);

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/atualizarStatus?id=' + id);
    xhr.addEventListener("load", () => {

        if(xhr.status === 204) {
            active.innerHTML = active.innerHTML === "Ativa: true" ? "Ativa: false" : "Ativa: true";
        }
    });

    xhr.send();
}
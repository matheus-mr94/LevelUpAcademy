function changeStatus(id, element) {
    const active = element.closest('ul').querySelector('#active-' +id);
    const url = '/atualizarStatus?id=' + id

    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.addEventListener("load", () => {

        if(xhr.status === 204) {
            active.innerHTML = active.innerHTML === "Ativa: true" ? "Ativa: false" : "Ativa: true";
        }
    });

    xhr.send();
}
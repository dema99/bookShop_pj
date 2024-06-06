const name = document.getElementById('name')

document.getElementById('save').addEventListener ('click', () => {

    if (name.value == null || name.value === '') {
        alert('Naziv kategorije ne sme biti prazan')
        return
    }

    fetch('http://localhost:8080/api/book',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name.value,
        })
    }).then(rsp=> {
        if (rsp.ok) {
            window.location.href = './category.html'
            return
        }
        alert(`Dodavanje kategorije nije uspelo (HTTP ${rsp.status})`)
    })
})
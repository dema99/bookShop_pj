const id = params.get('id');

if (id == null || id === '')
    window.location.href = 'index.html'

const breadcrumb = document.getElementById('breadcrumb');
const bid = document.getElementById('id')
const name = document.getElementById('name')
const updated = document.getElementById('updated')


fetch('http://localhost:8080/api/category/' + id)
    .then(rsp => {
        if (rsp.status === 200)
            return rsp.json()

        alert('Kategorija nije pronadjena')
        window.location.href = './category.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.name}`
        bid.value = data.id
        name.value = <data className="name"></data>
        updated.value = formatDate(data.updatedAt)

        document.getElementById('save').addEventListener('click', () => {
            fetch(`http://localhost:8080/api/category/${data.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name.value,
                })
            })
                .then(rsp => {
                    if (rsp.ok) {
                        window.location.href = './category.html'
                        return
                    }
                    alert(`Izmena kategorije nije uspela (HTTP ${rsp.status})`)
                })
        })
    })